CREATE TABLE users(
  id INT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);


CREATE TABLE transactions(
  id INT PRIMARY KEY,
  user_Id INT,
  user_Name VARCHAR(50),
  amount numeric(10,6),
  date DATE,
  target_User_Id INT,
  target_User_Name VARCHAR (50)
);

INSERT INTO users (id,name) VALUES (1,'James.Bond');
INSERT INTO users (id,name) VALUES (2,'Tom.Hanks');
INSERT INTO users (id,name) VALUES (3,'Jimmy.White');

INSERT INTO transactions (id,user_Id,user_Name,amount,date,target_User_Id,target_User_Name)
  VALUES(1,1,'James.Bond',50.0,'2018-11-04',2,'Tom.Hanks');
INSERT INTO transactions (id,user_Id,user_Name,amount,date,target_User_Id,target_User_Name)
  VALUES(2,1,'James.Bond',20.0,'2018-11-05',3,'Jimmy.White');
INSERT INTO transactions (id,user_Id,user_Name,amount,date,target_User_Id,target_User_Name)
  VALUES(3,2,'Tom.Hanks',25.0,'2018-11-07',3,'Jimmy.White');