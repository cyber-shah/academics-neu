drop database if exists mass_general_hospital;
create database mass_general_hospital;
use mass_general_hospital;

create table clinic (
	clinic_no		int				not null,
    clinic_name		varchar(20) 	not null,
	street_no		int,
    street_name		varchar(20),
    town			varchar(20),
    zip				varchar(10),
    
    primary key(clinic_no),
    unique(clinic_name),
    unique(street_no, street_name, town, zip)
);

create table donor (
	donor_id			int			not null,
    first_name			varchar(20)	not null,
    last_name			varchar(20)	not null,
    date_of_birth		date		not null,
    biological_gender	enum('Male', 'Female')	not null,
    lastDonationDate	date,
    is_contestant		boolean		default(false),
    total_donations		int,
    
    primary key(donor_id),
    check ((is_contestant is true and total_donations is not null))
);

create table contest (
	clinic_no		int				not null,
    contest_name	varchar(20)		not null,
    start_date		date,	
    end_date		date,
    prize_money		int,
    
    primary key(contest_name),
    foreign key(clinic_no) references clinic(clinic_no)
);

create table staff (
	employee_number		int 			not null,
    first_name			varchar(20)		not null,
    last_name			varchar(20)		not null,
    start_date			date			not null,
    clinic_no			int,
    
    primary key (employee_number),
    foreign key(clinic_no) references clinic(clinic_no)
);

create table blood_details (
	detail_id		int 				not null,
	blood_type		enum('A', 'B',
						'AB', 'O')		not null,
    rh_factor		enum('Positive',
					'Negative')			not null,
    total_bags		int,
    
    primary key(detail_id),
    unique(blood_type, rh_factor)
    -- total_bags 
);


create table blood_bag (
	bag_id				int			not null,
    is_empty			boolean 	not null,
    detail_id			int,
    
    primary key(bag_id),
    foreign key(detail_id) references blood_details(detail_id),
    check (is_empty = true and detail_id is not null)
);    
    

create table donation (
	donor_id			int 		not null,
    clinic_no			int			not null,
    bag_id				int 		not null,
    donation_date		date		not null,
    
    primary key(donor_id, donation_date),
    foreign key(donor_id) references donor(donor_id),
    foreign key(clinic_no) references clinic(clinic_no),
    foreign key(bag_id) references blood_bag(bag_id)
);