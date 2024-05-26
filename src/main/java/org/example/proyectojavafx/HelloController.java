package org.example.proyectojavafx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Controlador para la interfaz de usuario de la aplicación.
 * Esta clase maneja todos los componentes de la interfaz de usuario y sus interacciones.
 *
 * <p>Los componentes de la interfaz de usuario incluyen:</p>
 * <ul>
 *   <li>Imágenes</li>
 *   <li>Etiquetas</li>
 *   <li>Paneles</li>
 *   <li>Pestañas</li>
 *   <li>Botones</li>
 *   <li>Cajas de selección</li>
 *   <li>Tablas</li>
 *   <li>Campos de texto</li>
 * </ul>
 *
 * <p>Cada componente de la interfaz de usuario tiene un campo correspondiente en esta clase.</p>
 *
 * <p>Esta clase utiliza la anotación {@code @FXML} para vincular los componentos de la interfaz de usuario
 * en el archivo FXML con los campos en esta clase.</p>
 *
 * <p>Los métodos para manejar las interacciones del usuario con la interfaz de usuario deben ser agregados a esta clase.</p>
 */

public class HelloController {


    // Elementos de empresa
    @FXML
    private AnchorPane empresas_panel;

    @FXML
    private Tab empresas_tab;

    @FXML
    private Label tit_gest_empresas;

    @FXML
    private TabPane panel_principal;

    @FXML
    private Label tit_gest_plazas;


    // Elementos dentro de empresa de gest de empresa
    @FXML
    private Pane empresas_panel_empresa;

    @FXML
    private Label empresas_label_empresa;

    @FXML
    private ChoiceBox<String> panel_empresa_combobox_jornada;

    @FXML
    private ComboBox<String> panel_empresa_combobox_mod;

    @FXML
    private Label panel_empresa_label_cd;

    @FXML
    private Label panel_empresa_label_cif;

    @FXML
    private Label panel_empresa_label_cod;

    @FXML
    private Label panel_empresa_label_direcc;

    @FXML
    private Label panel_empresa_label_jornada;

    @FXML
    private Label panel_empresa_label_loca;

    @FXML
    private Label panel_empresa_label_mail;

    @FXML
    private Label panel_empresa_label_mod;

    @FXML
    private Label panel_empresa_label_nombre;

    @FXML
    private TextField panel_empresa_textfield_cif;

    @FXML
    private TextField panel_empresa_textfield_cod;

    @FXML
    private TextField panel_empresa_textfield_cp;

    @FXML
    private TextField panel_empresa_textfield_direcc;

    @FXML
    private TextField panel_empresa_textfield_local;

    @FXML
    private TextField panel_empresa_textfield_mail;

    @FXML
    private TextField panel_empresa_textfield_nom;


    // Elementos dentro de empresa de gest de personas
    @FXML
    private Pane empresas_panel_personas;

    @FXML
    private Label empresas_label_personas;

    @FXML
    private Label panel_personas_label_apelaboral;

    @FXML
    private Label panel_personas_label_apelegal;

    @FXML
    private Label panel_personas_label_laboral;

    @FXML
    private Label panel_personas_label_legal;

    @FXML
    private Label panel_personas_label_nomlaboral;

    @FXML
    private Label panel_personas_label_nomlegal;

    @FXML
    private Label panel_personas_label_telf;

    @FXML
    private TextField panel_personas_textfield_apelaboral;

    @FXML
    private TextField panel_personas_textfield_apelegal;

    @FXML
    private TextField panel_personas_textfield_laboral;

    @FXML
    private TextField panel_personas_textfield_legal;

    @FXML
    private TextField panel_personas_textfield_nomlaboral;

    @FXML
    private TextField panel_personas_textfield_nomlegal;

    @FXML
    private TextField panel_personas_textfield_telf;


    // Elementos dentro de empresa de los botones
    @FXML
    private Button empresas_boton_eliminar;

    @FXML
    private Button empresas_boton_insertar;

    @FXML
    private Button empresas_boton_modificar;


