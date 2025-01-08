FROM maven:3.9.9-amazoncorretto-21 AS build

WORKDIR /user-ms

COPY . .

RUN mvn clean package -DskipTests

FROM amazoncorretto:21

WORKDIR /app

COPY --from=build /user-ms/target/user-ms-0.0.1-SNAPSHOT.jar ./user-ms-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "./user-ms-0.0.1-SNAPSHOT.jar"]