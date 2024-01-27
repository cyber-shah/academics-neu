-- ANY, SOME, ALL

-- return all invoices such that 
-- subquery first then the larger one around it is executed
SELECT invoice_id, invoice_total from invoices
	WHERE invoice_total >=
    (SELECT line_item_amount FROM invoice_line_items);
    
-- this will start running
SELECT invoice_id, invoice_total from invoices
	WHERE invoice_total >= ALL
    (SELECT line_item_amount FROM invoice_line_items);
    
-- this will find the max item first
SELECT invoice_id, invoice_total from invoices
	WHERE invoice_total >= 
    (SELECT max(line_item_amount) FROM invoice_line_items);
    
-- ANY
SELECT invoice_id, invoice_total from invoices
	WHERE invoice_total >= any
    (SELECT max(line_item_amount) FROM invoice_line_items);
    
SELECT invoice_id, invoice_total from invoices
	WHERE invoice_total >=
    (SELECT min(line_item_amount) FROM invoice_line_items);
    
    
    
-- UNCORRELATED VS 
