CREATE TABLE users (
  id int ,
  username varchar(30) ,
  password varchar(30) ,
  PRIMARY KEY (id)
);
 
insert into users values(1,'rakesh','rkb');
insert into users values(2,'sudeep','sdp');
insert into users values(1,'vaibhav','vbv');
insert into users values(1,'ashwini','asw');
insert into users values(1,'monika','mnk');

CREATE TABLE roles (
  id int ,
  rolename varchar(30),
  PRIMARY KEY (id)
);

insert into users values(1,'admin');
insert into users values(2,'candidate');


CREATE TABLE user_roles (
  id int,
  users_id int,
  roles_id int,
  PRIMARY KEY (id),
  CONSTRAINT userroles_rolesid_roles_fk FOREIGN KEY (roles_id) REFERENCES roles (id),
  CONSTRAINT userroles_usersid_users_fk FOREIGN KEY (users_id) REFERENCES users (id)
);
 
insert into users values(1,1,2);
insert into users values(2,2,1);
insert into users values(3,3,2);
insert into users values(4,4,1);
insert into users values(5,5,2);

create table questions(
     id Integer,
     question varchar(30),
     Constraint questions_id_pk primary key (id)
     );
 insert into questions values(1,'who is president of india');

 insert into questions values(2,'name of capital of kenya');

 insert into questions values(3,'which are types of dbms');

 select *from questions;




create table options(
     id Integer,
     questions_id Integer,
     option_name varchar(30),
     constraint options_id_pk primary key (id),
     Constraint options_questionsid_questions_fk foreign key (questions_id) references questions (id)
     );
 insert into options values(1,1,'APJ Abdul kalam');


 insert into options values(2,1,'Parnav mukharjee');

 insert into options values(3,1,'Pratibha patil');

 insert into options values(4,1,'K Narayanan');

 insert into options values(5,2,'harare');

 insert into options values(6,2,'tokyo');

 insert into options values(7,2,'nairobi');

 insert into options values(8,2,'durban');

 insert into options values(9,3,'network');

 insert into options values(10,3,'stack');

 insert into options values(11,3,'relational');

 insert into options values(12,3,'primary');

 select * from options;







create table answers(
     id Integer,
     question_id Integer,
     correctoption varchar(30),
     Constraint answers_id_pk primary key (id),
     Constraint answers_questionsid_question_fk foreign key (question_id) references questions (id)
     );

insert into answers values(1,1,'Parnav mukharjee');

insert into answers values(2,2,'nairobi');

insert into answers values(3,3,'network');

insert into answers values(4,3,'relational');

select * from answers;





create table assignments(
     id Integer,
     assignment_name varchar(30),
     admin_users_id Integer,
     Constraint assignments_id_pk primary key (id),
     Constraint assignmentid_adminusersid_users_fk foreign key (admin_users_id) references users (id)
     );

insert into assignments values(1,'asg1',2);

insert into assignments values(2,'test',5);

 select * from assignments;





 create table assignment_allocations(
     id Integer,
     assignment_id Integer,
     candidate_user_id Integer,
     Constraint assignmentAllocation_id_pk primary key (id),
     Constraint assignmentAllocation_condidateusers_users_fk foreign key (candidate_user_id) references users (id)
     );
 
 insert into assignment_allocations values(1,1,1);

 insert into assignment_allocations values(2,1,4);

 insert into assignment_allocations values(3,2,3);

 select * from assignment_allocations;






create table assignment_questions(
     id Integer,
     assignment_id Integer,
     question_id Integer,
     Constraint assignmentquestion_id_pk primary key (id),                        -> Constraint assignmentalloc_questionid_fk foreign key (question_id) references questions(id),
     Constraint assignmentalloc_assignmentid_fk foreign key (assignment_id) references assignments (id)
    );

 insert into assignment_questions values(1,1,1);

 insert into assignment_questions values(2,1,3);

 insert into assignment_questions values(3,2,1);

 insert into assignment_questions values(4,2,2);

 insert into assignment_questions values(5,2,3);
 
select * from assignment_questions;


create table candidate_submissions(
     id Integer,
     assignment_id Integer,
     candidate_user_id Integer,
     question_id Integer,
     Constraint candidatesubmission_id_pk primary key (id),
     Constraint candidatesubmission_assignment_fk foreign key (assignment_id) references assignments (id),
     Constraint candidatesubmission_candidateuser_fk foreign key (candidate_user_id) references users (id),
     Constraint candidatesubmission_questionid_fk foreign key (question_id) references questions (id)
     );
insert into candidate_submissions values(1,1,1,1);

 insert into candidate_submissions values(2,1,1,3);

 insert into candidate_submissions values(3,2,3,1);

 insert into candidate_submissions values(4,2,3,2);

 insert into candidate_submissions values(5,2,3,3);

 insert into candidate_submissions values(6,1,4,1);

 insert into candidate_submissions values(7,1,4,3);

select * from candidate_submissions;




create table candidate_submission_answers(
     id Integer,
     candidate_submissions_id Integer,
     option_id Integer,
     Constraint candidateanswer_id_pk primary key (id),
     Constraint candidateanswer_candidatesubmission_fk foreign key (candidate_submissions_id) references candidate_submissions (id),
     Constraint candidateanswer_option_fk foreign key (option_id) references options (id)
     );

 insert into candidate_submission_answers values(1,1,1);

 insert into candidate_submission_answers values(2,2,2);

 insert into candidate_submission_answers values(3,3,2);

 insert into candidate_submission_answers values(4,4,1);

 insert into candidate_submission_answers values(5,5,3);

 insert into candidate_submission_answers values(6,6,2);

 insert into candidate_submission_answers values(7,7,1);

 select * from candidate_submission_answers;




create table assignment_scores(
     id Integer,
     candidate_user_id Integer,
     score Integer,
     assignment_id Integer,
     Constraint assignmentscore_id_pk primary key (id),
     Constraint assignmentscore_candidateusers_fk foreign key (candidate_user_id) references users (id),
     Constraint assignmentscore_assignment_fk foreign key (assignment_id) references assignments(id)
     );

 insert into assignment_scores values(1,1,56,1);

 insert into assignment_scores values(2,3,66,1);

 insert into assignment_scores values(3,4,60,2);

 select *from assignment_scores;





create view candidate_data_view AS
     select u.id,u.username,u.password,a.score
     from users u,assignment_scores a
     where u.id = a.candidate_user_id
     ;


 select *from candidate_data_view;


create view pivot1 as
     (select a.id,a.username,d.question,e.option_name from users a,candidate_submissions b,candidate_submission_answers c,questions d,options e where a.id = b.candidate_user_id and b.id = c.candidate_submissions_id and b.question_id = d.id and c.option_id = e.id);



SET @sql = NULL;

select group_concat(distinct concat('if(p.question = ''',question,''',option_name,NULL) as ',quote(question))) into @sql from pivot1 p;
 
set @sql = concat('select p.id,p.username,',@sql,' from pivot1 p');

prepare st from @sql;
execute st;

































