drop database if exists sweet_stuff;
create database sweet_stuff;
use sweet_stuff;

create table bakery_chain (
	chain_id 		int 			primary key,
    name 			varchar(64) 	not null unique,
    street_no		int 			not null,
    street_name		varchar(64)		not null,
    town 			varchar(64) 	not null,
    zip_code		varchar(5)		not null,
    unique ( street_no, street_name, town, zip_code)
);

create table customer (
	customer_id		char(10)			primary key,
    first_name		varchar(32)			not null,
    last_name		varchar(32)			not null
);

-- could be seperate tables instead as super and sub classes
create table baked_good (
	bid 				int 				auto_increment,
    baked_type 			enum('cookie', 'cupcake', 'brownie'),
    calories			int,
    description 		varchar(64),
    gluten_free			bool				default false,
    vegan 				bool				default false,
    cookie_flavor 		enum('chocolate chip', 'snickerdoodle'),
    cake_flavor			enum('chocolate', 'vanilla', 'red velvet'),
    frosting_flavor		enum('chocolate', 'vanilla', 'creamcheese'),
    
    check	((baked_type = 'cookie' and cookie_flavor is not null) or
			(baked_type = 'cupcake' and cake_flavor is not null and frosting_flavor is not null))
);
-- need to add more checks

-- even though this was a weak entity, when you move into logical model,
-- it MUST have a primary key.
create table cust_order (
	chain_id 			int,
    order_date			date,
    seq_number			int,
    pickup_date			date			not null,
    cust_id				char(10)		not null,
    
    primary key (chain_id, order_date, seq_number),
    foreign key (chain_id) 		references bakery_chain(chain_id) 		on update cascade on delete cascade,
    foreign key (cust_id) 		references customer (customer_id) 		on update cascade on delete cascade
);

-- multiple tuples represented as a list where everything will have the same ids except the baked goods id, bid.
-- there are NO LISTS in relational data model.
-- there would be an entry in this table only if it consists of a bID
-- 5		chocochip
-- 3		snikdoodle
-- 12 		brownies
create table order_baked_good (
	order_bid		int 			auto_increment,
    chain_id		int,
    order_date		date,
    bid				int,
    quantity 		int			not null,
    
    primary key (order_bid),
    unique (chain_id, order_date, seq_number, bid),
    foreign key (chain_id, order_date, seq_number) references 
				cust_order (chain_id, order_date, seq_number) on update cascade on delete cascade,
    foreign key (bid) references 
				baked_good(bid) on update cascade on delete cascade
);
    
    