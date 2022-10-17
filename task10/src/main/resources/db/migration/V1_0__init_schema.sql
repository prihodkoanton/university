CREATE TABLE courses
(
    id          bigserial        NOT NULL,
    course_name        text      NOT NULL,
    course_description text      NOT NULL,
    CONSTRAINT course_pkey PRIMARY KEY (id)
);

create table groups
(
    id   bigserial not null,
    group_name text      not null,
    constraint group_pkey primary key (id)
);

create table users
(
    id   bigserial     not null,
    user_name text          not null,
    user_type text          not null,
    user_role text          not null,
    user_password text      not null,
    constraint user_pkey primary key (id)
);

create table students
(
    id  bigint PRIMARY KEY references users (id) ON DELETE CASCADE,
    group_id bigint REFERENCES groups (id) ON DELETE CASCADE
);

create table teachers
(
    id   bigint primary key references users (id) ON DELETE CASCADE,
    course_id bigint references courses (id)ON DELETE CASCADE
);


create table rooms
(
    id    bigserial not null,
    room_title text not null,
    constraint room_pkey primary key (id)
);

create table lessons
(
    id          bigserial                                               not null,
    lesson_day_of_week text                                             not null,
    lesson_time_span   int                                              not null,
    room_ref           bigint references rooms (id)     not null,
    group_ref          bigint references groups (id)   not null,
    course_ref         bigint references courses (id) not null,
    teacher_ref        bigint references users (id)     not null,
    constraint lesson_pkey primary key (id),
    unique (lesson_day_of_week, lesson_time_span, room_ref),
    unique (lesson_day_of_week, lesson_time_span, group_ref),
    unique (lesson_day_of_week, lesson_time_span, teacher_ref)
);
