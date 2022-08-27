FROM openjdk:17
WORKDIR '/app'
ADD target/docker-crudJDBC-0.0.1-SNAPSHOT.jar crudJDBC-0.0.1-SNAPSHOT.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "crudJDBC-0.0.1-SNAPSHOT.jar"]