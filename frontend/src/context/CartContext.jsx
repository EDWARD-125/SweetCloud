// src/context/CartContext.jsx
import React, { createContext, useContext, useReducer, useEffect } from "react";
import api from "../services/api"; // tu api.js que añade token
import authService from "../services/authService"; // asumo que existe y tiene getCurrentUser()

const CartContext = createContext();

const STORAGE_KEY = "sweetcloud_cart_v1";

const initialState = { items: [] };

function reducer(state, action) {
  switch (action.type) {
    case "INIT":
      return action.payload || initialState;
    case "ADD": {
      const item = action.payload;
      const exists = state.items.find(i => i.id === item.id && i.type === item.type);
      if (exists) {
        return {
          ...state,
          items: state.items.map(i =>
            i.id === item.id && i.type === item.type ? { ...i, qty: i.qty + (item.qty || 1) } : i
          )
        };
      }
      return { ...state, items: [...state.items, { ...item, qty: item.qty || 1 }] };
    }
    case "REMOVE":
      return { ...state, items: state.items.filter(i => !(i.id === action.payload.id && i.type === action.payload.type)) };
    case "UPDATE_QTY":
      return { ...state, items: state.items.map(i => (i.id === action.payload.id && i.type === action.payload.type ? { ...i, qty: action.payload.qty } : i)) };
    case "CLEAR":
      return initialState;
    case "SET":
      return { ...state, items: action.payload };
    default:
      return state;
  }
}

export const CartProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  // inicializar desde localStorage
  useEffect(() => {
    try {
      const raw = localStorage.getItem(STORAGE_KEY);
      if (raw) {
        dispatch({ type: "INIT", payload: JSON.parse(raw) });
      }
    } catch (e) {
      console.warn("Error leyendo carrito desde localStorage", e);
    }
  }, []);

  // guardar en localStorage cada vez que cambia el carrito
  useEffect(() => {
    try {
      localStorage.setItem(STORAGE_KEY, JSON.stringify(state));
    } catch (e) {
      console.warn("Error guardando carrito en localStorage", e);
    }
  }, [state]);

  // sincronizar carrito con backend al hacer login (si hay usuario)
  useEffect(() => {
    const unsub = authService.onAuthChange(async (user) => {
      // onAuthChange es un helper de ejemplo; si tu authService no lo tiene, llama syncCartOnLogin cuando el login termine
      if (user) {
        await syncCartWithServer(user);
      }
    });
    return () => unsub && unsub();
  }, []);

  const addToCart = (item) => dispatch({ type: "ADD", payload: item });
  const removeFromCart = (item) => dispatch({ type: "REMOVE", payload: item });
  const updateQty = (id, qty, type = "product") => dispatch({ type: "UPDATE_QTY", payload: { id, qty, type } });
  const clearCart = () => {
    dispatch({ type: "CLEAR" });
    localStorage.removeItem(STORAGE_KEY);
  };

  const setCartItems = (items) => dispatch({ type: "SET", payload: items });

  const total = state.items.reduce((s, it) => s + (it.price || 0) * it.qty, 0);

  // enviar carrito al servidor (crea o actualiza un carrito/orden temporal en backend)
  const saveCartToServer = async (user) => {
    try {
      if (!user) return null;
      // envia el carrito como items JSON; backend decide si guardarlo como Order temporal
      const payload = {
        items: state.items,
        total,
        userEmail: user.email || user.username || null
      };
      const resp = await api.post("/orders/save-cart", payload); // endpoint sugerido
      return resp.data;
    } catch (e) {
      console.error("Error guardando carrito en servidor", e);
      return null;
    }
  };

  // sincronizar: obtiene el carrito guardado en servidor y lo mezcla con el local
  const syncCartWithServer = async (user) => {
    try {
      if (!user) return;
      const resp = await api.get("/orders/my-cart");
      const serverCart = resp.data?.items || [];
      // estrategia: si local está vacío, usar server. Si ambos tienen, preferir local y mergear unique ids.
      const local = state.items || [];
      if ((local.length === 0) && serverCart.length > 0) {
        setCartItems(serverCart);
      } else if (local.length > 0) {
        // enviar local to server (sobrescribe)
        await api.post("/orders/save-cart", { items: local, total, userEmail: user.email });
      }
    } catch (e) {
      console.warn("No se pudo sincronizar carrito con servidor", e);
    }
  };

  return (
    <CartContext.Provider value={{ cart: state, addToCart, removeFromCart, updateQty, clearCart, setCartItems, total, saveCartToServer }}>
      {children}
    </CartContext.Provider>
  );
};

export const useCart = () => useContext(CartContext);
