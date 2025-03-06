FROM openjdk:17

ARG APPLICATION_NAME
ARG VERSION

COPY ./$APPLICATION_NAME/build/libs/$APPLICATION_NAME-$VERSION.jar app.jar
COPY ./entryPoint.sh entryPoint.sh
RUN chmod +x entryPoint.sh

ENTRYPOINT ["sh", "-c", "./entryPoint.sh"]