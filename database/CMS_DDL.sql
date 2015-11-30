drop database cms_db;
CREATE DATABASE IF NOT EXISTS CMS_DB;
use cms_db;


CREATE TABLE department_details(

department_id varchar(15) NOT NULL PRIMARY KEY,
department_name varchar(25) NOT NULL

);


CREATE TABLE student_personal_details(

student_id varchar(15) PRIMARY KEY,
first_name varchar(25),
last_name varchar(25),
email_id varchar(30),
contact varchar(20),
join_date DATE,
date_of_birth DATE
);

CREATE TABLE student_prev_academic_details(

student_id varchar(15) PRIMARY KEY,
ten_instit varchar(50),
ten_marks decimal(4,2),
twelve_instit varchar(50),
twelve_marks decimal(4,2),
prev_projects varchar(50)
);

CREATE TABLE student_curr_academic_details(

student_id varchar(15) PRIMARY KEY,
department_id varchar(15) references department_details(department_id),
current_sem int,
sem_1_marks decimal(4,2),
sem_2_marks decimal(4,2),
sem_3_marks decimal(4,2),
sem_4_marks decimal(4,2)
);

CREATE TABLE login_details(

user_id varchar(15) PRIMARY KEY,
pwd varchar(35),
privilege_level int,
status boolean

);

CREATE TABLE admin_details(

admin_id varchar(15) PRIMARY KEY,
first_name varchar(25),
last_name varchar(25)
);

CREATE TABLE faculty_personal_details(

faculty_id varchar(15) PRIMARY KEY,
first_name varchar(25),
last_name varchar(25),
email_id varchar(30),
hire_date DATE,
date_of_birth DATE,
contact varchar(15)
);

CREATE TABLE faculty_prof_details(

faculty_id varchar(15) PRIMARY KEY,
department_id varchar(15),
grad_instit varchar(50),
grad_marks decimal(4,2),
post_grad_instit varchar(50),
post_grad_marks decimal(4,2),
current_design varchar(30),
constraint faculty_prof_model_constraint foreign key(department_id) references department_details(department_id)
);

