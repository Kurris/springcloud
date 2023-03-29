FROM openjdk:8
EXPOSE 8080
COPY ./target/ticket-service.jar /app/ticket-service.jar
WORKDIR /app
ENTRYPOINT ["java","-jar" ,"ticket-service.jar"]