// src/pages/Productos.js
import React, { useState, useEffect } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "../styles/Productos.css";

function Productos() {
  const [productos, setProductos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchProductos = async () => {
      try {
        const response = await fetch("http://localhost:8080/productos");
        if (!response.ok) throw new Error("Error al obtener productos");
        const data = await response.json();
        setProductos(data);
      } catch (err) {
        console.error(err);
        setError("No se pudieron cargar los productos.");
      } finally {
        setLoading(false);
      }
    };

    fetchProductos();
  }, []);

  // Construye la URL completa de la imagen
  const getImagenUrl = (imagen) => {
    if (!imagen) return "/default-product.png";
    return `http://localhost:8080/media/imagenes/${imagen}`;
  };

  return (
    <div className="productos-container">
      <Header />

      <main className="productos-main">
        <h2>Nuestros Productos</h2>

        {loading && <p>Cargando productos...</p>}
        {error && <p className="error">{error}</p>}

        <div className="productos-grid">
          {productos.map((producto) => (
            <div key={producto.id} className="producto-card">
              <img
                src={getImagenUrl(producto.imagen)}
                alt={producto.nombre}
              />
              <h3>{producto.nombre}</h3>
              <p>{producto.descripcion}</p>
              <span className="precio">{producto.precio} €</span>
              <button className="btn-carrito">Añadir al carrito</button>
            </div>
          ))}
        </div>
      </main>

      <Footer />
    </div>
  );
}

export default Productos;
