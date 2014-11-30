
drop table orderdetail;
drop table orderheader;
drop table product;
drop table address;
drop table party;
drop table currency;
drop table country;


create table country (
    id int primary key,
    isocode char(2) unique,
    name varchar(255),
    printablename varchar(255),
    iso3 char(3) unique,
    created timestamp,
    lastupdated timestamp
);

create table currency (
    code char(3) primary key,
    name varchar(255),
    created timestamp,
    lastupdated timestamp
);


create table party (
    id serial primary key,
    name varchar(255),
    phone varchar(32),
    fax varchar(32),
    email varchar(255)
);

create table address (
    id serial primary key,
    STREET1 varchar(255), 
    STREET2 varchar(255), 
    STATE varchar(32),  
    ZIPCODE varchar(32), 
    created timestamp,
    lastupdated timestamp,
    COUNTRYID int references country(id),
    PARTYID int references party(id)
);

create table product (
    id serial primary key,
    skucode varchar(32) unique,
    title varchar(255),
    description varchar(2000),
    unitprice decimal(20,2)
);

create table orderheader (
    id serial primary key,
    created timestamp ,
    submitted timestamp ,
    buyerid bigint references party(id)
)

create table orderdetail (
    id serial primary key,
    created timestamp ,
    orderid bigint references orderheader(id),
    skucode varchar(32),
    description varchar(255),
    unitprice decimal(20,2),
    quantity int,
    linetotal decimal(12,2)
)


