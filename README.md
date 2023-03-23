# signin-sample

## 개발 프레임워크
- java
    - corretto 17
- spring-boot 3.04
    - web
    - data-jpa
    - configuration-processor
- h2 database
- lombok

## 실행 방법

## 테스트 실행 방법

## API 명세서

## UseCase
- [ ] 유저는 회원 가입을 할 수 있음.
- [ ] 회원 가입한 유저는 로그인할 수 있음.
- [ ] 로그인 한 회원은 내 정보를 볼 수 있음.
  - 이메일, 닉네임, 이름, 전화번호
- [ ] 비밀번호 찾기, 재설정 기능

## 문제해결
- [v] 회원 가입 전에는 전화번호 인증이 되어야함. (전화번호 인증의 경우 mock 구현)
  - 인증 받은 회원은 token을 받는다. (mock)
- [ ] 회원 가입시 받는 정보는 이메일, 닉네임, 비빌번호, 이름, 전화번호, token(인증된 전화번호)
- [ ] 식별 가능한 모든 정보(이메일 or 전화번호) 로 로그인이 가능
- [ ] 로그인이 되지 않는 상태에서 비밀번호 재설정
- [ ] 전화번호 인증 후에 비밀전호 재설정이 가능해야함