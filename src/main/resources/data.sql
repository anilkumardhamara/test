CREATE DATABASE `test` ;

use test;

drop table if exists customer;
CREATE TABLE `customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `display_name` VARCHAR(45) NULL,
  `gender` VARCHAR(2) NULL,
  `age` INT(11) NULL,
  `product_id` INT(11) NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
  
  INSERT INTO `test`.`customer` (`first_name`, `last_name`, `display_name`, `gender`, `age`, `product_id`) VALUES ('JOHN', 'SMITH', 'JOHN S', 'M', '25', '1');
INSERT INTO `test`.`customer` (`first_name`, `last_name`, `display_name`, `gender`, `age`, `product_id`) VALUES ('NEAL', 'ADAMS', 'NEAL A', 'F', '26', '2');
INSERT INTO `test`.`customer` (`first_name`, `last_name`, `display_name`, `gender`, `age`, `product_id`) VALUES ('SHARN', 'WATTAS', 'SHARN W', 'F', '43', '1');
INSERT INTO `test`.`customer` (`first_name`, `last_name`, `display_name`, `gender`, `age`, `product_id`) VALUES ('JOHNY', 'GIBBS', 'GIBBS J', 'M', '18', '1');

drop table if exists employee;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `display_name` varchar(45) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



  
  