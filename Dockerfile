FROM maven:3.8.3-eclipse-temurin-17
WORKDIR /app
COPY technical/* .
RUN mvn package
CMD ["mvn", "test"]