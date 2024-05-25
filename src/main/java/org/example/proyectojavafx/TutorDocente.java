package org.example.proyectojavafx;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorDocente {

    private static boolean isTutorDataLoaded = false;

    public static void loadTutorDataFromXML() {
        if (isTutorDataLoaded) {
            return;
        }

        try {
            File inputFile = new File("src/main/java/org/example/proyectojavafx/tutoresdoc.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("tutordoc");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String dni = eElement.getElementsByTagName("dni").item(0).getTextContent();
                    int codtut = Integer.parseInt(eElement.getElementsByTagName("codtut").item(0).getTextContent());
                    String nomap = eElement.getElementsByTagName("nomap").item(0).getTextContent();
                    String correo = eElement.getElementsByTagName("correo").item(0).getTextContent();
                    String telefono = eElement.getElementsByTagName("telefono").item(0).getTextContent();

                    // Insertar los datos del tutor docente en la base de datos
                    insertTutorDataIntoDatabase(dni, codtut, nomap, correo, telefono);
                }
            }

            isTutorDataLoaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertTutorDataIntoDatabase(String dni, int codtut, String nomap, String correo, String telefono) {
        // Dividir el nombre y apellidos
        String[] partes = nomap.split(" ");
        String nombre = partes[0];
        String apellido1 = partes[1];
        String apellido2 = partes.length > 2 ? partes[2] : "";

        // SQL para insertar los datos
        String sql = "INSERT INTO Tutor_Docente(cod_tutor_docente, dni, nombre, apellido1, apellido2, correo, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String selectSql = "SELECT COUNT(*) FROM Tutor_Docente WHERE cod_tutor_docente = ?";

        try (Connection conn = ConexionBaseDeDatos.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

            // Verificar si ya existe un tutor docente con el mismo cod_tutor_docente
            selectStmt.setInt(1, codtut);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // Ya existe un tutor docente con el mismo cod_tutor_docente, no insertar
                System.out.println("Ya existe un tutor docente con el cod_tutor_docente: " + codtut);
                return;
            }

            // Establecer los valores de los parámetros
            pstmt.setInt(1, codtut);
            pstmt.setString(2, dni);
            pstmt.setString(3, nombre);
            pstmt.setString(4, apellido1);
            pstmt.setString(5, apellido2);
            pstmt.setString(6, correo);
            pstmt.setString(7, telefono);

            // Ejecutar la consulta
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ObservableList<String> obtenerNombresApellidosTutores() {
        ObservableList<String> listaNombresApellidosTutores = FXCollections.observableArrayList();
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "SELECT nombre, apellido1, apellido2 FROM Tutor_Docente";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String apellido1 = resultSet.getString("apellido1");
                String apellido2 = resultSet.getString("apellido2");
                listaNombresApellidosTutores.add(nombre + " " + apellido1 + " " + apellido2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaNombresApellidosTutores;
    }
}
