import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Contacto from "./pages/Contacto";
/*import Productos from "./pages/Productos";
import Carrito from "./pages/Carrito";
import Usuario from "./pages/Usuario";
import "./styles/App.css";
<Route path="/productos" element={<Productos />} />
<Route path="/carrito" element={<Carrito />} />
<Route path="/usuario" element={<Usuario />} />*/

function App() {
  return (
    <Router>

      <Routes>
        {/* Ruta por defecto */}
        <Route path="/" element={<Home />} />

        {/* Otras páginas */}
        
        <Route path="/contacto" element={<Contacto />} />
        
      </Routes>

    </Router>
  );
}

export default App;
