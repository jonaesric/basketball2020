package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    //no paramater
    Class noparams[] = {};


    /**
     * When this method is called, it will change the scene to
     * a ScoreboardMenu
     * @param event
     * @throws IOException
     */
    @FXML
    private void changeScreenSettingsMenuNewGameButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScoreboardMenu.fxml"));
        Parent root = loader.load();
        Scene settingsMenuNewGameScene = new Scene(root);

        // Get the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(settingsMenuNewGameScene);
        window.show();
    }

    /**
     * When this method is called, it will change the scene to
     * a SettingsMenu
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void changeScreenSettingsButtonPushed(ActionEvent event) throws IOException {
        //Parent scoreboardMenuParent = FXMLLoader.load(getClass().getResource("SettingsMenu.fxml"));
        //Scene scoreboardMenuScene = new Scene(scoreboardMenuParent);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsMenu.fxml"));
        Parent root = loader.load();
        Scene scoreboardMenuScene = new Scene(root);

        // Get the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scoreboardMenuScene);

        // Load the settings to the text fields when scene is changed
        SettingsMenuController controller = loader.getController();
        controller.setOldSettings();

        window.show();
    }



}
