drop database if exists crime_db2022shahp;
create database crime_db2022shahp;
use crime_db2022shahp;

drop table district;
create table district (
	district_code 		varchar(10),
    district_name		varchar(50),
    
    primary key(district_code),
    unique(district_code, district_name)
);
select * from district; 


drop table neighbourhood;
create table neighbourhood (
	n_name					varchar(50)		not null,
    district_code			varchar(10)		not null,
    
    primary key(district_code, n_name),
    foreign key(district_code) references district(district_code)
);
select * from neighbourhood; 


drop table offense_code;
create table offense_code (
	o_code				int						not null,
    description			varchar(200)			not null,
    
    primary key(o_code)
);
select * from offense_code;


drop table incidents;
create table incidents (
	incident_id			varchar(30)			not null,
    o_code				int					not null,
    shooting			boolean				not null		default(false),
    
    district_code		varchar(10)			not null,
    reporting_area		varchar(20),
    latitude 			decimal(10,8) 		not null,
	longitude 			decimal(11,8) 		not null,
    street			 	varchar(100)		not null,
     
    occurred_date		datetime,
    
    primary key(incident_id),
    foreign key(district_code) references district(district_code),
    foreign key (o_code) references offense_code(o_code)
);
select * from incidents;