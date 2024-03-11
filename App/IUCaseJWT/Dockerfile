# Use a imagem do OpenJDK como base
FROM openjdk:23-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR construído pelo Maven para o contêiner
COPY target/*.jar /app/app.jar

# Exponha a porta em que sua aplicação Spring Boot será executada
EXPOSE 80

# Comando para executar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "app.jar"]
