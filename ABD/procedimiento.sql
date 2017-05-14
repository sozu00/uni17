DELIMITER $$
CREATE PROCEDURE CambiarGrupo(IN id INT, IN g VARCHAR(25))
BEGIN
  UPDATE Miembro
  SET grupo = g
  WHERE idMiembro = id;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE CambiarDepartamento(IN id INT, IN D VARCHAR(25))
BEGIN
  UPDATE Miembro
  SET departamento = D
  WHERE idMiembro = id;
END$$
DELIMITER ;
