FROM ubuntu:25.10

# Evita prompts interactivos
ENV DEBIAN_FRONTEND=noninteractive

# Actualizar, instalar dependencias y limpiar cache
RUN apt-get update && apt-get install -y \
    python3 \
    python3-pip \
    python3-venv \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Crear directorio de trabajo y uploads
WORKDIR /app
RUN mkdir -p /app/uploads && chmod -R 777 /app/uploads

# Copiar requirements para aprovechar cache de Docker
COPY requirements.txt .

# Instalar dependencias de Python
RUN pip3 install --no-cache-dir --break-system-packages -r requirements.txt

# Copiar el resto de la app
COPY ./app ./app

# Exponer el puerto 5000 (igual que el docker-compose)
EXPOSE 5000

# Ejecutar la API en el puerto 5000
CMD ["python3", "-m", "uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "5000"]
