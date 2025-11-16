FROM eclipse-temurin:18
COPY ./target/GroupProject-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "devops.jar","db:3306","30000"]
