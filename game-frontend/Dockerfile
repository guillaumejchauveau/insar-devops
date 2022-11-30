FROM docker.io/node:14 AS builder

WORKDIR /app

COPY package-lock.json package.json ./
RUN npm ci

COPY . ./
RUN npm run build

FROM docker.io/nginx:alpine

COPY .docker/nginx.conf /etc/nginx/conf.d/default.conf

ENV NGINX_ENTRYPOINT_QUIET_LOGS=1

COPY --from=builder /app/dist/game-frontend /usr/share/nginx/html/
