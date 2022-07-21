insert into university.rooms (room_id, room_title)
VALUES (100, 'room for core java'),
       (101, 'room for spring'),
       (102, 'room for english');
       
insert into university.groups (group_id, group_name)
VALUES (100, 'AA-01'),
       (101, 'BB-02'),       
       (102, 'CC-03');       

insert into university.courses (course_id, course_name, course_description)
VALUES (100, 'java', 'Java course'),
       (101, 'english', 'English course'),
       (102, 'math', 'Math course');


insert into university.users (user_id, user_name, user_type)
VALUES (100, 'john', 'TEACHER'),
       (101, 'alice', 'TEACHER'),
       (102, 'bob', 'TEACHER');

insert into university.teachers (user_ref, course_ref)
VALUES (100, 100),
       (101, 101),
       (102, 102);
       
insert into university.lessons(lesson_id, lesson_day_of_week, lesson_time_span, room_ref, group_ref, course_ref, teacher_ref)
VALUES (100, 'MONDAY', 1, 100, 100, 100, 100),
       (101, 'TUESDAY', 1, 101, 101, 101, 101),
       (102, 'WEDNESDAY', 1, 102, 102, 102, 102);
       
        