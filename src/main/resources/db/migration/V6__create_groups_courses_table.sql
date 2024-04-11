CREATE TABLE groups_courses (
    group_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    
   PRIMARY KEY (group_id, course_id)
)