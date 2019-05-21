create table if not exists customer(
    customerId int auto_increment,
    firstName varchar(32) not null,
    lastName varchar(32) not null,
    username varchar(32) not null,
    password varchar(100),
    emailAddress varchar(64),
    primary key (customerId)
);

create table if not exists product(
    productId int auto_increment,
    name varchar(32),
    description varchar(64),
    price decimal(19,2),
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
    stockId int auto_increment,
    productId int,
    locationId int,
    quantity int,
    primary key (stockId)
);

create table if not exists location(
    locationId int auto_increment,
    name varchar(32) not null,
    addressId int,
    primary key (locationId)
);

create table if not exists orders(
    orderId int auto_increment,
    shippedFrom int not null,
    customerId int not null,
    createdAt timestamp not null,
    addressId int,
    primary key (orderId)
);

create table if not exists orderDetail(
    orderDetailId int auto_increment,
    orderId int not null,
    productId int not null,
    quantity int,
    primary key (orderDetailId)
);

create table if not exists revenue(
    revenueId int auto_increment,
    locationId int not null,
    date date,
    sum decimal(19,2),
    primary key (revenueId)
);

create table if not exists address(
    addressId int auto_increment,
    country varchar(32) not null,
    city varchar(32) not null,
    county varchar(32) not null,
    streetAddress varchar(32) not null,
    primary key (addressId)
);


alter table orders
add foreign key (customerId) references customer(customerId) on delete cascade;

alter table orders
add foreign key (shippedFrom) references location(locationId) on delete cascade;

alter table revenue
add foreign key (locationId) references location(locationId) on delete cascade;

alter table orderDetail
add foreign key (orderId) references orders(orderId) on delete cascade;

alter table orderDetail
add foreign key (productId) references product(productId) on delete cascade;

alter table product
add foreign key (productCategoryId) references productCategory(productCategoryId) on delete cascade;

alter table product
add foreign key (supplierId) references supplier(supplierId) on delete cascade;

alter table orders
add foreign key (addressId) references address(addressId) on delete cascade;

alter table location
add foreign key (addressId) references address(addressId) on delete cascade;

alter table stock
add foreign key (productId) references product(productId) on delete cascade;

alter table stock
add foreign key (locationId) references location(locationId) on delete cascade;

