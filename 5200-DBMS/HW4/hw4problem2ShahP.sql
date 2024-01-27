drop database if exists regional_schools;
create database regional_schools;
use regional_schools;

create table school (
	school_id 			int			not null,
    street_no			int			not null,
    street_name			varchar(20)	not null,
    town				varchar(20)	not null,
    zip_code			varchar(8)	not null,
    principal_id		int,
    phone				varchar(10)	not null,
    
    primary key(school_id),
    unique(principal_id),
    unique(phone),
    unique(street_no, street_name, town, zip_code) 	-- alternate key
);

create table teacher (
	NIN				int 					not null,
    first_name		varchar(20)				not null,
    last_name		varchar(20)				not null,
    sex				enum('Male', 
					'Female', 'Other')		not null,
	qualifications	varchar(60)				not null,
	school_id		int						not null,
    is_principal	bool					default(false),
    start_date		date,
    
    primary key(NIN),
    check ( (is_principal = true) and start_date is not null), 		
    -- if principal, must have start date
    foreign key(school_id) references school(school_id)
);

-- create foregin key for principal
alter table school add constraint principal_id 
			foreign key school(principal_id) references teacher(NIN);


create table pupil (
	pupil_id 		int 				not null,
    first_name		varchar(20)			not null,
    last_name		varchar(20)			not null,
	sex				enum('Male', 
					'Female', 'Other')	not null,
	date_of_birth	date				not null,
    school_id		int					not null, -- one school only
    
    primary key(pupil_id),
    foreign key(school_id) references school(school_id)
);


create table subject (
	subject_name 	varchar(20)		not null,
    subject_level	enum('Undergraduate',
					'Graduate')		not null,
	abbreviation	varchar(10)		not null,
    
    primary key(subject_name, abbreviation)
);


create table class (
	class_number	int			not null,
    number_hours	time		not null,
	teacher_id		int			not null,
    subject_name	varchar(20)	not null,
    
    primary key (subject_name, class_number),
    -- only one subject per class, but 0 to many classes for a subject
    foreign key(subject_name) references subject(subject_name),
	-- class can have one and only one teacher
    foreign key(teacher_id) references teacher(NIN)
);

create index idx_class_number on class (class_number);

create table pupils_class(
	class_number	int			not null,
    pupil_id		int 		not null,
    primary key(class_number, pupil_id),
    foreign key(class_number) references class(class_number),
    foreign key(pupil_id) references pupil(pupil_id)
);