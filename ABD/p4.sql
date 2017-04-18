use banco;

CREATE TABLE cuenta (
  nCuenta char(20) NOT NULL,
  nombre varchar(20) ,
  apellidos varchar(20) ,
  saldo double NOT NULL DEFAULT 0,
  PRIMARY KEY (nCuenta)
);

CREATE TABLE operacion(
  idOperacion INT AUTO_INCREMENT,
  cuenta_nCuenta char(20) NOT NULL,
  asunto VARCHAR(100),
  fecha DATETIME NOT NULL DEFAULT NOW(),
  cantidad DOUBLE NOT NULL,
  PRIMARY KEY(idOperacion),
  CONSTRAINT cuenta FOREIGN KEY (cuenta_nCuenta) REFERENCES cuenta(nCuenta)
);


CREATE TRIGGER nueva_operacion
    AFTER INSERT ON operacion FOR EACH ROW
    UPDATE cuenta SET saldo = saldo + NEW.cantidad
    where nCuenta = NEW.cuenta_nCuenta;

DELIMITER $$
CREATE TRIGGER nueva_cuenta
  BEFORE INSERT ON cuenta FOR EACH ROW
  BEGIN
    IF NEW.saldo < 0 THEN
      SET NEW.saldo = 0;
    END IF;
  END$$
DELIMITER ;
/*
INSERT INTO cuenta (nCuenta, Nombre, apellidos) VALUES ("12345678901234567890", "juan", "muñoz");
INSERT INTO cuenta (nCuenta, Nombre, apellidos, saldo) VALUES ("12345678901234567891", "pepe", "perez", -200);

INSERT INTO operacion(asunto, cantidad, cuenta) VALUES ("incremento", 100, "12345678901234567890");

*/
DELIMITER $$
CREATE PROCEDURE transaccion(IN cuenta1 CHAR(20), IN cuenta2 CHAR(20), IN cant DOUBLE, IN concepto VARCHAR(100))
BEGIN
  DECLARE EXIT HANDLER FOR 1452 ROLLBACK;
  START TRANSACTION;
  IF cant < 0 THEN
    SIGNAL SQLSTATE 'ERROR' SET MESSAGE_TEXT = 'Cantidad negativa';
  END IF;
  INSERT INTO operacion(asunto, cantidad, cuenta_nCuenta) VALUES (concepto, -cant, cuenta1);
  INSERT INTO operacion(asunto, cantidad,cuenta_nCuenta) VALUES (concepto, cant, cuenta2);

  COMMIT;
END$$
DELIMITER ;


/*call transaccion(12345678901234567890, 12345678901234567891, -1000, 'transaccionPRUEBA');
*/
