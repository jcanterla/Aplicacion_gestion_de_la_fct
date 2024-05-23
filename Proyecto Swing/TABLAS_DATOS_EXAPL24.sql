/*
    SQL para la creación del modelo físico de la BD del proyecto tercer trimestre

                Integrantes:  · Laura Blanco Gutierrez
                              · Vega Lopez Hernandez
                              · Juan Carlos Canterla
                              · David Marín Bermudez
                              · Javier Moya Rivero
*/

CREATE OR REPLACE DATABASE practicas;
USE practicas;

CREATE or replace TABLE Empresa (
    cod_empresa INT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE,
    CIF CHAR(9) UNIQUE,
    direccion VARCHAR(100),
    cp CHAR(6),
    localidad VARCHAR(45),
    provincia VARCHAR(45),
    jornada VARCHAR(20),
    modalidad VARCHAR(20),
    email VARCHAR(70) UNIQUE,
    CHECK (jornada IN ('Partida', 'Continua')),
    CHECK (modalidad IN ('Presencial', 'Semipresencial', 'Distancia'))
);

CREATE or replace TABLE Tutor_Docente(
    cod_tutor_docente INT PRIMARY KEY,
    dni CHAR(9) UNIQUE, 
    nombre VARCHAR(50),
    apellido1 VARCHAR(50),
    apellido2 VARCHAR(50),
    correo VARCHAR(70),
    telefono CHAR(9)
);

CREATE or replace TABLE Repre_Legal (
    id INT PRIMARY KEY AUTO_INCREMENT,
    dni CHAR(9) UNIQUE,
    nombre VARCHAR(50),
    apellido1 VARCHAR(50),
    apellido2 VARCHAR(50),
    cod_empresa INT,
    FOREIGN KEY (cod_empresa) 
        REFERENCES Empresa(cod_empresa) 
        ON DELETE CASCADE       
);

CREATE or replace TABLE Tutor_Laboral (
    cod_tutor_laboral INT PRIMARY KEY,
    dni CHAR(9) UNIQUE, 
    nombre VARCHAR(50),
    apellido1 VARCHAR(50),
    apellido2 VARCHAR(50),
    correo VARCHAR(75),
    telefono CHAR(9),
    cod_empresa INT,
    FOREIGN KEY (cod_empresa) 
        REFERENCES Empresa(cod_empresa) 
        ON DELETE CASCADE
);

CREATE or replace TABLE Alumno (
    cod_alumno INT PRIMARY KEY,
    dni CHAR(9) UNIQUE,
    nombre VARCHAR(50),
    apellido1 VARCHAR(50),
    apellido2 VARCHAR(50),
    fec_nac DATE,
    cod_tutor_laboral INT,
    cod_tutor_docente INT,
    FOREIGN KEY (cod_tutor_laboral) 
        REFERENCES Tutor_Laboral(cod_tutor_laboral) 
        ON DELETE SET NULL,       
    FOREIGN KEY (cod_tutor_docente) 
        REFERENCES Tutor_Docente(cod_tutor_docente) 
        ON DELETE SET NULL
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