import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import "./index.css";

// 👇 Importamos el contexto de autenticación
import { AuthProvider } from "./context/AuthContext.jsx";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    {/* 👇 Envolvemos la app dentro del AuthProvider */}
    <AuthProvider>
      <App />
    </AuthProvider>
  </StrictMode>
);
