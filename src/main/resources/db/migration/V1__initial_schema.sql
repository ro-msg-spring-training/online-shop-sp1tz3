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
    stockId int not null,
    productId int not null,
    locationId int not null,
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

create table if not exists stock_product(
    id int auto_increment,
    stockId int,
    productId int,
    primary key (id)
);

create table if not exists stock_location(
    id int auto_increment,
    stockId int,
    locationId int,
    primary key (id)
);

create table if not exists orderDetail_order(
    id int auto_increment,
    orderDetailId int,
    orderId int,
    primary key (id)
);

create table if not exists orderDetail_product(
    id int auto_increment,
    orderDetailId int,
    productId int,
    primary key (id)
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

alter table orders
add foreign key (addressId) references address(addressId);

alter table location
add foreign key (addressId) references address(addressId);

