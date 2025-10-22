import { createContext, useContext, useState, useEffect } from "react";

// Creamos el contexto global de autenticación
const AuthContext = createContext();

// Proveedor que envuelve la app
export function AuthProvider({ children }) {
  const [token, setToken] = useState(localStorage.getItem("token"));
  const [username, setUsername] = useState(localStorage.getItem("username"));
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Simula validación inicial del token (opcional)
    setLoading(false);
  }, []);

  // ✅ Iniciar sesión: guarda token y usuario
  const login = (jwt, user) => {
    localStorage.setItem("token", jwt);
    localStorage.setItem("username", user);
    setToken(jwt);
    setUsername(user);
  };

  // ✅ Cerrar sesión
  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    setToken(null);
    setUsername(null);
  };

  const isAuthenticated = !!token;

  return (
    <AuthContext.Provider value={{ token, username, isAuthenticated, login, logout, loading }}>
      {children}
    </AuthContext.Provider>
  );
}

// Hook personalizado para acceder fácilmente al contexto
export function useAuth() {
  return useContext(AuthContext);
}
