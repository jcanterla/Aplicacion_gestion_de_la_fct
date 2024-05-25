package org.example.proyectojavafx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepreLegal {
    private int id;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int cod_empresa;

    public RepreLegal(int id, String dni, String nombre, String apellido1, String apellido2, int cod_empresa) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.cod_empresa = cod_empresa;
    }

    public RepreLegal() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCod_empresa() {
        return cod_empresa;
    }

    public void setCod_empresa(int cod_empresa) {
        this.cod_empresa = cod_empresa;
    }

    public static int obtenerUltimoIdRepreLegal() {
        int ultimoIdRepreLegal = 0;
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "SELECT MAX(id) AS max_id FROM Repre_Legal";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ultimoIdRepreLegal = resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ultimoIdRepreLegal;
    }

    public void insertarRepreLegal() {
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "INSERT INTO Repre_Legal (id, dni, nombre, apellido1, apellido2, cod_empresa) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, this.id);
            preparedStatement.setString(2, this.dni);
            preparedStatement.setString(3, this.nombre);
            preparedStatement.setString(4, this.apellido1);
            preparedStatement.setString(5, this.apellido2);
            preparedStatement.setInt(6, this.cod_empresa);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " fila(s) insertada(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarRepreLegal() {
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "UPDATE Repre_Legal SET dni = ?, nombre = ?, apellido1 = ?, apellido2 = ? WHERE cod_empresa = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, this.dni);
            preparedStatement.setString(2, this.nombre);
            preparedStatement.setString(3, this.apellido1);
            preparedStatement.setString(4, this.apellido2);
            preparedStatement.setInt(5, this.cod_empresa);

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