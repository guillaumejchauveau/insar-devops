FROM openjdk:11-jre-slim

RUN apt-get update && apt-get install -y python3-pip curl

COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt
