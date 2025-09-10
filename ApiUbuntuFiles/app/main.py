from fastapi import FastAPI, UploadFile, File, HTTPException
from fastapi.responses import FileResponse, StreamingResponse
from starlette.middleware.cors import CORSMiddleware
import os
import shutil

# -------------------------------
# Configuración inicial
# -------------------------------
UPLOAD_DIR = "uploads"  # Carpeta donde se almacenan los archivos
os.makedirs(UPLOAD_DIR, exist_ok=True)  # Crea la carpeta si no existe

app = FastAPI(title="Mini API de almacenamiento")  # Instancia de FastAPI

# Configuración de CORS para permitir solicitudes desde cualquier origen
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # Permite cualquier origen
    allow_credentials=True,
    allow_methods=["*"],  # Permite todos los métodos HTTP
    allow_headers=["*"],  # Permite todos los headers
)

# Tipos de archivos permitidos
ALLOWED_IMAGE_EXTENSIONS = {'jpg', 'jpeg', 'png', 'gif'}
ALLOWED_VIDEO_EXTENSIONS = {'mp4', 'avi', 'mov', 'mkv'}

# -------------------------------
# Endpoints para Imágenes
# -------------------------------

@app.post("/upload/image/")
async def upload_image(file: UploadFile = File(...)):
    # Verifica extensión de archivo
    file_extension = file.filename.split('.')[-1].lower()
    if file_extension not in ALLOWED_IMAGE_EXTENSIONS:
        raise HTTPException(status_code=400, detail="Tipo de archivo no permitido")
    
    # Guarda el archivo en UPLOAD_DIR
    file_path = os.path.join(UPLOAD_DIR, file.filename)
    with open(file_path, "wb") as buffer:
        shutil.copyfileobj(file.file, buffer)
    return {"status": True, "filename": file.filename}

@app.get("/download/image/{filename}")
async def download_image(filename: str):
    # Descarga un archivo de imagen existente
    file_path = os.path.join(UPLOAD_DIR, filename)
    if not os.path.exists(file_path):
        raise HTTPException(status_code=404, detail="Archivo no encontrado")
    return FileResponse(file_path)

@app.get("/images/")
async def list_images():
    # Lista todas las imágenes disponibles
    try:
        if not os.path.exists(UPLOAD_DIR):
            raise HTTPException(status_code=404, detail="Directorio no encontrado")

        files = os.listdir(UPLOAD_DIR)
        images = [file for file in files if file.lower().endswith(tuple(ALLOWED_IMAGE_EXTENSIONS))]
        return {"imagenes": images}

    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Error al obtener las imágenes: {str(e)}")

# -------------------------------
# Endpoints para Videos
# -------------------------------

@app.post("/upload/video/")
async def upload_video(file: UploadFile = File(...)):
    # Verifica extensión de archivo
    file_extension = file.filename.split('.')[-1].lower()
    if file_extension not in ALLOWED_VIDEO_EXTENSIONS:
        raise HTTPException(status_code=400, detail="Tipo de archivo no permitido")
    
    # Guarda el archivo en UPLOAD_DIR
    file_path = os.path.join(UPLOAD_DIR, file.filename)
    with open(file_path, "wb") as buffer:
        shutil.copyfileobj(file.file, buffer)
    return {"status": True, "filename": file.filename}

@app.get("/download/video/{filename}")
async def download_video(filename: str):
    # Descarga un archivo de video como StreamingResponse
    file_path = os.path.join(UPLOAD_DIR, filename)
    if not os.path.exists(file_path):
        raise HTTPException(status_code=404, detail="Archivo no encontrado")
    
    video_stream = open(file_path, mode="rb")
    return StreamingResponse(
        video_stream,
        media_type=get_video_content_type(filename),
        headers={"Content-Disposition": f"attachment; filename={filename}"}  # Indica descarga
    )

def get_video_content_type(filename: str) -> str:
    # Retorna el MIME type apropiado según la extensión del video
    file_extension = filename.split('.')[-1].lower()
    
    if file_extension == "mp4":
        return "video/mp4"
    elif file_extension == "avi":
        return "video/x-msvideo"
    elif file_extension == "mov":
        return "video/quicktime"
    elif file_extension == "mkv":
        return "video/x-matroska"
    else:
        return "application/octet-stream"  # Tipo genérico

@app.get("/videos/")
async def list_videos():
    # Lista todos los videos disponibles
    try:
        if not os.path.exists(UPLOAD_DIR):
            raise HTTPException(status_code=404, detail="Directorio no encontrado")

        files = os.listdir(UPLOAD_DIR)
        videos = [file for file in files if file.lower().endswith(tuple(ALLOWED_VIDEO_EXTENSIONS))]
        return {"videos": videos}

    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Error al obtener los videos: {str(e)}")

# -------------------------------
# Endpoints para Eliminar Archivos
# -------------------------------

@app.delete("/delete/{filename}")
async def delete_file(filename: str):
    # Elimina un archivo específico
    file_path = os.path.join(UPLOAD_DIR, filename)
    if not os.path.exists(file_path):
        return {"status": False}
    os.remove(file_path)
    return {"status": True}

@app.delete("/delete-all/")
async def delete_all_files():
    # Elimina todos los archivos en el directorio
    deleted = []
    for filename in os.listdir(UPLOAD_DIR):
        path = os.path.join(UPLOAD_DIR, filename)
        if os.path.isfile(path):
            os.remove(path)
            deleted.append(filename)
    return {"status": True, "deleted_files": deleted}

# -------------------------------
# Endpoint de prueba
# -------------------------------
@app.get("/")
async def index():
    return {"message": "API de almacenamiento está en funcionamiento"}
