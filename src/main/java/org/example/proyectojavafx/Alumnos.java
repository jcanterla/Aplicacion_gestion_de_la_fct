package org.example.proyectojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.text.SimpleDateFormat;


public class Alumnos {
    private int cod_alumno;
    private String dni;
    private String nombre;
    private String apellidos;
    private String fec_nac;
    private int cod_tutor_laboral;
    private int cod_tutor_docente;
    private Object fichero_alumnos;


    public Alumnos() {
        this.cod_alumno = cod_alumno;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fec_nac = fec_nac;
        this.cod_tutor_laboral = cod_tutor_laboral;
        this.cod_tutor_docente = cod_tutor_docente;
    }


    public void insertarAlumnos() {
        SimpleDateFormat insertar_formato = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat quitar_formato = new SimpleDateFormat("yyyy-MM-dd");


        boolean encontrado_duplicado = false;

        try (FileInputStream fichero_alumnos = new FileInputStream("src/main/java/org/example/proyectojavafx/alumnos2cfs.dat");
             DataInputStream datos_alumnos = new DataInputStream(fichero_alumnos);
             Connection conexion = ConexionBaseDeDatos.getConnection()) {

            // Preparar la sentencia SQL para insertar un alumno
            String insertar_alumnos = "INSERT INTO Alumno (cod_alumno, dni, nombre, apellido1, apellido2, fec_nac) VALUES (?, ?, ?, ?, ?, ?)";
            String select_alumnos = "SELECT COUNT(*) FROM Alumno WHERE cod_alumno = ?";

            try (PreparedStatement sentencia_insert = conexion.prepareStatement(insertar_alumnos);
                 PreparedStatement sentencia_select = conexion.prepareStatement(select_alumnos)) {

                try {
                    while (true) {
                        cod_alumno = datos_alumnos.readInt();
                        dni = datos_alumnos.readUTF();
                        nombre = datos_alumnos.readUTF();
                        apellidos = datos_alumnos.readUTF();
                        fec_nac = datos_alumnos.readUTF();


                        // Dividir los apellidos en apellido1 y apellido2
                        String[] apellidoParts = apellidos.split(" ", 2);
                        String apellido1 = apellidoParts[0];
                        String apellido2 = apellidoParts.length > 1 ? apellidoParts[1] : "";



                        Date sql_fecha = null;

                        if (!fec_nac.isEmpty()) {
                            try {
                                java.util.Date parsedDate = insertar_formato.parse(fec_nac);
                                String formattedDate = quitar_formato.format(parsedDate);
                                sql_fecha = Date.valueOf(formattedDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }


                        // Verificar si el alumno ya existe
                        sentencia_select.setInt(1, cod_alumno);
                        ResultSet rs = sentencia_select.executeQuery();
                        rs.next();
                        int count = rs.getInt(1);

                        if (count > 0) {
                            encontrado_duplicado = true;
                        } else {
                            // Establecer los parámetros en la sentencia SQL para inserción
                            sentencia_insert.setInt(1, cod_alumno);
                            sentencia_insert.setString(2, dni);
                            sentencia_insert.setString(3, nombre);
                            sentencia_insert.setString(4, apellido1);
                            sentencia_insert.setString(5, apellido2);
                            if (sql_fecha == null) {
                                sentencia_insert.setNull(6, java.sql.Types.DATE); // Insertar un valor nulo
                            } else {
                                sentencia_insert.setDate(6, sql_fecha);
                            }

                            // Ejecutar la inserción
                            sentencia_insert.executeUpdate();
                        }
                    }
                } catch (EOFException e) {
                    // Se alcanzó el final del archivo, salir del bucle.
                    System.out.println("Inserción de alumnos completada.");
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<String> obtenerNombresApellidosAlumnos() {
        ObservableList<String> listaNombresApellidosAlumnos = FXCollections.observableArrayList();
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "SELECT nombre, apellido1, apellido2 FROM Alumno";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String apellido1 = resultSet.getString("apellido1");
                String apellido2 = resultSet.getString("apellido2");
                listaNombresApellidosAlumnos.add(apellido1 + " " + apellido2 + ", " + nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaNombresApellidosAlumnos;
    }
}

