/*
    SQL para la creación del modelo físico de la BD del proyecto tercer trimestre

                Integrantes:  · Laura Blanco Gutierrez
                              · Vega Lopez Hernandez
                              · Juan Carlos Canterla
                              · David Marín Bermudez
                              · Javier Moya Rivero
*/

CREATE OR REPLACE DATABASE Practicas;
USE Practicas;

CREATE OR REPLACE TABLE representante (
  id INT AUTO_INCREMENT PRIMARY KEY unique,
  DNI CHAR(9) NOT NULL,
  nombre VARCHAR(25) NOT NULL,
  apellido1 VARCHAR(50) NOT NULL,
  apellido2 VARCHAR(50) NOT NULL
);

CREATE OR REPLACE TABLE TutorDocente (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  DNI CHAR(9) NOT NULL,
  Nombre VARCHAR(255),
  Apellido VARCHAR(255),
  Email VARCHAR(255)
);

CREATE OR REPLACE TABLE Empresa (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  CIF CHAR(8),
  RazonSocial VARCHAR(255),
  Localidad VARCHAR(255),
  Jornada ENUM('Partida','Continua'),
  Modalidad ENUM('Presencial','Semipresencial','Distancia'),
  Email VARCHAR(255),
  Idrepresentante INT NOT NULL UNIQUE,
  CONSTRAINT Repre FOREIGN KEY (Idrepresentante) REFERENCES representante (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE OR REPLACE TABLE TutorLaboral (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  DNI CHAR(9) NOT NULL,
  Nombre VARCHAR(255),
  Apellido VARCHAR(255),
  Email VARCHAR(255),
  IdEmpresa INT,
  CONSTRAINT CodEmpresa FOREIGN KEY (IdEmpresa) REFERENCES Empresa (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Alumnos (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  DNI CHAR(9) NOT NULL,
  Nombre VARCHAR(255),
  Apellidos VARCHAR(255),
  FechaNacimiento DATE,
  IdTutorD INT,
  IdTutorL INT,
  CONSTRAINT TutorL FOREIGN KEY (IdTutorL) REFERENCES TutorLaboral (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT TutorD FOREIGN KEY (IdTutorD) REFERENCES TutorDocente (id) ON DELETE CASCADE ON UPDATE CASCADE
);

/*
Esta tabla nos permitirá continuar con la funcionalidad principal que debe manejar la aplicación,
que no es otra que la de la asignación de los alumnos a las empresas, así como la designación del
tutor docente responsable del seguimiento del trabajo del alumno.
*/

CREATE OR REPLACE TABLE Asignación (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  FechaAsignación DATE,
  NombreAlumno VARCHAR(255),
  NombreTutorDocente VARCHAR(255)
);