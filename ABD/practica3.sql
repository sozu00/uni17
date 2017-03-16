use empresa;

CREATE TABLE Departamento(
  dep_id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50),
  PRIMARY KEY(dep_id)
);

CREATE TABLE Empleados(
  DNI VARCHAR(9),
  nombre VARCHAR(50),
  apellidos VARCHAR(50),
  dep_id INT NOT NULL,
  PRIMARY KEY(DNI),
  CONSTRAINT dep_id FOREIGN KEY (dep_id) REFERENCES Departamento(dep_id)
);

CREATE TABLE Proyectos(
  proy_id INT NOT NULL,
  nombre VARCHAR(50),
  PRIMARY KEY (proy_id)
);

CREATE TABLE EmpleadoProyecto(
  DNI VARCHAR(9) NOT NULL,
  proy_id INT NOT NULL,
  FOREIGN KEY (DNI) REFERENCES Empleados(DNI),
  FOREIGN KEY (proy_id) REFERENCES Proyectos(proy_id),
  PRIMARY KEY (DNI, proy_id)
);

/* No había puesto la clave primaria DNI en el Empleado pero al hacer la tabla EmpleadoPryecto me ha dado un
error al crear las claves foraneas porque tienen que ser claves primarias
*/
INSERT INTO Departamento (nombre) values ('d1');
INSERT INTO Departamento (nombre) values ('d2');
INSERT INTO Departamento (nombre) values ('d3');

INSERT INTO Empleados (DNI, nombre, apellidos, dep_id) values ('12345678A', 'wan','chu', 1);
INSERT INTO Empleados (DNI, nombre, apellidos, dep_id) values ('22345678A', 'zri','for', 2);
INSERT INTO Empleados (DNI, nombre, apellidos, dep_id) values ('32345678A', 'antonio','telera', 3);
INSERT INTO Empleados (DNI, nombre, apellidos, dep_id) values ('42345678A', 'ivan','barbosa', 1);
INSERT INTO Empleados (DNI, nombre, apellidos, dep_id) values ('52345678A', 'manuel','lara', 2);
INSERT INTO Empleados (DNI, nombre, apellidos, dep_id) values ('62345678A', 'jesus','iñiguez', 3);
INSERT INTO Empleados (DNI, nombre, apellidos, dep_id) values ('72345678A', 'wan','chu', 1);
INSERT INTO Empleados (DNI, nombre, apellidos, dep_id) values ('82345678A', 'wan','chu', 2);
INSERT INTO Empleados (DNI, nombre, apellidos, dep_id) values ('92345678A', 'wan','chu', 3);
INSERT INTO Empleados (DNI, nombre, apellidos, dep_id) values ('02345678A', 'wan','chu', 1);

INSERT INTO Proyectos (proy_id, nombre) values (1, 'proyecto1');
INSERT INTO Proyectos (proy_id, nombre) values (2, 'proyecto2');
INSERT INTO Proyectos (proy_id, nombre) values (3, 'proyecto3');

INSERT INTO EmpleadoProyecto (DNI, proy_id) values ('02345678A', 1);
INSERT INTO EmpleadoProyecto (DNI, proy_id) values ('12345678A', 2);
INSERT INTO EmpleadoProyecto (DNI, proy_id) values ('22345678A', 3);
INSERT INTO EmpleadoProyecto (DNI, proy_id) values ('32345678A', 1);
INSERT INTO EmpleadoProyecto (DNI, proy_id) values ('42345678A', 2);
INSERT INTO EmpleadoProyecto (DNI, proy_id) values ('52345678A', 3);
INSERT INTO EmpleadoProyecto (DNI, proy_id) values ('62345678A', 1);
INSERT INTO EmpleadoProyecto (DNI, proy_id) values ('72345678A', 2);
INSERT INTO EmpleadoProyecto (DNI, proy_id) values ('82345678A', 3);
INSERT INTO EmpleadoProyecto (DNI, proy_id) values ('92345678A', 1);

/* El orden correcto es Departamento -> Empleados -> Proyectos -> Asociacion, porque
el Empleado debe insertarse TRAS el departamento (depende del mismo) y la asociación tras Empleados y Proyectos,
porque evidentemente depende de ambos, el proyecto puede ser insertado en cualquier momento

Al insertar un empleado a un departamento inexistente da un error al intentar hacer constraint constraint
la clave foránea

CREATE USER 'consulta'@'localhost';
CREATE USER 'modifica'@'localhost';
CREATE USER 'admin'@'localhost';

GRANT ALL PRIVILEGES ON * . * TO 'admin'@'localhost';
GRANT INSERT, DELETE, UPDATE, SELECT ON * . * TO 'modifica'@'localhost';
GRANT SELECT ON * . * TO 'consulta'@'localhost';

FLUSH PRIVILEGES;


ALTER TABLE Empleados DROP FOREIGN KEY dep_id;
ALTER TABLE Empleados DROP COLUMN dep_id;
ALTER TABLE Empleados ADD COLUMN dep_id INT;
ALTER TABLE Empleados ADD CONSTRAINT dep_id FOREIGN KEY (dep_id) REFERENCES Departamento(dep_id) ON DELETE SET NULL;
*/