    // Elementos dentro de empresa de la tabla de la bbdd
    @FXML
    private Pane empresas_panel_bbdd;

    @FXML
    private TableView<Empresa> empresas_bbdd_tabla;

    @FXML
    private Label empresas_label_bbdd;

    @FXML
    private TableColumn<Empresa, Integer> bbdd_colum_cif;

    @FXML
    private TableColumn<Empresa, String> bbdd_colum_cp;

    @FXML
    private TableColumn<Empresa, String> bbdd_colum_direcc;

    @FXML
    private TableColumn<Empresa, Integer> bbdd_colum_local;

    @FXML
    private TableColumn<Empresa, String> bbdd_colum_mail;

    @FXML
    private TableColumn<Empresa, String> bbdd_colum_mod;

    @FXML
    private TableColumn<Empresa, String> bbdd_colum_nombre;


    // Elementos alumnos
    @FXML
    private ImageView alumnos_foto;

    @FXML
    private Label alumnos_mensaje;

    @FXML
    private AnchorPane alumnos_panel;

    @FXML
    private Tab alumnos_tab;

    @FXML
    private Label tit_gest_alumnos;



    // Elementos de Tutores
    @FXML
    private Label tit_gest_tutores;

    @FXML
    private AnchorPane tutor_panel;

    @FXML
    private Tab tutor_tab;

    @FXML
    private ImageView tutores_foto;

    @FXML
    private Label tutores_mensaje;



    // Elementos de Asignación
    @FXML
    private Button asig_boton_confirm;

    @FXML
    private ChoiceBox<String> asig_combobox_alumno;

    @FXML
    private ChoiceBox<String> asig_combobox_tutor;

    @FXML
    private Label asig_label_texto_info;

    @FXML
    private Label asig_label_tutor;

    @FXML
    private AnchorPane asig_panel;

    @FXML
    private Tab asig_tab;

    @FXML
    private ChoiceBox<String> asig_combobox_empresa;

    @FXML
    private Label asig_label_empresa;

    @FXML
    private Label asig_label_alumnos;


    private boolean alumnos_insertados = false;

    private boolean tutores_insertados = false;

    public Tab getEmpresas_tab() {
        return empresas_tab;
    }

    public TabPane getPanel_principal() {
        return panel_principal;
    }

    public Tab getAlumnos_tab() {
        return alumnos_tab;
    }

    public Tab getTutor_tab() {
        return tutor_tab;
    }

    public Tab getAsig_tab() {
        return asig_tab;
    }

