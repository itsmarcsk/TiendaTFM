import React, { useState, useEffect } from "react"; // Importa React y hooks
import { FaShoppingCart, FaUser, FaBars, FaTimes } from "react-icons/fa"; // Iconos de react-icons
import "../styles/Header.css"; // Estilos específicos del header

function Header() {
  // -------------------------------
  // Estados locales
  // -------------------------------
  const [menuOpen, setMenuOpen] = useState(false); // Controla si el menú hamburguesa está abierto
  const [email, setEmail] = useState(null); // Almacena el email del usuario (si está logueado)

  // -------------------------------
  // Función para alternar menú
  // -------------------------------
  const toggleMenu = () => setMenuOpen(!menuOpen);

  // -------------------------------
  // useEffect para cargar email del localStorage
  // -------------------------------
  useEffect(() => {
    const storedEmail = localStorage.getItem("email"); // Obtiene email guardado en sesión
    if (storedEmail) {
      setEmail(storedEmail); // Actualiza estado
    }
  }, []);

  // -------------------------------
  // Renderizado
  // -------------------------------
  return (
    <header className="header">
      {/* Logo */}
      <div className="logo">
        <a href="/">🧘‍♂️</a> Tienda Fisioterapia
      </div>

      {/* Navegación */}
      <nav className={`nav ${menuOpen ? "nav-active" : ""}`}> 
        {/* Aplica clase 'nav-active' si menú abierto */}
        <ul className="nav-links">
          <li><a href="/">Inicio</a></li>    
          <li><a href="/productos">Productos</a></li>
          <li><a href="/contacto">Contacto</a></li>
          
          {/* Icono carrito */}
          <li>
            <a href="/carrito">
              <FaShoppingCart size={18} />
            </a>
          </li>

          {/* Icono usuario o enlace login */}
          <li>
            {email ? (
              <a href="/usuario">
                <FaUser size={18} />
              </a>
            ) : (
              <a href="/login">Iniciar Sesión / Crear Cuenta</a>
            )}
          </li>
        </ul>
      </nav>

      {/* Botón hamburguesa para menú responsive */}
      <button
        className="hamburger"
        onClick={toggleMenu} // Alterna menú
        onKeyDown={(e) => { // Permite accesibilidad por teclado
          if (e.key === "Enter" || e.key === " ") toggleMenu();
        }}
        aria-label={menuOpen ? "Cerrar menú" : "Abrir menú"} // Accesibilidad screen readers
      >
        {/* Cambia icono según estado del menú */}
        {menuOpen ? <FaTimes size={25} /> : <FaBars size={25} />}
      </button>
    </header>
  );
}

export default Header;
