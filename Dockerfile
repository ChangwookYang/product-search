FROM amazoncorretto:21

WORKDIR /app

COPY build/libs/product0218-0.0.1-SNAPSHOT.jar app.jar

# 애플리케이션 실행
CMD ["java", "-jar", "app.jar"]