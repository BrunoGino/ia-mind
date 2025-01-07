FROM maven:3.9.9-amazoncorretto-21 AS build

WORKDIR /students-ms

COPY . .

RUN mvn clean package -DskipTests

FROM amazoncorretto:21

WORKDIR /app

COPY --from=build /students-ms/target/students-ms-0.0.1-SNAPSHOT.jar ./students-ms-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "./students-ms-0.0.1-SNAPSHOT.jar"]