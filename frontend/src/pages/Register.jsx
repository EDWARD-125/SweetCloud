import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";
import AuthLayout from "../layouts/AuthLayout";

export default function Register() {
  const navigate = useNavigate();
  const [form, setForm] = useState({ username: "", password: "" });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");
    setLoading(true);

    try {
      const response = await api.post("/auth/register", form);
      setSuccess(response.data.mensaje || "Usuario registrado exitosamente 🎉");
      setForm({ username: "", password: "" });
      setTimeout(() => navigate("/login"), 2000);
    } catch {
      setError("No se pudo registrar el usuario. Verifica los datos.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <AuthLayout title="Crear Cuenta">
      {success && (
        <p className="text-green-600 bg-green-100 p-2 rounded text-center mb-3">
          {success}
        </p>
      )}
      {error && (
        <p className="text-red-600 bg-red-100 p-2 rounded text-center mb-3">
          {error}
        </p>
      )}

      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          name="username"
          placeholder="Usuario"
          value={form.username}
          onChange={handleChange}
          className="w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-indigo-400 outline-none"
          required
        />
        <input
          type="password"
          name="password"
          placeholder="Contraseña"
          value={form.password}
          onChange={handleChange}
          className="w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-indigo-400 outline-none"
          required
        />

        <button
          type="submit"
          disabled={loading}
          className={`w-full py-2 rounded-lg text-white font-semibold transition ${
            loading
              ? "bg-indigo-300 cursor-not-allowed"
              : "bg-indigo-600 hover:bg-indigo-700"
          }`}
        >
          {loading ? "Registrando..." : "Registrarse"}
        </button>
      </form>

      <p className="text-sm text-center mt-4 text-gray-600">
        ¿Ya tienes una cuenta?{" "}
        <span
          onClick={() => navigate("/login")}
          className="text-indigo-600 font-medium hover:underline cursor-pointer"
        >
          Inicia sesión
        </span>
      </p>
    </AuthLayout>
  );
}
