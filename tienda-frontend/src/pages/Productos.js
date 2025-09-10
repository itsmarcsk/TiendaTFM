import React, { useState, useEffect } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "../styles/Productos.css";

// Firebase
import { collection, query, where, getDocs, addDoc, updateDoc } from "firebase/firestore";
import { db } from "../firebase";

function Productos() {
  const [productos, setProductos] = useState([]); // Lista de productos desde la API
  const [loading, setLoading] = useState(true);   // Estado de carga
  const [error, setError] = useState("");         // Mensajes de error
  const [status, setStatus] = useState("");       // Mensajes de estado (añadir al carrito)

  // 🔹 Cargar productos desde el backend
  useEffect(() => {
    const fetchProductos = async () => {
      try {
        const response = await fetch("http://localhost:8080/productos"); // Endpoint Spring Boot
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

  // 🔹 Obtener URL de la imagen
  const getImagenUrl = (imagen) => (
    !imagen ? "/default-product.png" : `http://localhost:8080/media/imagenes/${imagen}`
  );

  // 🔹 Función para añadir productos al carrito en Firebase
  const agregarAlCarrito = async (producto) => {
    const email = localStorage.getItem("email");
    if (!email) {
      setStatus("Debes iniciar sesión para añadir productos al carrito.");
      return;
    }

    try {
      const carritoRef = collection(db, "carritos");
      const q = query(
        carritoRef,
        where("email", "==", email),
        where("productoNombre", "==", producto.nombre)
      );
      const snapshot = await getDocs(q);

      if (!snapshot.empty) {
        // Si ya está en el carrito, incrementa la cantidad
        const docRef = snapshot.docs[0].ref;
        const cantidadActual = snapshot.docs[0].data().cantidad || 1;
        await updateDoc(docRef, { cantidad: cantidadActual + 1 });
        setStatus(`Cantidad de "${producto.nombre}" incrementada ✅`);
      } else {
        // Si no existe, agrega nuevo producto al carrito
        await addDoc(carritoRef, {
          email,
          productoNombre: producto.nombre,
          cantidad: 1,
          precio: producto.precio
        });
        setStatus(`Producto "${producto.nombre}" añadido al carrito ✅`);
      }
    } catch (err) {
      console.error("Error al añadir al carrito:", err);
      setStatus("Error al añadir producto al carrito ❌");
    }
  };

  return (
    <div className="productos-container">
      <Header />
      <main className="productos-main">
        <h2>Nuestros Productos</h2>

        {loading && <p>Cargando productos...</p>}
        {error && <p className="error">{error}</p>}
        {status && <p className="status">{status}</p>}

        <div className="productos-grid">
          {productos.map((producto) => (
            <div key={producto.id} className="producto-card">
              <img src={getImagenUrl(producto.imagen)} alt={producto.nombre} />
              <h3>{producto.nombre}</h3>
              <p>{producto.descripcion}</p>
              <span className="precio">{producto.precio} €</span>
              <button className="btn-carrito" onClick={() => agregarAlCarrito(producto)}>
                Añadir al carrito
              </button>
            </div>
          ))}
        </div>
      </main>
      <Footer />
    </div>
  );
}

export default Productos;
