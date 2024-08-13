create database inventory_management;

\connect inventory_management;

create table products (
    id int primary key generated always as identity,
    name character varying(50) not null,
    description character varying(255) not null,
    price numeric(12,2) not null,
    quantity int not null
);

insert into products (name, description, price, quantity) values ('Laptop', 'A high performance laptop', 999.99, 10),
                                                                 ('Smartphone', 'Latest model smartphone', 699.99, 25),
                                                                 ('Tablet', 'Lightweight tablet with long battery life', 399.99, 15);