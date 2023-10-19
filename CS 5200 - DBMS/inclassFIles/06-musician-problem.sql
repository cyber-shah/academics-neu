-- CHAPTER 14 - reducing data redundancy -- functional dependency
-- alot of musicians with the same address,  just different names and ssns
-- everyone lives at the same address?
select * from notown3.musician_address;

-- modification anamoly
update musician_address set zipcode = "02115"
	where ssn = 1;
    
-- insertion anamoly
-- where you make your data inconsistent, break everything
insert into musician_address values
	(5, "Rumi", "5555555555", "181 Main", "Manhattan", "NY", "11223");

-- deletion anamoly
-- you delete everything instead of just deleting musicians    
delete from musician_address where ssn < 10;

-- dependencies amongst records is a problem?
-- how do we fix it
-- make address and musician a seperate entity