CREATE TABLE users (
  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
  group_id BIGINT,
  name VARCHAR(100) NOT NULL,
  surname VARCHAR(100) NOT NULL,
  login VARCHAR(100) NOT NULL,
  password VARCHAR(150) NOT NULL,
  role VARCHAR(50) NOT NULL,
  
  PRIMARY KEY (id)
);

ALTER TABLE users
ADD CONSTRAINT chk_valid_role
CHECK (role IN ('ADMIN', 'TEACHER', 'STUDENT'));
