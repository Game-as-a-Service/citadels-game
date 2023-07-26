FROM openjdk:17
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8
COPY citadels-backend-0.0.1-SNAPSHOT.jar /citadels-backend-0.0.1-SNAPSHOT.jar
ENV MONGO_URI=
ENTRYPOINT ["sh", "-c", "java -jar -DMONGO_URI=$MONGO_URI /citadels-backend-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
