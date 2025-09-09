CREATE DATABASE IF NOT EXISTS tiendaonline;
USE tiendaonline;

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellidos VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(30) NOT NULL,
    direccion VARCHAR(100),
    telefono VARCHAR(20),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de productos
CREATE TABLE IF NOT EXISTS producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150),
    descripcion TEXT,
    precio DECIMAL(10,2),
    stock INT NOT NULL DEFAULT 0,
    imagen VARCHAR(255), -- Aquí guardamos el nombre del archivo (ej: "camiseta_roja.jpg")
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);