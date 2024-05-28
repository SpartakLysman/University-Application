-- Step 1: Drop the foreign key constraint on student_id
ALTER TABLE schedule DROP CONSTRAINT IF EXISTS schedule_student_id_fkey;

-- Step 2: Remove the student_id column
ALTER TABLE schedule DROP COLUMN IF EXISTS student_id;

-- Step 3: Add the group_id column
ALTER TABLE schedule ADD COLUMN group_id BIGINT NOT NULL;

-- Step 4: Create a new foreign key constraint on group_id
ALTER TABLE schedule
    ADD CONSTRAINT schedule_group_id_fkey FOREIGN KEY (group_id) REFERENCES groups(id);