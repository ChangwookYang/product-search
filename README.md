○ 구현범위에 대한 설명  
○ 코드 빌드, 테스트, 실행 방법  
○ 기타추가정보

# 구현범위에 대한 설명
## 상품 조회 API
카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API  
단일 브랜드로 전체 카테고리 상품을 구매할 경우 최저가격인 브랜드와 총액을 조회하는 API  
특정 카테고리에서 최저가격 브랜드와 최고가격 브랜드를 확인하고 각 브랜드 상품의 가격을 확인하는 API

## 브랜드 CRUD
#### 브랜드 추가, 브랜드명 수정, 브랜드 삭제(매핑된 상품도 동시에 수정)

## 상품 CRUD
#### 상품 추가, 상품가격 수정, 상품 삭제
상품은 category, brand 당 1개만 가질 수 있다고 정의하였습니다.  
ex : 상의, A는 1개의 상품만 가질 수 있음

## 구현 설명
#### 헥사고날 아키텍쳐 구조로 구현 (web -> application -> infrastructure)
#### Spring boot + JPA(QueryDSL), H2(http://localhost:8080/h2-console), mapstruct 사용
#### Integration Test, Unit Test 구현
#### Swagger API 추가(http://localhost:8080/swagger-ui/index.html)

# 코드 빌드, 테스트, 실행 방법
#### Java version 21
#### ProductApplication.java 실행
#### Swagger API 통하여 테스트(http://localhost:8080/swagger-ui/index.html)
#### gradle > verification > test로 Test 코드 실행

#### or

#### 1. ./gradlew bootJar 실행 (프로젝트 루트에서)
#### 2. build/libs/product0218-0.0.1-SNAPSHOT.jar JAR 파일 확인
#### 3. java -jar build/libs/product0218-0.0.1-SNAPSHOT.jar 실행
