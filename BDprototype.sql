CREATE SCHEMA IF NOT EXISTS `monitime` DEFAULT CHARACTER SET utf8 ;
USE `monitime` ;

-- -----------------------------------------------------
-- Table `monitime`.`disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `monitime`.`disciplina` (
  `dissigla` VARCHAR(9) NOT NULL,
  `disnome` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`dissigla`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `monitime`.`horario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `monitime`.`horario` (
  `horid` INT(11) NOT NULL AUTO_INCREMENT,
  `horini` TIME NOT NULL,
  `horfim` TIME NOT NULL,
  PRIMARY KEY (`horid`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `monitime`.`listasalas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `monitime`.`listasalas` (
  `ltsid` INT(11) NOT NULL AUTO_INCREMENT,
  `ltsnome` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ltsid`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `monitime`.`sala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `monitime`.`sala` (
  `salid` VARCHAR(30) NOT NULL,
  `saldiasemana` VARCHAR(20) NOT NULL,
  `salhoraini` TIME NOT NULL,
  `salhorafim` TIME NOT NULL,
  `salocupacao` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`salid`, `saldiasemana`, `salhoraini`, `salhorafim`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `monitime`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `monitime`.`usuario` (
  `usumatricula` VARCHAR(13) NOT NULL,
  `usunome` VARCHAR(60) NOT NULL,
  `ususenha` VARCHAR(10) NULL DEFAULT '123',
  `usutipo` VARCHAR(3) NULL DEFAULT 'alu',
  PRIMARY KEY (`usumatricula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `monitime`.`monitor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `monitime`.`monitor` (
  `monusumatricula` VARCHAR(13) NOT NULL,
  `monmtndissigla` VARCHAR(9) NOT NULL,
  `mondtinicio` DATE NOT NULL,
  `mondtfim` DATE NOT NULL,
  PRIMARY KEY (`monusumatricula`, `monmtndissigla`),
  INDEX `monitor_fk_disciplina` (`monmtndissigla` ASC),
  CONSTRAINT `monitor_fk_disciplina`
    FOREIGN KEY (`monmtndissigla`)
    REFERENCES `monitime`.`disciplina` (`dissigla`),
  CONSTRAINT `monitor_fk_usuario`
    FOREIGN KEY (`monusumatricula`)
    REFERENCES `monitime`.`usuario` (`usumatricula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `monitime`.`Monitoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `monitime`.`Monitoria` (
  `moaid` INT NOT NULL AUTO_INCREMENT,
  `moamonusumatricula` VARCHAR(13) NOT NULL,
  `moadissigla` VARCHAR(9) NOT NULL,
  `moasalid` VARCHAR(30) NOT NULL,
  `moasaldiasemana` VARCHAR(20) NOT NULL,
  `moasalhoraini` TIME NOT NULL,
  `moasalhorafim` TIME NOT NULL,
  PRIMARY KEY (`moaid`, `moamonusumatricula`, `moadissigla`, `moasalid`, `moasaldiasemana`, `moasalhoraini`, `moasalhorafim`),
  INDEX `monitoria_fk_lotsalid` (`moasalid` ASC, `moasaldiasemana` ASC, `moasalhoraini` ASC, `moasalhorafim` ASC),
  INDEX `monitoria_fk_monitor_idx` (`moamonusumatricula` ASC),
  CONSTRAINT `monitoria_fk_lotdissigla`
    FOREIGN KEY (`moadissigla`)
    REFERENCES `monitime`.`disciplina` (`dissigla`),
  CONSTRAINT `monitoria_fk_lotsalid`
    FOREIGN KEY (`moasalid` , `moasaldiasemana` , `moasalhoraini` , `moasalhorafim`)
    REFERENCES `monitime`.`sala` (`salid` , `saldiasemana` , `salhoraini` , `salhorafim`),
  CONSTRAINT `monitoria_fk_monitor`
    FOREIGN KEY (`moamonusumatricula`)
    REFERENCES `monitime`.`monitor` (`monusumatricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `monitime`.`Lotaçao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `monitime`.`Lotaçao` (
  `lotmoaid` INT NOT NULL,
  `lotusumatricula` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`lotmoaid`, `lotusumatricula`),
  INDEX `lotacao_fk_usuario_idx` (`lotusumatricula` ASC),
  CONSTRAINT `lotacao_fk_monitoria`
    FOREIGN KEY (`lotmoaid`)
    REFERENCES `monitime`.`Monitoria` (`moaid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `lotacao_fk_usuario`
    FOREIGN KEY (`lotusumatricula`)
    REFERENCES `monitime`.`usuario` (`usumatricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `monitime` ;