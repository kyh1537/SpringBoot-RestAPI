# SpringBoot-RestAPI

![Java_8](https://img.shields.io/badge/java-v1.8-red?logo=java)
![Spring_Boot](https://img.shields.io/badge/Spring_Boot-v2.4.1-green.svg?logo=spring)
![Spring_Security](https://img.shields.io/badge/Spring_Security-v5.4.2-green.svg?logo=spring)

### 0. 개요
- SpringBoot2 framework 기반의 RESTful api 프로젝트

- Swagger
    - http://localhost:8080/swagger-ui.html

### 1. 개발환경
- Java 8
- SpringBoot 2.4.1
- SpringSecurity 5.4.2
- Jsonwebtoken:jjwt:0.9.1
- JPA, MySql
- Intellij Community

### 2. DDL
- DB 연동이 되어 있다면 프로젝트 실행시 기본 생성

create table user (
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
    
create table user_orders (
       order_number varchar(12) not null,
        name varchar(100) not null,
        reg_date datetime,
        uidx bigint not null,
        update_date datetime,
        primary key (order_number)
    ) engine=InnoDB;
    
create table user_roles (
       user_idx bigint not null,
        roles varchar(255)
    ) engine=InnoDB;
    
       
alter table user add constraint UK unique (mail);

alter table user_orders 
       add constraint FK
       foreign key (uidx) 
       references user (idx);

alter table user_roles 
       add constraint FK1
       foreign key (user_idx) 
       references user (idx);

