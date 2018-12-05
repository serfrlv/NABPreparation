CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);


CREATE TABLE transactions(
  id SERIAL PRIMARY KEY ,
  user_Id INT,
  user_Name VARCHAR(50),
  amount numeric,
  date DATE,
  target_User_Id INT,
  target_User_Name VARCHAR (50)
);