package org.example.proyectojavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import org.example.proyectojavafx.Empresa;

import java.util.List;

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

    @FXML
    private ImageView alumnos_foto;

    @FXML
    private Label alumnos_mensaje;

    @FXML
    private AnchorPane alumnos_panel;

    @FXML
    private Tab alumnos_tab;

    @FXML
    private Button asig_boton_confirm;

    @FXML
    private ChoiceBox<?> asig_combobox_alumno;

    @FXML
    private ChoiceBox<?> asig_combobox_empresa;

    @FXML
    private ChoiceBox<?> asig_combobox_tutor;

    @FXML
    private Label asig_label_alumnos;

    @FXML
    private Label asig_label_empresa;

    @FXML
    private Label asig_label_texto_info;

    @FXML
    private Label asig_label_tutor;

    @FXML
    private AnchorPane asig_panel;

    @FXML
    private Tab asig_tab;

    @FXML
    private TableColumn<?, ?> bbdd_colum_cif;

    @FXML
    private TableColumn<?, ?> bbdd_colum_cp;

    @FXML
    private TableColumn<?, ?> bbdd_colum_direcc;

    @FXML
    private TableColumn<?, ?> bbdd_colum_local;

    @FXML
    private TableColumn<?, ?> bbdd_colum_mail;

    @FXML
    private TableColumn<?, ?> bbdd_colum_mod;

    @FXML
    private TableColumn<?, ?> bbdd_colum_nombre;

    @FXML
    private TableView<?> empresas_bbdd_tabla;

    @FXML
    private Button empresas_boton_eliminar;

    @FXML
    private Button empresas_boton_insertar;

    @FXML
    private Button empresas_boton_modificar;

    @FXML
    private Label empresas_label_bbdd;

    @FXML
    private Label empresas_label_empresa;

    @FXML
    private Label empresas_label_personas;

    @FXML
    private AnchorPane empresas_panel;

    @FXML
    private Pane empresas_panel_bbdd;

    @FXML
    private Pane empresas_panel_empresa;

    @FXML
    private Pane empresas_panel_personas;

    @FXML
    private Tab empresas_tab;

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

    @FXML
    private TabPane panel_principal;

    @FXML
    private Label tit_gest_alumnos;

    @FXML
    private Label tit_gest_empresas;

    @FXML
    private Label tit_gest_plazas;

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

    // En la clase HelloController
    public void initialize() {
        List<String> opcionesJornada = Empresa.obtenerOpciones("Jornada");
        panel_empresa_combobox_jornada.getItems().addAll(opcionesJornada);

        List<String> opcionesModalidad = Empresa.obtenerOpciones("Modalidad");
        panel_empresa_combobox_mod.getItems().addAll(opcionesModalidad);
    }

    @FXML
    void botonEliminarEmpresa(ActionEvent event) {

    }

    @FXML
    void botonInsertarEmpresa(ActionEvent event) {
        // Recoger los datos de los campos de entrada
        String CIF = panel_empresa_textfield_cif.getText();
        String RazonSocial = panel_empresa_textfield_nom.getText();
        String Localidad = panel_empresa_textfield_local.getText();
        String Jornada = panel_empresa_combobox_jornada.getValue().toString();
        String Modalidad = panel_empresa_combobox_mod.getValue().toString();
        String Email = panel_empresa_textfield_mail.getText();
        int Idrepresentante = Integer.parseInt(panel_personas_textfield_legal.getText());

        // Crear una nueva instancia de la clase Empresa
        Empresa nuevaEmpresa = new Empresa(CIF, RazonSocial, Localidad, Jornada, Modalidad, Email, Idrepresentante);

        // Insertar la nueva empresa en la base de datos
        nuevaEmpresa.insertarEmpresa();
    }

    @FXML
    void botonModificarEmpresa(ActionEvent event) {

    }

}
