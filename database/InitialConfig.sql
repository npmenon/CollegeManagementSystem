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
