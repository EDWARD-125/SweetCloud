import api from "./api";

// Registrar un nuevo usuario
export const registerUser = async (username, password) => {
  try {
    const response = await api.post("/auth/register", { username, password });
    return response.data;
  } catch (error) {
    console.error("Error en registro:", error);
    throw error.response?.data || { mensaje: "Error desconocido" };
  }
};

// Iniciar sesión
export const loginUser = async (username, password) => {
  try {
    const response = await api.post("/auth/login", { username, password });
    const { token, username: user } = response.data.data;

    // Guardar token en localStorage
    localStorage.setItem("token", token);
    localStorage.setItem("username", user);

    return response.data;
  } catch (error) {
    console.error("Error en login:", error);
    throw error.response?.data || { mensaje: "Error desconocido" };
  }
};

// Cerrar sesión
export const logoutUser = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("username");
};
