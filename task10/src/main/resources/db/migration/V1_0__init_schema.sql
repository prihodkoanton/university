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
    user_ref  bigint PRIMARY KEY references university.users (user_id) ON DELETE CASCADE,
    group_ref bigint REFERENCES university.groups (group_id) ON DELETE CASCADE
);

create table university.teachers
(
    user_ref   bigint primary key references university.users (user_id) ON DELETE CASCADE,
    course_ref bigint references university.courses (course_id)ON DELETE CASCADE
);


create table university.rooms
(
    room_id    bigserial not null,
    room_title text not null,
    constraint room_pkey primary key (room_id)
);

create table university.lessons
(
    lesson_id          bigserial                                        not null,
    lesson_day_of_week text                                             not null,
    lesson_time_span   int                                              not null,
    room_ref           bigint references university.rooms (room_id)     not null,
    group_ref          bigint references university.groups (group_id)   not null,
    course_ref         bigint references university.courses (course_id) not null,
    teacher_ref        bigint references university.users (user_id)     not null,
    constraint lesson_pkey primary key (lesson_id),
    unique (lesson_day_of_week, lesson_time_span, room_ref),
    unique (lesson_day_of_week, lesson_time_span, group_ref),
    unique (lesson_day_of_week, lesson_time_span, teacher_ref)
);
