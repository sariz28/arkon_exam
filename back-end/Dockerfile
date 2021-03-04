FROM ubuntu:18.04

#Instala java 8
RUN \
  apt-get update && \
  apt-get install -y openjdk-8-jdk && \
  rm -rf /var/lib/apt/lists/*
 
#Directorio de trabajo
WORKDIR /app
 
#Copiamos el uber-jar en el directorio de trabajo
COPY build/libs/arkon_exam-0.0.1-SNAPSHOT.jar /app
 
 
#Comando que se ejecutará una vez ejecutemos el contendor
CMD ["java","-jar","arkon_exam-0.0.1-SNAPSHOT.jar"]

