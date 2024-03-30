create table message
(
    id         bigint     not null auto_increment,
    created_at datetime(6),
    user_id    bigint,
    updated_at datetime(6),
    filename   varchar(255),
    text       varchar(2084) not null,
    title      varchar(255) not null,
    primary key (id)
);

create table message_seq
(
    next_val bigint
);

insert into message_seq
values (1);

create table user
(
    active     bit         not null,
    age        integer,
    created_at datetime(6),
    id         bigint      not null auto_increment,
    updated_at datetime(6),
    email      varchar(255) not null,
    location   varchar(255),
    name       varchar(255),
    password   varchar(255) not null,
    photo_url  varchar(255),
    surname    varchar(255),
    primary key (id)
);

create table user_role
(
    user_id bigint not null,
    roles   enum ('USER','ADMIN')
);

alter table message
    add constraint message_user_fk foreign key (user_id) references user (id);

alter table user_role
    add constraint user_role_user_fk foreign key (user_id) references user (id);