    // En la clase HelloController
    public void initialize() {

        panel_empresa_combobox_jornada.getItems().addAll("Partida", "Continua");
        panel_empresa_combobox_jornada.setValue("Partida");

        panel_empresa_combobox_mod.getItems().addAll("Presencial", "Semipresencial", "Distancia");
        panel_empresa_combobox_mod.setValue("Presencial");

        asig_label_texto_info.setWrapText(true);
        asig_label_texto_info.setMaxWidth(500);

        ObservableList<Empresa> listaEmpresas = Empresa.obtenerTodasLasEmpresas();
        empresas_bbdd_tabla.setItems(listaEmpresas);

        bbdd_colum_cif.setCellValueFactory(new PropertyValueFactory<>("CIF"));
        bbdd_colum_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        bbdd_colum_direcc.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        bbdd_colum_cp.setCellValueFactory(new PropertyValueFactory<>("cp"));
        bbdd_colum_local.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        bbdd_colum_mod.setCellValueFactory(new PropertyValueFactory<>("modalidad"));
        bbdd_colum_mail.setCellValueFactory(new PropertyValueFactory<>("email"));

        empresas_bbdd_tabla.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Empresa empresaSeleccionada = newSelection;
                panel_empresa_textfield_nom.setText(empresaSeleccionada.getNombre());
                panel_empresa_textfield_cif.setText(empresaSeleccionada.getCIF());
                panel_empresa_textfield_direcc.setText(empresaSeleccionada.getDireccion());
                panel_empresa_textfield_cp.setText(empresaSeleccionada.getCp());
                panel_empresa_textfield_local.setText(empresaSeleccionada.getLocalidad());
                panel_empresa_combobox_jornada.setValue(empresaSeleccionada.getJornada());
                panel_empresa_combobox_mod.setValue(empresaSeleccionada.getModalidad());
                panel_empresa_textfield_mail.setText(empresaSeleccionada.getEmail());
                int codEmpresa = obtenerCodEmpresa(empresaSeleccionada.getCod_empresa());
                panel_empresa_textfield_cod.setText(String.valueOf(codEmpresa));

                RepreLegal repreLegal = obtenerRepreLegal(empresaSeleccionada.getCod_empresa());
                TutorLaboral tutorLaboral = obtenerTutorLaboral(empresaSeleccionada.getCod_empresa());

                // Establecer los campos de texto correspondientes
                if (repreLegal != null) {
                    panel_personas_textfield_legal.setText(repreLegal.getDni());
                    panel_personas_textfield_nomlegal.setText(repreLegal.getNombre());
                    panel_personas_textfield_apelegal.setText(repreLegal.getApellido1() + " " + repreLegal.getApellido2());
                }

                if (tutorLaboral != null) {
                    panel_personas_textfield_laboral.setText(tutorLaboral.getDni());
                    panel_personas_textfield_nomlaboral.setText(tutorLaboral.getNombre());
                    panel_personas_textfield_apelaboral.setText(tutorLaboral.getApellido1() + " " + tutorLaboral.getApellido2());
                    panel_personas_textfield_telf.setText(tutorLaboral.getTelefono());
                }
            } else if (newSelection == null){

                panel_empresa_textfield_nom.setText("");
                panel_empresa_textfield_cif.setText("");
                panel_empresa_textfield_direcc.setText("");
                panel_empresa_textfield_cp.setText("");
                panel_empresa_textfield_local.setText("");
                panel_empresa_combobox_jornada.setValue(null);
                panel_empresa_combobox_mod.setValue(null);
                panel_empresa_textfield_mail.setText("");
                panel_empresa_textfield_cod.setText("");

                panel_personas_textfield_legal.setText("");
                panel_personas_textfield_nomlegal.setText("");
                panel_personas_textfield_apelegal.setText("");

                panel_personas_textfield_laboral.setText("");
                panel_personas_textfield_nomlaboral.setText("");
                panel_personas_textfield_apelaboral.setText("");
                panel_personas_textfield_telf.setText("");
            }
        });

        Image imagen_alumnos = new Image(getClass().getResourceAsStream("/org/example/proyectojavafx/DATaTabla.png"));
        alumnos_foto.setImage(imagen_alumnos);
        alumnos_mensaje.setVisible(false);

        alumnos_foto.setOnMouseClicked(event -> {
            if (!alumnos_insertados) {
                insertarAlumnosDB();
                alumnos_insertados = true;
                alumnos_foto.setDisable(true);
                alumnos_mensaje.setVisible(true);
            }
        });

        cambio_tamaño();


        Image imagen_tutores = new Image(getClass().getResourceAsStream("/org/example/proyectojavafx/XMLaTabla.png"));
        tutores_foto.setImage(imagen_tutores);

        panel_empresa_textfield_cod.setEditable(false);
        tutores_mensaje.setVisible(false);

        empresas_bbdd_tabla.setOnMouseClicked(event -> {
            Empresa empresaSeleccionada = empresas_bbdd_tabla.getSelectionModel().getSelectedItem();
            if (empresaSeleccionada != null) {
                if (event.getClickCount() % 2 == 0) {
                    empresas_bbdd_tabla.getSelectionModel().clearSelection();
                } else {
                    empresas_bbdd_tabla.getSelectionModel().select(empresaSeleccionada);
                }
            }
        });

        asig_combobox_empresa.setItems(Empresa.obtenerNombresEmpresas());
        asig_combobox_alumno.setItems(Alumnos.obtenerNombresApellidosAlumnos());
        asig_combobox_tutor.setItems(TutorDocente.obtenerNombresApellidosTutores());

        asig_label_texto_info.setVisible(false);

        asig_boton_confirm.setOnAction(event -> {
            String nombreAlumno = asig_combobox_alumno.getValue();
            String nombreEmpresa = asig_combobox_empresa.getValue();
            String nombreTutorDocente = asig_combobox_tutor.getValue();

            if (nombreAlumno != null && nombreEmpresa != null && nombreTutorDocente != null) {
                insertarAsignacion(nombreAlumno, nombreEmpresa, nombreTutorDocente);
                asig_label_texto_info.setVisible(true);
            } else {
                // Mostrar un mensaje de error si alguno de los ComboBox no tiene un valor seleccionado
                System.out.println("Por favor, selecciona un valor en todos los ComboBox.");
            }
        });

    }


    /*
    **/


    private void insertarAlumnosDB() {
        Alumnos alumnos = new Alumnos();
        alumnos.insertarAlumnos();
        actualizarComboBoxes();
    }


    public int obtenerCodEmpresa(int cod_empresa) {
        int codEmpresa = 0;
        try (Connection conexion = ConexionBaseDeDatos.getConnection()) {
            String consulta = "SELECT cod_empresa FROM Empresa WHERE cod_empresa = ?";
            PreparedStatement pstmt = conexion.prepareStatement(consulta);
            pstmt.setString(1, String.valueOf(cod_empresa));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                codEmpresa = rs.getInt("cod_empresa");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codEmpresa;
    }

    public RepreLegal obtenerRepreLegal(int cod_empresa) {
        RepreLegal repreLegal = null;
        try (Connection conexion = ConexionBaseDeDatos.getConnection()) {
            String consulta = "SELECT * FROM Repre_Legal WHERE cod_empresa = ?";
            PreparedStatement pstmt = conexion.prepareStatement(consulta);
            pstmt.setInt(1, cod_empresa);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                repreLegal = new RepreLegal(rs.getInt("id"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getInt("cod_empresa"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repreLegal;
    }

    public TutorLaboral obtenerTutorLaboral(int cod_empresa) {
        TutorLaboral tutorLaboral = null;
        try (Connection conexion = ConexionBaseDeDatos.getConnection()) {
            String consulta = "SELECT * FROM Tutor_Laboral WHERE cod_empresa = ?";
            PreparedStatement pstmt = conexion.prepareStatement(consulta);
            pstmt.setInt(1, cod_empresa);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tutorLaboral = new TutorLaboral(rs.getInt("cod_tutor_laboral"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), rs.getString("correo"), rs.getString("telefono"), rs.getInt("cod_empresa"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tutorLaboral;
    }

    @FXML
    void botonEliminarEmpresa(ActionEvent event) {
        // Recoger el cod_empresa de la fila seleccionada
        int cod_empresa = empresas_bbdd_tabla.getSelectionModel().getSelectedItem().getCod_empresa();

        Empresa empresaBorrar = new Empresa();
        empresaBorrar.setCod_empresa(cod_empresa);
        empresaBorrar.borrar();

        TutorLaboral tutorLaboralBorrar = new TutorLaboral();
        tutorLaboralBorrar.setCod_empresa(cod_empresa);
        tutorLaboralBorrar.borrar();

        RepreLegal repreLegalBorrar = new RepreLegal();
        repreLegalBorrar.setCod_empresa(cod_empresa);
        repreLegalBorrar.borrar();

        // Actualizar la tabla
        ObservableList<Empresa> listaEmpresas = Empresa.obtenerTodasLasEmpresas();
        empresas_bbdd_tabla.setItems(listaEmpresas);

        actualizarComboBoxes();

    }

    @FXML
    void botonInsertarEmpresa(ActionEvent event) {
        // Recoger los datos de los campos de entrada para la empresa
        int cod_empresa = Empresa.obtenerUltimoCodEmpresa() + 1;
        String CIF = panel_empresa_textfield_cif.getText();
        String nombre = panel_empresa_textfield_nom.getText();
        String direccion = panel_empresa_textfield_direcc.getText();
        String cp = panel_empresa_textfield_cp.getText();
        String localidad = panel_empresa_textfield_local.getText();
        String provincia = "";
        String jornada = panel_empresa_combobox_jornada.getValue().toString();
        String modalidad = panel_empresa_combobox_mod.getValue().toString();
        String email = panel_empresa_textfield_mail.getText();


        if (CIF.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || cp.isEmpty() || localidad.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos.");
            return;
        }

        if (CIF.length() != 9) {
            JOptionPane.showMessageDialog(null, "Error, el CIF tiene que tener 9 carácteres.");
            return;
        }

        String cif_path = "^[A-Za-z][0-9]{8}$";
        Pattern cif_path_2 = Pattern.compile(cif_path);
        Matcher cif_comprobar = cif_path_2.matcher(CIF);

        if (!cif_comprobar.matches()) {
            JOptionPane.showMessageDialog(null, "Error, el CIF debe tener una letra como primer carácter y los demás como dígito.");
            return;
        }

        if (cp.length() != 5 || !cp.matches("[0-9]{5}")) {
            JOptionPane.showMessageDialog(null, "El código postal debe tener exactamente 5 dígitos.");
            return;
        }


        // Validar el email
        String email_path = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        // Crear el patrón y el matcher
        Pattern path = Pattern.compile(email_path);
        Matcher comprobar = path.matcher(email);

        // Validar el email
        if (!comprobar.matches()) {
            JOptionPane.showMessageDialog(null, "Error, el formato del email no es correcto");
            return;
        }


        Empresa nuevaEmpresa = new Empresa(cod_empresa, nombre, CIF, direccion, cp, localidad, provincia, jornada, modalidad, email);
        nuevaEmpresa.insertarEmpresa();

        int cod_tutor_laboral = TutorLaboral.obtenerUltimoCodTutorLaboral() + 1;
        String dni_tutor = panel_personas_textfield_laboral.getText();
        String nombre_tutor = panel_personas_textfield_nomlaboral.getText();
        String apellidoCompletoTutor = panel_personas_textfield_apelaboral.getText();
        String[] partesApellidoTutor = apellidoCompletoTutor.split(" ");

        String apellido1_tutor = partesApellidoTutor[0];
        String apellido2_tutor = partesApellidoTutor.length > 1 ? partesApellidoTutor[1] : "";
        String correo_tutor = "";
        String telefono_tutor = panel_personas_textfield_telf.getText();


        if (dni_tutor.length() != 9) {
            JOptionPane.showMessageDialog(null, "Error, los DNI tienen que tener 9 carácteres.");
            return;
        }

        String dniTPath = "^[0-9]{8}[A-Za-z]$";
        Pattern dniT_Path_2 = Pattern.compile(dniTPath);
        Matcher dniT_comprobar = dniT_Path_2.matcher(dni_tutor);

        if (!dniT_comprobar.matches()) {
            JOptionPane.showMessageDialog(null, "El DNI debe tener 8 números seguidos de una letra.");
            return;
        }

        if (telefono_tutor.length() != 9) {
            JOptionPane.showMessageDialog(null, "Error, los teléfonos móviles tienen que tener 9 dígitos.");
            return;
        }


        TutorLaboral nuevoTutorLaboral = new TutorLaboral(cod_tutor_laboral, dni_tutor, nombre_tutor, apellido1_tutor, apellido2_tutor, correo_tutor, telefono_tutor, cod_empresa);
        nuevoTutorLaboral.insertarTutorLaboral();

        int id_repre_legal = RepreLegal.obtenerUltimoIdRepreLegal() + 1;
        String dni_repre_legal = panel_personas_textfield_legal.getText();
        String nombre_repre_legal = panel_personas_textfield_nomlegal.getText();
        String apellidoCompleto = panel_personas_textfield_apelegal.getText();
        String[] partesApellido = apellidoCompleto.split(" ");

        String apellido1_repre_legal = partesApellido[0];
        String apellido2_repre_legal = partesApellido.length > 1 ? partesApellido[1] : "";


        if (dni_repre_legal.length() != 9) {
            JOptionPane.showMessageDialog(null, "Error, los DNI tienen que tener 9 carácteres.");
            return;
        }

        String dniRPath = "^[0-9]{8}[A-Za-z]$";
        Pattern dni_Path_2 = Pattern.compile(dniRPath);
        Matcher dniR_comprobar = dni_Path_2.matcher(dni_repre_legal);

        if (!dniR_comprobar.matches()) {
            JOptionPane.showMessageDialog(null, "El DNI debe tener 8 números seguidos de una letra.");
            return;
        }


        // Crear e insertar el nuevo representante legal
        RepreLegal nuevoRepreLegal = new RepreLegal(id_repre_legal, dni_repre_legal, nombre_repre_legal, apellido1_repre_legal, apellido2_repre_legal, cod_empresa);
        nuevoRepreLegal.insertarRepreLegal();

        ObservableList<Empresa> listaEmpresas = Empresa.obtenerTodasLasEmpresas();
        empresas_bbdd_tabla.setItems(listaEmpresas);

        bbdd_colum_cif.setCellValueFactory(new PropertyValueFactory<>("CIF"));
        bbdd_colum_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        bbdd_colum_direcc.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        bbdd_colum_cp.setCellValueFactory(new PropertyValueFactory<>("cp"));
        bbdd_colum_local.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        bbdd_colum_mod.setCellValueFactory(new PropertyValueFactory<>("modalidad"));
        bbdd_colum_mail.setCellValueFactory(new PropertyValueFactory<>("email"));

        panel_empresa_textfield_cif.setText("");
        panel_empresa_textfield_nom.setText("");
        panel_empresa_textfield_direcc.setText("");
        panel_empresa_textfield_cp.setText("");
        panel_empresa_textfield_local.setText("");
        panel_empresa_textfield_mail.setText("");

        panel_personas_textfield_laboral.setText("");
        panel_personas_textfield_nomlaboral.setText("");
        panel_personas_textfield_apelaboral.setText("");
        panel_personas_textfield_telf.setText("");

        panel_personas_textfield_legal.setText("");
        panel_personas_textfield_nomlegal.setText("");
        panel_personas_textfield_apelegal.setText("");

        actualizarComboBoxes();
    }



    @FXML
    void botonModificarEmpresa(ActionEvent event) {
        // Recoger los datos de los campos de entrada para la empresa
        int cod_empresa = Integer.parseInt(panel_empresa_textfield_cod.getText());
        String CIF = panel_empresa_textfield_cif.getText();
        String nombre = panel_empresa_textfield_nom.getText();
        String direccion = panel_empresa_textfield_direcc.getText();
        String cp = panel_empresa_textfield_cp.getText();
        String localidad = panel_empresa_textfield_local.getText();
        String jornada = panel_empresa_combobox_jornada.getValue().toString();
        String modalidad = panel_empresa_combobox_mod.getValue().toString();
        String email = panel_empresa_textfield_mail.getText();

        Empresa empresaModificada = new Empresa(cod_empresa, nombre, CIF, direccion, cp, localidad, "", jornada, modalidad, email);
        empresaModificada.actualizarEmpresa();

        // Recoger los datos de los campos de entrada para el tutor laboral
        String dni_tutor = panel_personas_textfield_laboral.getText();
        String nombre_tutor = panel_personas_textfield_nomlaboral.getText();
        String apellidoCompletoTutor = panel_personas_textfield_apelaboral.getText();
        String[] partesApellidoTutor = apellidoCompletoTutor.split(" ");
        String apellido1_tutor = partesApellidoTutor[0];
        String apellido2_tutor = partesApellidoTutor.length > 1 ? partesApellidoTutor[1] : "";
        String correo_tutor = "";
        String telefono_tutor = panel_personas_textfield_telf.getText();

        TutorLaboral tutorLaboralModificado = new TutorLaboral(cod_empresa, dni_tutor, nombre_tutor, apellido1_tutor, apellido2_tutor, correo_tutor, telefono_tutor, cod_empresa);
        tutorLaboralModificado.actualizarTutorLaboral();

        // Recoger los datos de los campos de entrada para el representante legal
        String dni_repre_legal = panel_personas_textfield_legal.getText();
        String nombre_repre_legal = panel_personas_textfield_nomlegal.getText();
        String apellidoCompleto = panel_personas_textfield_apelegal.getText();
        String[] partesApellido = apellidoCompleto.split(" ");
        String apellido1_repre_legal = partesApellido[0];
        String apellido2_repre_legal = partesApellido.length > 1 ? partesApellido[1] : "";

        RepreLegal repreLegalModificado = new RepreLegal(cod_empresa, dni_repre_legal, nombre_repre_legal, apellido1_repre_legal, apellido2_repre_legal, cod_empresa);
        repreLegalModificado.actualizarRepreLegal();

        // Actualizar la tabla
        ObservableList<Empresa> listaEmpresas = Empresa.obtenerTodasLasEmpresas();
        empresas_bbdd_tabla.setItems(listaEmpresas);

        actualizarComboBoxes();
    }

    @FXML
    public void handleTutorImageClick() {
        TutorDocente.loadTutorDataFromXML();
        tutores_mensaje.setVisible(true);
        actualizarComboBoxes();
    }

    public void actualizarComboBoxes() {
        asig_combobox_empresa.setItems(Empresa.obtenerNombresEmpresas());
        asig_combobox_alumno.setItems(Alumnos.obtenerNombresApellidosAlumnos());
        asig_combobox_tutor.setItems(TutorDocente.obtenerNombresApellidosTutores());
    }

    public void insertarAsignacion(String nombreAlumno, String nombreEmpresa, String nombreTutorDocente) {
        Connection connection = ConexionBaseDeDatos.getConnection();
        String sql = "INSERT INTO Asignación (FechaAsignación, NombreAlumno, NombreEmpresa, NombreTutorDocente) VALUES (CURDATE(), ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreAlumno);
            preparedStatement.setString(2, nombreEmpresa);
            preparedStatement.setString(3, nombreTutorDocente);
            preparedStatement.executeUpdate();

            // Obtener el nombre del tutor laboral de la empresa seleccionada
            String nombreTutorLaboral = obtenerNombreTutorLaboral(nombreEmpresa);

            // Modificar el texto del label
            asig_label_texto_info.setText("El alumno " + nombreAlumno + " queda asignado a la empresa " + nombreEmpresa + " supervisado por el tutor docente " + nombreTutorDocente + " y por el tutor laboral " + nombreTutorLaboral);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String obtenerNombreTutorLaboral(String nombreEmpresa) {
        String nombreTutorLaboral = "";
        try (Connection conexion = ConexionBaseDeDatos.getConnection()) {
            String consulta = "SELECT nombre FROM Tutor_Laboral WHERE cod_empresa = (SELECT cod_empresa FROM Empresa WHERE nombre = ?)";
            PreparedStatement pstmt = conexion.prepareStatement(consulta);
            pstmt.setString(1, nombreEmpresa);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                nombreTutorLaboral = rs.getString("nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreTutorLaboral;
    }

    public void cambio_tamaño() {
        //cambiar el tamaño del panel
        panel_principal.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            Stage stage = (Stage) panel_principal.getScene().getWindow();

            if (newTab.getText().equals("Empresas")) {
                stage.setWidth(1015);
                stage.setHeight(680);
            } else if (newTab.getText().equals("Alumnos")) {
                stage.setWidth(620);
                stage.setHeight(340);
            } else if (newTab.getText().equals("Tutores")) {
                stage.setWidth(620);
                stage.setHeight(340);
            } else if (newTab.getText().equals("Asignación")) {
                stage.setWidth(620);
                stage.setHeight(340);
            }
            newTab = null;
        });
    }
}
