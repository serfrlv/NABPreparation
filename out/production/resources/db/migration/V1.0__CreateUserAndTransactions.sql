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

INSERT INTO users (name) VALUES ('James.Bond');
INSERT INTO users (name) VALUES ('Tom.Hanks');
INSERT INTO users (name) VALUES ('Jimmy.White');

INSERT INTO transactions VALUES(1, 1, 'James.Bond',50.0, '2018-12-01', 2,'Tom.Hanks');
INSERT INTO transactions VALUES(2, 1, 'James.Bond',20.0, '2018-12-03', 3,'Jimmy.White');
INSERT INTO transactions VALUES(3, 2, 'Tom.Hanks',20.0, '2018-12-04', 3,'Jimmy.White');