package org.example.proyectojavafx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Empresa {
    private int id;
    private String CIF;
    private String RazonSocial;
    private String Localidad;
    private String Jornada;
    private String Modalidad;
    private String Email;
    private int Idrepresentante;

    public Empresa(String CIF, String RazonSocial, String Localidad, String Jornada, String Modalidad, String Email, int Idrepresentante) {
        this.CIF = CIF;
        this.RazonSocial = RazonSocial;
        this.Localidad = Localidad;
        this.Jornada = Jornada;
        this.Modalidad = Modalidad;
        this.Email = Email;
        this.Idrepresentante = Idrepresentante;
    }

    public static List<String> obtenerOpciones(String campo) {
        List<String> opciones = new ArrayList<>();
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "SHOW COLUMNS FROM Empresa LIKE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, campo);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String type = resultSet.getString("Type");
                    String enumOptions = type.substring(type.indexOf("(") + 1, type.lastIndexOf(")"));
                    opciones = Arrays.asList(enumOptions.split(","));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return opciones;
    }

    public void insertarEmpresa() {
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "INSERT INTO Empresa (CIF, RazonSocial, Localidad, Jornada, Modalidad, Email, Idrepresentante) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, this.CIF);
            preparedStatement.setString(2, this.RazonSocial);
            preparedStatement.setString(3, this.Localidad);
            preparedStatement.setString(4, this.Jornada);
            preparedStatement.setString(5, this.Modalidad);
            preparedStatement.setString(6, this.Email);
            preparedStatement.setInt(7, this.Idrepresentante);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " fila(s) insertada(s).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}