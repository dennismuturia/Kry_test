FROM openjdk:11 as base
VOLUME /tmp

FROM gradle:4.7.0-jdk8-alpine AS gradlebuild
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM base as test
CMD ["gradle", "test"]

FROM base as build
CMD ["gradle", "build"]

EXPOSE 8093


COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]