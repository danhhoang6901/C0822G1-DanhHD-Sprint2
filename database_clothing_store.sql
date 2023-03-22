drop database if exists clothing_store;
create database clothing_store;
use clothing_store;

create table `user` (
id int primary key auto_increment,
`name` varchar(50),
email varchar(50),
`password` varchar(50),
date_of_birth date,
phone_number varchar(20),
username varchar(50),
avatar varchar(500),
address varchar(50),
gender bit(1)
);

create table `role` (
id int primary key auto_increment,
`name` varchar(50)
);

create table user_role (
id_user int,
id_role int,
foreign key(id_user) references `user`(id),
foreign key(id_role) references `role`(id),
primary key (id_user,id_role)
);

create table bill (
id int primary key auto_increment,
buy_date date,
detail varchar(255),
quantity int,
payment_method varchar(255),
id_user int,
foreign key(id_user) references `user`(id)
);

create table trademark (
id int primary key auto_increment,
`name` varchar(50)
);

create table size (
id int primary key auto_increment,
`name` varchar(50)
);

create table origin (
id int primary key auto_increment,
`name` varchar (50)
);

create table style (
id int primary key auto_increment,
`name` varchar(50)
);

create table category (
id int primary key auto_increment,
`name` varchar(50)
);

create table product (
id int primary key auto_increment,
code_product varchar(10),
`name` varchar(50),
image varchar(255),
color varchar(50),
`description` varchar(255),
quantity int,
price double,
material varchar(50),
washing_instructions varchar(255),
id_origin int,
id_trademark int,
id_size int,
id_style int,
id_category int,
foreign key(id_category) references category(id),
foreign key(id_trademark) references trademark(id),
foreign key(id_origin) references origin(id),
foreign key(id_style) references style(id),
foreign key(id_size) references size(id)
);

create table bill_history (
id int primary key auto_increment,
id_product int,
id_bill int,
foreign key(id_product) references product(id),
foreign key(id_bill) references bill(id)
);

create table warahouse (
id int primary key auto_increment,
date_imports date,
quantity int,
id_product int,
foreign key(id_product) references product(id)
);