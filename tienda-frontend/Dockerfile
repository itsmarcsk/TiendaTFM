# Stage 1: build
FROM node:20-alpine AS build

WORKDIR /app

# Instala deps con caché fiable
COPY package*.json ./
RUN npm ci --no-audit --no-fund

# Copia código y build
COPY . .
RUN npm run build

# Stage 2: nginx
FROM nginx:alpine

# Limpia HTML de Nginx
RUN rm -rf /usr/share/nginx/html/*

# Config SPA: reenvía rutas a index.html
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copia el repo del builder y luego mueve lo que exista
COPY --from=build /app /tmp/app
RUN if [ -d /tmp/app/build ]; then cp -r /tmp/app/build/* /usr/share/nginx/html/; \
    elif [ -d /tmp/app/dist ]; then cp -r /tmp/app/dist/* /usr/share/nginx/html/; \
    else echo "ERROR: No se encontró carpeta de build (ni /app/build ni /app/dist)"; exit 1; fi \
    && rm -rf /tmp/app

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
