create table dishes
(
    id              int auto_increment
        primary key,
    name_of_dish    varchar(100) not null,
    time_to_cooking time         not null
);

create table product_properties
(
    name               varchar(255) not null,
    shelf_life         date         not null,
    amount             int          not null,
    cost               int          not null,
    calories_per_piece int          not null,
    products_id        int          not null
        primary key
);

create table products
(
    id                int auto_increment
        primary key,
    small_description varchar(50) not null
);

create table product_dishes
(
    id         int auto_increment
        primary key,
    product_id int not null,
    dish_id    int null,
    constraint product_dishes_dishes_id_fk
        foreign key (dish_id) references dishes (id),
    constraint product_dishes_products_id_fk
        foreign key (product_id) references products (id)
);

create table roles
(
    id        int auto_increment
        primary key,
    role_name varchar(255) not null
);

create table user
(
    id     int auto_increment
        primary key,
    login  varchar(255) not null,
    passwd varchar(255) not null
);

create table user_properties
(
    user_id     int auto_increment
        primary key,
    name        varchar(255) not null,
    second_name varchar(255) not null,
    surname     varchar(255) not null,
    sex         varchar(1)   not null,
    salary      int          null
);

create table user_roles
(
    id      int auto_increment
        primary key,
    user_id int not null,
    role_id int not null,
    constraint user_roles_roles_id_fk
        foreign key (role_id) references roles (id),
    constraint user_roles_user_id_fk
        foreign key (user_id) references user (id)
);

create table user_worktime
(
    user_id    int  not null,
    start_time time not null,
    end_time   time not null,
    constraint user_worktime_user_id_uindex
        unique (user_id),
    constraint user_worktime_user_id_fk
        foreign key (user_id) references user (id)
);

alter table user_worktime
    add primary key (user_id);

