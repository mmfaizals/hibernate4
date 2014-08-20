CREATE TABLE `person`(  
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255),
  `last_name` VARCHAR(255),
  PRIMARY KEY (`id`)
) ENGINE=INNODB;


CREATE TABLE `email`(  
  `id` INT NOT NULL AUTO_INCREMENT,
  `person_id` INT NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`person_id`) REFERENCES `person`(`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `transport`(  
  `id` INT NOT NULL AUTO_INCREMENT,
  `transport_type` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `person_transport`(
  `person_id` INT NOT NULL,
  `transport_id` INT NOT NULL,
  FOREIGN KEY (`person_id`) REFERENCES `person`(`id`),
  FOREIGN KEY (`transport_id`) REFERENCES `transport`(`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `country`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_bin;

ALTER TABLE `person`   
  ADD COLUMN `country_id` INT NULL ,
  ADD FOREIGN KEY (country_id) REFERENCES `country`(`id`);		

INSERT INTO country (name) VALUES
 ('India'),('Japan');


CREATE TABLE `passport`(  
  `id` INT NOT NULL AUTO_INCREMENT,
  `passport_number` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `person`(`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `employee`(  
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255),
  `last_name` VARCHAR(255),
  `joining_date` DATE,
  `discriminator` VARCHAR(255),
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `permanent_employee`(  
  `id` INT NOT NULL,
  `salary_per_month` DOUBLE,
  `bonus` DOUBLE,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `employee`(`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `contract_employee`(  
  `id` INT NOT NULL,
  `salary_per_hour` DOUBLE,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES `employee`(`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_bin;

/*
 * SQL for Table per concrete class
 * */


drop table permanent_employee;
drop table contract_employee;
drop table employee;

CREATE TABLE `permanent_employee`(  
  `id` INT NOT NULL ,
  `first_name` VARCHAR(255),
  `last_name` VARCHAR(255),
  `joining_date` DATE,
  `salary_per_month` DOUBLE,
  `bonus` DOUBLE,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `contract_employee`(  
  `id` INT NOT NULL ,
  `first_name` VARCHAR(255),
  `last_name` VARCHAR(255),
  `joining_date` DATE,
  `salary_per_hour` DOUBLE,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `hibernate_unique_key`(
 `next_hi` INT NULL 
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_bin;

insert into hibernate_unique_key (next_hi) values (1);


CREATE TABLE `order`(
`id` INT NOT NULL,
`name` VARCHAR(255),
PRIMARY KEY (id)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `line_item`(
`id` INT NOT NULL,
`name` VARCHAR(255),
`order_id` INT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (order_id) REFERENCES `order`(id)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_bin;