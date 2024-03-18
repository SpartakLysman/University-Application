CREATE TABLE students_courses (
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    
    PRIMARY KEY (student_id, course_id)   
)