package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;;

public class NewGame {

    @FXML
    private BorderPane HOME_LOGO;
    @FXML
    private BorderPane AWAY_LOGO;
    @FXML
    private TextField HOME_NAME;
    @FXML
    private TextField AWAY_NAME;
    @FXML
    private StackPane homeLogo;
    @FXML
    private StackPane awayLogo;

    @FXML
    private TextField HOME_PLAYER_1_NAME;
    @FXML
    private TextField HOME_PLAYER_2_NAME;
    @FXML
    private TextField HOME_PLAYER_3_NAME;
    @FXML
    private TextField HOME_PLAYER_4_NAME;
    @FXML
    private TextField HOME_PLAYER_5_NAME;
    @FXML
    private TextField HOME_PLAYER_6_NAME;
    @FXML
    private TextField HOME_PLAYER_7_NAME;
    @FXML
    private TextField HOME_PLAYER_8_NAME;
    @FXML
    private TextField HOME_PLAYER_9_NAME;
    @FXML
    private TextField HOME_PLAYER_10_NAME;
    @FXML
    private TextField HOME_PLAYER_11_NAME;
    @FXML
    private TextField HOME_PLAYER_12_NAME;


    @FXML
    private TextField AWAY_PLAYER_1_NAME;
    @FXML
    private TextField AWAY_PLAYER_2_NAME;
    @FXML
    private TextField AWAY_PLAYER_3_NAME;
    @FXML
    private TextField AWAY_PLAYER_4_NAME;
    @FXML
    private TextField AWAY_PLAYER_5_NAME;
    @FXML
    private TextField AWAY_PLAYER_6_NAME;
    @FXML
    private TextField AWAY_PLAYER_7_NAME;
    @FXML
    private TextField AWAY_PLAYER_8_NAME;
    @FXML
    private TextField AWAY_PLAYER_9_NAME;
    @FXML
    private TextField AWAY_PLAYER_10_NAME;
    @FXML
    private TextField AWAY_PLAYER_11_NAME;
    @FXML
    private TextField AWAY_PLAYER_12_NAME;


    @FXML
    private TextField HOME_PLAYER_1_NUMBER;
    @FXML
    private TextField HOME_PLAYER_2_NUMBER;
    @FXML
    private TextField HOME_PLAYER_3_NUMBER;
    @FXML
    private TextField HOME_PLAYER_4_NUMBER;
    @FXML
    private TextField HOME_PLAYER_5_NUMBER;
    @FXML
    private TextField HOME_PLAYER_6_NUMBER;
    @FXML
    private TextField HOME_PLAYER_7_NUMBER;
    @FXML
    private TextField HOME_PLAYER_8_NUMBER;
    @FXML
    private TextField HOME_PLAYER_9_NUMBER;
    @FXML
    private TextField HOME_PLAYER_10_NUMBER;
    @FXML
    private TextField HOME_PLAYER_11_NUMBER;
    @FXML
    private TextField HOME_PLAYER_12_NUMBER;

    @FXML
    private TextField AWAY_PLAYER_1_NUMBER;
    @FXML
    private TextField AWAY_PLAYER_2_NUMBER;
    @FXML
    private TextField AWAY_PLAYER_3_NUMBER;
    @FXML
    private TextField AWAY_PLAYER_4_NUMBER;
    @FXML
    private TextField AWAY_PLAYER_5_NUMBER;
    @FXML
    private TextField AWAY_PLAYER_6_NUMBER;
    @FXML
    private TextField AWAY_PLAYER_7_NUMBER;
    @FXML
    private TextField AWAY_PLAYER_8_NUMBER;
    @FXML
    private TextField AWAY_PLAYER_9_NUMBER;
    @FXML
    private TextField AWAY_PLAYER_10_NUMBER;
    @FXML
    private TextField AWAY_PLAYER_11_NUMBER;
    @FXML
    private TextField AWAY_PLAYER_12_NUMBER;

    private final String FILEPATH = "./json/players.json";


