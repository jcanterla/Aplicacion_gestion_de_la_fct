package org.example.proyectojavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación JavaFX.
 *
 * @author: Juan Carlos Canterla Rufino, Vega López Hernández, David Marín Vermúdez, Francisco Javier Moya Rivero
 * @version: 1.0
 */


public class HelloApplication extends Application {
    /**
     * Clase principal de la aplicación JavaFX.
     *
     * Se llama después de que el método {@code init} ha retornado, y después de que el sistema está listo para que la aplicación comience a funcionar.</p>
     *
     * <p>En el método {@code start}, se carga el archivo FXML de la interfaz de usuario utilizando {@code FXMLLoader}.
     * Luego, se crea una nueva {@code Scene} con la interfaz de usuario cargada y se establece en el {@code Stage} principal.</p>
     *
     * <p>El método {@code main} lanza la aplicación llamando al método {@code launch} que es un método de la clase {@code Application}.</p>
     */

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ProyectoJavaFX.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}