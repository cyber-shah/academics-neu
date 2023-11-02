-- Q12. Using the Chinook database, create a MYSQL SQL SELECT statement that returns for each track  on a playlist the following fields:
-- Playlist name, playlist id, Track id, track name, track composer, the media type id, and the media type name. 
use chinook;

select p.playlistid, p.name, pt.trackid, t.name,t.composer, t.mediatypeid, m.name
from playlist as p
inner join playlisttrack as pt on pt.playlistid = p.playlistid
inner join track as t on t.trackid = pt.trackid
inner join mediatype as m on m.mediatypeid = t.mediatypeid;







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
		
        
        
        

-- Q10. Using the chinook database, write a query that finds the artist that is associated with every music genre.  
-- Each row in the result should contain the artist name. (HINT: artists are associated with genres through tracks). 

use chinook;

-- distinct genres
SELECT COUNT(DISTINCT genreid) AS num_distinct_genres
FROM track;

-- artists with distinct genres
select album.artistId, count(distinct track.genreid) as genre_count,
		(select count(distinct genreid) as distinct_genre
        from track) as distinct_g
from album
inner join track on track.albumid = album.albumid
group by album.artistId
having genre_count > 3;



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





