-- Copyright 2013 Sean McKenna
-- 
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
-- 
--        http://www.apache.org/licenses/LICENSE-2.0
-- 
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

-- inspired by David Adrian & Nancy Decker
-- collection of various SQL queries, used in PHP code
-- not intended to be used alone


-- simple queries
SELECT quiz_name FROM {quiz} WHERE algorithm = \"$alg\"
SELECT visualizer FROM {quiz} WHERE algorithm = \"$alg\"
SELECT nid FROM {node} WHERE type = \"algo_lesson\" AND title = '%s'
SELECT status FROM {node} WHERE type = \"algo_lesson\" AND nid = '%s'
SELECT quiz_name FROM {quizzes_in_lesson} WHERE lesson_title = '%s'
SELECT algorithm FROM {quiz} WHERE quiz_name = '%s'
SELECT intro FROM {lessons} WHERE title = '%s'


-- simple creation
INSERT INTO lessons (title, authors, intro, ref, username, category, status) VALUES ('%s', '%s', '%s','%s', '%s','%s','%d')
INSERT INTO sections (title, section_title, section_number, content) VALUES ('%s', '%s', '%d', '%s')
INSERT INTO classes (class) VALUES ('%s')
INSERT INTO professors (professor, class) VALUES ('%s', '%s')
INSERT INTO students (student, class) VALUES ('%s', '%s')
INSERT INTO assigned_lessons (class, professor, lesson, time_due) VALUES ('%s', '%s', '%s', '%s')
INSERT INTO assignment_complete (aid, student, complete) VALUES ('%s', '%s', 0)
INSERT INTO {quizzes_in_lesson} (lesson_title, quiz_name) VALUES ($lesson_title, $quiz_name)


-- simple deletions
DELETE FROM {sections} WHERE title ="'.$form_state['values']['fullname'].'"
DELETE FROM {lessons} WHERE title ="'.$form_state['values']['fullname'].'"
DELETE FROM {quizzes_in_lesson} WHERE lesson_title ="'.$form_state['values']['fullname'].'"


-- update query
UPDATE assignment_complete SET complete=" . $set . " WHERE student='%s' AND aid='%s'


-- querying for all information
SELECT * FROM {lessons} WHERE title = '%s'
SELECT * FROM {sections} WHERE title = '%s'
SELECT * from lessons where title= '%s'
SELECT * FROM {categories}
SELECT * FROM professors WHERE professor LIKE '%s' AND class LIKE '%s'


-- more complex queries
SELECT aid FROM assignment_complete WHERE student='%s' AND complete=0
SELECT * FROM assigned_lessons WHERE aid=" . $ele . " AND time_due > NOW()
SELECT * FROM assignment_complete c, assigned_lessons l WHERE c.student='%s' AND c.complete=0 AND l.time_due > NOW() AND l.aid=c.aid
SELECT time_assigned, time_due FROM assigned_lessons WHERE lesson='%s' AND class='%s'
SELECT a.lesson, a.class, a.aid, c.complete, a.time_due FROM assigned_lessons a, students s, assignment_complete c WHERE a.class=s.class AND s.student=c.student AND s.student='%s' AND a.aid=c.aid AND c.complete=0 AND a.time_due > NOW() ORDER BY a.class ASC
SELECT * FROM assigned_lessons WHERE UPPER(lesson)=UPPER('%s') AND UPPER(class)=UPPER('%s') AND UPPER(professor)=UPPER('%s')
SELECT * FROM quiz_taken WHERE email='%s' AND quiz_name IN $ele AND (time BETWEEN '". $time_assigned . "' AND '" . $time_due . "') ORDER BY quiz_name ASC limit 20
SELECT COUNT(a.aid) AS ct FROM assigned_lessons a, students s, assignment_complete c WHERE a.class=s.class AND s.student=c.student AND s.student='%s' AND a.aid=c.aid AND c.complete=1
SELECT a.lesson, a.class, a.aid, c.complete FROM assigned_lessons a, students s, assignment_complete c WHERE a.class=s.class AND s.student=c.student AND s.student='%s' AND a.aid=c.aid AND c.complete=1 ORDER BY a.class ASC
SELECT COUNT(a.aid) AS ct FROM assigned_lessons a, students s, assignment_complete c WHERE a.class=s.class AND s.student=c.student AND s.student='%s' AND a.aid=c.aid AND c.complete=0 AND a.time_due < NOW()
SELECT a.lesson, a.class, a.aid, c.complete FROM assigned_lessons a, students s, assignment_complete c WHERE a.class=s.class AND s.student=c.student AND s.student='%s' AND a.aid=c.aid AND c.complete=0 AND a.time_due < NOW() ORDER BY a.class ASC


-- special types of queries
SELECT COUNT(normalized_score) AS count FROM quiz_taken WHERE email='%s' AND quiz_name='%s' AND (time BETWEEN '". $time_assigned . "' AND '" . $time_due . "') ORDER BY quiz_name ASC
SELECT COUNT(normalized_score) AS count FROM quiz_taken WHERE email='%s' AND quiz_name='%s' AND (time BETWEEN '". $time_assigned . "' AND '" . $time_due . "') ORDER BY quiz_name ASC
SELECT COUNT(algorithm) AS count FROM quiz
SELECT COUNT(class) AS ct FROM professors WHERE professor = '%s'
SELECT COUNT(class) AS ct FROM students WHERE student = '%s'
SELECT COUNT(aid) AS ct FROM assigned_lessons WHERE professor = '%s'
SELECT COUNT(a.aid) AS ct FROM assigned_lessons a, students s, assignment_complete c WHERE a.class=s.class AND s.student=c.student AND s.student='%s' AND a.aid=c.aid AND c.complete=0 AND a.time_due > NOW()
SELECT DISTINCT algo_type FROM {quiz} ORDER BY algo_type
