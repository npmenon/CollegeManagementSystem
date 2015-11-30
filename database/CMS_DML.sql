use cms_db;
SET SQL_SAFE_UPDATES = 0;

#######################initial configuration######################

insert into login_details(user_id,pwd,privilege_level) values ("448501","cts-2014",3);
insert into admin_details values ("448501","cts-2014",3);

insert into department_details values
("1000","CSE"),
("1001","IT"),
("1002","EEE"),
("1003","ECE"),
("1004","CE");

insert into login_details(user_id,pwd,privilege_level) values
("S1000","abc",1),
("S1001","abc",1),
("F1000","123",2),
("F1001","123",2);

insert into student_prev_academic_details values
("S1000","NCS",87.5,"NCS",88,"Game"),
("S1001","ACS",90,"BGKV",99,"Nanotechnology");

insert into student_curr_academic_details values
("S1000","1000",8,72,74,70,71),
("S1001","1001",8,70,74,78,78);

insert into student_personal_details values
("S1000","Arjun","Kumar","arjunkumar@gmail.com","923232232","2000-02-01","1990-11-20"),
("S1001","Krishna","Bommani","krishna@gmail.com","923435435","2001-01-01","1989-11-20");

insert into faculty_personal_details values
("F1000","Anita","Nair","anitanair@gmail.com","2005-04-22","1979-02-11","9823424234"),
("F1001","Varghese","George","vg@gmail.com","2000-04-22","1960-08-12","982242443");

insert into faculty_prof_details values
("F1000","1000","Rajagiri School of Engineering and Technology",78,"IIT",75,"Senior lecturer"),
("F1001","1001","IEM",78,"IIT",75,"Professor");

######################department details###########################

insert into department_details values
("1000","CSE"),
("1001","IT"),
("1002","EEE"),
("1003","ECE"),
("1004","CE");

#######################student details##################################### 
insert into login_details(user_id,pwd,privilege_level) values
("S10012000",pwd1),
("S10012001",1),
("S10012002",1),
("S10012003",1),
("S10012004",1);

update login_details set pwd = "abc" where user_id = "S10012000";

delete from student_prev_academic_details;
insert into student_prev_academic_details(student_id) values
("S10012000"),
("S10012001"),
("S10012002"),
("S10012003"),
("S10012004");

insert into student_personal_details(student_id) values
("S10012000"),
("S10012001"),
("S10012002"),
("S10012003"),
("S10012004");

delete from student_curr_academic_details;
insert into student_curr_academic_details values
("S10012000","1000",8,72,74,70,71),
("S10012001","1001",8,70,74,78,78),
("S10012002","1002",8,68,74,56,70),
("S10012003","1003",8,65,74,65,74),
("S10012004","1004",8,60,74,49,72);

delete from student_prev_academic_details;
insert into student_prev_academic_details values
("S10012000","Navy Children School Kochi",88,"Navy Children School Kochi",89,"Android game");


#######################faculty details##################################### 

insert into login_details(user_id,privilege_level) values
("F441000",2),
("F441001",2),
("F441002",2),
("F441003",2),
("F441004",2);

delete from faculty_personal_details;
insert into faculty_personal_details(faculty_id) values
("F441000"),
("F441001"),
("F441002"),
("F441003"),
("F441004");

insert into faculty_prof_details values
("F441000","1001","Rajagiri School of Engineering and Technology",78,"IIT",75,"Senior lecturer");

insert into faculty_prof_details(faculty_id) values
("F441001"),
("F441002"),
("F441003"),
("F441004");


update faculty_prof_details set department_id = "1000" where faculty_id = "F441001";

desc faculty_personal_details;

#*************************admin details***************************

desc admin_details;

insert into login_details(user_id,pwd,privilege_level) values ("448590","cts-7044",3);
insert into admin_details values ("448590","cts-7044",3);

#creating new user

delete from faculty_prof_details where faculty_id = "F441005";
insert into faculty_prof_details(faculty_id,department_id) values
("F441005","1000");

