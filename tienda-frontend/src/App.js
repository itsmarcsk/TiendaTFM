import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Contacto from "./pages/Contacto";
import Login from "./pages/Login";
import Registro from "./pages/Registro";
import Productos from "./pages/Productos";
import Usuario from "./pages/Usuario";


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

        {/* Ruta para pedidos */}
        <Route path="/usuario" element={<Usuario />} />
        
      </Routes>

    </Router>
  );
}

export default App;
