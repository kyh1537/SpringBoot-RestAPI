# SpringBoot-RestAPI

![Java_8](https://img.shields.io/badge/java-v1.8-red?logo=java)
![Spring_Boot](https://img.shields.io/badge/Spring_Boot-v2.4.1-green.svg?logo=spring)
![Spring_Security](https://img.shields.io/badge/Spring_Security-v5.4.2-green.svg?logo=spring)

### 0. 개요
- SpringBoot2 framework 기반의 RESTful api 프로젝트

- API Document(Swagger)
    - http://localhost:8080/swagger-ui.html
    - swagger의 버그인지는 모르겠으나 @ApiParam의 example이 동작을 안하여 swagger-ui상에서 제대로 보이지 않음.

### 1. 개발환경
- Java 8
- SpringBoot 2.4.1
- SpringSecurity 5.4.2
- Jsonwebtoken:jjwt:0.9.1
- JPA, MySql, Gradle
- Intellij Community

### 2. DDL
- DB 연동이 되어 있다면 프로젝트 실행시 기본 생성

- create table user (
       idx bigint not null auto_increment,
        mail varchar(100) not null,
        name varchar(20) not null,
        nick_name varchar(30) not null,
        phone_number varchar(20) not null,
        pw varchar(30) not null,
        reg_date datetime,
        sex varchar(255),
        update_date datetime,
        primary key (idx)
    ) engine=InnoDB;
    
- create table user_orders (
       order_number varchar(12) not null,
        name varchar(100) not null,
        reg_date datetime,
        uidx bigint not null,
        update_date datetime,
        primary key (order_number)
    ) engine=InnoDB;
    
- create table user_roles (
       user_idx bigint not null,
        roles varchar(255)
    ) engine=InnoDB;
    
       
- alter table user add constraint UK unique (mail);

- alter table user_orders 
       add constraint FK
       foreign key (uidx) 
       references user (idx);

- alter table user_roles 
       add constraint FK1
       foreign key (user_idx) 
       references user (idx);
       
 ### 3. 구현 API
- 회원 가입
- 회원 로그인(인증)
- 회원 로그아웃(미구현)
  - 해당 기능을 구현하려면 로그인시 발급한 토큰을 redis에 저장하고 로그아웃시에 redis에서 지우는 방식으로 진행해야 될 듯 합니다.
- 단일 회원 상세 정보 조회
- 단일 회원의 주문 목록 조회
- 여러 회원 목록 조회 :
    - 페이지네이션으로 일정 단위로 조회합니다.
    - 이름, 이메일을 이용하여 검색 기능이 필요합니다.
    - 각 회원의 마지막 주문 정보
    
  #### 회원 권한의 경우 일반 회원인지 관리자인지 명확하게 기재되어 있지 않지만 회원 정보 및 주문 목록 조회 API를 보아 관리자 권한이 필요한 API인 듯하여 기본 가입시 ADMIN 권한으로 생성하였습니다.
