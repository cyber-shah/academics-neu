/* 
a. How many databases are created by the script?
		3 databases
*/

/* 
b .List the database names and the tables created for each database.
		The three databases are as follows:
		1. ap
		+-------------------------+
		| Tables_in_ap            |
		+-------------------------+
		| general_ledger_accounts |
		| invoice_archive         |
		| invoice_line_items      |
		| invoices                |
		| terms                   |
		| vendor_contacts         |
		| vendors                 |
		+-------------------------+
		2. ex
		+-----------------+
		| Tables_in_ex    |
		+-----------------+
		| active_invoices |
		| color_sample    |
		| customers       |
		| date_sample     |
		| departments     |
		| employees       |
		| float_sample    |
		| null_sample     |
		| paid_invoices   |
		| projects        |
		| string_sample   |
		+-----------------+
		3. om
		+---------------+
		| Tables_in_om  |
		+---------------+
		| customers     |
		| items         |
		| order_details |
		| orders        |
		+---------------+ 
        Query used can be found below:
        
        use ap; show tables;
		use ex; show tables;
		use om; show tables;
*/


/* 
How many records does the script insert into the om.order_details table?
	+----------+
	| count(*) |
	+----------+
	|       68 |
	+----------+
    query used can be found below:
    select count(*) from om.order_details;
*/

/* 
How many records does the script insert into the ap.invoices table?
	+----------+
	| count(*) |
	+----------+
	|      114 |
	+----------+
	query used can be found below:
    select count(*) from ap.invoices;
*/


/* 
How many records does the script insert into the ap.vendors table?
	+----------+
	| count(*) |
	+----------+
	|      122 |
	+----------+
    query used can be found below:
    select count(*) from ap.vendors;
*/

/* 
Is there a foreign key between the ap.invoices and the ap.vendors table?
	The foreign key BETWEEN ap.invoices and the ap.vendors table is the terms_id which is stored in
	the terms_id attribute inside the invoices table and default_terms_id inside the vendors table;
*/

/*
How many foreign keys does the ap.vendors table have?
	Two. They are default_terms_id and default_account_number referencing
    terms (terms_id) and general_ledger_accounts (account_number) respectively.
*/

/* 
What is the primary key for the om.customers table?
	customer_id
*/

/*  
Write a SQL command that will retrieve all values for all fields from the om.orders table
	SELECT * from om.orders;
*/

/* 
Write a SQL command that will retrieve the fields: title and artist from the om.items table
	SELECT title, artist 
	from om.items;
*/












/*
How many tables are created by the script?
	11 tables


List the names of the tables created for the Chinook database.
	show tables;
	+-------------------+
	| Tables_in_Chinook |
	+-------------------+
	| Album             |
	| Artist            |
	| Customer          |
	| Employee          |
	| Genre             |
	| Invoice           |
	| InvoiceLine       |
	| MediaType         |
	| Playlist          |
	| PlaylistTrack     |
	| Track             |
	+-------------------+

How many records does the script insert into the Album table?
	select count(*) from Chinook.Album;
	+----------+
	| count(*) |
	+----------+
	|      347 |
	+----------+
	
What is the primary key for the Album table?
	AlbumId
    
Write a SQL SELECT command to retrieve all data from all columns and rows in the Artist table.
	select * from Chinook.Artist;

Write a SQL SELECT command to retrieve the fields FirstName, LastName and Title from the Employee table
	select FirstName, LastName, Title
    from Chinook.Employee;
*/





