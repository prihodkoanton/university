drop schema if exists university cascade;
create schema university;

create table university.courses
(
    course_id          bigserial primary key,
    course_name        text,
    course_description text
);


create table university.groups
(
    group_id   bigserial primary key,
    group_name text
);

create table university.users
(
    user_id   bigserial primary key,
    user_name text not null,
    user_type text not null
);

create table university.students
(
    user_ref  bigint primary key references university.users (user_id),
    group_ref bigint references university.groups (group_id)
);

create table university.teachers
(
    user_ref   bigint primary key references university.users (user_id),
    course_ref bigint references university.courses (course_id)
);
