package RunApp;

/**
 * Created by TaInt on 15.03.2015.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/fxml/game.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource(fxmlFile));
        stage.setTitle("Аменция: История Горла");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

}
