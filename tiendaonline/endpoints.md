# 📦 API REST de Tienda Online (Spring Boot)

Esta API maneja **usuarios, productos y medios** (imágenes). Construida con **Spring Boot**, devuelve JSON y archivos binarios según corresponda.

---

## 🖼️ Endpoints de Imágenes

### 1️⃣ Listar imágenes
- **URL:** `/media/imagenes`
- **Método:** `GET`  
- **Descripción:** Devuelve la lista de imágenes disponibles.  
- **Respuesta ejemplo:**
```json
["imagen1.jpg", "imagen2.png", "imagen3.gif"]
```

---

### 2️⃣ Descargar imagen
- **URL:** `/media/imagenes/{filename}`
- **Método:** `GET`  
- **Parámetros:** `filename` → nombre del archivo  
- **Descripción:** Descarga la imagen como `byte[]`. Ajusta el tipo MIME según la extensión.  
- **Tipos MIME soportados:** `jpeg`, `png`, `gif`, `bmp`, `webp`, `tiff`  
- **Errores:**  
  - 404 → Imagen no encontrada

---

## 🛒 Endpoints de Productos

### 1️⃣ Crear producto
- **URL:** `/productos`
- **Método:** `POST`  
- **Body:** JSON con la información del producto
```json
{
  "nombre": "Producto A",
  "descripcion": "Descripción del producto",
  "precio": 12.99
}
```
- **Respuesta exitosa:** `"Producto creado correctamente"`  
- **Errores:** `"Error al crear producto: mensaje"`

---

### 2️⃣ Listar productos
- **URL:** `/productos`
- **Método:** `GET`  
- **Descripción:** Devuelve todos los productos en JSON  
- **Respuesta ejemplo:**
```json
[
  {"id": 1, "nombre": "Producto A", "descripcion": "Desc A", "precio": 12.99},
  {"id": 2, "nombre": "Producto B", "descripcion": "Desc B", "precio": 25.50}
]
```

---

### 3️⃣ Eliminar producto
- **URL:** `/productos/{id}`
- **Método:** `DELETE`  
- **Parámetros:** `id` → ID del producto a eliminar  
- **Respuesta exitosa:** `"Producto eliminado correctamente"`  
- **Errores:** `"Error al eliminar producto: mensaje"`

---

## 👤 Endpoints de Usuarios

### 1️⃣ Crear usuario
- **URL:** `/usuarios`
- **Método:** `POST`  
- **Body:** JSON con los datos del usuario  
- **Respuesta:** `true` → si se creó correctamente, `false` → si hubo error

---

### 2️⃣ Obtener usuario por email
- **URL:** `/usuarios/email/{email}`
- **Método:** `GET`  
- **Parámetros:** `email` → correo del usuario  
- **Respuesta ejemplo:**
```json
{
  "nombre": "Ana",
  "apellidos": "Pérez",
  "email": "ana@example.com",
  "direccion": "Calle Falsa 123",
  "telefono": "600123456"
}
```

---

### 3️⃣ Verificar login
- **URL:** `/usuarios/login/{email}/{contrasena}`
- **Método:** `GET`  
- **Parámetros:** `email`, `contrasena`  
- **Respuesta ejemplo:**
```json
{"success": true}
```

---

### 4️⃣ Listar todos los usuarios
- **URL:** `/usuarios`
- **Método:** `GET`  
- **Descripción:** Devuelve lista de usuarios  
- **Respuesta ejemplo:**
```json
[
  {"nombre": "Ana", "email": "ana@example.com"},
  {"nombre": "Carlos", "email": "carlos@example.com"}
]
```

---

### 5️⃣ Eliminar usuario
- **URL:** `/usuarios/{email}`
- **Método:** `DELETE`  
- **Parámetros:** `email` → correo del usuario  
- **Respuesta exitosa:** `"Usuario eliminado correctamente"`  
- **Errores:** `"Error al eliminar usuario: mensaje"`

---

### 6️⃣ Actualizar contraseña
- **URL:** `/usuarios/{email}/contrasena`
- **Método:** `PUT`  
- **Body:** nueva contraseña (string)  
- **Respuesta exitosa:** `"Contraseña actualizada correctamente"`  
- **Errores:** `"Error al actualizar contraseña: mensaje"`

---

## 📝 Notas
- Todos los endpoints devuelven JSON o `byte[]` según corresponda.
- La gestión de errores es básica, se recomienda manejar códigos HTTP adecuados y mensajes claros.
- Los endpoints de imágenes devuelven **tipos MIME** dinámicos según la extensión.
