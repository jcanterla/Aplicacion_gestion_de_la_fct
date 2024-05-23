import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Esta clase representa la interfaz principal de la aplicación de gestión de datos para empresas, alumnos y tutores.
 * Proporciona una interfaz gráfica de usuario (GUI) para interactuar con la base de datos y realizar operaciones
 * como insertar, modificar, eliminar y visualizar datos de empresas, alumnos y tutores.
 *
 * La interfaz está dividida en varias pestañas, cada una dedicada a una funcionalidad específica:
 * - Empresas: Permite la gestión de datos de empresas, incluyendo la inserción, modificación y eliminación.
 * - Alumnos: Facilita la importación de datos de alumnos desde un archivo y su gestión dentro de la aplicación.
 * - Tutores: Permite la importación y gestión de datos de tutores desde un archivo XML.
 * - Asignación: Ofrece una interfaz para asignar alumnos a empresas y tutores, gestionando estas relaciones.
 *
 * La clase utiliza JDBC para conectarse a una base de datos MariaDB y realizar las operaciones SQL necesarias.
 *
 * @version 1.0
 */
public class Interfaz {

    private JTextField cif;
    private JTextField nombre;
    private JTextField direccion;
    private JTextField CP;
    private JTextField localidad;
    private JComboBox jornada;
    private JComboBox modalidad;
    private JTextField email;
    private JTextField dniR;
    private JTextField nombreR;
    private JTextField apellidoR;
    private JTextField dniT;
    private JTextField nombreT;
    private JTextField apellidoT;
    private JTextField telefonoT;
    private JTextField cod;

    private JTable tablaEmpresas;
    private DefaultTableModel modeloTabla;
    private JComboBox eleccionT = new JComboBox();
    private JComboBox eleccionE = new JComboBox();
    private JComboBox eleccionA = new JComboBox();
    private boolean botonUsado = false;
    private boolean botonUsado1 = false;

    /**
     * Método principal que inicia la aplicación.
     * @param args Los argumentos de la línea de comandos, no se utilizan en esta aplicación.
     */

    public static void main(String[] args) {
        new Interfaz();
    }

