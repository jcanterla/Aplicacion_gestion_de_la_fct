package org.example.proyectojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Empresa {
    private int cod_empresa;
    private String nombre;
    private String CIF;
    private String direccion;
    private String cp;
    private String localidad;
    private String provincia;
    private String jornada;
    private String modalidad;
    private String email;

    public Empresa(int cod_empresa, String nombre, String CIF, String direccion, String cp, String localidad, String provincia, String jornada, String modalidad, String email) {
        this.cod_empresa = cod_empresa;
        this.nombre = nombre;
        this.CIF = CIF;
        this.direccion = direccion;
        this.cp = cp;
        this.localidad = localidad;
        this.provincia = provincia;
        this.jornada = jornada;
        this.modalidad = modalidad;
        this.email = email;
    }

    public Empresa() {

    }

    public int getCod_empresa() {
        return cod_empresa;
    }

    public void setCod_empresa(int cod_empresa) {
        this.cod_empresa = cod_empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static int obtenerUltimoCodEmpresa() {
        int ultimoCodEmpresa = 0;
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "SELECT MAX(cod_empresa) AS max_cod_empresa FROM Empresa";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ultimoCodEmpresa = resultSet.getInt("max_cod_empresa");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ultimoCodEmpresa;
    }


    public void insertarEmpresa() {
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "INSERT INTO Empresa (cod_empresa, nombre, CIF, direccion, cp, localidad, provincia, jornada, modalidad, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, this.cod_empresa);
            preparedStatement.setString(2, this.nombre);
            preparedStatement.setString(3, this.CIF);
            preparedStatement.setString(4, this.direccion);
            preparedStatement.setString(5, this.cp);
            preparedStatement.setString(6, this.localidad);
            preparedStatement.setString(7, this.provincia);
            preparedStatement.setString(8, this.jornada);
            preparedStatement.setString(9, this.modalidad);
            preparedStatement.setString(10, this.email);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " fila(s) insertada(s).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Empresa> obtenerTodasLasEmpresas() {
        ObservableList<Empresa> listaEmpresas = FXCollections.observableArrayList();
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "SELECT * FROM Empresa";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int cod_empresa = resultSet.getInt("cod_empresa");
                String nombre = resultSet.getString("nombre");
                String CIF = resultSet.getString("CIF");
                String direccion = resultSet.getString("direccion");
                String cp = resultSet.getString("cp");
                String localidad = resultSet.getString("localidad");
                String provincia = resultSet.getString("provincia");
                String jornada = resultSet.getString("jornada");
                String modalidad = resultSet.getString("modalidad");
                String email = resultSet.getString("email");

                Empresa empresa = new Empresa(cod_empresa, nombre, CIF, direccion, cp, localidad, provincia, jornada, modalidad, email);
                listaEmpresas.add(empresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaEmpresas;
    }

    public void actualizarEmpresa() {
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "UPDATE Empresa SET CIF = ?, nombre = ?, direccion = ?, cp = ?, localidad = ?, jornada = ?, modalidad = ?, email = ? WHERE cod_empresa = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, this.CIF);
            preparedStatement.setString(2, this.nombre);
            preparedStatement.setString(3, this.direccion);
            preparedStatement.setString(4, this.cp);
            preparedStatement.setString(5, this.localidad);
            preparedStatement.setString(6, this.jornada);
            preparedStatement.setString(7, this.modalidad);
            preparedStatement.setString(8, this.email);
            preparedStatement.setInt(9, this.cod_empresa);

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

    public static ObservableList<String> obtenerNombresEmpresas() {
        ObservableList<String> listaNombresEmpresas = FXCollections.observableArrayList();
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "SELECT nombre FROM Empresa";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                listaNombresEmpresas.add(nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaNombresEmpresas;
    }
}