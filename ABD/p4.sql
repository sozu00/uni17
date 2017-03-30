use banco;

CREATE TABLE Banco (
  Numero_cuenta char(20) NOT NULL,
  Nombre varchar(50) ,
  Apellidos varchar(50) ,
  Saldo float NOT NULL DEFAULT '0',
  PRIMARY KEY (Numero_cuenta)
);

CREATE TABLE Operacion(
  id INT AUTO_INCREMENT,
  asunto VARCHAR(100),
  fecha DATETIME NOT NULL DEFAULT NOW(),
  cantidad FLOAT NOT NULL,
  cuenta char(20) NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT cuenta FOREIGN KEY (cuenta) REFERENCES Banco(Numero_cuenta)
);


CREATE TRIGGER nueva_operacion
    AFTER INSERT ON Operacion FOR EACH ROW
    UPDATE Banco SET Saldo = Saldo + NEW.cantidad
    where Numero_cuenta = NEW.cuenta;

DELIMITER $$
CREATE TRIGGER nueva_cuenta
  BEFORE INSERT ON Banco FOR EACH ROW
  BEGIN
    IF NEW.Saldo < 0 THEN
      SET NEW.Saldo = 0;
    END IF;
  END$$
DELIMITER ;

INSERT INTO Banco (Numero_cuenta, Nombre, Apellidos) VALUES ("12345678901234567890", "juan", "muñoz");
INSERT INTO Banco (Numero_cuenta, Nombre, Apellidos, Saldo) VALUES ("12345678901234567891", "pepe", "perez", -200);

INSERT INTO Operacion(asunto, cantidad, cuenta) VALUES ("incremento", 100, "12345678901234567890");


DELIMITER $$
CREATE PROCEDURE transaccion(IN cuenta1 CHAR(20), IN cuenta2 CHAR(20), IN cant FLOAT, IN concepto VARCHAR(100))
BEGIN
  DECLARE EXIT HANDLER FOR 1452 ROLLBACK;
  START TRANSACTION;
  IF cant < 0 THEN
    SIGNAL SQLSTATE 'ERROR' SET MESSAGE_TEXT = 'Cantidad negativa';
  END IF;
  INSERT INTO Operacion(asunto, cantidad, cuenta) VALUES (concepto, -cant, cuenta1);
  INSERT INTO Operacion(asunto, cantidad, cuenta) VALUES (concepto, cant, cuenta2);

  COMMIT;
END$$
DELIMITER ;


/*call transaccion(12345678901234567890, 12345678901234567891, -1000, 'transaccionPRUEBA');
*/
