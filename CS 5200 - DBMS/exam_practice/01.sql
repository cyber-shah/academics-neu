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
select 	i.invoice_id,
		i.invoice_total,
        i.payment_total,
        i.credit_total
        
from invoices as i

where (i.invoice_total > 1000 or i.payment_total > 1000 or i.credit_total > 1000);


-- Find the vendor names and the total number of invoices issued by each vendor.
select 	vendor_name,
		count(i.invoice_id)
        
from vendors

left join invoices i on vendors.vendor_id = i.vendor_id
group by(vendor_name)
order by(count(i.invoice_id)) desc;


