import React, { useState, useEffect } from "react";
import Header from "../components/Header"; // Cabecera de la página
import Footer from "../components/Footer"; // Pie de página
import "../styles/Carrito.css"; // Estilos específicos del carrito

// 🔹 Firebase
import { collection, query, where, onSnapshot, deleteDoc, doc, getDocs } from "firebase/firestore";
import { db } from "../firebase"; // Conexión a Firestore

function Carrito() {
  // -------------------------------
  // Estados locales
  // -------------------------------
  const [productos, setProductos] = useState([]); // Lista de productos en el carrito
  const [status, setStatus] = useState(""); // Mensajes de estado (éxito o error)

  // -------------------------------
  // useEffect: Obtener productos en tiempo real
  // -------------------------------
  useEffect(() => {
    const email = localStorage.getItem("email"); // Obtener email del usuario
    if (!email) {
      setStatus("Debes iniciar sesión para ver tu carrito.");
      return;
    }

    const carritoRef = collection(db, "carritos"); // Referencia a la colección "carritos"
    const q = query(carritoRef, where("email", "==", email)); // Consulta solo los productos del usuario

    // 🔹 Escuchar cambios en tiempo real
    const unsubscribe = onSnapshot(q, (snapshot) => {
      const carritoData = snapshot.docs.map((doc) => ({
        id: doc.id,
        ...doc.data(), // Datos del documento
      }));
      setProductos(carritoData); // Actualiza estado con los productos
    });

    return () => unsubscribe(); // Limpieza al desmontar el componente
  }, []);

  // -------------------------------
  // Ocultar mensajes automáticamente a los 4s
  // -------------------------------
  useEffect(() => {
    if (status) {
      const timer = setTimeout(() => setStatus(""), 4000);
      return () => clearTimeout(timer);
    }
  }, [status]);

  // -------------------------------
  // Vaciar carrito
  // -------------------------------
  const vaciarCarrito = async () => {
    const email = localStorage.getItem("email");
    if (!email) return;

    const carritoRef = collection(db, "carritos");
    const q = query(carritoRef, where("email", "==", email));
    const snapshot = await getDocs(q);

    // Elimina todos los documentos del carrito del usuario
    snapshot.forEach(async (docSnap) => {
      await deleteDoc(doc(db, "carritos", docSnap.id));
    });

    setStatus("Carrito vaciado ✅"); // Mensaje de éxito
  };

  // -------------------------------
  // Finalizar compra
  // -------------------------------
  const finalizarCompra = async () => {
    const email = localStorage.getItem("email");
    if (!email) return;

    const carritoRef = collection(db, "carritos");
    const q = query(carritoRef, where("email", "==", email));
    const snapshot = await getDocs(q);

    // Elimina todos los documentos del carrito del usuario (simula compra)
    snapshot.forEach(async (docSnap) => {
      await deleteDoc(doc(db, "carritos", docSnap.id));
    });

    setStatus("Compra finalizada ✅"); // Mensaje de éxito
  };

  // -------------------------------
  // Calcular total del carrito
  // -------------------------------
  const total = productos.reduce((acc, p) => acc + p.precio * p.cantidad, 0);

  // -------------------------------
  // Renderizado del componente
  // -------------------------------
  return (
    <div className="carrito-container">
      <Header />
      <main className="carrito-main">
        <h2>Tu Carrito</h2>
        {status && <p className="status">{status}</p>} {/* Mensaje de estado */}

        {productos.length === 0 && !status && <p>Tu carrito está vacío.</p>} {/* Mensaje si no hay productos */}

        {productos.length > 0 && (
          <div className="carrito-items">
            {productos.map((producto) => (
              <div key={producto.id} className="carrito-item">
                <p className="nombre">{producto.productoNombre}</p>
                <p className="cantidad">Cantidad: {producto.cantidad}</p>
                <p className="precio">
                  Precio unitario: ${producto.precio.toFixed(2)} | Total: ${(producto.precio * producto.cantidad).toFixed(2)}
                </p>
              </div>
            ))}

            <div className="total">
              <p>Total carrito: ${total.toFixed(2)}</p>
            </div>

            <div className="carrito-buttons">
              <button className="vaciar-btn" onClick={vaciarCarrito}>Vaciar Carrito</button>
              <button className="checkout-btn" onClick={finalizarCompra}>Finalizar Compra</button>
            </div>
          </div>
        )}
      </main>
      <Footer />
    </div>
  );
}

export default Carrito;
