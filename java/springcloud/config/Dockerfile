FROM hub.c.163.com/library/java:8-alpine

MAINTAINER Keanqi kean_qi@163.com

add target/*.jar app.jar

expose 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]