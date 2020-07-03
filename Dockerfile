FROM openjdk:11-jdk-slim
MAINTAINER Raul Klumpp <raulklumpp@gmail.com>

ENV APP_HOME /app
RUN mkdir -p $APP_HOME

ADD build/libs/ze.delivery-0.0.1-SNAPSHOT.jar $APP_HOME/ze-delivery.jar

WORKDIR $APP_HOME

EXPOSE 8080

CMD ["java","-jar","/app/ze-delivery.jar"]