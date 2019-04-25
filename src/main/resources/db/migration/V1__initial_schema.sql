create table if not exists customer(
    customerId int auto_increment,
    firstName varchar(32) not null,
    lastName varchar(32) not null,
    username varchar(32) not null,
    password varchar(32),
    emailAddress varchar(64),
    primary key (customerId)
);

create table if not exists product(
    productId int auto_increment,
    name varchar(32),
    description varchar(64),
    price int,
    weight double,
    productCategoryId int not null,
    supplierId int not null,
    imageURL varchar(32),
    primary key (productId)
);

create table if not exists productCategory(
    productCategoryId int auto_increment,
    name varchar(32) not null,
    description varchar(64),
    primary key (productCategoryId)
);

create table if not exists supplier(
    supplierId int auto_increment,
    name varchar(32) not null,
    primary key (supplierId)
);

create table if not exists stock(
    productId int not null,
    locationId int not null,
    quantity int
);

create table if not exists location(
    locationId int auto_increment,
    name varchar(32) not null,
    country varchar(32) not null,
    city varchar(32) not null,
    county varchar(32) not null,
    streetAddress varchar(32) not null,
    primary key (locationId)
);

create table if not exists orders(
    orderId int auto_increment,
    shippedFrom int not null,
    customerId int not null,
    createdAt date not null,
    country varchar(32) not null,
    city varchar(32) not null,
    county varchar(32) not null,
    streetAddress varchar(32) not null,
    primary key (orderId)
);

create table if not exists orderDetail(
    orderId int not null,
    productId int not null,
    quantity int
);

create table if not exists revenue(
    revenueId int auto_increment,
    locationId int not null,
    date date,
    sum int,
    primary key (revenueId)
);

alter table orders
add foreign key (customerId) references customer(customerId);

alter table orders
add foreign key (shippedFrom) references location(locationId);

alter table revenue
add foreign key (locationId) references location(locationId);

alter table stock
add foreign key (locationId) references location(locationId);

alter table stock
add foreign key (productId) references product(productId);

alter table orderDetail
add foreign key (orderId) references orders(orderId);

alter table orderDetail
add foreign key (productId) references product(productId);

alter table product
add foreign key (productCategoryId) references productCategory(productCategoryId);

alter table product
add foreign key (supplierId) references supplier(supplierId);

