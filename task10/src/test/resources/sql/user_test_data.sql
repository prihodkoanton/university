insert into courses (id, course_name, course_description)
VALUES (100, 'java', 'Java course'),
       (101, 'english', 'English course'),
       (103, 'math', 'Math course'),
       (104, 'test', 'test');

insert into groups (id, group_name)
VALUES (100, 'AA-01'),
       (101, 'BB-02'),
       (102, 'CC-03');

insert into users (id, user_name, user_type, user_role, user_password)
VALUES (100, 'john', 'TEACHER', 'USER', '12345678'),
       (101, 'peter', 'STUDENT', 'USER', '12345678'),
       (102, 'alice', 'TEACHER', 'USER', '12345678'),
       (103, 'bob', 'STUDENT', 'USER', '12345678'),
       (104, 'none', 'NONE', 'ADMIN', '12345678');

insert into teachers (id, course_id)
VALUES (100, 100),
       (102, 101);

insert into students (id, group_id)
VALUES (101, 100),
       (103, 101);
       
