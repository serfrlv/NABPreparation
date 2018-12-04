CREATE TABLE users(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
);


CREATE TABLE transactions(
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_Id INT,
  user_Name VARCHAR(50),
  amount DOUBLE,
  date DATE,
  target_User_Id INT,
  target_User_Name VARCHAR (50)
);
