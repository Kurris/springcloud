FROM openjdk:8
EXPOSE 8080
COPY ./target/order-service.jar /app/order-service.jar
WORKDIR /app
ENTRYPOINT ["java","-jar" ,"order-service.jar"]