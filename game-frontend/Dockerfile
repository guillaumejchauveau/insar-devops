FROM nginx:alpine

RUN echo 'server {\
  listen 80;\
  location / {\
    root /usr/share/nginx/html;\
    index index.html;\
    try_files $uri /index.html =404;\
  }\
}' > /etc/nginx/conf.d/default.conf

COPY dist/game-frontend /usr/share/nginx/html
