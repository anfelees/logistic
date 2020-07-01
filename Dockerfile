FROM amazoncorretto:11
EXPOSE 8082
COPY ./build/libs/logistic-1.0.0.jar logistic.jar
ENTRYPOINT ["java","-jar","/logistic.jar"]