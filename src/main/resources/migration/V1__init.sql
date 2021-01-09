create table user
(
    idx          bigint       not null auto_increment,
    mail         varchar(100) not null,
    name         varchar(20)  not null,
    nick_name    varchar(30)  not null,
    phone_number varchar(20)  not null,
    pw           varchar(30)  not null,
    reg_date     datetime,
    sex          varchar(255),
    update_date  datetime,
    primary key (idx)
) engine = InnoDB;

create table user_orders
(
    order_number varchar(12)  not null,
    name         varchar(100) not null,
    reg_date     datetime,
    uidx         bigint       not null,
    update_date  datetime,
    primary key (order_number)
) engine = InnoDB;

create table user_roles
(
    user_idx bigint not null,
    roles    varchar(255)
) engine = InnoDB;