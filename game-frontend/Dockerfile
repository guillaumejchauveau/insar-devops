FROM docker.io/node:14 AS builder

WORKDIR /app

COPY package-lock.json package.json ./
RUN npm ci

COPY . ./
RUN npm run build

FROM docker.io/nginx:alpine

RUN echo "\
server { \
  listen 80; \
  location / { \
    root /usr/share/nginx/html; \
    index index.html; \
    try_files $uri /index.html =404; \
  } \
}" > /etc/nginx/conf.d/default.conf


COPY --from=builder /app/dist/game-frontend /usr/share/nginx/html/