    @FXML
    private void setOnDragOverH(DragEvent event){
        final Dragboard db = event.getDragboard();
        boolean properType = db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg");
        if (db.hasFiles()) {
            if (properType) {
                homeLogo.setStyle("-fx-background-color: #C6C6C6;");
                event.acceptTransferModes(TransferMode.COPY);
            }
        } else {
            event.consume();
        }
    }

    @FXML
    private void setOnDragOverA(DragEvent event){
        final Dragboard db = event.getDragboard();
        boolean properType = db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg");
        if (db.hasFiles()) {
            if (properType) {
               awayLogo.setStyle("-fx-background-color: #C6C6C6;");
                event.acceptTransferModes(TransferMode.COPY);
            }
        } else {
            event.consume();
        }
    }

    @FXML
    private void setOnDragDroppedH(DragEvent event){
        boolean home = true;
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            File file = db.getFiles().get(0);
            FileInputStream fi = null;
            try{
                fi = new FileInputStream(file);
                Image img;
                try{
                    String name = file.getName();
                    File dest = new File("./json/" + name);
                    Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    fi = new FileInputStream(dest);
                    img = new Image(fi);
                    addImage(img, homeLogo, home);
                    TeamInfo.filePath(home, dest.toString());
                } catch(IOException ex){
                    ex.printStackTrace();
                }
            } catch(FileNotFoundException e){
                e.printStackTrace();
            }finally{
                if(fi != null){
                    try{
                        fi.close();
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }

    @FXML
    private void setOnDragDroppedA(DragEvent event){
        boolean home = false;
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            success = true;
            File file = db.getFiles().get(0);
            FileInputStream fi = null;
            try{
                fi = new FileInputStream(file);
                Image img;
                try{
                    String name = file.getName();
                    File dest = new File("./json/" + name);
                    Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    fi = new FileInputStream(dest);
                    img = new Image(fi);
                    addImage(img, awayLogo, home);
                    TeamInfo.filePath(home, dest.toString());
                } catch(IOException ex){
                    ex.printStackTrace();
                }
            } catch(FileNotFoundException e){
                e.printStackTrace();
            }finally{
                if(fi != null){
                    try{
                        fi.close();
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }

    @FXML
    private void dragExitH(DragEvent event){
        homeLogo.setStyle("");
    }

    @FXML
    private void dragExitA(DragEvent event){
        awayLogo.setStyle("");
    }

    private void addImage(Image i, StackPane pane, boolean home){
        ImageView iV = new ImageView();
        iV.fitWidthProperty().bind(pane.widthProperty());
        iV.fitHeightProperty().bind(pane.heightProperty());
        iV.setPreserveRatio(true);
        iV.setImage(i);
        pane.getChildren().clear();
        pane.getChildren().add(iV);
        if(home){
            HOME_LOGO.setCenter(pane);
        }
        if(!home){
            AWAY_LOGO.setCenter(pane);
        }
    }



    /**
     * When this method is called, it will change the scene to
     * a SettingsMenuNewGame
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

    //accepts only if it has files

    //vaihdettu olemaan hyväksymättä muita kuin txt filejä
    @FXML
    private void handleDragOver (DragEvent event){
        final Dragboard db = event.getDragboard();
        boolean properType = db.getFiles().get(0).getName().toLowerCase().endsWith(".txt");
        if(event.getDragboard().hasFiles() && properType) {
            event.acceptTransferModes(TransferMode.ANY);
        }else{
            event.consume();
        }
    }


    //method to get the file from drag and drop

    @FXML
    private void handleDropHome (DragEvent event) {

        try {
            List<File> files = event.getDragboard().getFiles();
            File file = files.get(0);
            setNamesHome(file);
            /*
            if(file.getAbsolutePath().toLowerCase().contains(".txt")) {
                setNamesHome(file);
            }
            else{
                System.out.println("aseta txt tiedosto");
            }
             */
        } catch (Exception e) {
            System.out.println("meni mönkään");
        }
    }

    @FXML
    private void handleDropGuest (DragEvent event) {
        try {
            List<File> files = event.getDragboard().getFiles();
            File file = files.get(0);
            setNamesGuest(file);
            /*
            if(file.getAbsolutePath().toLowerCase().contains(".txt")) {
                setNamesGuest(file);
            }else{
                System.out.println("aseta txt tiedosto");
            }

             */
        } catch (Exception e) {
            System.out.println("meni mönkään");
        }
    }

    // method to set the names and player numbers for Home-side
    private void setNamesHome(File a){
        String i;
        ArrayList<String> pelaajatKoti = new ArrayList<String>();
        try{
            Scanner sc = new Scanner(a);
            while(sc.hasNextLine()) {
                i=sc.nextLine();
                pelaajatKoti.add(i);
            }

        }
        catch(Exception e){
            System.out.println("meni mönkään");
        }

        if(!pelaajatKoti.get(0).isEmpty()){
            HOME_PLAYER_1_NAME.setText(pelaajatKoti.get(0).substring(3));
            HOME_PLAYER_1_NUMBER.setText(pelaajatKoti.get(0).substring(0,2));
        }
        if(!pelaajatKoti.get(1).isEmpty()){
            HOME_PLAYER_2_NAME.setText(pelaajatKoti.get(1).substring(3));
            HOME_PLAYER_2_NUMBER.setText(pelaajatKoti.get(1).substring(0,2));
        }
        if(!pelaajatKoti.get(2).isEmpty()){
            HOME_PLAYER_3_NAME.setText(pelaajatKoti.get(2).substring(3));
            HOME_PLAYER_3_NUMBER.setText(pelaajatKoti.get(2).substring(0,2));
        }
        if(!pelaajatKoti.get(3).isEmpty()){
            HOME_PLAYER_4_NAME.setText(pelaajatKoti.get(3).substring(3));
            HOME_PLAYER_4_NUMBER.setText(pelaajatKoti.get(3).substring(0,2));
        }
        if(!pelaajatKoti.get(4).isEmpty()){
            HOME_PLAYER_5_NAME.setText(pelaajatKoti.get(4).substring(3));
            HOME_PLAYER_5_NUMBER.setText(pelaajatKoti.get(4).substring(0,2));
        }
        if(!pelaajatKoti.get(5).isEmpty()){
            HOME_PLAYER_6_NAME.setText(pelaajatKoti.get(5).substring(3));
            HOME_PLAYER_6_NUMBER.setText(pelaajatKoti.get(5).substring(0,2));
        }
        if(!pelaajatKoti.get(6).isEmpty()){
            HOME_PLAYER_7_NAME.setText(pelaajatKoti.get(6).substring(3));
            HOME_PLAYER_7_NUMBER.setText(pelaajatKoti.get(6).substring(0,2));
        }
        if(!pelaajatKoti.get(7).isEmpty()){
            HOME_PLAYER_8_NAME.setText(pelaajatKoti.get(7).substring(3));
            HOME_PLAYER_8_NUMBER.setText(pelaajatKoti.get(7).substring(0,2));
        }
        if(!pelaajatKoti.get(8).isEmpty()){
            HOME_PLAYER_9_NAME.setText(pelaajatKoti.get(8).substring(3));
            HOME_PLAYER_9_NUMBER.setText(pelaajatKoti.get(8).substring(0,2));
        }
        if(!pelaajatKoti.get(9).isEmpty()){
            HOME_PLAYER_10_NAME.setText(pelaajatKoti.get(9).substring(3));
            HOME_PLAYER_10_NUMBER.setText(pelaajatKoti.get(9).substring(0,2));
        }
        if(!pelaajatKoti.get(10).isEmpty()){
            HOME_PLAYER_11_NAME.setText(pelaajatKoti.get(10).substring(3));
            HOME_PLAYER_11_NUMBER.setText(pelaajatKoti.get(10).substring(0,2));
        }
        if(!pelaajatKoti.get(11).isEmpty()){
            HOME_PLAYER_12_NAME.setText(pelaajatKoti.get(11).substring(3));
            HOME_PLAYER_12_NUMBER.setText(pelaajatKoti.get(11).substring(0,2));
        }

    }

    private void setNamesGuest(File a) {
        String i;
        ArrayList<String> pelaajat = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(a);
            while (sc.hasNextLine()) {
                i = sc.nextLine();
                pelaajat.add(i);
            }


        } catch (Exception e) {
            System.out.println("meni mönkään");
        }
        if(!pelaajat.get(0).isEmpty()){
            AWAY_PLAYER_1_NAME.setText(pelaajat.get(0).substring(3));
            AWAY_PLAYER_1_NUMBER.setText(pelaajat.get(0).substring(0,2));
        }
        if(!pelaajat.get(1).isEmpty()){
            AWAY_PLAYER_2_NAME.setText(pelaajat.get(1).substring(3));
            AWAY_PLAYER_2_NUMBER.setText(pelaajat.get(1).substring(0,2));
        }
        if(!pelaajat.get(2).isEmpty()){
            AWAY_PLAYER_3_NAME.setText(pelaajat.get(2).substring(3));
            AWAY_PLAYER_3_NUMBER.setText(pelaajat.get(2).substring(0,2));
        }
        if(!pelaajat.get(3).isEmpty()){
            AWAY_PLAYER_4_NAME.setText(pelaajat.get(3).substring(3));
            AWAY_PLAYER_4_NUMBER.setText(pelaajat.get(3).substring(0,2));
        }
        if(!pelaajat.get(4).isEmpty()){
            AWAY_PLAYER_5_NAME.setText(pelaajat.get(4).substring(3));
            AWAY_PLAYER_5_NUMBER.setText(pelaajat.get(4).substring(0,2));
        }
        if(!pelaajat.get(5).isEmpty()){
            AWAY_PLAYER_6_NAME.setText(pelaajat.get(5).substring(3));
            AWAY_PLAYER_6_NUMBER.setText(pelaajat.get(5).substring(0,2));
        }
        if(!pelaajat.get(6).isEmpty()){
            AWAY_PLAYER_7_NAME.setText(pelaajat.get(6).substring(3));
            AWAY_PLAYER_7_NUMBER.setText(pelaajat.get(6).substring(0,2));
        }
        if(!pelaajat.get(7).isEmpty()){
            AWAY_PLAYER_8_NAME.setText(pelaajat.get(7).substring(3));
            AWAY_PLAYER_8_NUMBER.setText(pelaajat.get(7).substring(0,2));
        }
        if(!pelaajat.get(8).isEmpty()){
            AWAY_PLAYER_9_NAME.setText(pelaajat.get(8).substring(3));
            AWAY_PLAYER_9_NUMBER.setText(pelaajat.get(8).substring(0,2));
        }
        if(!pelaajat.get(9).isEmpty()){
            AWAY_PLAYER_10_NAME.setText(pelaajat.get(9).substring(3));
            AWAY_PLAYER_10_NUMBER.setText(pelaajat.get(9).substring(0,2));
        }
        if(!pelaajat.get(10).isEmpty()){
            AWAY_PLAYER_11_NAME.setText(pelaajat.get(10).substring(3));
            AWAY_PLAYER_11_NUMBER.setText(pelaajat.get(10).substring(0,2));
        }
        if(!pelaajat.get(11).isEmpty()) {
            AWAY_PLAYER_12_NAME.setText(pelaajat.get(11).substring(3));
            AWAY_PLAYER_12_NUMBER.setText(pelaajat.get(11).substring(0, 2));
        }
    }

    /*
    @FXML
    private boolean homeTextFieldInputValidator() {

        TextField home1 = HOME_PLAYER_1_NUMBER;
        //TextField home2 = HOME_PLAYER_2_NUMBER;
        home1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    home1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    */


    private void setTeamNames(){
        TeamInfo.filePath(HOME_NAME.getText(), true);
        TeamInfo.filePath(AWAY_NAME.getText(), false);
    }

    @FXML
    private void createPlayersJSON(ActionEvent event) throws IOException {
        setTeamNames();                 //Asettaa joukueiden nimet

        ArrayList<player> HomePlayerList = new ArrayList<>();
        ArrayList<player> AwayPlayerList = new ArrayList<>();

        // Home
        HomePlayerList.add(new player(HOME_PLAYER_1_NAME.getText(), HOME_PLAYER_1_NUMBER.getText()));
        HomePlayerList.add(new player(HOME_PLAYER_2_NAME.getText(), HOME_PLAYER_2_NUMBER.getText()));
        HomePlayerList.add(new player(HOME_PLAYER_3_NAME.getText(), HOME_PLAYER_3_NUMBER.getText()));
        HomePlayerList.add(new player(HOME_PLAYER_4_NAME.getText(), HOME_PLAYER_4_NUMBER.getText()));
        HomePlayerList.add(new player(HOME_PLAYER_5_NAME.getText(), HOME_PLAYER_5_NUMBER.getText()));
        HomePlayerList.add(new player(HOME_PLAYER_6_NAME.getText(), HOME_PLAYER_6_NUMBER.getText()));
        HomePlayerList.add(new player(HOME_PLAYER_7_NAME.getText(), HOME_PLAYER_7_NUMBER.getText()));
        HomePlayerList.add(new player(HOME_PLAYER_8_NAME.getText(), HOME_PLAYER_8_NUMBER.getText()));
        HomePlayerList.add(new player(HOME_PLAYER_9_NAME.getText(), HOME_PLAYER_9_NUMBER.getText()));
        HomePlayerList.add(new player(HOME_PLAYER_10_NAME.getText(), HOME_PLAYER_10_NUMBER.getText()));
        HomePlayerList.add(new player(HOME_PLAYER_11_NAME.getText(), HOME_PLAYER_11_NUMBER.getText()));
        HomePlayerList.add(new player(HOME_PLAYER_12_NAME.getText(), HOME_PLAYER_12_NUMBER.getText()));
        // Away
        AwayPlayerList.add(new player(AWAY_PLAYER_1_NAME.getText(), AWAY_PLAYER_1_NUMBER.getText()));
        AwayPlayerList.add(new player(AWAY_PLAYER_2_NAME.getText(), AWAY_PLAYER_2_NUMBER.getText()));
        AwayPlayerList.add(new player(AWAY_PLAYER_3_NAME.getText(), AWAY_PLAYER_3_NUMBER.getText()));
        AwayPlayerList.add(new player(AWAY_PLAYER_4_NAME.getText(), AWAY_PLAYER_4_NUMBER.getText()));
        AwayPlayerList.add(new player(AWAY_PLAYER_5_NAME.getText(), AWAY_PLAYER_5_NUMBER.getText()));
        AwayPlayerList.add(new player(AWAY_PLAYER_6_NAME.getText(), AWAY_PLAYER_6_NUMBER.getText()));
        AwayPlayerList.add(new player(AWAY_PLAYER_7_NAME.getText(), AWAY_PLAYER_7_NUMBER.getText()));
        AwayPlayerList.add(new player(AWAY_PLAYER_8_NAME.getText(), AWAY_PLAYER_8_NUMBER.getText()));
        AwayPlayerList.add(new player(AWAY_PLAYER_9_NAME.getText(), AWAY_PLAYER_9_NUMBER.getText()));
        AwayPlayerList.add(new player(AWAY_PLAYER_10_NAME.getText(), AWAY_PLAYER_10_NUMBER.getText()));
        AwayPlayerList.add(new player(AWAY_PLAYER_11_NAME.getText(), AWAY_PLAYER_11_NUMBER.getText()));
        AwayPlayerList.add(new player(AWAY_PLAYER_12_NAME.getText(), AWAY_PLAYER_12_NUMBER.getText()));

        Players.playerList home = new Players.playerList("HomeTeam", HomePlayerList);
        Players.playerList away = new Players.playerList("AwayTeam", AwayPlayerList);

        Players players = new Players(home, away);

        // Create a json file and write to it
        try (Writer writer = new FileWriter(FILEPATH)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(players, writer);
            writer.close();
        }

    }


    /**
     * Starts new game
     * Used file type is JSON
     * //@param File
     * @throws IOException
     */
    @FXML
    private void startNewGame(ActionEvent event) throws IOException {

        createPlayersJSON(event);

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

        // Set logos
        controller.setHomeTeamLogo();
        controller.setAwayTeamLogo();

        anotherScene.getRoot().requestFocus();
        anotherStage.setScene(anotherScene);
        anotherStage.show();
    }


}
