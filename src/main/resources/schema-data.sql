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