    /**
     * Constructor de la clase. Inicializa la interfaz gráfica de usuario y configura los componentes y eventos.
     */
    public Interfaz() {     // Panel principal y de pestañas
        JFrame marco = new JFrame();
        JPanel panelGeneral = new JPanel(new BorderLayout());
        JTabbedPane panelPestanas = new JTabbedPane();

        panelGeneral.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));

        // Bordes para los paneles
        Border bordeReducido = BorderFactory.createEmptyBorder(6, 5, 6, 5);
        Border borde = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Empresas");
        Border bordecon = BorderFactory.createCompoundBorder(borde, bordeReducido);

        // Tipo de fuente Arial como indica en el Pdf y de tamaño 20 para todos los títulos
        Font fuente = new Font("Arial", Font.BOLD, 20);

        // Listener para cambiar el tamaño del marco al cambiar de pestaña
        panelPestanas.addChangeListener(e ->{
            int pestaña = panelPestanas.getSelectedIndex();
            if(pestaña == 0){
                marco.setSize(1015, 680);
            }else if(pestaña == 1){
                marco.setSize(620, 340);

            }else if(pestaña == 2){
                marco.setSize(620, 340);

            }else if(pestaña == 3){
                marco.setSize(620, 340);
            }
            cargarAlumnosEmpresasTutores();
        });

        // Pestaña Empresas
        panelPestanas.addTab("Empresas",panelGeneral);
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titulo = new JLabel("GESTIÓN DE DATOS DE EMPRESAS");



        titulo.setFont(fuente);
        panelTitulo.add(titulo);

        JPanel Panelempresas = new JPanel();

        Panelempresas.setBorder(bordecon);


        // Inputs y etiquetas para los datos empresa
        JLabel codLabel = new JLabel();
        codLabel.setText("Código empresa:");
        cod=new JTextField(" ",1);
        cod.setEditable(false);
        JLabel cifLabel = new JLabel();
        cif=new JTextField(" ",6);
        cifLabel.setText("CIF:");
        JLabel nombreLabel = new JLabel();
        nombre=new JTextField(" ",30);
        nombreLabel.setText("Nombre:");

        JLabel direccionLabel = new JLabel();
        direccionLabel.setText("Dirección:");
        direccion=new JTextField(" ",15);
        JLabel cpLabel = new JLabel();
        CP=new JTextField(" ",4);
        cpLabel.setText("C.P.:");
        JLabel localidadLabel = new JLabel();
        localidad=new JTextField(" ",20);
        localidadLabel.setText("Localidad:");

        JLabel jornadaLabel = new JLabel();
        jornadaLabel.setText("Jornada:");
        jornada = new JComboBox();
        jornada.addItem("Partida");
        jornada.addItem("Continua");
        JLabel modalidadLabel = new JLabel();
        modalidad = new JComboBox();
        modalidad.addItem("Presencial");
        modalidad.addItem("Semipresencial");
        modalidad.addItem("Distancia");
        modalidadLabel.setText("Modalidad:");
        JLabel emailLabel = new JLabel();
        email=new JTextField(" ",20);
        emailLabel.setText("Mail:");


        // Tres paneles para cada fila de datos
        JPanel panel1E = new JPanel(new FlowLayout(FlowLayout.LEFT,15,5));
        JPanel panel2E = new JPanel(new FlowLayout(FlowLayout.LEFT,15,5));
        JPanel panel3E = new JPanel(new FlowLayout(FlowLayout.LEFT,15,5));


        // Se añaden cada elemento a su panel
        panel1E.add(codLabel);
        panel1E.add(cod);
        panel1E.add(cifLabel);
        panel1E.add(cif);
        panel1E.add(nombreLabel);
        panel1E.add(nombre);

        panel2E.add(direccionLabel);
        panel2E.add(direccion);
        panel2E.add(cpLabel);
        panel2E.add(CP);
        panel2E.add(localidadLabel);
        panel2E.add(localidad);

        panel3E.add(jornadaLabel);
        panel3E.add(jornada);
        panel3E.add(modalidadLabel);
        panel3E.add(modalidad);
        panel3E.add(emailLabel);
        panel3E.add(email);

        Panelempresas.setLayout(new GridLayout(3, 0));
        panel1E.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
        panel2E.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
        panel3E.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));

        // Se añaden los tres paneles al panel padre
        Panelempresas.add(panel1E);
        Panelempresas.add(panel2E);
        Panelempresas.add(panel3E);

        // Panel Personas
        JPanel panelPersonas = new JPanel(new FlowLayout(FlowLayout.CENTER,15,0));
        Border borde2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Personas");

        // Inputs y etiquetas para los datos de personas
        JLabel dniRLabel = new JLabel();
        dniRLabel.setText("DNI Rep. Legal:");
        dniR=new JTextField(" ",5);
        JLabel nombreRLabel = new JLabel();
        nombreR=new JTextField(" ",7);
        nombreRLabel.setText("Nombre RL:");
        JLabel apellidoRLabel = new JLabel();
        apellidoR=new JTextField(" ",20);
        apellidoRLabel.setText("Apellido RL:");

        JLabel dniTLabel = new JLabel();
        dniTLabel.setText("DNI Tut. Laboral:");
        dniT=new JTextField(" ",5);
        JLabel nombreTLabel = new JLabel();
        nombreT=new JTextField(" ",7);
        nombreTLabel.setText("Nombre TL:");
        JLabel apellidoTLabel = new JLabel();
        apellidoT=new JTextField(" ",13);
        apellidoTLabel.setText("Apellido TL:");
        JLabel telefonoTLabel = new JLabel();
        telefonoT=new JTextField(" ",9);
        telefonoTLabel.setText("Tlf. TL:");

        // Dos paneles para cada fila de datos
        JPanel Panel1P = new JPanel(new FlowLayout(FlowLayout.LEFT,20,5));
        JPanel Panel2P = new JPanel(new FlowLayout(FlowLayout.LEFT,20,5));

        // Se añaden bordes a los paneles
        panelPersonas.setBorder(borde2);
        panelPersonas.setLayout(new GridLayout(2, 2, 2, 2));
        Panel1P.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        Panel2P.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // Se añaden cada elemento a su panel
        Panel1P.add(dniRLabel);
        Panel1P.add(dniR);
        Panel1P.add(nombreRLabel);
        Panel1P.add(nombreR);
        Panel1P.add(apellidoRLabel);
        Panel1P.add(apellidoR);

        Panel2P.add(dniTLabel);
        Panel2P.add(dniT);
        Panel2P.add(nombreTLabel);
        Panel2P.add(nombreT);
        Panel2P.add(apellidoTLabel);
        Panel2P.add(apellidoT);
        Panel2P.add(telefonoTLabel);
        Panel2P.add(telefonoT);

        // Se añaden los dos paneles al panel padre
        panelPersonas.add(Panel1P);
        panelPersonas.add(Panel2P);

        // Panel Botones
        JPanel PanelB = new JPanel();
        JPanel Panelbotones = new JPanel(new FlowLayout(FlowLayout.CENTER,0,10));
        PanelB.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // Bordes para los paneles
        Border bordeLowered = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        Border bordePequeno = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        Panelbotones.setBorder(BorderFactory.createCompoundBorder(bordeLowered, bordePequeno));

        // Botones para insertar, modificar y eliminar datos
        JButton botonInsertar = new JButton("Insertar");
        botonInsertar.setBackground(new Color(0x5ADF5D));

        JButton botonModificar = new JButton("Modificar");
        botonModificar.setBackground(new Color(0xE9AD4D));

        JButton botonEliminar = new JButton("Eliminar");
        botonEliminar.setBackground(new Color(0xE9564D));


        // Se añaden los botones al panel de botones
        Panelbotones.add(botonInsertar);
        Panelbotones.add(botonModificar);
        Panelbotones.add(botonEliminar);

        // Se añade el panel de botones al panel padre
        PanelB.add(Panelbotones);

        // Reflejo base de datos
        JPanel panelTabla = new JPanel();
        Border borde3 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Reflejo de la Base de Datos");
        panelTabla.setBorder(borde3);

        // Crear modelo de tabla para la tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("CIF");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("C.P.");
        modeloTabla.addColumn("Localidad");
        modeloTabla.addColumn("Jornada");
        modeloTabla.addColumn("Modalidad");
        modeloTabla.addColumn("Email");

        // Crear tabla para las empresas
        tablaEmpresas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaEmpresas);
        scrollPane.setPreferredSize(new Dimension(900, 200));

        // Variable para almacenar la última fila seleccionada
        int[] lastSelectedRow = {-1};

        tablaEmpresas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tablaEmpresas.getSelectedRow();
                if (selectedRow == lastSelectedRow[0]) {
                    tablaEmpresas.clearSelection();
                    lastSelectedRow[0] = -1; // Reset the last selected row

                    // Clear text fields
                    cod.setText("");
                    cif.setText("");
                    nombre.setText("");
                    direccion.setText("");
                    CP.setText("");
                    localidad.setText("");
                    jornada.setSelectedIndex(0);
                    modalidad.setSelectedIndex(0);
                    email.setText("");

                    dniR.setText("");
                    nombreR.setText("");
                    apellidoR.setText("");

                    dniT.setText("");
                    nombreT.setText("");
                    apellidoT.setText("");
                    telefonoT.setText("");

                } else {
                    lastSelectedRow[0] = selectedRow;

                    // Set the values from the selected row
                    cod.setText(String.valueOf(selectedRow + 1)); // Adjust based on your table's indexing

                    String cifEmpresa = (String) modeloTabla.getValueAt(selectedRow, 0);
                    cif.setText(cifEmpresa);

                    try (java.sql.Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/proy3TEx", "root", "root");
                         java.sql.Statement sentencia = conexion.createStatement()) {

                        // Get the company code based on CIF
                        String consultaEmpresa = "SELECT cod_empresa FROM Empresa WHERE CIF = ?";
                        PreparedStatement sentenciaEmpresa = conexion.prepareStatement(consultaEmpresa);
                        sentenciaEmpresa.setString(1, cifEmpresa);
                        ResultSet resultadoEmpresa = sentenciaEmpresa.executeQuery();

                        int codEmpresa = 0;
                        if (resultadoEmpresa.next()) {
                            codEmpresa = resultadoEmpresa.getInt("cod_empresa");
                        }

                        // Get representative legal data
                        String consultaRepreLegal = "SELECT dni, nombre, apellido1 FROM Repre_Legal WHERE cod_empresa = ?";
                        PreparedStatement sentenciaRepreLegal = conexion.prepareStatement(consultaRepreLegal);
                        sentenciaRepreLegal.setInt(1, codEmpresa);
                        ResultSet resultadoRepreLegal = sentenciaRepreLegal.executeQuery();

                        if (resultadoRepreLegal.next()) {
                            dniR.setText(resultadoRepreLegal.getString("dni"));
                            nombreR.setText(resultadoRepreLegal.getString("nombre"));
                            apellidoR.setText(resultadoRepreLegal.getString("apellido1"));
                        } else {
                            dniR.setText("");
                            nombreR.setText("");
                            apellidoR.setText("");
                        }

                        // Get tutor laboral data
                        String consultaTutorLaboral = "SELECT dni, nombre, apellido1, telefono FROM Tutor_Laboral WHERE cod_empresa = ?";
                        PreparedStatement sentenciaTutorLaboral = conexion.prepareStatement(consultaTutorLaboral);
                        sentenciaTutorLaboral.setInt(1, codEmpresa);
                        ResultSet resultadoTutorLaboral = sentenciaTutorLaboral.executeQuery();

                        if (resultadoTutorLaboral.next()) {
                            dniT.setText(resultadoTutorLaboral.getString("dni"));
                            nombreT.setText(resultadoTutorLaboral.getString("nombre"));
                            apellidoT.setText(resultadoTutorLaboral.getString("apellido1"));
                            telefonoT.setText(resultadoTutorLaboral.getString("telefono"));
                        } else {
                            dniT.setText("");
                            nombreT.setText("");
                            apellidoT.setText("");
                            telefonoT.setText("");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


        panelTabla.add(scrollPane);

        // Agregar panel y titulo al panel general
        panelGeneral.add(panelTitulo);
        panelGeneral.add(Panelempresas);


        llenarTablaEmpresas(modeloTabla);


        // Listener para el botón de insertar
        botonInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarTotal();
            }
        });

        // Seleccionar fila y llenar campos
        tablaEmpresas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tablaEmpresas.getSelectedRow();
                if (selectedRow != -1) {
                    cod.setText(String.valueOf(selectedRow));
                    cif.setText((String) modeloTabla.getValueAt(selectedRow, 0));
                    nombre.setText((String) modeloTabla.getValueAt(selectedRow, 1));
                    direccion.setText((String) modeloTabla.getValueAt(selectedRow, 2));
                    CP.setText((String) modeloTabla.getValueAt(selectedRow, 3));
                    localidad.setText((String) modeloTabla.getValueAt(selectedRow, 4));
                    jornada.setSelectedItem(modeloTabla.getValueAt(selectedRow, 5));
                    modalidad.setSelectedItem(modeloTabla.getValueAt(selectedRow, 6));
                    email.setText((String) modeloTabla.getValueAt(selectedRow, 7));

                    try {
                        Connection con = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/proy3TEx", "root", "root");
                        PreparedStatement stmt = con.prepareStatement("SELECT * FROM repre_legal WHERE cod_empresa = ?");
                        stmt.setString(1, cod.getText());
                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {
                            dniR.setText(rs.getString("dni"));
                            nombreR.setText(rs.getString("nombre"));
                            apellidoR.setText(rs.getString("apellido1") + " " + rs.getString("apellido2"));
                        }



                        PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM tutor_laboral WHERE cod_empresa = ?");
                        stmt2.setString(1, cod.getText());
                        ResultSet rs2 = stmt2.executeQuery();

                        if (rs2.next()) {
                            dniT.setText(rs2.getString("dni"));
                            nombreT.setText(rs2.getString("nombre"));
                            apellidoT.setText(rs2.getString("apellido1") + " " + rs2.getString("apellido2"));
                            telefonoT.setText(rs2.getString("telefono"));
                        }

                        rs.close();
                        stmt.close();
                        rs2.close();
                        stmt2.close();
                        con.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });

        // Modificar datos
        botonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarTotal();
            }

        });

        // Eliminar datos
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarTotal();
            }

        });

        // Añadir los paneles al panel principal de la pestaña empresa
        panelGeneral.add(panelPersonas);
        panelGeneral.add(Box.createRigidArea(new Dimension(70, 10)));
        panelGeneral.add(Panelbotones);
        panelGeneral.add(panelTabla);

        // Pestaña Alumnos y titulo
        JPanel panelGeneral2 = new JPanel();
        panelPestanas.addTab("Alumnos", panelGeneral2);
        JLabel titulo2 = new JLabel("GESTIÓN DE DATOS DE ALUMNOS");

        // Bordes y fuente panel alumnos
        panelGeneral2.add(titulo2);
        panelGeneral2.setLayout(new BoxLayout(panelGeneral2, BoxLayout.Y_AXIS));
        panelGeneral2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelGeneral2.setLayout(new FlowLayout(FlowLayout.CENTER));
        titulo2.setFont(fuente);

        // Panel principal para importar datos del archivo DAT
        JPanel Panelboton = new JPanel();
        Panelboton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        // Panel para el botón de importar datos e imagen
        JPanel panelDat = new JPanel();
        ImageIcon imagenDat = new ImageIcon("src/DATaTabla.png");
        JLabel Mensajefinal1 = new JLabel();
        Mensajefinal1.setText("Información del fichero ahora registrada en la tabla de Alumnos. ");
        Border outerBorder = BorderFactory.createLineBorder(new Color(153,204,102));
        Mensajefinal1.setBackground(new Color(153,204,102));
        Mensajefinal1.setOpaque(true);
        Mensajefinal1.setBorder(outerBorder);
        Mensajefinal1.setVisible(false);




        // Botón para importar datos
        JButton botonDat = new JButton();
        botonDat.setIcon(imagenDat);

        botonDat.addActionListener(e -> {
            Mensajefinal1.setVisible(true);
        });

        // Listener para el botón de importar datos al archivo DAT
        botonDat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarAlumnosDesdeArchivo();
            }
        });

        // Añadir cada panel a su panel padre
        panelDat.add(botonDat);
        Panelboton.add(panelDat);

        panelGeneral2.add(Panelboton);
        panelGeneral2.add(Mensajefinal1);

        // Pestaña Tutores y titulo
        JPanel panelGeneral3 = new JPanel();
        panelPestanas.addTab("Tutores", panelGeneral3);
        JLabel titulo3 = new JLabel("GESTIÓN DE DATOS DE TUTORES");

        titulo3.setFont(fuente);
        panelGeneral3.setLayout(new BoxLayout(panelGeneral3, BoxLayout.Y_AXIS));
        panelGeneral3.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelGeneral3.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Panel principal para importar datos del archivo XML
        JPanel Panelboton2 = new JPanel();

        Panelboton2.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        // Panel, botón e imagen importar datos
        JPanel panelXml = new JPanel();
        ImageIcon imagenXml = new ImageIcon("src/XMLaTabla.png");
        JButton botonXml = new JButton();
        JLabel Mensajefinal2 = new JLabel();
        Mensajefinal2.setText("Información del fichero ahora registrada en la tabla de tutores. ");
        Border outerBorder2 = BorderFactory.createLineBorder(new Color(102,153,204));
        Mensajefinal2.setBackground(new Color(102,153,204));
        Mensajefinal2.setOpaque(true);
        Mensajefinal2.setBorder(outerBorder2);
        Mensajefinal2.setVisible(false);

        botonXml.addActionListener(e -> {
            Mensajefinal2.setVisible(true);
        });

        // Listener para el botón de importar datos al archivo XML
        botonXml.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDatosDesdeXML();
            }
        });

        botonXml.setIcon(imagenXml);

        panelXml.add(botonXml);
        Panelboton2.add(panelXml);

        panelGeneral3.add(titulo3);
        panelGeneral3.add(Panelboton2);
        panelGeneral3.add(Mensajefinal2);


        // Pestaña Asignación y titulo
        JPanel panelGeneral4 = new JPanel(new FlowLayout());
        panelPestanas.addTab("Asignación", panelGeneral4);
        JLabel titulo4 = new JLabel("GESTIÓN DE LA ASIGNACIÓN DE PLAZAS");

        panelGeneral4.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelGeneral4.setLayout(new FlowLayout(FlowLayout.CENTER));
        titulo4.setFont(fuente);

        // Panel para las elecciones de alumno, empresa y tutor
        JPanel PanelCentro = new JPanel(new BorderLayout());
        JPanel PanelElecciones = new JPanel(new BorderLayout());
        JPanel PanelBoton = new JPanel(new BorderLayout());
        JPanel PanelMensaje = new JPanel(new BorderLayout());
        JPanel PanelElecciones1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel PanelElecciones2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel PanelElecciones3 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        PanelElecciones.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        JLabel eleccionALabel = new JLabel("Elección de Alumno:");
        JLabel eleccionELabel = new JLabel("Elección de Empresa:");
        JLabel eleccionTLabel = new JLabel("Elección de Tutor:");

        eleccionA.setPreferredSize(new Dimension(150,25));
        eleccionE.setPreferredSize(new Dimension(100,25));
        eleccionT.setPreferredSize(new Dimension(100,25));

        // Añadir los elementos al panel de elecciones
        PanelElecciones1.add(eleccionALabel);
        PanelElecciones1.add(eleccionA);
        PanelElecciones2.add(eleccionELabel);
        PanelElecciones2.add(eleccionE);
        PanelElecciones3.add(eleccionTLabel);
        PanelElecciones3.add(eleccionT);


        PanelElecciones.add(PanelElecciones1, BorderLayout.NORTH);
        PanelElecciones.add(PanelElecciones2, BorderLayout.CENTER);
        PanelElecciones.add(PanelElecciones3, BorderLayout.SOUTH);


        JButton botonConfirmar = new JButton("Confirmar");
        botonConfirmar.setBackground(new Color(0x5ADF5D));

        Box botonBox = Box.createHorizontalBox();
        botonBox.add(botonConfirmar);
        botonBox.add(Box.createHorizontalGlue());
        PanelBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
        PanelBoton.add(botonBox);

        JLabel Mensajefinal3 = new JLabel();
        Mensajefinal3.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        botonConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarConfirmar();
                String seleccion = (String) eleccionA.getSelectedItem();
                String seleccion2 = (String) eleccionE.getSelectedItem();
                String seleccion3 = (String) eleccionT.getSelectedItem();
                Mensajefinal3.setText("El alumno " + seleccion + " ha sido asignado a la empresa " + seleccion2 + " con el tutor " + seleccion3 + ".");
                Mensajefinal3.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                Mensajefinal3.setBackground(Color.GRAY);
                Mensajefinal3.setOpaque(true);

            }
        });

        //PanelBoton.add(botonConfirmar);
        PanelMensaje.add(Mensajefinal3);

        PanelCentro.add(PanelElecciones, BorderLayout.NORTH);
        PanelCentro.add(PanelBoton, BorderLayout.CENTER);
        PanelCentro.add(PanelMensaje, BorderLayout.SOUTH);
        panelGeneral4.add(titulo4);
        panelGeneral4.add(PanelCentro);


        //
        for (int i = 1; i < panelPestanas.getTabCount(); i++) {
            JPanel panel = (JPanel) panelPestanas.getComponentAt(i);
            panel.setPreferredSize(new Dimension(680, 340));
        }

        // Añadir los paneles al marco principal
        marco.add(panelPestanas, BorderLayout.NORTH);


        // Configuración del marco
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setVisible(true);

        // Centrar el marco en la pantalla
        marco.setLocationRelativeTo(null);

        actualizarCodigoEmpresa(cod);

    }
    /**
     * Método para insertar la confirmación de asignación en la base de datos.
     * Este método se encarga de recoger los datos seleccionados en la interfaz y ejecutar la inserción en la base de datos.
     */
    private void insertarConfirmar() {
        String nombreAlumno = (String) eleccionA.getSelectedItem();
        String nombreEmpresa = (String) eleccionE.getSelectedItem(); // Aunque no se usa directamente, se asume que puede ser necesario para futuras expansiones o validaciones.
        String nombreTutorDocente = (String) eleccionT.getSelectedItem();

        java.sql.Date fechaAsignacion = java.sql.Date.valueOf(LocalDate.now());

        String url = "jdbc:mariadb://localhost:3306/proy3TEx";
        String usuario = "root";
        String contrasena = "root";

        String sql = "INSERT INTO Asignación (FechaAsignación, NombreAlumno, NombreEmpresa, NombreTutorDocente) VALUES (?, ?, ?, ?)";

        try (Connection conexion = (Connection) DriverManager.getConnection(url, usuario, contrasena);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setDate(1, fechaAsignacion);
            pstmt.setString(2, nombreAlumno);
            pstmt.setString(3, nombreEmpresa);
            pstmt.setString(4, nombreTutorDocente);

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Asignación realizada con éxito.");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al realizar la asignación: " + ex.getMessage());
        }
        // La conexión y la sentencia se cierran automáticamente
    }

    /**
     * Inserta los datos de una empresa, representante legal y tutor laboral en la base de datos,
     * después de validar los campos introducidos en la interfaz gráfica.
     * <p>
     * Este método realiza las siguientes validaciones:
     * <ul>
     *     <li>Todos los campos deben estar completos.</li>
     *     <li>El CIF debe tener exactamente 9 caracteres y cumplir con el formato especificado.</li>
     *     <li>El código postal debe tener exactamente 5 dígitos.</li>
     *     <li>El email debe tener un formato válido.</li>
     *     <li>El DNI del representante y del tutor deben tener exactamente 9 caracteres y cumplir con el formato especificado.</li>
     *     <li>El teléfono del tutor debe tener exactamente 9 dígitos.</li>
     * </ul>
     * Si alguna de las validaciones falla, se muestra un mensaje de error al usuario.
     * <p>
     * Después de realizar las validaciones, el método obtiene los identificadores necesarios
     * para insertar los nuevos registros en las tablas `Empresa`, `Repre_Legal` y `Tutor_Laboral`
     * de la base de datos, asegurando que no haya duplicados en los campos clave.
     * <p>
     * Si la inserción es exitosa, se actualiza la tabla de empresas en la interfaz gráfica y
     * se limpian los campos de entrada. Si ocurre un error durante la inserción, se muestra
     * un mensaje de error al usuario.
     */

    private void insertarTotal() {
        // Obtener los datos
        String cifValue = cif.getText().trim();
        String nombreValue = nombre.getText().trim();
        String direccionValue = direccion.getText().trim();
        String cpValue = CP.getText().trim();
        String localidadValue = localidad.getText().trim();
        String jornadaValue = (String) jornada.getSelectedItem();
        String modalidadValue = (String) modalidad.getSelectedItem();
        String emailValue = email.getText().trim();

        String dniRValue = dniR.getText().trim();
        String nombreRValue = nombreR.getText().trim();
        String apellidoRValue = apellidoR.getText().trim();

        String dniTValue = dniT.getText().trim();
        String nombreTValue = nombreT.getText().trim();
        String apellidoTValue = apellidoT.getText().trim();

        String telefonoTValue = telefonoT.getText().trim();


        if (cifValue.isEmpty() || nombreValue.isEmpty() || direccionValue.isEmpty() || cpValue.isEmpty() || localidadValue.isEmpty() || jornadaValue.isEmpty() || modalidadValue.isEmpty() || emailValue.isEmpty() || dniRValue.isEmpty() || nombreRValue.isEmpty() || apellidoRValue.isEmpty() || dniTValue.isEmpty() || nombreTValue.isEmpty() || apellidoTValue.isEmpty() || telefonoTValue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos.");
            return;
        }


        if (cifValue.length() != 9) {
            JOptionPane.showMessageDialog(null, "Error, el CIF tiene que tener 9 carácteres.");
            return;
        }

        String cif_path = "^[A-Za-z][0-9]{8}$";
        Pattern cif_path_2 = Pattern.compile(cif_path);
        Matcher cif_comprobar = cif_path_2.matcher(cifValue);

        if (!cif_comprobar.matches()) {
            JOptionPane.showMessageDialog(null, "Error, el CIF debe tener una letra como primer carácter y los demás como dígito.");
            return;
        }


        if (cpValue.length() != 5 || !cpValue.matches("[0-9]{5}")) {
            JOptionPane.showMessageDialog(null, "El código postal debe tener exactamente 5 dígitos.");
            return;
        }


        // Validar el email
        String email_path = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        // Crear el patrón y el matcher
        Pattern path = Pattern.compile(email_path);
        Matcher comprobar = path.matcher(emailValue);

        // Validar el email
        if (!comprobar.matches()) {
            JOptionPane.showMessageDialog(null, "Error, el formato del email no es correcto");
            return;
        }


        if (dniRValue.length() != 9) {
            JOptionPane.showMessageDialog(null, "Error, los DNI tienen que tener 9 carácteres.");
            return;
        }

        String dniRPath = "^[0-9]{8}[A-Za-z]$";
        Pattern dni_Path_2 = Pattern.compile(dniRPath);
        Matcher dniR_comprobar = dni_Path_2.matcher(dniRValue);

        if (!dniR_comprobar.matches()) {
            JOptionPane.showMessageDialog(null, "El DNI debe tener 8 números seguidos de una letra.");
            return;
        }



        if (dniTValue.length() != 9) {
            JOptionPane.showMessageDialog(null, "Error, los DNI tienen que tener 9 carácteres.");
            return;
        }

        String dniTPath = "^[0-9]{8}[A-Za-z]$";
        Pattern dniT_Path_2 = Pattern.compile(dniTPath);
        Matcher dniT_comprobar = dniT_Path_2.matcher(dniTValue);

        if (!dniT_comprobar.matches()) {
            JOptionPane.showMessageDialog(null, "El DNI debe tener 8 números seguidos de una letra.");
            return;
        }


        if (telefonoTValue.length() != 9) {
            JOptionPane.showMessageDialog(null, "Error, los teléfonos móviles tienen que tener 9 dígitos.");
            return;
        }

        int cod_empresa = 0;
        int id_representante = 0;
        int id_tutor = 0;

        // Insertarlos en la BD
        try (java.sql.Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/proy3TEx", "root", "root")) {

            // Consulta para obtener el siguiente cod_empresa para Empresa
            String consultaEmpresa = "SELECT MAX(cod_empresa) AS max_cod FROM Empresa";
            PreparedStatement sentenciaEmpresa = conexion.prepareStatement(consultaEmpresa);
            ResultSet resultadoEmpresa = sentenciaEmpresa.executeQuery();

            if (resultadoEmpresa.next()) {
                int maxCodEmpresa = resultadoEmpresa.getInt("max_cod");
                cod_empresa = maxCodEmpresa + 1;
            }

            // Consulta para obtener el siguiente id para Repre_Legal
            String consultaRepreLegal = "SELECT MAX(id) AS max_id FROM Repre_Legal";
            PreparedStatement sentenciaRepreLegal = conexion.prepareStatement(consultaRepreLegal);
            ResultSet resultadoRepreLegal = sentenciaRepreLegal.executeQuery();

            if (resultadoRepreLegal.next()) {
                int maxIdRepreLegal = resultadoRepreLegal.getInt("max_id");
                id_representante = maxIdRepreLegal + 1;
            }

            // Consulta para obtener el siguiente cod_tutor_laboral para Tutor_Laboral
            String consultaTutorLaboral = "SELECT MAX(cod_tutor_laboral) AS max_cod FROM Tutor_Laboral";
            PreparedStatement sentenciaTutorLaboral = conexion.prepareStatement(consultaTutorLaboral);
            ResultSet resultadoTutorLaboral = sentenciaTutorLaboral.executeQuery();

            if (resultadoTutorLaboral.next()) {
                int maxCodTutorLaboral = resultadoTutorLaboral.getInt("max_cod");
                id_tutor = maxCodTutorLaboral + 1;
            }


            String sql = "INSERT INTO Empresa (cod_empresa, CIF, nombre, direccion, cp, localidad, jornada, modalidad, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, cod_empresa);
            stmt.setString(2, cifValue);
            stmt.setString(3, nombreValue);
            stmt.setString(4, direccionValue);  // corregir el nombre de la columna
            stmt.setString(5, cpValue);
            stmt.setString(6, localidadValue);
            stmt.setString(7, jornadaValue);
            stmt.setString(8, modalidadValue);
            stmt.setString(9, emailValue);

            String consulta_Repre_legal = "SELECT COUNT(*) AS count FROM Repre_Legal";
            PreparedStatement sentencia_Repre_legal = conexion.prepareStatement(consulta_Repre_legal);
            ResultSet resultado_2 = sentencia_Repre_legal.executeQuery();

            if (resultado_2.next()) {
                int count = resultado_2.getInt("count");
                if (count > 0) {
                    id_representante += 1;
                }
            }
            resultado_2.close();
            sentencia_Repre_legal.close();

            String sql2 = "INSERT INTO Repre_Legal (id, dni, nombre, apellido1, apellido2, cod_empresa) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt2 = conexion.prepareStatement(sql2);
            stmt2.setInt(1, id_representante);
            stmt2.setString(2, dniRValue);
            stmt2.setString(3, nombreRValue);

            String apellido1R = "";
            String apellido2R = "";
            if (apellidoRValue.contains(" ")) {
                apellido1R = apellidoRValue.substring(0, apellidoRValue.indexOf(" "));
                apellido2R = apellidoRValue.substring(apellidoRValue.indexOf(" ") + 1);
            } else {
                apellido1R = apellidoRValue;
            }

            stmt2.setString(4, apellido1R);
            stmt2.setString(5, apellido2R);
            stmt2.setString(6, String.valueOf(cod_empresa));


            String consulta_Tutor_Laboral = "SELECT COUNT(*) AS count FROM Tutor_Laboral";
            PreparedStatement sentencia_Tutor_Laboral = conexion.prepareStatement(consulta_Tutor_Laboral);
            ResultSet resultado_3 = sentencia_Tutor_Laboral.executeQuery();

            if (resultado_3.next()) {
                int count = resultado_3.getInt("count");
                if (count > 0) {
                    id_tutor += 1;
                }
            }
            resultado_3.close();
            sentencia_Tutor_Laboral.close();

            String sql3 = "INSERT INTO Tutor_Laboral (cod_tutor_laboral, dni, nombre, apellido1, apellido2, telefono, cod_empresa) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt3 = conexion.prepareStatement(sql3);
            stmt3.setInt(1, id_tutor);
            stmt3.setString(2, dniTValue);
            stmt3.setString(3, nombreTValue);

            String apellido1T = "";
            String apellido2T = "";
            if (apellidoTValue.contains(" ")) {
                apellido1T = apellidoTValue.substring(0, apellidoTValue.indexOf(" "));
                apellido2T = apellidoTValue.substring(apellidoTValue.indexOf(" ") + 1);
            } else {
                apellido1T = apellidoTValue;
            }

            stmt3.setString(4, apellido1T);
            stmt3.setString(5, apellido2T);
            stmt3.setString(6, telefonoTValue);
            stmt3.setString(7, String.valueOf(cod_empresa));



            // Ejecutar la consulta
            int filasAfectadas = stmt.executeUpdate();
            int filasAfectadas2 = stmt2.executeUpdate();
            int filasAfectadas3 = stmt3.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (filasAfectadas > 0 || filasAfectadas2 > 0 || filasAfectadas3 > 0) {
                // Actualizar la tabla de empresas con los nuevos datos
                DefaultTableModel modelo = (DefaultTableModel) tablaEmpresas.getModel();
                modelo.addRow(new Object[]{cifValue, nombreValue, direccionValue, cpValue, localidadValue, jornadaValue, modalidadValue, emailValue});

                JOptionPane.showMessageDialog(null, "Datos insertados correctamente.");
                // Limpiar los campos de texto después de la inserción
                cif.setText("");
                nombre.setText("");
                direccion.setText("");
                CP.setText("");
                localidad.setText("");
                jornada.setSelectedIndex(0);
                modalidad.setSelectedIndex(0);
                email.setText("");

                dniR.setText("");
                nombreR.setText("");
                apellidoR.setText("");

                dniT.setText("");
                nombreT.setText("");
                apellidoT.setText("");

                telefonoT.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Error al insertar datos en la base de datos.");
            }

            // Cerrar la conexión y liberar recursos
            stmt.close();
            stmt2.close();
            stmt3.close();
            conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar datos en la base de datos: No puede haber nombres de empresas duplicados");
        }
    }

    /**
     * Modifica los datos de una empresa seleccionada en la tabla de empresas, así como sus respectivos
     * representante legal y tutor laboral, después de validar los campos introducidos en la interfaz gráfica.
     * <p>
     * Este método realiza las siguientes validaciones:
     * <ul>
     *     <li>Todos los campos deben estar llenos.</li>
     *     <li>El CIF debe tener 9 caracteres y cumplir con el formato especificado (letra seguida de 8 dígitos).</li>
     *     <li>El código postal debe tener exactamente 5 dígitos.</li>
     *     <li>El email debe cumplir con el formato de email estándar.</li>
     *     <li>Los DNIs deben tener 9 caracteres y cumplir con el formato especificado (8 números seguidos de una letra).</li>
     *     <li>El teléfono debe tener exactamente 9 dígitos.</li>
     * </ul>
     * Si alguna de estas validaciones falla, se muestra un mensaje de error al usuario y no se realiza la modificación.
     * <p>
     * Después de realizar las validaciones, el método actualiza los datos de la empresa, su representante legal y
     * su tutor laboral en la base de datos. Si la modificación es exitosa, los nuevos datos se reflejan en la tabla
     * de empresas y se limpian los campos de entrada.
     * <p>
     * En caso de error durante la modificación en la base de datos, se muestra un mensaje de error al usuario.
     */

    private void modificarTotal() {
        int selectedRow = tablaEmpresas.getSelectedRow();
        if (selectedRow != -1) {
            String cifValue = cif.getText().trim();
            String nombreValue = nombre.getText().trim();
            String direccionValue = direccion.getText().trim();
            String cpValue = CP.getText().trim();
            String localidadValue = localidad.getText().trim();
            String jornadaValue = (String) jornada.getSelectedItem();
            String modalidadValue = (String) modalidad.getSelectedItem();
            String emailValue = email.getText().trim();

            String dniRValue = dniR.getText().trim();
            String nombreRValue = nombreR.getText().trim();
            String apellidoRValue = apellidoR.getText().trim();

            String dniTValue = dniT.getText().trim();
            String nombreTValue = nombreT.getText().trim();
            String apellidoTValue = apellidoT.getText().trim();

            String telefonoTValue = telefonoT.getText().trim();


            if (cifValue.isEmpty() || nombreValue.isEmpty() || direccionValue.isEmpty() || cpValue.isEmpty() || localidadValue.isEmpty() || jornadaValue.isEmpty() || modalidadValue.isEmpty() || emailValue.isEmpty() || dniRValue.isEmpty() || nombreRValue.isEmpty() || apellidoRValue.isEmpty() || dniTValue.isEmpty() || nombreTValue.isEmpty() || apellidoTValue.isEmpty() || telefonoTValue.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Rellene todos los campos.");
                return;
            }


            if (cifValue.length() != 9) {
                JOptionPane.showMessageDialog(null, "Error, el CIF tiene que tener 9 carácteres.");
                return;
            }

            String cif_path = "^[A-Za-z][0-9]{8}$";
            Pattern cif_path_2 = Pattern.compile(cif_path);
            Matcher cif_comprobar = cif_path_2.matcher(cifValue);

            if (!cif_comprobar.matches()) {
                JOptionPane.showMessageDialog(null, "Error, el CIF debe tener una letra como primer carácter y los demás como dígito.");
                return;
            }


            if (cpValue.length() != 5 || !cpValue.matches("[0-9]{5}")) {
                JOptionPane.showMessageDialog(null, "El código postal debe tener exactamente 5 dígitos.");
                return;
            }


            // Validar el email
            String email_path = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

            // Crear el patrón y el matcher
            Pattern path = Pattern.compile(email_path);
            Matcher comprobar = path.matcher(emailValue);

            // Validar el email
            if (!comprobar.matches()) {
                // El email no es válido
                JOptionPane.showMessageDialog(null, "Error, el formato del email no es correcto");
                return;
            }

            if (dniRValue.length() != 9) {
                JOptionPane.showMessageDialog(null, "Error, los DNI tienen que tener 9 carácteres.");
                return;
            }

            String dniRPath = "^[0-9]{8}[A-Za-z]$";
            Pattern dni_Path_2 = Pattern.compile(dniRPath);
            Matcher dniR_comprobar = dni_Path_2.matcher(dniRValue);

            if (!dniR_comprobar.matches()) {
                JOptionPane.showMessageDialog(null, "El DNI debe tener 8 números seguidos de una letra.");
                return;
            }



            if (dniTValue.length() != 9) {
                JOptionPane.showMessageDialog(null, "Error, los DNI tienen que tener 9 carácteres.");
                return;
            }

            String dniTPath = "^[0-9]{8}[A-Za-z]$";
            Pattern dniT_Path_2 = Pattern.compile(dniTPath);
            Matcher dniT_comprobar = dniT_Path_2.matcher(dniTValue);

            if (!dniT_comprobar.matches()) {
                JOptionPane.showMessageDialog(null, "El DNI debe tener 8 números seguidos de una letra.");
                return;
            }


            if (telefonoTValue.length() != 9) {
                JOptionPane.showMessageDialog(null, "Error, los teléfonos móviles tienen que tener 9 dígitos.");
                return;
            }

            Connection conexion = null;
            PreparedStatement modificar_empresa = null;
            PreparedStatement modificar_representante = null;
            PreparedStatement modificar_tutor = null;

            try {
                conexion = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/proy3TEx", "root", "root");
                modificar_empresa = conexion.prepareStatement("UPDATE empresa SET cif=?, nombre=?, direccion=?, cp=?, localidad=?, jornada=?, modalidad=?, email=? WHERE cif=?");

                modificar_empresa.setString(1, cifValue);
                modificar_empresa.setString(2, nombreValue);
                modificar_empresa.setString(3, direccionValue);
                modificar_empresa.setString(4, cpValue);
                modificar_empresa.setString(5, localidadValue);
                modificar_empresa.setString(6, jornadaValue);
                modificar_empresa.setString(7, modalidadValue);
                modificar_empresa.setString(8, emailValue);
                modificar_empresa.setString(9, (String) modeloTabla.getValueAt(selectedRow, 0));

                int rowsAffected = modificar_empresa.executeUpdate();
                if (rowsAffected > 0) {
                    modeloTabla.setValueAt(cifValue, selectedRow, 0);
                    modeloTabla.setValueAt(nombreValue, selectedRow, 1);
                    modeloTabla.setValueAt(direccionValue, selectedRow, 2);
                    modeloTabla.setValueAt(cpValue, selectedRow, 3);
                    modeloTabla.setValueAt(localidadValue, selectedRow, 4);
                    modeloTabla.setValueAt(jornadaValue, selectedRow, 5);
                    modeloTabla.setValueAt(modalidadValue, selectedRow, 6);
                    modeloTabla.setValueAt(emailValue, selectedRow, 7);
                }

                // Modificar representante legal
                modificar_representante = conexion.prepareStatement("UPDATE repre_legal SET dni=?, nombre=?, apellido1=?, apellido2=? WHERE cod_empresa=(SELECT cod_empresa FROM Empresa WHERE cif=?)");
                modificar_representante.setString(1, dniRValue);
                modificar_representante.setString(2, nombreRValue);

                String apellido1R = "";
                String apellido2R = "";
                if (apellidoRValue.contains(" ")) {
                    apellido1R = apellidoRValue.substring(0, apellidoRValue.indexOf(" "));
                    apellido2R = apellidoRValue.substring(apellidoRValue.indexOf(" ") + 1);
                } else {
                    apellido1R = apellidoRValue;
                }

                modificar_representante.setString(3, apellido1R);
                modificar_representante.setString(4, apellido2R);
                modificar_representante.setString(5, (String) modeloTabla.getValueAt(selectedRow, 0));

                modificar_representante.executeUpdate();

                // Modificar tutor laboral
                modificar_tutor = conexion.prepareStatement("UPDATE tutor_laboral SET dni=?, nombre=?, apellido1=?, apellido2=?, telefono=? WHERE cod_empresa=(SELECT cod_empresa FROM Empresa WHERE cif=?)");
                modificar_tutor.setString(1, dniTValue);
                modificar_tutor.setString(2, nombreTValue);

                String apellido1T = "";
                String apellido2T = "";
                if (apellidoTValue.contains(" ")) {
                    apellido1T = apellidoTValue.substring(0, apellidoTValue.indexOf(" "));
                    apellido2T = apellidoTValue.substring(apellidoTValue.indexOf(" ") + 1);
                } else {
                    apellido1T = apellidoTValue;
                }

                modificar_tutor.setString(3, apellido1T);
                modificar_tutor.setString(4, apellido2T);
                modificar_tutor.setString(5, telefonoTValue);
                modificar_tutor.setString(6, (String) modeloTabla.getValueAt(selectedRow, 0));

                modificar_tutor.executeUpdate();

                JOptionPane.showMessageDialog(null, "Empresa modificada con éxito");


                cod.setText("");
                cif.setText("");
                nombre.setText("");
                direccion.setText("");
                CP.setText("");
                localidad.setText("");
                jornada.setSelectedIndex(0);
                modalidad.setSelectedIndex(0);
                email.setText("");

                dniR.setText("");
                nombreR.setText("");
                apellidoR.setText("");

                dniT.setText("");
                nombreT.setText("");
                apellidoT.setText("");

                telefonoT.setText("");

                tablaEmpresas.clearSelection();

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al modificar la empresa: " + ex.getMessage());
            } finally {
                try {
                    if (modificar_empresa != null) modificar_empresa.close();
                    if (modificar_representante != null) modificar_representante.close();
                    if (modificar_tutor != null) modificar_tutor.close();
                    if (conexion != null) conexion.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una empresa para modificar");
        }
    }

    /**
     * Carga los datos de alumnos, empresas y tutores desde la base de datos y los agrega a los respectivos JComboBox.
     * <p>
     * Este método realiza las siguientes acciones:
     * <ul>
     *     <li>Limpia los elementos actuales de los JComboBox: {@code eleccionA}, {@code eleccionE} y {@code eleccionT}.</li>
     *     <li>Consulta la base de datos para obtener los nombres completos de los alumnos y los agrega a {@code eleccionA}.</li>
     *     <li>Consulta la base de datos para obtener los nombres de las empresas y los agrega a {@code eleccionE}.</li>
     *     <li>Consulta la base de datos para obtener los nombres completos de los tutores docentes y los agrega a {@code eleccionT}.</li>
     * </ul>
     * Si ocurre un error durante cualquiera de las consultas a la base de datos, se muestra un mensaje de error al usuario.
     * <p>
     * Los nombres completos de los alumnos se forman concatenando los apellidos y el nombre en el formato "apellido1 apellido2, nombre".
     * Los nombres completos de los tutores se forman concatenando el nombre y el primer apellido en el formato "nombre apellido1".
     */
    //Metodo para que salga los alumnos,la empresa y tutores en el combobox
    private void cargarAlumnosEmpresasTutores() {
        // Limpiar los JComboBox
        eleccionA.removeAllItems();
        eleccionE.removeAllItems();
        eleccionT.removeAllItems();

        // Realizar la consulta a la base de datos para obtener los alumnos
        try (Connection conexion = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/proy3TEx", "root", "root");
             Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT nombre, apellido1, apellido2 FROM Alumno")) {

            // Agregar los resultados al JComboBox
            while (rs.next()) {
                String nombreCompletoAlumno = rs.getString("apellido1") + " " + rs.getString("apellido2") + ", " + rs.getString("nombre");
                eleccionA.addItem(nombreCompletoAlumno);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los alumnos: " + ex.getMessage());
        }

        // Realizar la consulta a la base de datos para obtener las empresas
        try (Connection conexion = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/proy3TEx", "root", "root");
             Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT nombre FROM empresa")) {

            // Agregar los resultados al JComboBox
            while (rs.next()) {
                eleccionE.addItem(rs.getString("nombre"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar las empresas: " + ex.getMessage());
        }

        // Método para llenar el ComboBox de tutores laborales
        try (Connection conexion = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/proy3TEx", "root", "root");
             Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT nombre, apellido1 FROM tutor_docente")) {

            // Agregar los resultados al JComboBox
            while (rs.next()) {
                String nombreCompleto = rs.getString("nombre") + " " + rs.getString("apellido1");
                eleccionT.addItem(nombreCompleto);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los tutores: " + ex.getMessage());
        }
    }


    //Metodo para borrar algunos de los registros de la base de datos
    private void borrarTotal() {
        int selectedRow = tablaEmpresas.getSelectedRow();
        if (selectedRow != -1) {
            String nombreEmpresa = (String) modeloTabla.getValueAt(selectedRow, 1); // Suponiendo que el nombre de la empresa está en la columna 1

            try (Connection conexion = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/proy3TEx", "root", "root")) {
                // Obtener cod_empresa a partir del nombre de la empresa
                String cod_empresa = null;
                PreparedStatement obtenerCodEmpresa = conexion.prepareStatement("SELECT cod_empresa FROM empresa WHERE nombre = ?");
                obtenerCodEmpresa.setString(1, nombreEmpresa);
                ResultSet rs = obtenerCodEmpresa.executeQuery();
                if (rs.next()) {
                    cod_empresa = rs.getString("cod_empresa");
                }

                if (cod_empresa != null) {
                    // Eliminar representante legal
                    PreparedStatement eliminar_representante = conexion.prepareStatement("DELETE FROM repre_legal WHERE cod_empresa=?");
                    eliminar_representante.setString(1, cod_empresa);
                    eliminar_representante.executeUpdate();

                    // Eliminar tutor laboral
                    PreparedStatement eliminar_tutor = conexion.prepareStatement("DELETE FROM tutor_laboral WHERE cod_empresa=?");
                    eliminar_tutor.setString(1, cod_empresa);
                    eliminar_tutor.executeUpdate();

                    // Eliminar empresa
                    PreparedStatement eliminar_empresa = conexion.prepareStatement("DELETE FROM empresa WHERE cod_empresa=?");
                    eliminar_empresa.setString(1, cod_empresa);
                    eliminar_empresa.executeUpdate();

                    // Eliminar la fila del modelo de tabla
                    modeloTabla.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Empresa eliminada con éxito");

                    // Limpiar campos
                    cod.setText("");
                    cif.setText("");
                    nombre.setText("");
                    direccion.setText("");
                    CP.setText("");
                    localidad.setText("");
                    jornada.setSelectedIndex(0);
                    modalidad.setSelectedIndex(0);
                    email.setText("");

                    dniR.setText("");
                    nombreR.setText("");
                    apellidoR.setText("");

                    dniT.setText("");
                    nombreT.setText("");
                    apellidoT.setText("");

                    telefonoT.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo encontrar el código de la empresa seleccionada.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al eliminar la empresa: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona una empresa para eliminar");
        }
    }

    /**
     * Elimina la empresa seleccionada en la tabla, junto con su representante legal y su tutor laboral,
     * de la base de datos y actualiza el modelo de la tabla en la interfaz de usuario.
     * <p>
     * Este método realiza las siguientes acciones:
     * <ul>
     *     <li>Comprueba si hay una fila seleccionada en la tabla de empresas.</li>
     *     <li>Obtiene el nombre de la empresa seleccionada desde el modelo de la tabla.</li>
     *     <li>Obtiene el código de la empresa (cod_empresa) correspondiente al nombre de la empresa.</li>
     *     <li>Elimina las entradas de representante legal y tutor laboral asociadas a la empresa en la base de datos.</li>
     *     <li>Elimina la entrada de la empresa en la base de datos.</li>
     *     <li>Elimina la fila correspondiente en el modelo de la tabla.</li>
     *     <li>Limpia los campos de entrada en la interfaz de usuario.</li>
     * </ul>
     * Si ocurre un error durante cualquier operación de base de datos, se muestra un mensaje de error al usuario.
     * <p>
     * Si no hay una fila seleccionada en la tabla, se muestra un mensaje al usuario solicitando que seleccione una empresa.
     * <p>
     * Si no se puede encontrar el código de la empresa, se muestra un mensaje de error al usuario.
     */
    private static void llenarTablaEmpresas(DefaultTableModel model) {
        try {
            // Establecer la conexión con la base de datos
            Connection con = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/proy3TEx", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Empresa");

            // Llenar la tabla con los datos de la base de datos
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("CIF"),
                        rs.getString("Nombre"),
                        rs.getString("localidad"),
                        rs.getString("CP"),
                        rs.getString("Localidad"),
                        rs.getString("Jornada"),
                        rs.getString("Modalidad"),
                        rs.getString("Email")
                });
            }

            // Cerrar la conexión y liberar recursos
            rs.close();
            stmt.close();
            con.close();

        // Manejar excepciones
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para actualizar el código de empresa mostrado en el campo de texto correspondiente.
     * Este método calcula el próximo código de empresa basándose en los datos existentes en la base de datos.
     * @param cod El campo de texto donde se mostrará el código de empresa actualizado.
     */
    private static void actualizarCodigoEmpresa(JTextField cod) {
        int siguienteCodigo = obtenerProximoCodigo(1);
        if (siguienteCodigo != -1) {
            cod.setText(String.valueOf(siguienteCodigo));
        } else {
            cod.setText("Error al obtener código");
        }
    }


    /**
     * Obtiene el siguiente código disponible para una entidad específica en la base de datos.
     * <p>
     * Este método se conecta a la base de datos para contar el número total de entradas en la tabla
     * especificada por el parámetro {@code objetivo}, y devuelve el siguiente número disponible.
     * <p>
     * Las entidades posibles son:
     * <ul>
     *     <li>1: Empresa</li>
     *     <li>2: Alumno</li>
     *     <li>4: Tutor docente</li>
     * </ul>
     * <p>
     * Si ocurre un error durante la conexión a la base de datos o la ejecución de la consulta,
     * se imprime la traza del error y se devuelve -1.
     *
     * @param objetivo un entero que especifica la entidad cuyo próximo código se desea obtener.
     * @return el siguiente código disponible para la entidad especificada, o -1 si ocurre un error.
     */
    private static int obtenerProximoCodigo(int objetivo) {
        String url = "jdbc:mariadb://localhost:3306/proy3TEx";
        String usuario = "root";
        String contrasena = "root";
        int siguienteCodigo = -1;

        try (Connection conexion = (Connection) DriverManager.getConnection(url, usuario, contrasena);
             Statement sentencia = conexion.createStatement()) {

            String consulta = null;
            if (objetivo == 1) {
                consulta = "SELECT COUNT(*) AS total FROM empresa";
            } else if (objetivo == 2) {
                consulta = "SELECT COUNT(*) AS total FROM alumno";
            } else if (objetivo == 4) {
                consulta = "SELECT COUNT(*) AS total FROM tutor_docente";
            }

            if (consulta != null) {
                ResultSet resultado = sentencia.executeQuery(consulta);
                if (resultado.next()) {
                    int total = resultado.getInt("total");
                    siguienteCodigo = total + 1;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return siguienteCodigo;
    }

    /**
     * Inserta un nuevo tutor docente en la base de datos.
     * <p>
     * Este método se conecta a la base de datos y ejecuta una sentencia SQL para insertar
     * un nuevo registro en la tabla `Tutor_Docente` con los valores proporcionados.
     * Si ocurre un error durante la conexión a la base de datos o la ejecución de la sentencia,
     * se imprime la traza del error y se muestra un mensaje de error al usuario.
     *
     * @param codTut   el código del tutor docente.
     * @param dni      el DNI del tutor docente.
     * @param nombre   el nombre del tutor docente.
     * @param apellido1 el primer apellido del tutor docente.
     * @param apellido2 el segundo apellido del tutor docente.
     * @param correo   el correo electrónico del tutor docente.
     * @param telefono el número de teléfono del tutor docente.
     */
    private void insertarTutorEnBD(int codTut, String dni, String nombre, String apellido1, String apellido2, String correo, String telefono) {
        try (Connection conexion = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/proy3TEx", "root", "root");
             PreparedStatement statement = conexion.prepareStatement("INSERT INTO Tutor_Docente (cod_tutor_docente, dni, nombre, apellido1, apellido2, correo, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, codTut);
            statement.setString(2, dni);
            statement.setString(3, nombre);
            statement.setString(4, apellido1);
            statement.setString(5, apellido2);
            statement.setString(6, correo);
            statement.setString(7, telefono);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar datos en la base de datos: " + ex.getMessage());
        }
    }


    private void insertarAlumnosDesdeArchivo() {
        if (botonUsado1) {
            JOptionPane.showMessageDialog(null, "Este botón ya ha sido utilizado.");
            return;
        } else {
            String url = "jdbc:mariadb://localhost:3306/proy3TEx";
            String usuario = "root";
            String contraseña = "root";
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            boolean encontradoDuplicado = false; // Variable para rastrear duplicados

            try (FileInputStream fis = new FileInputStream("alumnos2cfs.dat");
                 DataInputStream dis = new DataInputStream(fis);
                 Connection conn = (Connection) DriverManager.getConnection(url, usuario, contraseña)) {

                // Preparar la sentencia SQL para insertar un alumno
                String insertSql = "INSERT INTO Alumno (cod_alumno, dni, nombre, apellido1, apellido2, fec_nac) VALUES (?, ?, ?, ?, ?, ?)";
                String selectSql = "SELECT COUNT(*) FROM Alumno WHERE cod_alumno = ?";

                try (PreparedStatement pstmtInsert = conn.prepareStatement(insertSql);
                     PreparedStatement pstmtSelect = conn.prepareStatement(selectSql)) {

                    try {
                        while (true) {
                            int codAlumno = dis.readInt();
                            String dni = dis.readUTF();
                            String nombre = dis.readUTF();
                            String apellidos = dis.readUTF(); // Leer los apellidos completos
                            String fechaNacim = dis.readUTF();

                            // Dividir los apellidos en apellido1 y apellido2
                            String[] apellidoParts = apellidos.split(" ", 2);
                            String apellido1 = apellidoParts[0];
                            String apellido2 = apellidoParts.length > 1 ? apellidoParts[1] : "";

                            // Convertir la fecha al formato adecuado
                            Date sqlDate = null;
                            if (!fechaNacim.isEmpty()) {
                                try {
                                    java.util.Date parsedDate = inputDateFormat.parse(fechaNacim);
                                    String formattedDate = outputDateFormat.format(parsedDate);
                                    sqlDate = Date.valueOf(formattedDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }

                            // Verificar si el alumno ya existe
                            pstmtSelect.setInt(1, codAlumno);
                            ResultSet rs = pstmtSelect.executeQuery();
                            rs.next();
                            int count = rs.getInt(1);

                            if (count > 0) {
                                encontradoDuplicado = true;
                            } else {
                                // Establecer los parámetros en la sentencia SQL para inserción
                                pstmtInsert.setInt(1, codAlumno);
                                pstmtInsert.setString(2, dni);
                                pstmtInsert.setString(3, nombre);
                                pstmtInsert.setString(4, apellido1);
                                pstmtInsert.setString(5, apellido2);
                                if (sqlDate == null) {
                                    pstmtInsert.setNull(6, java.sql.Types.DATE); // Insertar un valor nulo
                                } else {
                                    pstmtInsert.setDate(6, sqlDate);
                                }

                                // Ejecutar la inserción
                                pstmtInsert.executeUpdate();
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


            if (encontradoDuplicado) {
                JOptionPane.showMessageDialog(null, "Se encontraron alumnos que ya han sido añadidos anteriormente.");
            }

            botonUsado1 = true;
        }
    }


    /**
     * Carga datos de tutores desde un archivo XML y los inserta en la base de datos.
     * <p>
     * Este método lee datos de tutores desde un archivo XML y los inserta en la base de datos.
     * Solo permite una ejecución por ciclo de vida del programa, indicado por la variable `botonUsado`.
     * <p>
     * El archivo XML debe tener la siguiente estructura:
     * <pre>
     * {@code
     * <tutoresdoc>
     *     <tutordoc>
     *         <dni>...</dni>
     *         <codtut>...</codtut>
     *         <nomap>...</nomap>
     *         <correo>...</correo>
     *         <telefono>...</telefono>
     *     </tutordoc>
     *     ...
     * </tutoresdoc>
     * }
     * </pre>
     * <p>
     * El método divide el nombre completo del tutor en nombre, primer apellido y segundo apellido.
     * <p>
     * Si el código de tutor ya existe en la base de datos, se muestra un mensaje indicando esta situación.
     * <p>
     * Se utiliza una conexión a la base de datos MariaDB.
     * <p>
     * En caso de error durante la lectura del archivo XML o la inserción en la base de datos, se muestra un mensaje de error.
     */
    private void cargarDatosDesdeXML() {
        if (botonUsado) {
            JOptionPane.showMessageDialog(null, "Este botón ya ha sido utilizado.");
            return;
        } else {
            try {
                File archivoXML = new File("tutoresdoc.xml");
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(archivoXML);
                Element root = doc.getDocumentElement();
                NodeList tutoresDoc = root.getElementsByTagName("tutordoc");

                for (int i = 0; i < tutoresDoc.getLength(); i++) {
                    Node tutorDocNode = tutoresDoc.item(i);
                    if (tutorDocNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element tutorDocElement = (Element) tutorDocNode;

                        String dni = tutorDocElement.getElementsByTagName("dni").item(0).getTextContent();
                        int codTut = Integer.parseInt(tutorDocElement.getElementsByTagName("codtut").item(0).getTextContent());
                        String nomap = tutorDocElement.getElementsByTagName("nomap").item(0).getTextContent();
                        String correo = tutorDocElement.getElementsByTagName("correo").item(0).getTextContent();
                        String telefono = tutorDocElement.getElementsByTagName("telefono").item(0).getTextContent();

                        // Dividir el nombre completo en nombre, primer apellido y segundo apellido
                        String[] nombreCompleto = nomap.split(" ");
                        String nombre = nombreCompleto[0];
                        String apellido1 = nombreCompleto.length > 1 ? nombreCompleto[1] : "";
                        String apellido2 = nombreCompleto.length > 2 ? nombreCompleto[2] : "";

                        // Verificar si el tutor ya existe en la base de datos
                        if (existeTutorEnBD(codTut)) {
                            JOptionPane.showMessageDialog(null, "Ya existen datos con el código de tutor: " + codTut);
                            return;
                        } else {
                            insertarTutorEnBD(codTut, dni, nombre, apellido1, apellido2, correo, telefono);
                        }
                    }
                }

                botonUsado = true;
                JOptionPane.showMessageDialog(null, "Datos cargados desde el archivo XML correctamente.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cargar los datos desde el archivo XML: " + ex.getMessage());
            }
        }
    }


    /**
     * Verifica si un tutor con el código especificado existe en la base de datos.
     * <p>
     * Este método se conecta a la base de datos MariaDB y ejecuta una consulta para verificar
     * si un tutor con el código proporcionado ya está presente en la tabla Tutor_Docente.
     *
     * @param codTut el código del tutor a verificar
     * @return true si el tutor existe en la base de datos, false en caso contrario
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    private boolean existeTutorEnBD(int codTut) throws SQLException {
        boolean existe = false;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/proy3TEx", "root", "root");

        try {

            // Preparar la consulta SQL para verificar la existencia del tutor
            String query = "SELECT COUNT(*) FROM Tutor_Docente WHERE cod_tutor_docente = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, codTut);

            // Ejecutar la consulta
            resultSet = statement.executeQuery();

            // Si el resultado tiene al menos una fila, significa que el tutor existe en la base de datos
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                existe = count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción adecuadamente
        } finally {
            // Cerrar la conexión, el statement y el resultSet en bloques finally para asegurarse de que se cierren correctamente
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejar la excepción adecuadamente
            }
        }

        return existe;
    }



}


