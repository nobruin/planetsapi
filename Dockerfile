FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD /build/libs/*.jar spring-boot-docker-1.0.jar
ENTRYPOINT ["java","-jar","spring-boot-docker-1.0.jar"]