package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import javafx.geometry.Rectangle2D;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class ScoreboardMenuController {

    private Stage getStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    /**
     * When this method is called, it will change the scene to a MainMenu
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void changeScreenMainMenuButtonPushed(ActionEvent event) throws IOException {
        Parent MaindMenuParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene MainMenuScene = new Scene(MaindMenuParent);

        // Get the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(MainMenuScene);
        window.show();
    }

    /**
     * When this method is called, it will change the scene to a
     * SettingsMenuNewGame
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void changeScreenSettingsMenuNewGameButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsMenuNewGame.fxml"));
        Parent root = loader.load();
        Scene settingsMenuNewGameScene = new Scene(root);

        // Get the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(settingsMenuNewGameScene);

        // Load the settings to the text fields when scene is changed
        SettingsMenuNewGameController controller = loader.getController();
        controller.setOldSettings();

        window.show();
    }

    /**
     * Opens old game scene Used file type is JSON //@param File
     *
     * @throws IOException
     */
    @FXML
    private void oldGameStage(String s) throws IOException {

        // FXMLLoader loader = new FXMLLoader(getClass().getResource("dummyGame.fxml"));
        // Parent root = loader.load();
        // Stage stage = new Stage();
        // stage.setTitle("Old Game");
        // stage.setScene(new Scene(root));
        // stage.setMaximized(true);
        // stage.show();
        Controller controller = new Controller();
        controller.oldGame = true;
        controller.oldGameFilePath = s;
        Font.loadFont(getClass().getResourceAsStream("College.ttf"), 14);
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "panel.fxml"
                )
        );
        loader.setController(controller);

        Parent root = loader.load();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Basketball2020");

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        primaryStage.setMaximized(true);

        Scene scene = new Scene(root);
        scene.getRoot().requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();

        FXMLLoader loader2 = new FXMLLoader(
                getClass().getResource(
                        "panel2.fxml"
                )
        );
        loader2.setController(controller);
        Parent anotherRoot = loader2.load();

        Stage anotherStage = new Stage();
        Scene anotherScene = new Scene(anotherRoot);
        anotherScene.getRoot().requestFocus();
        anotherStage.setScene(anotherScene);
        anotherStage.show();
    }

    /**
     * Opens file chooser for loading a game
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void openFileChooser(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON Files", "*.json")
        );
        fileChooser.setInitialDirectory(new File("./previous_games"));
        File selectedFile = fileChooser.showOpenDialog(getStage(event));
        System.out.println("Selected file: " + selectedFile.toString());
        oldGameStage(selectedFile.toString());
    }

}
