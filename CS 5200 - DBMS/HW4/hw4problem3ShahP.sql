drop database if exists federal_parks;
create database federal_parks;
use federal_parks;

create table global_location (
	location_id		int			not null,
    latitude		double		not null,
    longitude		double		not null,
    
	primary key(location_id),
    unique(latitude, longitude)
);

create table territory (
	territory_id		int								not null,
    name				varchar(20)						not null,
    type				enum('state',
						'official US territory')		not null,
    location_id			int								not null,
    adjacent_territory	int,
    
	primary key(territory_id),
    foreign key(location_id) references global_location(location_id),
    foreign key(adjacent_territory) references territory(territory_id),
    unique(location_id)
);

create table national_park (
	park_name 		varchar(20)			not null,
    directions		varchar(62),
    description		varchar(62),
    location_id		int					not null,
    territory_id	int					not null,
    
    primary key(park_name),
    foreign key(location_id) references global_location(location_id),
    foreign key(territory_id) references territory(territory_id),
    unique(location_id)
);

create table accomodation (
	accomodation_id	int 			not null,
    name			varchar(20)		not null,
    directions		varchar(64),
    cost_per_night	int,
    phone_number	varchar(10)		not null,
    type			enum('campsite', 'hotel'),
    park_name		varchar(20)		not null,
    location_id		int				not null,
    
    primary key(accomodation_id),
    unique(phone_number),
    foreign key(park_name) references national_park(park_name),
    foreign key(location_id) references global_location(location_id),
    unique(location_id)
);

create table site (
	site_id			int				not null,
	unique_name		varchar(20)		not null,
    directions		varchar(62),
    description		varchar(62),
    location_id		int				not null,
    park_name		varchar(20)		not null,
    
    primary key(site_id), 												-- created this to be able to be referenced by image
    unique (unique_name),
    foreign key(location_id) references global_location(location_id),
    foreign key(park_name) references national_park(park_name),
    unique(location_id)
);

create table photo (
	photo_id		int 		not null,
    site_id			int,
    park_name		varchar(20),
    accomodation_id	int,

	primary key (photo_id),
    check ( (site_id IS NOT NULL AND park_name IS NULL AND accommodation_id IS NULL) OR
			(site_id IS NULL AND park_name IS NOT NULL AND accommodation_id IS NULL) OR 
			(site_id IS NULL AND park_name IS NULL AND accommodation_id IS NOT NULL))  
);