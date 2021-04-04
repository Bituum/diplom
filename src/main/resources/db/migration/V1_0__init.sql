create table other_thing
(
    id          int auto_increment
        primary key,
    name        varchar(255) not null,
    description varchar(255) not null,
    amount      int          not null
);

create table product_properties
(
    name                  varchar(255) not null,
    `shelf life`          date         not null,
    amount                int          not null,
    cost                  int          not null,
    ` calories_per_piece` int          not null,
    products_id           int          not null
        primary key
);

create table products
(
    id int auto_increment
        primary key
);

create table roles
(
    id        int auto_increment
        primary key,
    role_name varchar(255) not null
);

create table storage
(
    id             int not null
        primary key,
    product_id     int not null,
    other_things   int not null,
    other_thing_id int not null,
    constraint storage_other_thing_id_fk
        foreign key (other_thing_id) references other_thing (id),
    constraint storage_products_id_fk
        foreign key (product_id) references products (id)
);

create table dishes
(
    id                    int auto_increment
        primary key,
    name_of_dish          int  not null,
    time_to_cooking       time not null,
    products_from_storage int  null,
    constraint dishes_storage_id_fk
        foreign key (products_from_storage) references storage (id)
);

create table user
(
    id     int auto_increment
        primary key,
    login  varchar(255) not null,
    passwd varchar(255) not null
);

create table menu
(
    id         int auto_increment
        primary key,
    dish       int not null,
    cashier_id int not null,
    constraint menu_dishes_id_fk
        foreign key (dish) references dishes (id),
    constraint menu_user_id_fk
        foreign key (cashier_id) references user (id)
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

