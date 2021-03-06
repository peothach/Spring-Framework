drop table if exists student CASCADE;

    create table student (
       student_id bigint generated by default as identity,
        student_address varchar(255),
        student_birthday date,
        student_email varchar(255),
        student_gender varchar(255),
        student_name varchar(255),
        student_phone varchar(255),
        primary key (student_id)
    );

    alter table student
           add column created_by varchar(255);

    alter table student
           add column created_date date;

    alter table student
           add column last_modified_by varchar(255);

    alter table student
           add column last_modified_date date;



