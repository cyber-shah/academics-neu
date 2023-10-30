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



-- Retrieve the vendor names and the total payment amount for each vendor.
select 	v.vendor_name,
		sum(i.invoice_total)
	
from vendors as v

left join invoices i on i.vendor_id = v.vendor_id

group by(v.vendor_id)
order by (sum(i.invoice_total)) desc;



-- Retrieve the vendor names and their corresponding invoice totals for vendors who have issued 
-- invoices with an amount greater than the average invoice amount for all vendors.
-- how can I get the average of invoice per vendor?
select 	v.vendor_name,
		sum(i.invoice_total) as sum,
			(select avg(i.invoice_total)
			from invoices as i) 
        as avg_invoice_per_vendor
        
from vendors as v

inner join invoices i on i.vendor_id = v.vendor_id

group by(v.vendor_id)
having (sum > avg_invoice_per_vendor);



-- List the vendor names and their contact information (last name and first name) 
-- for vendors who have contact information and have issued invoices with an average total 
-- greater than the average total of all invoices.
select 	v.vendor_name,
		vc.last_name,
        vc.first_name,
        sum(i.invoice_total) as sum,
        (select avg(i.invoice_total)
        from invoices as i) as invoice_total_avg
        
from vendor_contacts as vc

inner join vendors v on v.vendor_id = vc.vendor_id
inner join invoices as i on i.vendor_id = vc.vendor_id

group by v.vendor_name,
		vc.last_name,
        vc.first_name
having (sum > invoice_total_avg);




-- Find the invoice numbers and their corresponding payment date for invoices paid after the latest payment date in the invoice archive table.
select i.invoice_id, i.payment_date, i.invoice_due_date

from invoices as i

where (i.payment_date > i.invoice_due_date);


(select 	count(i.invoice_id) as num_invoices,
					i.vendor_id
                    
		from invoices as i
        group by(i.vendor_id)
        );


-- Retrieve the vendor names and the count of invoices for vendors who have issued more invoices than the average number of invoices per vendor.
select 	v.vendor_name,
		count(i.invoice_id)
					
        
from invoices as i

left join vendors as v on v.vendor_id = i.invoice_id;


