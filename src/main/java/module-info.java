module org.example.proyectojavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;
    requires java.desktop;


    opens org.example.proyectojavafx to javafx.fxml;
    exports org.example.proyectojavafx;
}