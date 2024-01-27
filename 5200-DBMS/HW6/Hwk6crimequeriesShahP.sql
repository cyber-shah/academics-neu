-- @block
USE crime_db2022shahp;

select * from district;
select * from incidents;
select * from neighbourhood;
select * from offense_code;

-- @block 4 
-- number of crimes per day
SELECT  DATE(occurred_date) AS occurred_date,
        COUNT(incident_id) AS `num_crimes`

FROM incidents

GROUP BY DATE(occurred_date)
ORDER BY DATE(occurred_date) ASC;

-- @block 5 
-- street with the most crimes
SELECT  street,
        COUNT(incident_id) as `num_crimes`

from incidents

GROUP BY(street)
ORDER BY(COUNT(incident_id)) DESC
LIMIT 1;


-- @block 6
-- max crimes that could have occurred in - north end
SELECT COUNT(incident_id) as `num_crimes`

FROM incidents

GROUP BY (district_code)
HAVING district_code = 'A1';


-- @block 7
-- crimes occurred in hyde park
SELECT COUNT(incident_id) as `num_crimes`

FROM incidents
WHERE incidents.district_code = 'E18'

GROUP BY incidents.district_code;


-- @block 8 
-- rape crimes, ordered by date and then district
SELECT  COUNT(incident_id) as `num_crimes`,
        incidents.o_code,
        incidents.district_code,
        incidents.occurred_date,
        offense_code.description

FROM incidents
INNER JOIN offense_code on offense_code.o_code = incidents.o_code

WHERE offense_code.description LIKE '%rape%'
GROUP BY incidents.o_code, incidents.district_code, incidents.occurred_date
ORDER BY incidents.occurred_date DESC, incidents.district_code DESC;


-- @block 9
-- number of crimes per offense code
SELECT  offense_code.o_code,
        offense_code.description,
        COUNT(incidents.o_code) as 'num_occurrences'

FROM incidents
RIGHT JOIN offense_code on offense_code.o_code = incidents.o_code

GROUP BY offense_code.description, offense_code.o_code
ORDER BY (COUNT(incidents.o_code)) DESC;


-- @block 10
-- number of crimes per district
SELECT  COUNT(incidents.incident_id) AS `num_crimes`,
        district.district_code,
        district.district_name

FROM incidents
INNER JOIN district ON district.district_code = incidents.district_code
GROUP BY district.district_code, district.district_name
ORDER BY `num_crimes` DESC;



-- @block 11 
-- for each offense code, number of districts that have had that crime
SELECT  offense_code.o_code,
        COUNT(DISTINCT incidents.district_code) AS `num_districts`,
        offense_code.description
        
FROM offense_code

LEFT JOIN incidents ON incidents.o_code = offense_code.o_code
GROUP BY offense_code.o_code
ORDER BY `num_districts` DESC;



-- @block 12
-- crimes that occurred between 12/25/2022 and 12/28/2022
SELECT  incidents.incident_id,
        district.district_name,
        offense_code.description,
        incidents.occurred_date

FROM incidents

INNER JOIN district on district.district_code = incidents.district_code
INNER JOIN offense_code on offense_code.o_code = incidents.o_code

WHERE incidents.occurred_date > '2022-12-25' AND incidents.occurred_date < '2022-12-29' 

ORDER BY incidents.occurred_date ASC;



-- @block 13
-- aggregation and selection
SELECT  d1.district_name,
        d1.of_description,
        d1.num_incidents

FROM (
    SELECT  district.district_name,
            district.district_code as d_code,
            offense_code.o_code as of_code,
            offense_code.description as of_description,
            COUNT(incidents.incident_id) as num_incidents
    FROM district
    INNER JOIN incidents on district.district_code = incidents.district_code
    INNER JOIN offense_code on offense_code.o_code = incidents.o_code
    GROUP BY district.district_name, district.district_code, offense_code.o_code, of_description
) as d1
-- COUNT total incidents GROUPED BY offense code and district

INNER JOIN (
    SELECT  d_code,
            MAX(num_incidents) as max_num_incidents
    FROM (
        SELECT  district.district_code as d_code,
                COUNT(incidents.incident_id) as num_incidents
        FROM district
        INNER JOIN incidents on district.district_code = incidents.district_code
        INNER JOIN offense_code on offense_code.o_code = incidents.o_code
        GROUP BY district.district_code, offense_code.o_code
    ) as incident_counts
    -- COUNT incidents for each GROUP district and offense
    GROUP BY d_code
) as d2 
-- return MAX(number of incidents) for the offense which has max number of incidents for each district
ON d1.d_code = d2.d_code AND d1.num_incidents = d2.max_num_incidents;



-- @block 14
-- for each offense code, aggregate districts and number of times it took place
SELECT offense_code.description,
         GROUP_CONCAT(DISTINCT district.district_name) AS districts,
         COUNT(incidents.incident_id) AS num_crimes

FROM offense_code
INNER JOIN incidents on incidents.o_code = offense_code.o_code
INNER JOIN district on district.district_code = incidents.district_code

GROUP BY offense_code.description
ORDER BY num_crimes DESC;



-- @block 15
-- crimes per hour of day between 18:00 to 23:59
SELECT  HOUR(incidents.occurred_date) AS hour_day,
        COUNT(incidents.incident_id)

FROM incidents
GROUP BY hour_day
HAVING hour_day BETWEEN 18 AND 24
ORDER BY hour_day ASC;



-- @block 16
-- number of crimes per day of the week
SELECT
    DAYNAME(incidents.occurred_date) AS day_name,
    COUNT(incidents.incident_id) AS daily_incidents

FROM incidents

GROUP BY day_name

ORDER BY FIELD(day_name, 
'Monday', 'Tuesday', 'Wednesday', 
'Thursday', 'Friday', 'Saturday', 'Sunday');


-- @block 17
-- average number of crimes per day
SELECT AVG(subquery.daily_incidents)

FROM (
    SELECT  
        DATE(incidents.occurred_date),
        COUNT(incidents.incident_id) AS daily_incidents

    FROM incidents

    GROUP BY DATE(incidents.occurred_date)
) AS subquery;


-- @block 18
-- crimes that were never committed
SELECT  
        offense_code.o_code,
        offense_code.description

FROM offense_code

LEFT JOIN incidents on incidents.o_code = offense_code.o_code

GROUP BY offense_code.o_code, offense_code.description
HAVING COUNT(incidents.incident_id) = 0;
