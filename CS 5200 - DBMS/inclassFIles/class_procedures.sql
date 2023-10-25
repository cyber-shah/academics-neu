/** 
database procedures
database programming objects
routines standard method for accessing the databse
LIBRARY that programmers can use to work with the database
within that

roles for fans vs coaches
limit the kinds of actions that can be done on the database
OPTIMIZATION of processing queries

MUST know who is this programming object for
*/


/**
So what do we need to make this as a programming project

WE need VARIABLES!
to store it from one query to another

LOOPS, and BRANCHING!
error handling....
*/

/**
what can we do?
procedures, functions, triggers, events
*/


/**
VARIABLES:
1. Session - for debugging 
2. Local - varaibles to a procedure

These variables continue to exist until you shut down the server.
*/
select @T + 1;
set @T = 5;

use student_simple;
select count(*) into @t from student;
select @t;


create procedure test() 
begin

end;


delimiter $$
create procedure test2() 
	begin
		declare i_var int;
	end$$
delimiter ;

-- cannot do this, because its scope is inside procedure
-- select i_var;
drop procedure test3; 

delimiter $$
create procedure test3(IN n_p INT)
	begin
		declare i_var INT;
        select n_p;
    END $$
delimiter ;
call test3(5);
-- IN stands for input, saying I want a VALUES
-- OUT stands for output, CHANGING the value
-- IN OUT, passed a value and we will change it


delimiter $$
create procedure ToKhoury(IN student_id INT )
	begin
    update student set school = "khoury"
		where id = student_id;
    END$$
delimiter ;
call ToKhoury(2);


delimiter $$
create procedure ToKhouryLimit(IN student_id INT )
	begin
    if (id_p > 10) then
		update student set school = "khoury"
			where id = student_id;
	else select "cannot update";
    end if;
    END$$
delimiter ;
call ToKhoury(2);


delimiter $$
create procedure ToKhouryDiffSkUl(IN student_id INT )
	begin
    if (student_id > 10) then
		update student set school = "khoury"
			where id = student_id;
	elseif (student_id < 5) then 
		update student set school = "CO E"
			where id = student_id;
    end if;
    END$$
delimiter ;
call ToKhoury(2);