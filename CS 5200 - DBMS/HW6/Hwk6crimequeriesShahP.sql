drop database if exists crime_db2022ShahP;
create database crime_db2022ShahP;
use crime_db2022ShahP;


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
    description			varchar(50)			not null
);


create table incidents (
	incident_id			int			auto_increment,
    o_code				varchar(5)			not null,
    shooting			boolean				not null		default(false),
    
    district_code		varchar(5)			not null,
    reporting_area		varchar(10),
    street				varchar(20),
    latitude 			decimal(10,8),
	longitude 			decimal(11,8),
    
    occurred_date		datetime,
    
    primary key(incident_id),
    foreign key(district_code) references district(district_code)
);

create index code_idx on offense_code(o_code);

alter table incidents 
add constraint fk_o_code
foreign key (o_code) references offense_code(o_code);






