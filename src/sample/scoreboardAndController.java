package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class scoreboardAndController {

        /**
         * Starts new game
         * Used file type is JSON
         * //@param File
         * @throws IOException
         */
        @FXML
        private void startNewGame() throws IOException {

                /*
                FXMLLoader loader = new FXMLLoader(getClass().getResource("dummyGame.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Old Game");
                stage.setScene(new Scene(root));
                stage.setMaximized(true);
                stage.show();
                // Hide this current window (Main Menu window / ScoreboardMenu window)
                //getStage(event).hide();
                //((Node)(event.getSource())).getScene().getWindow().hide(); <-- ylempi kelpaa

                 */
                Controller controller = new Controller();

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
        /*
        @Override
        public void start(Stage primaryStage) throws Exception {


                Controller controller = new Controller();

                Font.loadFont(getClass().getResourceAsStream("College.ttf"), 14);
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                                "panel.fxml"
                        )
                );
                loader.setController(controller);

                Parent root = loader.load();


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
        */
}

