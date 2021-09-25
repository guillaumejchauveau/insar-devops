FROM nginx:alpine

COPY . /usr/share/nginx/html

RUN echo 'server {\
  listen 80;\
  location / {\
    root /usr/share/nginx/html;\
    index index.html;\
    try_files $uri $uri/index.html /index.html =404;\
  }\
}' > /etc/nginx/conf.d/default.conf
