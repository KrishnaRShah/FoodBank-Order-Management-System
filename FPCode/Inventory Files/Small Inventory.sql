/* ENSF 409 W22 Project Example Database
 2022 Barcomb and Marasco
 
 Each time this file is executed, it will reset the database to the original state defined below.
 
 */

DROP DATABASE IF EXISTS FOOD_INVENTORY;
CREATE DATABASE FOOD_INVENTORY; 
USE FOOD_INVENTORY;


DROP TABLE IF EXISTS DAILY_CLIENT_NEEDS;
CREATE TABLE DAILY_CLIENT_NEEDS (
	ClientID		int not null AUTO_INCREMENT,
	Client			varchar(25),
	WholeGrains		int,
	FruitVeggies	int,
	Protein			int,
	Other			int,
	Calories		int,
	primary key (ClientID)
);

INSERT INTO DAILY_CLIENT_NEEDS (Client, WholeGrains, FruitVeggies, Protein, Other, Calories)
VALUES
('Adult Male',	16,	28,	26,	30,	2500),
('Adult Female', 16, 28, 26, 30, 2000),
('Child over 8', 21, 33, 31, 15, 2200),
('Child under 8', 21, 33, 31, 15, 1400);

DROP TABLE IF EXISTS AVAILABLE_FOOD;
CREATE TABLE AVAILABLE_FOOD (
	ItemID			int not null AUTO_INCREMENT,
	Name			varchar(50),
	GrainContent	int,
	FVContent		int,
	ProContent		int,
	Other			int,
	Calories		int,
	primary key (ItemID)
);

INSERT INTO AVAILABLE_FOOD (Name, GrainContent, FVContent, ProContent, Other, Calories)
VALUES
('Tomato Sauce, jar', 0, 80, 10, 10, 120),
('Apple, dozen', 0, 100, 0, 0, 624),
('Granola Bar, box', 71, 0, 6, 23, 936),
('Baby carrots, pound', 0, 100, 0, 0, 159),
('Broccoli, 3 bunches', 0, 92, 8, 0, 621),
('Wheat bread, loaf', 96, 0, 4, 0, 2192),
('Orange, dozen', 0, 100, 0, 0, 864),
('Eggs, dozen', 0, 0, 9, 91, 864),
('Chicken breast, pound', 0, 0, 70, 30, 730), 
('Pasta, dry, pound', 75, 0, 13, 12, 1683),
('Cheddar cheese, pound', 0, 0, 23, 77, 1851),
('Bananas, bunch', 0, 100, 0, 0, 1740),
('Whole grain bread, loaf', 89, 0, 11, 0, 1904),
('Ground beef, pound', 0, 0, 74, 26, 1179),
('Corn tortillas, pound', 94, 0, 6, 0, 989),
('Pasta, dry, two pounds', 75, 0, 13, 12, 2366), 
('Ground pork, pound', 0, 0, 83, 17, 1193),
('Cauliflower, 2 heads', 0, 100, 0, 0, 420),
('Strawberries, 2 kg', 0, 100, 0, 0, 640),
('Trail mix, 1 kg', 21, 0, 20, 59, 6000), 
('Soy protein, 1 kg', 0, 0, 88, 12, 3350); 
