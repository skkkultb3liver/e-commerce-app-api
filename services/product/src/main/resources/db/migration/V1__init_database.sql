create table if not exists category
(
    id bigserial not null primary key,
    name varchar(255),
    description varchar(255),
    slug varchar(255)
);

create table if not exists product
(
    id bigserial not null primary key,
    title varchar(255),
    description varchar(255),
    quantity integer,
    price numeric(38, 2),
    category_id bigserial constraint fk1zzz references category
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;