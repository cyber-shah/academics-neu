-- drop if exists
drop database if exists stellar_market;
create database stellar_market;
use stellar_market;

-- customers information should be shared amongst many chains
create table stellar_chain (
	chain_id 		int 			not null,
    street_number	int				not null,
    street_name		varchar(64)		not null,
    zip_code	    varchar(7)		not null,
    state			varchar(2)		not null,
    opening_time	time,
    closing_time	time,
    manager_id		int, 	-- can be zero
    primary key(chain_id),
	unique (street_number, street_name, zip_code, state),
    unique(manager_id)
    -- has one and only one manager, which is unique
);

create table staff (
	staff_no		int				not null,
    first_name		varchar(20)		not null,
    last_name	 	varchar(20) 	not null,
    chain_id		int,								-- works at a specific chain can be zero
    is_manager		bool			default(false),
    start_date		date,
    primary key(staff_no),
    foreign key(chain_id) references stellar_chain(chain_id),
    check ( (is_manager = true) and start_date is not null)
);

-- create foreign keys ------
alter table stellar_chain add constraint fk_manager
    foreign key(manager_id) references staff(staff_no);


create table product_genre (
	genre 		enum('bakery', 'non-perishable', 		
				'produce', 'meat', 'dairy products')		not null,
	primary key(genre)
);

create table product_type (
	unique_name		varchar(20)		not null,
    genre			enum('bakery', 'non-perishable', 		
				'produce', 'meat', 'dairy products')		not null,
    total_stock		int				not null,
    primary key(unique_name),
    foreign key(genre) references product_genre(genre),
    foreign key(sold_at) references stellar_chain(chain_id)
);

create table product_sold_at (
	unique_name		varchar(20)		not null,
    chain_id		int				not null,
    primary key(unique_name, chain_id),
    foreign key(unique_name) references product_type(unique_name),
    foreign key(chain_id) references stellar_chain(chain_id)
);

create table customer (
	customer_id		int				not null 		auto_increment,
	first_name 		varchar(20)		not null,
    last_name	 	varchar(20) 	not null,
    street_number	int				not null,
    street_name		varchar(64)		not null,
    zip_code	    varchar(7)		not null,
    state			varchar(2)		not null,
    chain_id		int				not null, -- chain they were registered at -- 0 to many instead?
    primary key(customer_id),
    foreign key(chain_id) references stellar_chain(chain_id)
);

create table credit_card (
	card_id			int									not null		auto_increment,
    card_type		enum('visa', 'amex', 'mastercard')	not null,
    card_number		int									not null,
    expiration_date	date								not null,
    primary key(card_id)
);

create table customer_card (
	card_id			int			not null,
    customer_id		int			not null,
    primary key(card_id, customer_id),
    foreign key(customer_id) references customer(customer_id),
    foreign key(card_id) references credit_card(card_id)
);

create table chain_order (
	order_id					int			not null 		auto_increment,
	customer_id					int 		not null,
    chain_id					int 		not null,
    customer_identification		enum('credit card', 'driving license', 'passport') not null,
    card_id						int			not null,
    order_date					date		not null,
    primary key(order_id),
    foreign key(customer_id) references customer(customer_id),
    foreign key(card_id) references credit_card(card_id),
    foreign key(chain_id) references stellar_chain(chain_id)
);

create table product_details (
	order_id			int 			not null,
    product				varchar(20)		not null,
    quantity			int				not null 		default(1),
    primary key(order_id, product),
    foreign key(order_id) references chain_order(order_id),
    foreign key(product) references product_type(unique_name)
);




