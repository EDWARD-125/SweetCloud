export default function AuthLayout({ title, children }) {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-sky-100 via-blue-200 to-indigo-300">
      <div className="bg-white/90 backdrop-blur-sm p-8 rounded-3xl shadow-2xl w-96 border border-white/40">
        <div className="flex flex-col items-center mb-6">
          <img
            src="/logo.png" // 🪶 Coloca tu logo aquí (te explico abajo)
            alt="SweetCloud logo"
            className="w-20 h-20 mb-2 drop-shadow-md"
          />
          <h1 className="text-3xl font-extrabold text-indigo-600">
            {title}
          </h1>
          <p className="text-gray-500 text-sm mt-1">SweetCloud Platform ☁️</p>
        </div>
        {children}
      </div>
    </div>
  );
}
