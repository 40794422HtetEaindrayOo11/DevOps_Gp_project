FROM eclipse-temurin:18
COPY ./target/GroupProject-0.1.0.5-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "GroupProject-0.1.0.5-jar-with-dependencies.jar"]