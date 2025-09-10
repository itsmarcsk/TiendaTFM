# 📁 API de Almacenamiento de Archivos

Esta API permite **subir, descargar, listar y eliminar** imágenes y videos.
Está construida con **FastAPI** y utiliza la carpeta local `uploads/` para almacenar los archivos.

---

## 🌐 Configuración de CORS
- Permite solicitudes desde **cualquier origen**
- Métodos permitidos: `GET`, `POST`, `PUT`, `DELETE`, `OPTIONS`
- Headers permitidos: **todos**

---

## 🖼️ Endpoints para Imágenes

### 1️⃣ Subir imagen
- **URL:** `/upload/image/`
- **Método:** `POST`  
- **Body:** `multipart/form-data` con el archivo `file`  
- **Extensiones permitidas:** `jpg`, `jpeg`, `png`, `gif`  

**Respuesta exitosa:**
```json
{
  "status": true,
  "filename": "nombre_del_archivo.jpg"
}
```

**Errores:**  
- 400 → Tipo de archivo no permitido

---

### 2️⃣ Descargar imagen
- **URL:** `/download/image/{filename}`
- **Método:** `GET`  
- **Parámetros:** `filename` → Nombre del archivo  

**Respuesta exitosa:** Devuelve el archivo como `FileResponse`  

**Errores:**  
- 404 → Archivo no encontrado

---

### 3️⃣ Listar imágenes
- **URL:** `/images/`
- **Método:** `GET`  

**Respuesta exitosa:**
```json
{
  "imagenes": ["imagen1.jpg", "imagen2.png"]
}
```

**Errores:**  
- 404 → Directorio no encontrado  
- 500 → Error interno al listar archivos

---

## 🎥 Endpoints para Videos

### 1️⃣ Subir video
- **URL:** `/upload/video/`
- **Método:** `POST`  
- **Body:** `multipart/form-data` con el archivo `file`  
- **Extensiones permitidas:** `mp4`, `avi`, `mov`, `mkv`  

**Respuesta exitosa:**
```json
{
  "status": true,
  "filename": "video.mp4"
}
```

**Errores:**  
- 400 → Tipo de archivo no permitido

---

### 2️⃣ Descargar video
- **URL:** `/download/video/{filename}`
- **Método:** `GET`  
- **Parámetros:** `filename` → Nombre del archivo  

**Respuesta exitosa:** Streaming del archivo con `Content-Disposition: attachment`  

**Errores:**  
- 404 → Archivo no encontrado

---

### 3️⃣ Listar videos
- **URL:** `/videos/`
- **Método:** `GET`  

**Respuesta exitosa:**
```json
{
  "videos": ["video1.mp4", "video2.mov"]
}
```

**Errores:**  
- 404 → Directorio no encontrado  
- 500 → Error interno al listar archivos

---

## 🗑️ Endpoints para eliminar archivos

### 1️⃣ Eliminar un archivo
- **URL:** `/delete/{filename}`
- **Método:** `DELETE`  
- **Parámetros:** `filename` → Nombre del archivo  

**Respuesta exitosa:**
```json
{"status": true}
```

Si no existe el archivo:
```json
{"status": false}
```

---

### 2️⃣ Eliminar todos los archivos
- **URL:** `/delete-all/`
- **Método:** `DELETE`  

**Respuesta exitosa:**
```json
{
  "status": true,
  "deleted_files": ["archivo1.jpg", "video1.mp4"]
}
```

---

## ⚡ Endpoint de prueba
- **URL:** `/`
- **Método:** `GET`  

**Respuesta:**
```json
{
  "message": "API de almacenamiento está en funcionamiento"
}
```

---

## 📝 Notas
- Todos los archivos se almacenan en la carpeta `uploads/`.
- La API distingue entre **imágenes** y **videos** según su extensión.
- Se recomienda manejar las rutas de archivos con cuidado para **evitar sobrescrituras**.
