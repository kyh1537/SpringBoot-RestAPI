alter table user add constraint UK unique (mail);

alter table user_orders
       add constraint FK
       foreign key (uidx)
       references user (idx);

alter table user_roles
       add constraint FK1
       foreign key (user_idx)
       references user (idx);