USE crime_db2022shahp;

select * from district;
select * from incidents;
select * from neighbourhood;
select * from offense_code;


-- 4
SELECT  COUNT(incident_id) AS `num_crimes`, 
        DATE(occurred_date) AS occurred_date

FROM incidents

GROUP BY DATE(occurred_date)
ORDER BY DATE(occurred_date) ASC;


-- 5
SELECT  street,
        COUNT(incident_id) as `num_crimes`

from incidents

GROUP BY(street)
ORDER BY(COUNT(incident_id)) DESC
LIMIT 1;


-- 6
SELECT COUNT(incident_id) as `num_crimes`

FROM incidents

GROUP BY (district_code)
HAVING district_code = 'A1';


-- 7
SELECT COUNT(incident_id) as `num_crimes`

FROM incidents
WHERE incidents.district_code = 'E18'

GROUP BY incidents.district_code;


-- 8 
SELECT  COUNT(incident_id) as `num_crimes`,
        incidents.o_code,
        district_code,
        occurred_date,
        offense_code.description

FROM incidents
INNER JOIN offense_code on offense_code.o_code = incidents.o_code

WHERE offense_code.description LIKE '%rape%'
GROUP BY incidents.o_code, district_code, occurred_date;


-- 9
SELECT  offense_code.o_code,
        offense_code.description,
        COUNT(incidents.o_code) as 'num_occurrences'

FROM incidents
RIGHT JOIN offense_code on offense_code.o_code = incidents.o_code

GROUP BY offense_code.description, offense_code.o_code
ORDER BY (COUNT(incidents.o_code)) DESC;


-- 10
SELECT  COUNT(incidents.incident_id) AS `num_crimes`,
        district.district_code,
        district.district_name

FROM incidents
INNER JOIN district ON district.district_code = incidents.district_code
GROUP BY district.district_code, district.district_name;



-- 11 
SELECT  offense_code.o_code,
        COUNT(DISTINCT incidents.district_code) AS `num_districts`
        

FROM offense_code

LEFT JOIN incidents ON incidents.o_code = offense_code.o_code
GROUP BY offense_code.o_code
ORDER BY `num_districts` DESC;


-- 12
SELECT  incidents.incident_id,
        district.district_name,
        offense_code.description,
        incidents.occurred_date

FROM incidents

INNER JOIN district on district.district_code = incidents.district_code
INNER JOIN offense_code on offense_code.o_code = incidents.o_code

WHERE incidents.occurred_date > '2022-12-25' AND incidents.occurred_date < '2022-12-29' 

ORDER BY incidents.occurred_date ASC;


-- 13 select only one now!
SELECT  district.district_name,
        offense_code.description,
        offense_code.o_code,
        COUNT(incidents.incident_id) 

FROM district

INNER JOIN incidents on district.district_code = incidents.district_code
INNER JOIN offense_code on offense_code.o_code = incidents.o_code

GROUP BY district.district_name, offense_code.description, offense_code.o_code;


-- 14



-- 15


-- 16
SELECT
    DAYNAME(incidents.occurred_date) AS day_name,
    COUNT(incidents.incident_id) AS daily_incidents

FROM incidents

GROUP BY day_name

ORDER BY CASE
    WHEN day_name = 'Monday' THEN 1
    WHEN day_name = 'Tuesday' THEN 2
    WHEN day_name = 'Wednesday' THEN 3
    WHEN day_name = 'Thursday' THEN 4
    WHEN day_name = 'Friday' THEN 5
    WHEN day_name = 'Saturday' THEN 6
    WHEN day_name = 'Sunday' THEN 7
END;


-- 17
SELECT AVG(subquery.daily_incidents)

FROM (
    SELECT  
        DATE(incidents.occurred_date),
        COUNT(incidents.incident_id) AS daily_incidents

    FROM incidents

    GROUP BY DATE(incidents.occurred_date)
) AS subquery;


-- 18
SELECT  
        offense_code.o_code,
        offense_code.description

FROM offense_code

LEFT JOIN incidents on incidents.o_code = offense_code.o_code

GROUP BY offense_code.o_code, offense_code.description
HAVING COUNT(incidents.incident_id) = 0;
