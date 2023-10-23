drop database if exists crime_db2022shahp;
create database crime_db2022shahp;
use crime_db2022shahp;


create table district (
	district_code 		varchar(5)		not null,
    district_name		varchar(20)		not null,
    
    primary key(district_code),
    unique(district_code, district_name)
);


create table neighbourhood (
	n_name					varchar(20)		not null,
    district_code			varchar(5)		not null,
    
    primary key(district_code, n_name),
    foreign key(district_code) references district(district_code)
);

create table offense_code (
	o_code				varchar(5)			not null,
    description			varchar(50)			not null,
    
    primary key(o_code)
);

drop table street_locations;
-- create table street_locations (
-- 	street				varchar(20)			not null,
--     latitude 			decimal(10,8) 		not null,
-- 	longitude 			decimal(11,8) 		not null,
--     
--     primary key(latitude, longitude, street)
-- );
-- create index latitude on street_locations(latitude);
-- create index longitude on street_locations(longitude);


drop table incidents;
create table incidents (
	incident_id			varchar(20)			not null,
    o_code				varchar(5)			not null,
    shooting			boolean				not null		default(false),
    
    district_code		varchar(5)			not null,
    reporting_area		varchar(10),
    latitude 			decimal(10,8) 		not null,
	longitude 			decimal(11,8) 		not null,
    street			 	varchar(20)			not null,
     
    occurred_date		datetime,
    
    primary key(incident_id),
    foreign key(district_code) references district(district_code),
    foreign key (o_code) references offense_code(o_code)
);

select * from district; 
select * from neighbourhood; 
select * from offense_code;
select * from street_locations;
select * from incidents;