package org.example.proyectojavafx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorLaboral {
    private int cod_tutor_laboral;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String correo;
    private String telefono;
    private int cod_empresa;

    public TutorLaboral(int cod_tutor_laboral, String dni, String nombre, String apellido1, String apellido2, String correo, String telefono, int cod_empresa) {
        this.cod_tutor_laboral = cod_tutor_laboral;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correo = correo;
        this.telefono = telefono;
        this.cod_empresa = cod_empresa;
    }

    public TutorLaboral() {

    }

    public int getCod_tutor_laboral() {
        return cod_tutor_laboral;
    }

    public void setCod_tutor_laboral(int cod_tutor_laboral) {
        this.cod_tutor_laboral = cod_tutor_laboral;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCod_empresa() {
        return cod_empresa;
    }

    public void setCod_empresa(int cod_empresa) {
        this.cod_empresa = cod_empresa;
    }

    public static int obtenerUltimoCodTutorLaboral() {
        int ultimoCodTutorLaboral = 0;
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "SELECT MAX(cod_tutor_laboral) AS max_cod_tutor_laboral FROM Tutor_Laboral";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ultimoCodTutorLaboral = resultSet.getInt("max_cod_tutor_laboral");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ultimoCodTutorLaboral;
    }

    public void insertarTutorLaboral() {
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "INSERT INTO Tutor_Laboral (cod_tutor_laboral, dni, nombre, apellido1, apellido2, correo, telefono, cod_empresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, this.cod_tutor_laboral);
            preparedStatement.setString(2, this.dni);
            preparedStatement.setString(3, this.nombre);
            preparedStatement.setString(4, this.apellido1);
            preparedStatement.setString(5, this.apellido2);
            preparedStatement.setString(6, this.correo);
            preparedStatement.setString(7, this.telefono);
            preparedStatement.setInt(8, this.cod_empresa);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " fila(s) insertada(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarTutorLaboral() {
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "UPDATE Tutor_Laboral SET dni = ?, nombre = ?, apellido1 = ?, apellido2 = ?, correo = ?, telefono = ? WHERE cod_empresa = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, this.dni);
            preparedStatement.setString(2, this.nombre);
            preparedStatement.setString(3, this.apellido1);
            preparedStatement.setString(4, this.apellido2);
            preparedStatement.setString(5, this.correo);
            preparedStatement.setString(6, this.telefono);
            preparedStatement.setInt(7, this.cod_empresa);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " fila(s) actualizada(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrar() {
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "DELETE FROM Empresa WHERE cod_empresa = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, this.cod_empresa);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " fila(s) borrada(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}