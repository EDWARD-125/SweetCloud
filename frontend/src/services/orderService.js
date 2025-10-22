// src/services/orderService.js
import api from "./api";

export const saveCartServer = async (cartPayload) => {
  const resp = await api.post("/orders/save-cart", cartPayload);
  return resp.data;
};

export const getMyCart = async () => {
  const resp = await api.get("/orders/my-cart");
  return resp.data;
};

export const createOrder = async (orderPayload) => {
  const resp = await api.post("/orders", orderPayload);
  return resp.data;
};
