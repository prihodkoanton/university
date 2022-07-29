insert into university.courses (course_id, course_name, course_description)
VALUES (100, 'java', 'Java course'),
       (101, 'english', 'English course'),
       (104, 'math', 'Math course');

insert into university.groups (group_id, group_name)
VALUES (100, 'AA-01'),
       (101, 'BB-02');

insert into university.users (user_id, user_name, user_type)
VALUES (100, 'john', 'TEACHER'),
       (101, 'peter', 'STUDENT'),
       (102, 'alice', 'TEACHER'),
       (103, 'bob', 'STUDENT');

insert into university.teachers (user_ref, course_ref)
VALUES (100, 100),
       (102, 101);

insert into university.students (user_ref, group_ref)
VALUES (101, 100),
       (103, 101);
       
