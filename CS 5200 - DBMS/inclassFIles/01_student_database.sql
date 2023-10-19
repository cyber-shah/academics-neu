-- @block start by creating a database named student
CREATE DATABASE IF NOT EXISTS student_db;

-- @block use the database
USE student_db;


-- @block create the table
CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    credits_earned INT DEFAULT 0,
    credits_req INT NOT NULL,
    date_of_birth DATE NOT NULL CHECK (date_of_birth > '1900-01-01'),
    class INT CHECK (class < 2050));

-- @block show the table
show columns from student;


-- @block create a teacher view
-- let's create a teacher view of the student table
-- teachers should only have access to the
-- student_id, first_name, and last_name of the student
-- customize the name of the field in the view with the AS keyword
CREATE VIEW teacher_view AS
SELECT id, first_name, last_name FROM student;
show columns from teacher_view;

-- @block create a teacher view to not show the actual column names
CREATE VIEW teacher_view_no_names AS
SELECT id AS StudentID, first_name AS StudentFirst, last_name AS StudentLast FROM student;
show columns from teacher_view_no_names;

-- @block insert tuples
INSERT INTO student (
    first_name, last_name, credits_earned, credits_req, date_of_birth, class)
VALUES 
('Lucy', 'Liu' , 20, 128, '2004-12-22', '2022'), -- note the date format
('Matt', 'Finn', 24, 128, '2006-07-04', '2022'),
('Sam', 'Asner', 32, 120, '2005-03-14', '2022');
SELECT * FROM student;


-- @block represent the school from the ERD
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