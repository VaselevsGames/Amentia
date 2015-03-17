package RunApp;

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
        new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        stage.setTitle("Amentia: history Throat");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

}
