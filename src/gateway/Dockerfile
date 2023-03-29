FROM openjdk:8
EXPOSE 8080
COPY ./target/gateway.jar /app/gateway.jar
WORKDIR /app
ENTRYPOINT ["java","-jar" ,"gateway.jar"]