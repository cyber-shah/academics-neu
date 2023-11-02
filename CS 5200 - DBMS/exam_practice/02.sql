-- Q8.  Using the order details and orders table in  the om database, 
-- create  a MYSQL SQL SELECT statement that returns fields from the order details 
-- and orders tables for the items in an order that have not been shipped (no ship date)  
-- The fields in the result should be the order_id, the customer_id, the item id, and the order date.

use om;

select order_details.order_id, customers.customer_id, order_details.item_id, orders.order_date, orders.shipped_date
from order_details
inner join orders on orders.order_id = order_details.order_id
inner join customers on customers.customer_id = orders.customer_id
where orders.shipped_date is null;


-- Q11. Using the order_details, orders and items tables in the om database, 
-- create  a MYSQL SQL SELECT statement that  identifies the items that have appeared in orders greater than 
-- the average number of times each item has appeared in an order.  
-- To determine these items you must first compute the number of times each item has appeared in an order, 
-- then take an average of these values. The result should contain the item id, item title,  
-- and the number of unique orders that this item has appeared it.  
-- (renamed to num_orders in the result). The result should be sorted in descending order using the num_orders field. 

select 	(select avg(count_items) as avg_count 
		
        from
			(select item_id, count(order_id) as count_items
			from order_details
			group by (item_id)
			) as alias) 
		as avg_orders,
		items.item_id, 
        items.title,
        count(order_details.order_id) as order_count
        
from items

right join order_details on order_details.item_id = items.item_id
group by items.item_id, items.title, avg_orders
having ( order_count > avg_orders);
		
        

