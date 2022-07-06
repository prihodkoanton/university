drop schema if exists university cascade;
create schema university;

CREATE TABLE university.courses
(
    course_id          bigserial NOT NULL,
    course_name        text      NOT NULL,
    course_description text      NOT NULL,
    CONSTRAINT course_pkey PRIMARY KEY (course_id)
);