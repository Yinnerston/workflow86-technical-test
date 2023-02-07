FROM maven:3.8.3-eclipse-temurin-17 as base
WORKDIR /app
COPY technical/* .
RUN mvn package

FROM base as test
CMD ["mvn", "test"]
