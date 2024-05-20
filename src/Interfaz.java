import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Esta clase representa la interfaz principal de la aplicación.
 */
public class Interfaz {
    /**
     * El método principal de la aplicación.
     * @param args Los argumentos de la línea de comandos.
     */

    public static void main(String[] args) {
        // Panel principal y de pestañas
        JFrame marco = new JFrame();
        JPanel panelGeneral = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTabbedPane panelPestanas = new JTabbedPane();

        panelGeneral.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));

        // Bordes para los paneles
        Border bordeReducido = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border borde = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE),"Empresas");
        Border bordecon = BorderFactory.createCompoundBorder(borde, bordeReducido);

        // Fuente para los títulos
        Font fuente = new Font("Verdana", Font.BOLD, 20);

        // Listener para cambiar el tamaño del marco al cambiar de pestaña
        panelPestanas.addChangeListener(e ->{
            int pestaña = panelPestanas.getSelectedIndex();
            if(pestaña == 0){
                marco.setSize(1015, 640);
            }else if(pestaña == 1){
                marco.setSize(640, 360);

            }else if(pestaña == 2){
                marco.setSize(640, 360);

            }else if(pestaña == 3){
                marco.setSize(640, 360);
            }
        });

        // Pestaña Empresas
        panelPestanas.addTab("Empresas",panelGeneral);
        JLabel titulo = new JLabel("GESTIÓN DE DATOS DE EMPRESAS");



        titulo.setFont(fuente);


        JPanel Panelempresas = new JPanel();
        Panelempresas.setBorder(bordecon);


        // Inputs y etiquetas para los datos empresa
        JLabel codLabel = new JLabel();
        codLabel.setText("Código empresa:");
        JTextField cod=new JTextField(" ",1);
        cod.setEditable(false);
        JLabel cifLabel = new JLabel();
        JTextField cif=new JTextField(" ",6);
        cifLabel.setText("CIF:");
        JLabel nombreLabel = new JLabel();
        JTextField nombre=new JTextField(" ",30);
        nombreLabel.setText("Nombre:");

        JLabel direccionLabel = new JLabel();
        direccionLabel.setText("Dirección:");
        JTextField direccion=new JTextField(" ",15);
        JLabel cpLabel = new JLabel();
        JTextField CP=new JTextField(" ",4);
        cpLabel.setText("C.P.:");
        JLabel localidadLabel = new JLabel();
        JTextField localidad=new JTextField(" ",20);
        localidadLabel.setText("Localidad:");

        JLabel jornadaLabel = new JLabel();
        jornadaLabel.setText("Jornada:");
        JComboBox jornada = new JComboBox();
        jornada.addItem("Partida");
        jornada.addItem("Continua");
        JLabel modalidadLabel = new JLabel();
        JComboBox modalidad = new JComboBox();
        modalidad.addItem("Presencial");
        modalidad.addItem("Semipresencial");
        modalidad.addItem("Distancia");
        modalidadLabel.setText("Modalidad:");
        JLabel emailLabel = new JLabel();
        JTextField email=new JTextField(" ",20);
        emailLabel.setText("Mail:");


        // Tres paneles para cada fila de datos
        JPanel panel1E = new JPanel(new FlowLayout(FlowLayout.LEFT,15,0));
        JPanel panel2E = new JPanel(new FlowLayout(FlowLayout.LEFT,15,0));
        JPanel panel3E = new JPanel(new FlowLayout(FlowLayout.LEFT,15,0));


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
        panel1E.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panel2E.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panel3E.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

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
        JTextField dniR=new JTextField(" ",5);
        JLabel nombreRLabel = new JLabel();
        JTextField nombreR=new JTextField(" ",7);
        nombreRLabel.setText("Nombre RL:");
        JLabel apellidoRLabel = new JLabel();
        JTextField apellidoR=new JTextField(" ",20);
        apellidoRLabel.setText("Apellido RL:");

        JLabel dniTLabel = new JLabel();
        dniTLabel.setText("DNI Tut. Laboral:");
        JTextField dniT=new JTextField(" ",5);
        JLabel nombreTLabel = new JLabel();
        JTextField nombreT=new JTextField(" ",7);
        nombreTLabel.setText("Nombre TL:");
        JLabel apellidoTLabel = new JLabel();
        JTextField apellidoT=new JTextField(" ",13);
        apellidoTLabel.setText("Apellido TL:");
        JLabel telefonoTLabel = new JLabel();
        JTextField telefonoT=new JTextField(" ",9);
        telefonoTLabel.setText("Tlf. TL:");

        // Dos paneles para cada fila de datos
        JPanel Panel1P = new JPanel(new FlowLayout(FlowLayout.LEFT,15,0));
        JPanel Panel2P = new JPanel(new FlowLayout(FlowLayout.LEFT,15,0));

        // Se añaden bordes a los paneles
        panelPersonas.setBorder(borde2);
        panelPersonas.setLayout(new GridLayout(4, 2, 2, 2));
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
        JPanel Panelbotones = new JPanel();

        // Bordes para los paneles
        Border bordeLowered = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        Border bordePequeno = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        Panelbotones.setBorder(BorderFactory.createCompoundBorder(bordeLowered, bordePequeno));

        // Botones para insertar, modificar y eliminar datos
        JButton botonInsertar = new JButton("Insertar");
        JButton botonModificar = new JButton("Modificar");
        JButton botonEliminar = new JButton("Eliminar");

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
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("CIF");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("C.P.");
        modeloTabla.addColumn("Localidad");
        modeloTabla.addColumn("Jornada");
        modeloTabla.addColumn("Modalidad");
        modeloTabla.addColumn("Email");

        // Crear tabla para las empresas
        JTable tablaEmpresas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaEmpresas);
        scrollPane.setPreferredSize(new Dimension(600, 200));

        panelTabla.add(scrollPane);

        // Agregar panel y titulo al panel general
        panelGeneral.add(titulo);
        panelGeneral.add(Panelempresas);


        llenarTablaEmpresas(modeloTabla);


        // Listener para el botón de insertar
        botonInsertar.addActionListener(e -> {
            // Obtener los datos
            String cifValue = cif.getText();
            String nombreValue = nombre.getText();
            String direccionValue = direccion.getText();
            String cpValue = CP.getText();
            String localidadValue = localidad.getText();
            String jornadaValue = (String) jornada.getSelectedItem();
            String modalidadValue = (String) modalidad.getSelectedItem();
            String emailValue = email.getText();

            // Insertarlos en la BD
            try {
                Connection con = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/Proy3TE1", "root", "123456");

                String sql = "INSERT INTO Empresa (CIF, Nombre, Localidad, Jornada, Modalidad, Email) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, cifValue);
                stmt.setString(2, nombreValue);
                stmt.setString(3, localidadValue);
                stmt.setString(4, jornadaValue);
                stmt.setString(5, modalidadValue);
                stmt.setString(6, emailValue);

                // Ejecutar la consulta
                int filasAfectadas = stmt.executeUpdate();

                // Verificar si la inserción fue exitosa
                if (filasAfectadas > 0) {
                    // Limpiar los campos de texto después de la inserción
                    cif.setText("");
                    nombre.setText("");
                    direccion.setText("");
                    CP.setText("");
                    localidad.setText("");
                    jornada.setSelectedIndex(0);
                    modalidad.setSelectedIndex(0);
                    email.setText("");

                    // Actualizar la tabla de empresas con los nuevos datos
                    DefaultTableModel modelo = (DefaultTableModel) tablaEmpresas.getModel();
                    modelo.addRow(new Object[]{cifValue, nombreValue, direccionValue, cpValue, localidadValue, jornadaValue, modalidadValue, emailValue});
                } else {
                    JOptionPane.showMessageDialog(null, "Error al insertar datos en la base de datos.");
                }

                // Cerrar la conexión y liberar recursos
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al insertar datos en la base de datos: " + ex.getMessage());
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

        // Botón para importar datos
        JButton botonDat = new JButton();
        botonDat.setIcon(imagenDat);

        // Añadir cada panel a su panel padre
        panelDat.add(botonDat);
        Panelboton.add(panelDat);

        panelGeneral2.add(Panelboton);

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

        botonXml.setIcon(imagenXml);

        panelXml.add(botonXml);
        Panelboton2.add(panelXml);

        panelGeneral3.add(titulo3);
        panelGeneral3.add(Panelboton2);

        // Pestaña Asignación y titulo
        JPanel panelGeneral4 = new JPanel();
        panelPestanas.addTab("Asignación", panelGeneral4);
        JLabel titulo4 = new JLabel("GESTIÓN DE LA ASIGNACIÓN DE PLAZAS");

        panelGeneral4.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        panelGeneral4.setLayout(new FlowLayout(FlowLayout.CENTER));
        titulo4.setFont(fuente);

        // Panel para las elecciones de alumno, empresa y tutor
        JPanel PanelElecciones = new JPanel();
        JLabel eleccionALabel = new JLabel();
        eleccionALabel.setText("Elección de Alumno:");

        // Combobox para las elecciones
        JComboBox eleccionA = new JComboBox();
        eleccionA.addItem("---");
        eleccionA.addItem("---");
        JLabel eleccionELabel = new JLabel();
        JComboBox eleccionE = new JComboBox();
        eleccionE.addItem("----");
        eleccionE.addItem("----");
        eleccionE.addItem("----");
        eleccionELabel.setText("Elección de Empresa:");
        JLabel eleccionTLabel = new JLabel();
        JComboBox eleccionT = new JComboBox();
        eleccionT.addItem("---");
        eleccionT.addItem("---");
        eleccionTLabel.setText("Elección de Tutor:");

        PanelElecciones.setLayout(new FlowLayout(FlowLayout.CENTER));
        PanelElecciones.setLayout(new GridLayout(3,2,1,7));
        PanelElecciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        PanelElecciones.setLayout(new BoxLayout(PanelElecciones, BoxLayout.Y_AXIS));

        // Añadir los elementos al panel de elecciones
        PanelElecciones.add(eleccionALabel);
        PanelElecciones.add(eleccionA);
        PanelElecciones.add(eleccionELabel);
        PanelElecciones.add(eleccionE);
        PanelElecciones.add(eleccionTLabel);
        PanelElecciones.add(eleccionT);

        panelGeneral4.add(titulo4);
        panelGeneral4.add(PanelElecciones);

        //
        for (int i = 1; i < panelPestanas.getTabCount(); i++) {
            JPanel panel = (JPanel) panelPestanas.getComponentAt(i);
            panel.setPreferredSize(new Dimension(680, 340));
        }

        // Añadir los paneles al marco principal
        marco.add(panelPestanas, BorderLayout.NORTH);
        marco.pack();

        // Configuración del marco
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setVisible(true);

        // Centrar el marco en la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int windowWidth = marco.getWidth();
        int windowHeight = marco.getHeight();
        int x = (screenWidth - windowWidth) / 2;
        int y = (screenHeight - windowHeight) / 2;
        marco.setLocation(x, y);
    }

    /**
     * Este método se utiliza para llenar la tabla de empresas con datos de la base de datos.
     * @param model El modelo de tabla que se llenará con datos.
     */
    private static void llenarTablaEmpresas(DefaultTableModel model) {
        try {
            // Establecer la conexión con la base de datos
            Connection con = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/Proy3TE1", "root", "123456");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Empresa");

            // Llenar la tabla con los datos de la base de datos
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("CIF"),
                        rs.getString("Nombre"),
                        rs.getString("Direccion"),
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
     * Este método se utiliza para obtener el id del representante de la base de datos.
     * @param dniRepresentante El DNI del representante.
     * @return El id del representante.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    private static int obtenerIdRepresentante(String dniRepresentante) throws SQLException {
        int idRepresentante = -1;
        Connection con = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/Proy3TE1", "root", "123456");
        PreparedStatement stmt = con.prepareStatement("SELECT id FROM representante WHERE DNI = ?");
        stmt.setString(1, dniRepresentante);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            idRepresentante = rs.getInt("id");
        }
        rs.close();
        stmt.close();
        con.close();
        return idRepresentante;
    }

}
