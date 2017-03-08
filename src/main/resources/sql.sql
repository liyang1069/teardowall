ALTER TABLE `teardowall`.`users` 
ADD COLUMN `longitude` VARCHAR(45) NULL AFTER `county_name`,
ADD COLUMN `latitude` VARCHAR(45) NULL AFTER `longitude`,
ADD COLUMN `ip` VARCHAR(45) NULL AFTER `latitude`;

CREATE TABLE `teardowall`.`donate_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `donator_name` VARCHAR(255) NULL,
  `receiver` VARCHAR(255) NULL,
  `amount` DOUBLE NULL,
  `created_date` DATETIME NULL,
  `updated_date` DATETIME NULL,
  PRIMARY KEY (`id`));