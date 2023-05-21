

CREATE DATABASE kalil_school_system;
\c kalil_school_system;

CREATE USER schooluser WITH PASSWORD '12345';

GRANT ALL PRIVILEGES ON DATABASE kalil_school_system TO schooluser;

-- tabela user
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  role VARCHAR(20) NOT NULL
);

--  tabela estudante
CREATE TABLE students (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  date_of_birth DATE NOT NULL,
  gender VARCHAR(10) NOT NULL,
  grade INTEGER NOT NULL,
  address VARCHAR(100),
  parent_name VARCHAR(100),
  contact_number VARCHAR(20)
);

-- Insert a user for login and update table permissions
INSERT INTO users (username, password, role) VALUES ('admin', '12345', 'admin');
ALTER TABLE users OWNER TO schooluser;
ALTER TABLE students OWNER TO schooluser;