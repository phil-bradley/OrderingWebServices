
drop table address;
drop table country;

create table country (
    id int primary key,
    isocode char(2),
    name varchar(255),
    printablename varchar(255),
    iso3 char(3),
    created timestamp,
    lastupdated timestamp
);

create table address (
    id serial primary key,
    STREET1 varchar(255), 
    STREET2 varchar(255), 
    STATE varchar(32),  
    ZIPCODE varchar(32), 
    created timestamp,
    lastupdated timestamp,
    COUNTRYID int references country(id)
);