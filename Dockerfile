# Usar uma imagem base compatível com ARM e x86
FROM eclipse-temurin:17-jdk

# Criar o diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR gerado pelo Maven/Gradle
COPY target/auth-0.0.1-SNAPSHOT.jar auth-0.0.1-SNAPSHOT.jar

# Expor a porta da aplicação
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "auth-0.0.1-SNAPSHOT.jar"]