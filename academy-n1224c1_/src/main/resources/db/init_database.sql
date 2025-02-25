create database student_management_n1224c1;
use student_management_n1224c1;
create table student(
                        id int auto_increment primary key ,
                        name nvarchar(50),
                        score double
);
insert into student(name,score) values ('thanh',8);
insert into student(name,score) values ('hai',8.2);
insert into student(name,score) values ('linh',8);

select * from student;

update student set name='thanh1', score=8.0 where id=1;


create table employee(
    id int auto_increment primary key,
    name nvarchar(50),
    datetime datetime,
    gender ENUM('Male','Female'),
    salary double
);
INSERT INTO employee(name, datetime, gender, salary)
VALUES ('thanh', '2003-11-22 00:00:00', 'Female', 15000000),
       ('hai', '2005-09-30 00:00:00', 'Male', 15000000);

select * from employee;