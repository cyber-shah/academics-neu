use ap;

select * from general_ledger_accounts;

-- List the names of all vendors and their associated default account numbers.
select 	vendor_contacts.first_name,
		vendor_contacts.last_name,
        vendors.default_account_number
        
from vendor_contacts

inner join vendors on vendor_contacts.vendor_id = vendors.vendor_id;

-- Retrieve the invoice numbers and their corresponding invoice total for invoices 
-- with a total greater than $1,000.


