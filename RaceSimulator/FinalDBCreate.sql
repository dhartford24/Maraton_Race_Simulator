CONNECT 'jdbc:derby:FinalDB;create=true';

-- drop tables (ignore errors if they don't exist)
DROP TABLE Runners;

-- create Runners table
CREATE TABLE Runners
(
    Name VARCHAR(20), 
	RunnersSpeed INTEGER, 
	RestPercentage INTEGER
);

-- insert data into Runners table
INSERT INTO Runners VALUES
('Tortoise', 10 , 0);   	

INSERT INTO Runners VALUES
('Hare', 100, 90);

INSERT INTO Runners VALUES
('Dog', 50, 40);

INSERT INTO Runners VALUES
('Cat', 30, 75);

-- view data in Runners table
SELECT * FROM Runners;

DISCONNECT;