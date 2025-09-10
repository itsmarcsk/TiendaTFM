import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Contacto from "./pages/Contacto";
import Login from "./pages/Login";
import Registro from "./pages/Registro";
import Productos from "./pages/Productos";
import Usuario from "./pages/Usuario";
import Carrito from "./pages/Carrito";
import CambiarContraseña from "./pages/CambiarContrasena";

function App() {
  return (
    <Router>
      <Routes>
        {/* Ruta por defecto */}
        <Route path="/" element={<Home />} />

        {/* Otras páginas */}
        <Route path="/contacto" element={<Contacto />} />
        <Route path="/login" element={<Login />} />
        <Route path="/registro" element={<Registro />} />

        {/* Ruta para productos */}
        <Route path="/productos" element={<Productos />} />

        {/* Ruta para usuario */}
        <Route path="/usuario" element={<Usuario />} />

        {/* Ruta para carrito */}
        <Route path="/carrito" element={<Carrito />} />

        {/* Ruta para cambiar contraseña */}
        <Route path="/cambiar-contrasena" element={<CambiarContraseña />} />
      </Routes>
    </Router>
  );
}

export default App;
