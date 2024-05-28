-- Step 1: Drop the foreign key constraint on group_id (if it exists)
ALTER TABLE schedule DROP CONSTRAINT IF EXISTS schedule_group_id_fkey;

-- Step 2: Modify the group_id column to allow NULL values
ALTER TABLE schedule ALTER COLUMN group_id DROP NOT NULL;

-- Step 3: Recreate the foreign key constraint on group_id
ALTER TABLE schedule
    ADD CONSTRAINT schedule_group_id_fkey FOREIGN KEY (group_id) REFERENCES groups(id);
