// src/services/api.js
import axios from "axios";

// ✅ Configura la URL base de tu backend
const api = axios.create({
  baseURL: "http://localhost:8080/api", // Cambia el puerto si es distinto
  headers: {
    "Content-Type": "application/json",
  },
});

// ✅ Interceptor para añadir el token a cada petición
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// ✅ Interceptor para manejar errores de autenticación
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      console.warn("⚠️ Token expirado o inválido, cerrando sesión...");
      localStorage.removeItem("token");
      localStorage.removeItem("username");
      window.location.href = "/login";
    }
    return Promise.reject(error);
  }
);

export default api;
