# 회원 가입 및 비빌번호 재설정 API


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

## UseCase

> 최종 구현 기능

- [x] 유저는 회원 가입을 할 수 있음.
- [x] 회원 가입한 유저는 로그인할 수 있음.
- [x] 로그인 한 회원은 내 정보를 볼 수 있음.
    - 이메일, 닉네임, 이름, 전화번호
- [x] 비밀번호 찾기, 재설정 기능

### 문제해결
- [x] 회원 가입 전에는 전화번호 인증이 되어야함. 
  - 전화번호 인증의 경우, 외부 서비스를 이용한다고 가정하고, 응답을 mocking함.
  - 외부 서비스로 부터 전화 번호 인증 받은 회원은 token을 받는다.
- [x] 회원 가입시 받는 정보는 이메일, 닉네임, 비빌번호, 이름, 전화번호, token(인증된 전화번호)
  - token을 기반으로, 해당 인증이 정상적인지 확인한다 (mock)
- [x] 식별 가능한 모든 정보(이메일 or 전화번호) 로 로그인이 가능
- [x] 로그인이 되지 않는 상태에서 비밀번호 재설정

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