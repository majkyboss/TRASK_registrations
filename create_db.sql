SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Role` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`User` ;

CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `degree` VARCHAR(45) NULL,
  `birth_number` VARCHAR(45) NOT NULL,
  `date_in` DATE NOT NULL,
  `date_out` DATE NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_User_Role_idx` (`role_id` ASC),
  CONSTRAINT `fk_User_Role`
    FOREIGN KEY (`role_id`)
    REFERENCES `mydb`.`Role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Branch`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Branch` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Branch` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `manager_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Branch_User1_idx` (`manager_id` ASC),
  CONSTRAINT `fk_Branch_User1`
    FOREIGN KEY (`manager_id`)
    REFERENCES `mydb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Unit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Unit` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Unit` (
  `user_id` INT NOT NULL,
  `branch_id` INT NOT NULL,
  PRIMARY KEY (`branch_id`, `user_id`),
  INDEX `fk_Unit_User1_idx` (`user_id` ASC),
  INDEX `fk_Unit_Branch1_idx` (`branch_id` ASC),
  CONSTRAINT `fk_Unit_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Unit_Branch1`
    FOREIGN KEY (`branch_id`)
    REFERENCES `mydb`.`Branch` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`RegState`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`RegState` ;

CREATE TABLE IF NOT EXISTS `mydb`.`RegState` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Registration`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Registration` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Registration` (
  `ico` VARCHAR(15) NOT NULL,
  `company_name` VARCHAR(45) NULL,
  `reg_date` DATE NOT NULL,
  `unit_user_id` INT NOT NULL,
  `unit_branch_id` INT NOT NULL,
  `regState_id` INT NOT NULL,
  PRIMARY KEY (`ico`, `reg_date`),
  INDEX `fk_Registration_Unit1_idx` (`unit_user_id` ASC, `unit_branch_id` ASC),
  INDEX `fk_Registration_RegState1_idx` (`regState_id` ASC),
  CONSTRAINT `fk_Registration_Unit1`
    FOREIGN KEY (`unit_user_id` , `unit_branch_id`)
    REFERENCES `mydb`.`Unit` (`user_id` , `branch_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Registration_RegState1`
    FOREIGN KEY (`regState_id`)
    REFERENCES `mydb`.`RegState` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Note` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_date` VARCHAR(45) NULL,
  `text` VARCHAR(45) NULL,
  `registration_ico` VARCHAR(15) NOT NULL,
  `registration_reg_date` DATE NOT NULL,
  PRIMARY KEY (`id`, `registration_ico`, `registration_reg_date`),
  INDEX `fk_Note_Registration1_idx` (`registration_ico` ASC, `registration_reg_date` ASC),
  CONSTRAINT `fk_Note_Registration1`
    FOREIGN KEY (`registration_ico` , `registration_reg_date`)
    REFERENCES `mydb`.`Registration` (`ico` , `reg_date`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
