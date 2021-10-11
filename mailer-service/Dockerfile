FROM alpine:latest

RUN mkdir /app
ADD /target/mailer-*.jar /app/mailer.jar

EXPOSE 8080

run apk --update add openjdk11

ENTRYPOINT ["java","-jar", "/app/mailer.jar"]
