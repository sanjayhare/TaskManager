## Taks Manager
create database taskmanager;

CREATE TABLE IF NOT EXISTS `Projects` (
  `project_Id` int NOT NULL AUTO_INCREMENT,
  `project_Name` varchar(100) NOT NULL,
  `date_Of_Start` varchar(10) NOT NULL,
  `no_Of_Teammates` varchar(50) NOT NULL,
   PRIMARY KEY (`person_Id`)
);

CREATE TABLE IF NOT EXISTS `countries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `country_name` varchar(100) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`id`)
);

select * from Users;

CREATE TABLE IF NOT EXISTS `Users` (
  `person_Id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `email_Id` varchar(50) NOT NULL,
  `mobile_Number` varchar(20) NOT NULL,
  `date_Of_Birth` varchar(50) NOT NULL,
  `address` varchar(20) NOT NULL,
  `pwd` varchar(200) NOT NULL,
  `authorities` varchar(200) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`person_Id`)
);
