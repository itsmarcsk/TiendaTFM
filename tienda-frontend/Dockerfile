# Stage 1: build
FROM node:20-alpine AS build

WORKDIR /app

# Copiamos package.json y package-lock.json para instalar dependencias
COPY package*.json ./

RUN npm install

# Copiamos el resto del código y construimos el proyecto
COPY . .

RUN npm run build

# Stage 2: serve with nginx
FROM nginx:alpine

# Copiamos los archivos de build al directorio de Nginx
COPY --from=build /app/build /usr/share/nginx/html

# Copiamos configuración personalizada de Nginx si tienes (opcional)
# COPY nginx.conf /etc/nginx/conf.d/default.conf

# Exponemos puerto 80
EXPOSE 80

# Comando por defecto
CMD ["nginx", "-g", "daemon off;"]
