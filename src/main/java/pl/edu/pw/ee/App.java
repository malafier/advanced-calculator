package pl.edu.pw.ee;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("calculator.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Advanced Calculator");
        stage.setResizable(false);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("icons8-calculator-58.png")));
        stage.show();
    }

    public static void main(String[] args) {
        App.launch();
    }

}