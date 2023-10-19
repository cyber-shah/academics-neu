/* 
Chapter 3,4,6 in murach's mySQL
*/

use ap;

-- selected three fields so we will have three columns in your output
select invoice_id, invoice_date, invoice_total
	from invoices;

-- we can also do some computation on this
-- now we will have 5 columns on this
select invoice_id, invoice_date, invoice_total,
		payment_total, 
        invoice_total - payment_total as amount_owed
		from invoices;
        
-- we can order them by amount_owed now
select invoice_id, invoice_date, invoice_total,
		payment_total, 
        invoice_total - payment_total as amount_owed
		from invoices order by amount_owed desc; -- descending for the order
        
-- order more than 1 fields
-- show me all the people who owe me money first whenever there is a tie, use invoice date to show first
select invoice_id, invoice_date, invoice_total,
		payment_total, 
        invoice_total - payment_total as amount_owed
		from invoices order by amount_owed desc, 
        invoice_date desc;
        
-- limit to determine maximum # of tuples ot be returned
select invoice_id, invoice_date, invoice_total,
		payment_total, 
        invoice_total - payment_total as amount_owed
		from invoices order by amount_owed desc, 
        invoice_date desc limit 10;
        
-- limit on both start and end, this would mean ignore the first two
select invoice_id, invoice_date, invoice_total,
		payment_total, 
        invoice_total - payment_total as amount_owed
		from invoices order by amount_owed desc, 
        invoice_date desc limit 2, 10;
        
-- these will be applied after the table is generated, so first of all it will select these and then 
-- it will apply the limits so, no optimisation in run time here

-- QUERIES are NOT RELATIONS, so they can have duplicates
select vendor_id from invoices;

-- QUERIES are NOT RELATIONS, you can get unique values
select distinct vendor_id from invoices;



-- where clauses in select
select * from invoices
		where vendor_id = 37;

-- multiple vendors        
select * from invoices
		where invoice_id >= 7 and invoice_id <=20;
        
-- between can be written instead?
select * from invoices
		where invoice_id between 7 and 20;
        
        
-- select vendors from two states
select * from vendors where vendor_state = 'MA'
					or vendor_state = 'CT';
                    
select * from vendors where vendor_state in ('MA', 'CT');


-- select all vendors that are NOT in new england
select * from vendors where vendor_state not in 
	('MA', 'CT', 'NH', 'VT', 'ME', 'RI');


    
-- LIKE - a pattern that strings match,
-- two special characters '_' this means a character 
-- and '%' this means everything in between 

-- what if all names must begin with something
select * from vendors 
		where vendor_name like 				-- just give it a pattern to match now
        'A%';
        
-- start with A and end with S
select * from vendors 
		where vendor_name like
        'A%s';
        
-- second character of the vendor name must be A and end with S
-- first character can be anything
select * from vendors 
		where vendor_name like
        '_A%s';
        


-- aggregation
-- all the aggreagates have to work with numbers
select 	sum(invoice_total) as money_owed, 
		count(*) as number_of_invoices, 
        avg(payment_total) as average 
        from invoices;

-- take all the 115 invoices and seperate them by 35 vendors showing group of them
select vendor_id, sum(invoice_total) as vendor_total 
		from invoices
		group by vendor_id
        order by vendor_total desc;

-- in the last row of the select is a sum
-- roll up is linked to sum command
select vendor_id, sum(invoice_total) as vendor_total 
		from invoices
		group by vendor_id
        with rollup;

-- show only vendors with payment greater than $800
-- where has very similar functionality to having, where and having both have predicates
-- cannot use 'where' becuase vendor total doesn't exist until you have a group by aggregation
select vendor_id, sum(invoice_total) as vendor_total 
		from invoices
		group by vendor_id
        having vendor_total > 800 order by vendor_total desc;
        
-- group concat
select vendor_state, group_concat(distinct vendor_city)
		from vendors group by vendor_state;
        
        
        
-- UNIOUN
select vendor_id, last_name, first_name
		from vendor_contacts
union

select vendor_id, vendor_contact_last_name,
		vendor_contact_first_name from vendors
        
order by vendor_id;


-- 	 LEARN MORE on this
select vendor_id, last_name, first_name
		from vendor_contacts;
select vendor_id, vendor_contact_last_name,
		vendor_contact_first_name from vendors;        
        
        
-- JOIN - ask how many tables do I need to answer this question
-- we'll use this alot becuase we decompose so much of data into parts to normalize
select invoice_id, vendor_name from
	invoices inner join vendors
    on invoices.vendor_id = vendors.vendor_id; -- this is the foreign key referencing vendors.vendor_id
    -- one row for every invoice id
    
    
select invoice_id, vendor_name from
	invoices inner join vendors
    using(vendor_id);
    -- vendor_id has to be common in both, instead of using on
    
    
-- not using on or using, so no limit the number of tuples on my result to have the same vendor ID
select invoice_id, vendor_name from
	invoices inner join vendors;
    
-- this is a cross join - cross product
select invoice_id, vendor_name, invoices.vendor_id, vendors.vendor_id from
	invoices inner join vendors
    order by vendors.vendor_id;