drop database if exists gymapp;
create database gymapp;

use gymapp;

drop table if exists role;
create table role(
    id int auto_increment primary key,
    description varchar(150) not null,
    code varchar(100) not null,
    visible boolean not null,
    creation_date datetime not null,
    modification_date datetime
);

drop table if exists permission;
create table permission(
    id int auto_increment primary key,
    description varchar(150) not null,
    code varchar(100) not null,
    visible boolean not null,
    creation_date datetime not null,
    modification_date datetime
);

drop table if exists company;
create table company(
    id int auto_increment primary key,
    description varchar(100) not null,
    description_long varchar(200) not null,
    logo varchar(100),
    status boolean not null,
    creation_date datetime not null,
    modification_date datetime not null
);

drop table if exists user;
create table user(
    id int auto_increment primary key,
    username varchar(20) unique not null,
    password varchar(20) not null,
    name varchar (150) not null,
    last_name varchar(150) not null,
    email varchar(100) not null,
    phone varchar(15),
    gender ENUM("MEN", "WOMEN"),
    id_role int not null,
    id_company int not null,
    status boolean not null,
    creation_date datetime not null,
    modification_date datetime,
    foreign key (id_role) references role(id) on delete cascade on update cascade,
    foreign key (id_company) references company(id) on delete cascade on update cascade
);

drop table if exists role_permission;
create table role_permission(
    id int auto_increment primary key,
    id_role int not null,
    id_permission int not null,
    status boolean not null,
    creation_date datetime not null,
    modification_date datetime,
    foreign key (id_role) references role(id) on delete cascade on update cascade,
    foreign key (id_permission) references permission(id) on delete cascade on update cascade
);

drop table if exists membership;
create table membership(
    id int auto_increment primary key,
    id_user_register int not null,
    id_user_client int not null,
    id_company int not null,
    status ENUM("ACTIVE", "INACTIVE", "ON_PAUSE") not null,
    creation_date datetime not null,
    modification_date datetime not null,
    foreign key (id_user_register) references user(id),
    foreign key (id_user_client) references user(id),
    foreign key (id_company) references company(id)
);

drop table if exists promotion_type;
create table promotion_type(
    id int auto_increment primary key,
    description varchar(150) not null,
    is_user_relation boolean not null,
    is_regular boolean not null
);

drop table if exists promotion;
create table promotion(
    id int auto_increment primary key,
    title varchar(200) not null,
    duration int not null,
    duration_type ENUM("YEAR", "MONTH", "DAY") not null,
    id_promotion_type int not null,
    id_company int not null,
    status boolean not null,
    price decimal not null,
    start_date datetime not null,
    end_date datetime not null,
    creation_date datetime not null,
    modification_date datetime not null,
    foreign key (id_promotion_type) references promotion_type(id),
    foreign key (id_company) references company(id)
);

drop table if exists membership_period;
create table membership_period(
    id int auto_increment primary key,
    id_membership int not null,
    id_promotion int,
    start_date datetime not null,
    end_date datetime not null,
    creation_date datetime not null,
    modification_date datetime not null,
    foreign key (id_membership) references membership(id),
    foreign key (id_promotion) references promotion(id)
);

drop table if exists promotion_user_relation;
create table promotion_user_relation(
    id int auto_increment primary key,
    id_membership_period int not null,
    id_user_client int not null,
    foreign key (id_membership_period) references membership_period(id),
    foreign key (id_user_client) references user(id)
);

insert into role(description, code, visible, creation_date, modification_date) values('Administrador', 'ADMIN', FALSE, curdate(), curdate());
insert into role(description, code, visible, creation_date, modification_date) values('Usuario', 'USER', TRUE, curdate(), curdate());
insert into role(description, code, visible, creation_date, modification_date) values('Trainer', 'TRAINER', TRUE, curdate(), curdate());
insert into role(description, code, visible, creation_date, modification_date) values('Cliente', 'CUSTOMER', TRUE, curdate(), curdate());

insert into permission(description, code, visible, creation_date, modification_date) values('Ver', 'view', TRUE, curdate(), curdate());
insert into permission(description, code, visible, creation_date, modification_date) values('Editar', 'edit', TRUE, curdate(), curdate());
insert into permission(description, code, visible, creation_date, modification_date) values('Guardar', 'save', TRUE, curdate(), curdate());
insert into permission(description, code, visible, creation_date, modification_date) values('Reportes', 'sheet', TRUE, curdate(), curdate());

insert into company(description, description_long, logo, status, creation_date, modification_date) values('Devs', 'Developers', 'devs.jpeg', TRUE, curdate(), curdate());
insert into company(description, description_long, logo, status, creation_date, modification_date) values('Gym 1', 'Gimnasio Numero 1', 'gym1.jpeg', TRUE, curdate(), curdate());
insert into company(description, description_long, logo, status, creation_date, modification_date) values('Gym 2', 'Gimnasio Numero 2', 'gym2.jpeg', TRUE, curdate(), curdate());

insert into user(username, password, name, last_name, email, phone, gender, id_role, id_company, status, creation_date, modification_date) values('admin', '12345678', 'Piero', 'Sanchez', 'sanchezpiero96@gmail.com', '960766111', 'MEN', 1, 1, TRUE, curdate(), curdate());

insert into promotion_type(description, is_user_relation, is_regular) values('2X1', TRUE, FALSE);
insert into promotion_type(description, is_user_relation, is_regular) values('REGULAR', FALSE, TRUE);

