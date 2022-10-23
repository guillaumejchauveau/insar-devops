# syntax = docker/dockerfile:1.4.0

FROM node:14 AS builder

WORKDIR /app

COPY package-lock.json package.json ./
RUN npm ci

COPY . ./
RUN npm run build

FROM nginx:alpine

RUN <<EOF cat > /etc/nginx/conf.d/default.conf
server {
  listen 80;
  location / {
    root /usr/share/nginx/html;
    index index.html;
    try_files $uri /index.html =404;
  }
}
EOF

COPY --from=builder /app/dist/game-frontend /usr/share/nginx/html/
