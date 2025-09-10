import React from "react"; // Importa React para definir el componente
import "../styles/Footer.css"; // Importa los estilos CSS específicos del footer

function Footer() {
  return (
    <footer className="footer"> {/* Elemento semántico <footer> */}
      <div className="footer-container"> {/* Contenedor para el contenido del footer */}
        {/* Texto con el año actual dinámico */}
        <p>© {new Date().getFullYear()} Tienda Fisioterapia. Todos los derechos reservados.</p>
        {/* Información de contacto */}
        <p>📞 Contacto: info@tiendafisioterapia.com | ☎ +34 600 000 000</p>
      </div>
    </footer>
  );
}

export default Footer; // Exporta el componente para usarlo en otras partes de la app
