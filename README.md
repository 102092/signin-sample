# sign-in, sign-up sample

## 개발 프레임워크
- java
    - corretto 17
- spring-boot 3.04
    - web
    - data-jpa
- h2 database
- jsonwebtoken (로그인 인증을 처리하기 위함)
- javax.xml.bind (java.lang.NoClassDefFoundError: javax/xml/bind/DatatypeConverter 에러를 해결하기 위함)
- lombok

## 실행 방법
```bash
cd signin-sample
./gradlew bootRun -Dorg.gradle.java.home={JAVA_17_HOME}
```

## 테스트 실행 방법

> 모든 테스트 실행

```bash
cd signin-sample
./gradlew clean test -Dorg.gradle.java.home={JAVA_17_HOME}
```

> request test after application running 

- `http` 내부 파일 참고

## 특별히 신경쓴 부분
- 도메인 설계 및 프로젝트 구조
- 민감정보 (이메일, 전화번호) 저장 시, 암호화하여 저장되도록.
- 테스트 작성

## API 명세서

> POST auth/signup

- body

```json
{
  "email": "test@gmail.com",
  "nick_name" : "hello",
  "password" : "password",
  "name" : "meme",
  "phone_number" : "010-1234-5678",
  "token" : "verified"
}
```

- 응답

```json
{
  "id": "1"
}
```

- token은 phone_number에 대해 외부에서 인증받았다고 가정.

> POST auth/signin

- body

```json
{
  "email": "test@gmail.com",
  "password" : "newPassword"
}

```
- `email` 대신 `phone_number` 를 입력해도 로그인은 가능.
  - `email`, `phone_number` 가 식별 가능한 정보로 가정 (unique)

 - 응답
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJpIjoxfQ.1CHbz5rG9hIK5kOUCkfnqbIXFWEY4kIQxtuBNe4L1Ka2VQdiEtb0qjBZRrgUodRfTxP0ZoPRj5gZy2Wl81izVA"
}
```

> GET /auth/info/{id}


- header
  - x-auth-token : jwttoken, auth/sigin 시 응답 받은 토근을 헤더에 명시해야함.
- param
  - {id} : userId
- 응답
```json
{
  "email": "test@gmail.com",
  "nick_name": "hello",
  "name": "meme",
  "phone_number": "010-1234-5678"
}
```

> POST auth/reset

- body
```json
{
  "password" : "newPassword",
  "phone_number" : "010-1234-5678",
  "token": "mock tokens"
}
```
- `token` 전화 번호 인증(mock) 받았을 경우 수령하는 토큰, auth/reset 시에도 검증함.

- 응답
```json
"Reset completed. Please re-login"
```