import { useAuth } from "../context/AuthContext";

export default function Dashboard() {
  const { logout } = useAuth();

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-green-50">
      <h1 className="text-3xl font-bold text-green-600 mb-4">Bienvenido a SweetCloud 🌤️</h1>
      <button
        onClick={logout}
        className="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600"
      >
        Cerrar sesión
      </button>
    </div>
  );
}
