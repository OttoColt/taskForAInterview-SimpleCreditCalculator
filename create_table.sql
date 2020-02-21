CREATE TABLE users
(
    id         serial      not null,
    login      varchar(45) not null,
    password   varchar(45) not null,
    first_name varchar(45) not null,
    last_name  varchar(45) not null,
    UNIQUE (login),
    PRIMARY KEY (id)
);
CREATE TABLE schedule_type
(
    id   serial      not null,
    name varchar(45) not null,
    PRIMARY KEY (id)
);

CREATE TABLE condition
(
    id             serial not null,
    initial_amount int    not null,
    final_amount   int    not null,
    period         int    not null,
    PRIMARY KEY (id)
);

CREATE TABLE products
(
    id                  serial      not null,
    to_user_id          int references users (id),
    name                varchar(45) not null,
    to_schedule_type_id int         not null references schedule_type (id),
    percent             int         not null,
    to_condition_id     int references condition (id),
    PRIMARY KEY (id)
);

insert into schedule_type(name)
values ('аннуитетный');
insert into schedule_type(name)
values ('дифференцированный');

insert into condition(initial_amount, final_amount, period)
values (1000, 1100, 12);
insert into condition(initial_amount, final_amount, period)
values (1000, 1200, 24);
insert into condition(initial_amount, final_amount, period)
values (1000, 1300, 36);
insert into condition(initial_amount, final_amount, period)
values (1000, 1400, 48);


insert into users (first_name, last_name, login, password)
values ('Аня', 'Иванова', 'ai', 'ai');

insert into products (to_user_id, name, to_schedule_type_id, percent, to_condition_id)
values (null, 'ипотечный', 2, 10, null);

