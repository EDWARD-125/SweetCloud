// src/pages/CheckoutPage.jsx
import React, { useState } from "react";
import { useCart } from "../context/CartContext";
import { createOrder } from "../services/orderService";
import authService from "../services/authService";
import { useNavigate } from "react-router-dom";

const CheckoutPage = () => {
  const { cart, total, clearCart } = useCart();
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);

  const handleConfirm = async () => {
    setLoading(true);
    try {
      const user = authService.getCurrentUser(); // tu helper real
      const orderPayload = {
        items: cart.items,
        total,
        userEmail: user?.email || null,
        address: "", // agrega campos si deseas
        paymentMethod: "cash"
      };
      await createOrder(orderPayload);
      // opcional: borrar carrito local y en backend (backend puede retornar success)
      clearCart();
      alert("Compra registrada con éxito 🎉");
      navigate("/");
    } catch (e) {
      console.error("Error creando orden", e);
      alert("Hubo un error al crear la orden. Intenta de nuevo.");
    } finally {
      setLoading(false);
    }
  };

  if (!cart.items.length) return <div style={{padding:20}}>El carrito está vacío.</div>;

  return (
    <div style={{padding:20}}>
      <h2>Checkout</h2>
      <div>
        {cart.items.map(it => <div key={it.id + "-" + it.type}>{it.name} x {it.qty} — ${(it.price * it.qty).toFixed(2)}</div>)}
      </div>
      <h3>Total: ${total.toFixed(2)}</h3>
      <button onClick={handleConfirm} disabled={loading}>{loading ? "Procesando..." : "Confirmar compra"}</button>
    </div>
  );
};

export default CheckoutPage;
