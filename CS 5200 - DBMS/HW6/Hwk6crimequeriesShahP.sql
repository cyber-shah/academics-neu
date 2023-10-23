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


