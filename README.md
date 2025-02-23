1. JAR 파일 빌드
./gradlew bootJar
생성된 JAR 파일은 build/libs/your-app.jar 경로에 위치


2. Dockerfile 작성
FROM amazoncorretto:21

WORKDIR /app
COPY build/libs/your-app.jar app.jar

CMD ["java", "-jar", "app.jar"]

3. Docker 이미지 빌드
docker build -t my-spring-app .
my-spring-app: 이미지 이름

4. Docker 컨테이너 실행
docker run -p 8080:8080 --name spring-app -d my-spring-app
spring-app: 컨테이너 이름

API 요구사항
1. 고객은 카테고리 별로 최저가격인 브랜드와 가격을 조회하고 총액이 얼마인지 확인할 수 있어야 합니다.
2. 고객은 단일 브랜드로 전체 카테고리 상품을 구매할 경우 최저가격인 브랜드와 총액이 얼마인지 확인할 수 있어야 합니다.
3. 고객은 특정 카테고리에서 최저가격 브랜드와 최고가격 브랜드를 확인하고 각 브랜드 상품의 가격을 확인할 수 있어야 합니다.
4. 운영자는 새로운 브랜드를 등록하고, 모든 브랜드의 상품을 추가, 변경, 삭제할 수 있어야 합니다.