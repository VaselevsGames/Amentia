package RunApp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by TaInt on 15.03.2015.
 */

public class MainMenuController {

    @FXML
    private Pane mainPane;

    @FXML
    private Button btn_startGame;
    @FXML
    private Button btn_about;
    @FXML
    private Button btn_exit;

    @FXML
    public void initialize() {

    }

    private Stage currentStage() {
        return (Stage) mainPane.getScene().getWindow();
    }

    @FXML
    public void startGameAction() {
        System.out.println("Start game button clicked");
    }

    @FXML
    public void aboutAction() {
        System.out.println("About button clicked");
    }

    @FXML
    public void exitAction() {
        System.out.println("Exit button clicked");
        currentStage().close();
    }
}
