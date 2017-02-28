ALTER TABLE `teardowall`.`users` 
ADD COLUMN `longitude` VARCHAR(45) NULL AFTER `county_name`,
ADD COLUMN `latitude` VARCHAR(45) NULL AFTER `longitude`,
ADD COLUMN `ip` VARCHAR(45) NULL AFTER `latitude`;
