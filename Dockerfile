FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY src ./src
CMD ["java", "src/main/java/Technical.java"]