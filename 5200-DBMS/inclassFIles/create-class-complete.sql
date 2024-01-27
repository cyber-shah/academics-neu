-- start by creating a database named student
CREATE DATABASE student_db;
CREATE schema student_db;
CREATE DATABASE IF NOT EXISTS student_db;
USE student_db;


-- what happens if I try to create it again
-- to avoid the error, review the IF NOT EXISTS clause
-- set your default database using the USE command
-- if no database is specified SQL uses the default value for all the commands that
-- follow
/* Syntax for the CREATE TABLE command
CREATE TABLE table_name
( field1 field1_type field_constraint [, field2 field2_type ...);
*/
/* What are the field constraints in MySQL? */
/* UNIQUE, NOT NULL, PRIMARY KEY, AUTO_INCREMENT
-- some require a value like DEFAULT default_value
/* You can also express table constraints for a table

CREATE TABLE name(field1 field_type1 field1_attributes
[, field2 field_type2 field2_attributes ]
[, table_constraints ] ) ;
Table constraints limit the table
*/
-- CREATE a base table for Student
-- LET'S SAY WE WANT TO CREATE A TABLE THAT CONTAINS THE FIELDS: STUDENT_ID,
-- FIRST_NAME, LAST_NAME, CREDITS_EARNED, CREDIT_REQ, DATE OF BIRTH
-- let's claim name needs a value, student_id is the primary key, and we
-- are putting a constraint on the date of birth to be afterr 1/1/1900
-- another constraint we have is that the class value cannot be >- 2050
CREATE TABLE student (
nuid INT AUTO_INCREMENT PRIMARY KEY,
first_name varchar(100) NOT NULL,
last_name varchar(100) NOT NULL,
credit_earned INT DEFAULT 0,
credit_req INT NOT NULL,
date_of_birth DATE NOT NULL CHECK(date_of_birth > '1900-01-01'),
class YEAR NOT NULL CHECK( class > 1920));
-- use of the refresh button to update the workbench
-- show the system catalog tables for the database
show tables;
show columns from student;
/* THIS SELECT COMMAND READS THE DESCRIPTION OF THE
COLUMNS IN THE STUDENT TABLE FOR THE CURRENT DATABASE
Database() is a function provided by MySQL
*/
SELECT COLUMN_NAME
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = Database()
AND TABLE_NAME = 'student' ;
SELECT *
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = Database()
AND TABLE_NAME = 'student' ;
/* can also use the table name */
SELECT COLUMN_NAME
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'student_db'
AND TABLE_NAME = 'student' ;
-- selecting specific fields
SELECT table_name, column_name, ordinal_position
FROM INFORMATION_SCHEMA.COLUMNS
WHERE table_schema = "student_db" ;
-- Now drop the table using the DROP TABLE command
DROP TABLE student;
-- recreate the table but make sure your GENERAL constraints are named
-- we can also provide a name for the general constraint
/* create a general using CONSTRAINT keyword constraints
*/
CREATE TABLE student (
nuid INT AUTO_INCREMENT PRIMARY KEY,
first_name varchar(100) NOT NULL,
last_name varchar(100) NOT NULL,
credit_earned INT DEFAULT 0,
credit_req INT NOT NULL,
date_of_birth DATE NOT NULL,
class YEAR NOT NULL,
CONSTRAINT birth_constraint CHECK(date_of_birth > '1900-01-01'),
CONSTRAINT class_limit CHECK(class > 1920) );
-- let's create a teacher view of the student table
-- teachers should only have access to the
-- student_id, first_name, and last_namme of the student
-- customize the name of the field in the view with the AS keyword
CREATE VIEW teacher_view AS
SELECT nuid, first_name, last_name FROM student;
-- view of the table
CREATE VIEW teacher_view2 AS
SELECT nuid, first_name AS student_first ,
last_name AS student_last FROM student;
-- insert tuples into the table - we specify the names of the fields
-- then specify the values, each tuple in its own parentheses
INSERT INTO
Student(first_name , last_name ,
credit_earned, credit_req,
date_of_birth , class)
VALUES
('Lucy', 'Liu' , 20, 128, '2004-12-22', '2022'), -- note the date format
('Matt', 'Finn', 24, 128, '2006-07-04', '2022'),
('Sam', 'Asner', 32, 120, '2005-03-14', '2022');
-- represent the school from the ERD
create table nu_college
( id INT auto_INCREMENT primary key,
name VARCHAR(64) unique not null );
INSERT INTO nu_college (name) VALUES
("Khoury College of Computer Sciences"),
("College of Science"),
("College of Engineering"),
("College of Social Sciences and Humanities"),
("D'Amore McKim School of Business"),
("Behrakis School of Health"),
("College of Arts Media and Design"),
("School of Law");
-- review the different options FOR BEHAVIOR
-- CASCADE | RESTRICT | NO ACTION | SET NULL | SET DEFAULT
drop table if exists student;
CREATE TABLE student (
nuid INT AUTO_INCREMENT PRIMARY KEY,
first_name varchar(100) NOT NULL,
last_name varchar(100) NOT NULL,
credit_earned INT DEFAULT 0,
credit_req INT NOT NULL,
date_of_birth DATE NOT NULL,
class YEAR NOT NULL,
school VARCHAR(64),
CONSTRAINT birth_constraint CHECK(date_of_birth > '1900-01-01'),
CONSTRAINT class_limit CHECK(class > 1920),
CONSTRAINT student_school_fk FOREIGN KEY (school) REFERENCES
nu_college(name) ON UPDATE SET NULL ON DELETE SET NULL);
-- insert tuples into the table - we specify the names of the fields
-- then specify the values, each tuple in its own parentheses
INSERT INTO
Student(first_name , last_name , school,
credit_earned, credit_req,
date_of_birth , class)
VALUES
('Lucy', 'Liu' , "Khoury College of computer sciences", 20, 128, '2004-12-22',
'2022'), -- note the date format
('Matt', 'Finn', "Khoury College of computer sciences", 24, 128, '2006-07-04',
'2022'),
('Sam', 'Asner', "College of Science", 32, 120, '2005-03-14', '2022');
-- now let's determine the tables needed to represent the relationship between
-- a student declares a major
-- a student has 0 to many majors and a major has 0 to many students
-- what type of relationship is this?
-- how many tables do we need.
-- now let's create a table for the majors offered at Northeastern
-- TABLE FOR MAJOR
CREATE TABLE available_major (
major VARCHAR(30) PRIMARY KEY);
-- INSERT tuples into the available_major table
INSERT INTO available_major VALUES
('CS'),
('DS'),
('Accounting');
CREATE TABLE student_major -- for the relationship
( nuid INT,
student_major VARCHAR(30),
PRIMARY KEY (nuid, student_major),
foreign key (nuid) references student(nuid)
ON UPDATE CASCADE ON DELETE CASCADE,
foreiGN key (student_major) references available_major(major)
ON UPDATE CASCADE ON DELETE CASCADE
);
-- if you try to insert the same tuples you
-- will generate an error
INSERT INTO available_major VALUES
('CS'), ('Accounting'), ('DS');
-- INSERT data into the student_major table
INSERT INTO student_major VALUES
(1,'CS'),
(1,'Accounting'),
(2,'CS'),
(3, 'DS');
-- Will this command succeed?
INSERT INTO student_major VALUES
(1, 'DS');
-- Will the following command succeed?
INSERT INTO student_major VALUES
(6, 'DS');
/* what if we wanted to represent the registers relationship
* between student and courses
* we need to look at the multiplicities to determine how to
represent it. A student can register for 0 to many courses
a course is registered by 1 to many courses. Since both sides
have a cadninalty of many it is a *:* relationship. We need a
separate table to represent a *:* relationship
We need to determine the PRIMARY KEY, FOREIGN keys
*/
-- create the table
CREATE TABLE course ( cid INT PRIMARY KEY,
name VARCHAR(30) NOT NULL );
INSERT INTO course VALUES (1,"CS 5200"), (2, " CS 5010");
/*
CREATE TABLE registers (
cid INT,
nuid INT,
PRIMARY KEY (cid, nuid)
);
*/
/* next we need to represent that registers.cid and registers.sid
should be limitied to the values in course.cid and student.sid
do you remember how we do this?
*/
DROP TABLE IF EXISTS registers;
/* we need a foreign key - actually we need 2 foreign keys - so
lets create them there are 2 different methods for
representing foreign keys, in one method you use the
CONSTRAINT key word, this allows you to specify a name for
the foreign key. The other method you do not provide a name */
/* example of a composite primary key - needs to be specified as
a table constraint */
CREATE TABLE registers (
cid INT,
nuid INT,
PRIMARY KEY (cid, nuid),
FOREIGN KEY (cid)
REFERENCES course(cid) ON UPDATE NO ACTION ON DELETE NO ACTION,
FOREIGN KEY (nuid)
REFERENCES student(nuid) ON UPDATE RESTRICT
ON DELETE RESTRICT
);
-- add tuples to represent the relationship between students and courses
INSERT into registers VALUES (1,1), (1,2);
/* We can also express what behavior we want to happen if there
is a change to the parent tuple that would violate the FK
what change should be made to the child tuple to ensure referential
integrity?
We use the ON UPDATE and ON DELETE clause for the FK to
specify the behavior we want
What actions can violate the FK?
*/
/* We can also express what behavior we want to happen if there
is a change to the parent tuple that would violate the FK
what change should be made to the child tuple to ensure referential
integrity?
We use the ON UPDATE and ON DELETE clause for the FK to
specify the behavior we want
What actions can violate the FK?
*/
Annotations
