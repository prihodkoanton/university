drop schema if exists university cascade;
create schema university;

CREATE TABLE university.courses
(
    course_id          bigserial NOT NULL,
    course_name        text      NOT NULL,
    course_description text      NOT NULL,
    CONSTRAINT course_pkey PRIMARY KEY (course_id)
);

create table university.groups
(
    group_id   bigserial not null,
    group_name text      not null,
    constraint group_pkey primary key (group_id)
);

create table university.users
(
    user_id   bigserial not null,
    user_name text      not null,
    user_type text      not null,
    constraint user_pkey primary key (user_id)
);

create table university.students
(
    users_ref bigint PRIMARY KEY references university.users (user_id),
    group_ref bigint REFERENCES university.groups (group_id)
);

create table university.teachers
(
    user_ref   bigint primary key references university.users (user_id),
    course_ref bigint references university.courses (course_id)
);


create table university.rooms
(
    room_id bigserial not null,
    title   text,
    constraint room_pkey primary key (room_id)
);

create table university.lesson
(
    lesson_id   bigserial                                        not null,
    day_of_weak text                                             not null,
    time_span   int                                              not null,
    room_ref    bigint references university.rooms (room_id)     not null,
    group_ref   bigint references university.groups (group_id)   not null,
    course_ref  bigint references university.courses (course_id) not null,
    teacher_ref bigint references university.users (user_id)     not null,
    constraint lesson_pkey primary key (lesson_id),
    unique (day_of_weak, time_span, room_ref),
    unique (day_of_weak, time_span, group_ref),
    unique (day_of_weak, time_span, teacher_ref)
);