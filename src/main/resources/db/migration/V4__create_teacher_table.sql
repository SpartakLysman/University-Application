CREATE TABLE teachers (
  id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
  name VARCHAR(100) NOT NULL,
  surname VARCHAR(100) NOT NULL,
  login VARCHAR(100) NOT NULL,
  password VARCHAR(150) NOT NULL,
  PRIMARY KEY (id)
);
