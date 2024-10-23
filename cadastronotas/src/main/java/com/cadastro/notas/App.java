package com.cadastro.notas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    

    @Override
    public void start(Stage stage) throws IOException {

        try {
            scene = new Scene(loadFXML());
            stage.setScene(scene);
            stage.show();

        } catch (Error error) {
            System.out.println(error.getMessage());
            System.out.println(error.getLocalizedMessage());
            System.out.println(error.getCause());

        }
    }

    private Parent loadFXML() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/JanelaPrincipal.fxml"));

        return fxmlLoader.load();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }

}