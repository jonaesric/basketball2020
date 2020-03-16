package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderImage;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

public class Controller {

    String FilePath = "./src/gamestate.json";

    Sound sound = new Sound();
    Gson gson = new Gson();
    // USEFULL FOR FORMATTING TIME
    Generator generator = new Generator();

    //DUMMY TEAMS
    private Team home = new Team("hupi-ukot");
    private Team away = new Team("hupi-akat");
    private Board board = new Board(home, away);

    // DUMMY PLAYER LISTS
    String[][] home_players_info = new String[][]{new String[]{"1", "I. Seppala"}, new String[]{"2", "V. Simpson"}, new String[]{"4", "A. Diaz"}, new String[]{"6", "J. Herrala"}, new String[]{"7", "A. Perttu"},
    new String[]{"8", "T. Huolila"}, new String[]{"9", "M. Ojala"}, new String[]{"12", "S. Vanttaja"}, new String[]{"31", "T. Palmi"}, new String[]{"32", "R. Hollis-Jefferson"},
    new String[]{"", ""}, new String[]{"", ""}};

    String[][] away_players_info = new String[][]{new String[]{"0", "T. Golden"}, new String[]{"2", "V. Zaryazhko"}, new String[]{"3", "A. Ireland"}, new String[]{"9", "A. Petenev"}, new String[]{"10", "N. Mikhailovskii"},
    new String[]{"12", "P. Buford"}, new String[]{"14", "B. Savovic"}, new String[]{"23", "T. McLean"}, new String[]{"32", "A. Kvitkovskikh"}, new String[]{"35", "A. Zabelin"}, new String[]{"45", "D. Kravish"},
    new String[]{"", ""}};

    // DUMMY SETTINGS
    private Settings settings = new Settings(70, 4, 10, 10, 20, 5, 3, 1, 1, 5, 1, 1, true, 10);

    private LinkedList<String> player = new LinkedList<>();
    private Game GameTool = new Game(board, settings, home_players_info, away_players_info);
    private Timeline timeline;
    private boolean isStarted = false;
    private boolean isTimeoutAlready = false;
    private boolean onBreak = false;

    // import Labels from the panel.fxml file so we can edit those.
    @FXML
    private GridPane mainGrid;
    @FXML
    private Label HOME_FOULS, HOME_TIMEOUTS;
    @FXML
    private Label AWAY_FOULS, AWAY_TIMEOUTS;
    @FXML
    private Label SCORE, TIME, PERIOD;
    @FXML
    private Label HOME_PLAYER_1_FOULS, HOME_PLAYER_2_FOULS, HOME_PLAYER_3_FOULS, HOME_PLAYER_4_FOULS, HOME_PLAYER_5_FOULS, HOME_PLAYER_6_FOULS, HOME_PLAYER_7_FOULS, HOME_PLAYER_8_FOULS, HOME_PLAYER_9_FOULS, HOME_PLAYER_10_FOULS, HOME_PLAYER_11_FOULS, HOME_PLAYER_12_FOULS;
    @FXML
    private Label HOME_PLAYER_1_RED_FOUL, HOME_PLAYER_2_RED_FOUL, HOME_PLAYER_3_RED_FOUL, HOME_PLAYER_4_RED_FOUL, HOME_PLAYER_5_RED_FOUL, HOME_PLAYER_6_RED_FOUL, HOME_PLAYER_7_RED_FOUL, HOME_PLAYER_8_RED_FOUL, HOME_PLAYER_9_RED_FOUL, HOME_PLAYER_10_RED_FOUL, HOME_PLAYER_11_RED_FOUL, HOME_PLAYER_12_RED_FOUL;
    @FXML
    private Label HOME_PLAYER_1_POINTS, HOME_PLAYER_2_POINTS, HOME_PLAYER_3_POINTS, HOME_PLAYER_4_POINTS, HOME_PLAYER_5_POINTS, HOME_PLAYER_6_POINTS, HOME_PLAYER_7_POINTS, HOME_PLAYER_8_POINTS, HOME_PLAYER_9_POINTS, HOME_PLAYER_10_POINTS, HOME_PLAYER_11_POINTS, HOME_PLAYER_12_POINTS;
    @FXML
    private JButton HOME_PLAYER_1_POINTSPLUS, HOME_PLAYER_2_POINTSPLUS, HOME_PLAYER_3_POINTSPLUS, HOME_PLAYER_4_POINTSPLUS;
    @FXML
    private Label HOME_PLAYER_1_NAME, HOME_PLAYER_2_NAME, HOME_PLAYER_3_NAME, HOME_PLAYER_4_NAME, HOME_PLAYER_5_NAME, HOME_PLAYER_6_NAME, HOME_PLAYER_7_NAME, HOME_PLAYER_8_NAME, HOME_PLAYER_9_NAME, HOME_PLAYER_10_NAME, HOME_PLAYER_11_NAME, HOME_PLAYER_12_NAME;
    @FXML
    private Label HOME_PLAYER_1_NUMBER, HOME_PLAYER_2_NUMBER, HOME_PLAYER_3_NUMBER, HOME_PLAYER_4_NUMBER, HOME_PLAYER_5_NUMBER, HOME_PLAYER_6_NUMBER, HOME_PLAYER_7_NUMBER, HOME_PLAYER_8_NUMBER, HOME_PLAYER_9_NUMBER, HOME_PLAYER_10_NUMBER, HOME_PLAYER_11_NUMBER, HOME_PLAYER_12_NUMBER;
    @FXML
    private Label AWAY_PLAYER_1_POINTS, AWAY_PLAYER_2_POINTS, AWAY_PLAYER_3_POINTS, AWAY_PLAYER_4_POINTS, AWAY_PLAYER_5_POINTS, AWAY_PLAYER_6_POINTS, AWAY_PLAYER_7_POINTS, AWAY_PLAYER_8_POINTS, AWAY_PLAYER_9_POINTS, AWAY_PLAYER_10_POINTS, AWAY_PLAYER_11_POINTS, AWAY_PLAYER_12_POINTS;
    @FXML
    private Label AWAY_PLAYER_1_FOULS, AWAY_PLAYER_2_FOULS, AWAY_PLAYER_3_FOULS, AWAY_PLAYER_4_FOULS, AWAY_PLAYER_5_FOULS, AWAY_PLAYER_6_FOULS, AWAY_PLAYER_7_FOULS, AWAY_PLAYER_8_FOULS, AWAY_PLAYER_9_FOULS, AWAY_PLAYER_10_FOULS, AWAY_PLAYER_11_FOULS, AWAY_PLAYER_12_FOULS;
    @FXML
    private Label AWAY_PLAYER_1_RED_FOUL, AWAY_PLAYER_2_RED_FOUL, AWAY_PLAYER_3_RED_FOUL, AWAY_PLAYER_4_RED_FOUL, AWAY_PLAYER_5_RED_FOUL, AWAY_PLAYER_6_RED_FOUL, AWAY_PLAYER_7_RED_FOUL, AWAY_PLAYER_8_RED_FOUL, AWAY_PLAYER_9_RED_FOUL, AWAY_PLAYER_10_RED_FOUL, AWAY_PLAYER_11_RED_FOUL, AWAY_PLAYER_12_RED_FOUL;
    @FXML
    private Label AWAY_PLAYER_1_NAME, AWAY_PLAYER_2_NAME, AWAY_PLAYER_3_NAME, AWAY_PLAYER_4_NAME, AWAY_PLAYER_5_NAME, AWAY_PLAYER_6_NAME, AWAY_PLAYER_7_NAME, AWAY_PLAYER_8_NAME, AWAY_PLAYER_9_NAME, AWAY_PLAYER_10_NAME, AWAY_PLAYER_11_NAME, AWAY_PLAYER_12_NAME;
    @FXML
    private Label AWAY_PLAYER_1_NUMBER, AWAY_PLAYER_2_NUMBER, AWAY_PLAYER_3_NUMBER, AWAY_PLAYER_4_NUMBER, AWAY_PLAYER_5_NUMBER, AWAY_PLAYER_6_NUMBER, AWAY_PLAYER_7_NUMBER, AWAY_PLAYER_8_NUMBER, AWAY_PLAYER_9_NUMBER, AWAY_PLAYER_10_NUMBER, AWAY_PLAYER_11_NUMBER, AWAY_PLAYER_12_NUMBER;

    /**
     * Binds every Label to StringProperty of Game instance.
     */
    public void initialize() throws FileNotFoundException {
        gameObject g = returnGameObject();
        settings = g.settings;
        TIME.textProperty().bind(generator.getTimeFormat(GameTool.timeMillis, !GameTool.getPeriodInfo().isBreak(), GameTool));

        if (GameTool.getSettings().getKellonSuunta()) {
            GameTool.timeMillis.setValue(GameTool.getPeriodInfo().getSeconds() * 1000L);
        } else {
            GameTool.timeMillis.setValue(0L);
        }

        SCORE.textProperty().bind(GameTool.SCORE);
        PERIOD.textProperty().bind(GameTool.PERIOD);
        AWAY_FOULS.textProperty().bind(GameTool.AWAY_FOULS);
        AWAY_TIMEOUTS.textProperty().bind(GameTool.AWAY_TIMEOUTS);
        HOME_FOULS.textProperty().bind(GameTool.HOME_FOULS);
        HOME_TIMEOUTS.textProperty().bind(GameTool.HOME_TIMEOUTS);

        HOME_PLAYER_1_FOULS.textProperty().bind(GameTool.HOME_PLAYER_1_FOULS);
        HOME_PLAYER_2_FOULS.textProperty().bind(GameTool.HOME_PLAYER_2_FOULS);
        HOME_PLAYER_3_FOULS.textProperty().bind(GameTool.HOME_PLAYER_3_FOULS);
        HOME_PLAYER_4_FOULS.textProperty().bind(GameTool.HOME_PLAYER_4_FOULS);
        HOME_PLAYER_5_FOULS.textProperty().bind(GameTool.HOME_PLAYER_5_FOULS);
        HOME_PLAYER_6_FOULS.textProperty().bind(GameTool.HOME_PLAYER_6_FOULS);
        HOME_PLAYER_7_FOULS.textProperty().bind(GameTool.HOME_PLAYER_7_FOULS);
        HOME_PLAYER_8_FOULS.textProperty().bind(GameTool.HOME_PLAYER_8_FOULS);
        HOME_PLAYER_9_FOULS.textProperty().bind(GameTool.HOME_PLAYER_9_FOULS);
        HOME_PLAYER_10_FOULS.textProperty().bind(GameTool.HOME_PLAYER_10_FOULS);
        HOME_PLAYER_11_FOULS.textProperty().bind(GameTool.HOME_PLAYER_11_FOULS);
        HOME_PLAYER_12_FOULS.textProperty().bind(GameTool.HOME_PLAYER_12_FOULS);

        HOME_PLAYER_1_RED_FOUL.textProperty().bind(GameTool.HOME_PLAYER_1_RED_FOUL);
        HOME_PLAYER_2_RED_FOUL.textProperty().bind(GameTool.HOME_PLAYER_2_RED_FOUL);
        HOME_PLAYER_3_RED_FOUL.textProperty().bind(GameTool.HOME_PLAYER_3_RED_FOUL);
        HOME_PLAYER_4_RED_FOUL.textProperty().bind(GameTool.HOME_PLAYER_4_RED_FOUL);
        HOME_PLAYER_5_RED_FOUL.textProperty().bind(GameTool.HOME_PLAYER_5_RED_FOUL);
        HOME_PLAYER_6_RED_FOUL.textProperty().bind(GameTool.HOME_PLAYER_6_RED_FOUL);
        HOME_PLAYER_7_RED_FOUL.textProperty().bind(GameTool.HOME_PLAYER_7_RED_FOUL);
        HOME_PLAYER_8_RED_FOUL.textProperty().bind(GameTool.HOME_PLAYER_8_RED_FOUL);
        HOME_PLAYER_9_RED_FOUL.textProperty().bind(GameTool.HOME_PLAYER_9_RED_FOUL);
        HOME_PLAYER_10_RED_FOUL.textProperty().bind(GameTool.HOME_PLAYER_10_RED_FOUL);
        HOME_PLAYER_11_RED_FOUL.textProperty().bind(GameTool.HOME_PLAYER_11_RED_FOUL);
        HOME_PLAYER_12_RED_FOUL.textProperty().bind(GameTool.HOME_PLAYER_12_RED_FOUL);

        HOME_PLAYER_1_POINTS.textProperty().bind((GameTool.HOME_PLAYER_1_POINTS));
        HOME_PLAYER_2_POINTS.textProperty().bind((GameTool.HOME_PLAYER_2_POINTS));
        HOME_PLAYER_3_POINTS.textProperty().bind((GameTool.HOME_PLAYER_3_POINTS));
        HOME_PLAYER_4_POINTS.textProperty().bind((GameTool.HOME_PLAYER_4_POINTS));
        HOME_PLAYER_5_POINTS.textProperty().bind((GameTool.HOME_PLAYER_5_POINTS));
        HOME_PLAYER_6_POINTS.textProperty().bind((GameTool.HOME_PLAYER_6_POINTS));
        HOME_PLAYER_7_POINTS.textProperty().bind((GameTool.HOME_PLAYER_7_POINTS));
        HOME_PLAYER_8_POINTS.textProperty().bind((GameTool.HOME_PLAYER_8_POINTS));
        HOME_PLAYER_9_POINTS.textProperty().bind((GameTool.HOME_PLAYER_9_POINTS));
        HOME_PLAYER_10_POINTS.textProperty().bind((GameTool.HOME_PLAYER_10_POINTS));
        HOME_PLAYER_11_POINTS.textProperty().bind((GameTool.HOME_PLAYER_11_POINTS));
        HOME_PLAYER_12_POINTS.textProperty().bind((GameTool.HOME_PLAYER_12_POINTS));

        HOME_PLAYER_1_NAME.textProperty().bind(GameTool.HOME_PLAYER_1_NAME);
        HOME_PLAYER_2_NAME.textProperty().bind(GameTool.HOME_PLAYER_2_NAME);
        HOME_PLAYER_3_NAME.textProperty().bind(GameTool.HOME_PLAYER_3_NAME);
        HOME_PLAYER_4_NAME.textProperty().bind(GameTool.HOME_PLAYER_4_NAME);
        HOME_PLAYER_5_NAME.textProperty().bind(GameTool.HOME_PLAYER_5_NAME);
        HOME_PLAYER_6_NAME.textProperty().bind(GameTool.HOME_PLAYER_6_NAME);
        HOME_PLAYER_7_NAME.textProperty().bind(GameTool.HOME_PLAYER_7_NAME);
        HOME_PLAYER_8_NAME.textProperty().bind(GameTool.HOME_PLAYER_8_NAME);
        HOME_PLAYER_9_NAME.textProperty().bind(GameTool.HOME_PLAYER_9_NAME);
        HOME_PLAYER_10_NAME.textProperty().bind(GameTool.HOME_PLAYER_10_NAME);
        HOME_PLAYER_11_NAME.textProperty().bind(GameTool.HOME_PLAYER_11_NAME);
        HOME_PLAYER_12_NAME.textProperty().bind(GameTool.HOME_PLAYER_12_NAME);

        HOME_PLAYER_1_NUMBER.textProperty().bind(GameTool.HOME_PLAYER_1_NUMBER);
        HOME_PLAYER_2_NUMBER.textProperty().bind(GameTool.HOME_PLAYER_2_NUMBER);
        HOME_PLAYER_3_NUMBER.textProperty().bind(GameTool.HOME_PLAYER_3_NUMBER);
        HOME_PLAYER_4_NUMBER.textProperty().bind(GameTool.HOME_PLAYER_4_NUMBER);
        HOME_PLAYER_5_NUMBER.textProperty().bind(GameTool.HOME_PLAYER_5_NUMBER);
        HOME_PLAYER_6_NUMBER.textProperty().bind(GameTool.HOME_PLAYER_6_NUMBER);
        HOME_PLAYER_7_NUMBER.textProperty().bind(GameTool.HOME_PLAYER_7_NUMBER);
        HOME_PLAYER_8_NUMBER.textProperty().bind(GameTool.HOME_PLAYER_8_NUMBER);
        HOME_PLAYER_9_NUMBER.textProperty().bind(GameTool.HOME_PLAYER_9_NUMBER);
        HOME_PLAYER_10_NUMBER.textProperty().bind(GameTool.HOME_PLAYER_10_NUMBER);
        HOME_PLAYER_11_NUMBER.textProperty().bind(GameTool.HOME_PLAYER_11_NUMBER);
        HOME_PLAYER_12_NUMBER.textProperty().bind(GameTool.HOME_PLAYER_12_NUMBER);

        AWAY_PLAYER_1_POINTS.textProperty().bind(GameTool.AWAY_PLAYER_1_POINTS);
        AWAY_PLAYER_2_POINTS.textProperty().bind(GameTool.AWAY_PLAYER_2_POINTS);
        AWAY_PLAYER_3_POINTS.textProperty().bind(GameTool.AWAY_PLAYER_3_POINTS);
        AWAY_PLAYER_4_POINTS.textProperty().bind(GameTool.AWAY_PLAYER_4_POINTS);
        AWAY_PLAYER_5_POINTS.textProperty().bind(GameTool.AWAY_PLAYER_5_POINTS);
        AWAY_PLAYER_6_POINTS.textProperty().bind(GameTool.AWAY_PLAYER_6_POINTS);
        AWAY_PLAYER_7_POINTS.textProperty().bind(GameTool.AWAY_PLAYER_7_POINTS);
        AWAY_PLAYER_8_POINTS.textProperty().bind(GameTool.AWAY_PLAYER_8_POINTS);
        AWAY_PLAYER_9_POINTS.textProperty().bind(GameTool.AWAY_PLAYER_9_POINTS);
        AWAY_PLAYER_10_POINTS.textProperty().bind(GameTool.AWAY_PLAYER_10_POINTS);
        AWAY_PLAYER_11_POINTS.textProperty().bind(GameTool.AWAY_PLAYER_11_POINTS);
        AWAY_PLAYER_12_POINTS.textProperty().bind(GameTool.AWAY_PLAYER_12_POINTS);

        AWAY_PLAYER_1_FOULS.textProperty().bind(GameTool.AWAY_PLAYER_1_FOULS);
        AWAY_PLAYER_2_FOULS.textProperty().bind(GameTool.AWAY_PLAYER_2_FOULS);
        AWAY_PLAYER_3_FOULS.textProperty().bind(GameTool.AWAY_PLAYER_3_FOULS);
        AWAY_PLAYER_4_FOULS.textProperty().bind(GameTool.AWAY_PLAYER_4_FOULS);
        AWAY_PLAYER_5_FOULS.textProperty().bind(GameTool.AWAY_PLAYER_5_FOULS);
        AWAY_PLAYER_6_FOULS.textProperty().bind(GameTool.AWAY_PLAYER_6_FOULS);
        AWAY_PLAYER_7_FOULS.textProperty().bind(GameTool.AWAY_PLAYER_7_FOULS);
        AWAY_PLAYER_8_FOULS.textProperty().bind(GameTool.AWAY_PLAYER_8_FOULS);
        AWAY_PLAYER_9_FOULS.textProperty().bind(GameTool.AWAY_PLAYER_9_FOULS);
        AWAY_PLAYER_10_FOULS.textProperty().bind(GameTool.AWAY_PLAYER_10_FOULS);
        AWAY_PLAYER_11_FOULS.textProperty().bind(GameTool.AWAY_PLAYER_11_FOULS);
        AWAY_PLAYER_12_FOULS.textProperty().bind(GameTool.AWAY_PLAYER_12_FOULS);

        AWAY_PLAYER_1_RED_FOUL.textProperty().bind(GameTool.AWAY_PLAYER_1_RED_FOUL);
        AWAY_PLAYER_2_RED_FOUL.textProperty().bind(GameTool.AWAY_PLAYER_2_RED_FOUL);
        AWAY_PLAYER_3_RED_FOUL.textProperty().bind(GameTool.AWAY_PLAYER_3_RED_FOUL);
        AWAY_PLAYER_4_RED_FOUL.textProperty().bind(GameTool.AWAY_PLAYER_4_RED_FOUL);
        AWAY_PLAYER_5_RED_FOUL.textProperty().bind(GameTool.AWAY_PLAYER_5_RED_FOUL);
        AWAY_PLAYER_6_RED_FOUL.textProperty().bind(GameTool.AWAY_PLAYER_6_RED_FOUL);
        AWAY_PLAYER_7_RED_FOUL.textProperty().bind(GameTool.AWAY_PLAYER_7_RED_FOUL);
        AWAY_PLAYER_8_RED_FOUL.textProperty().bind(GameTool.AWAY_PLAYER_8_RED_FOUL);
        AWAY_PLAYER_9_RED_FOUL.textProperty().bind(GameTool.AWAY_PLAYER_9_RED_FOUL);
        AWAY_PLAYER_10_RED_FOUL.textProperty().bind(GameTool.AWAY_PLAYER_10_RED_FOUL);
        AWAY_PLAYER_11_RED_FOUL.textProperty().bind(GameTool.AWAY_PLAYER_11_RED_FOUL);
        AWAY_PLAYER_12_RED_FOUL.textProperty().bind(GameTool.AWAY_PLAYER_12_RED_FOUL);

        AWAY_PLAYER_1_NAME.textProperty().bind(GameTool.AWAY_PLAYER_1_NAME);
        AWAY_PLAYER_2_NAME.textProperty().bind(GameTool.AWAY_PLAYER_2_NAME);
        AWAY_PLAYER_3_NAME.textProperty().bind(GameTool.AWAY_PLAYER_3_NAME);
        AWAY_PLAYER_4_NAME.textProperty().bind(GameTool.AWAY_PLAYER_4_NAME);
        AWAY_PLAYER_5_NAME.textProperty().bind(GameTool.AWAY_PLAYER_5_NAME);
        AWAY_PLAYER_6_NAME.textProperty().bind(GameTool.AWAY_PLAYER_6_NAME);
        AWAY_PLAYER_7_NAME.textProperty().bind(GameTool.AWAY_PLAYER_7_NAME);
        AWAY_PLAYER_8_NAME.textProperty().bind(GameTool.AWAY_PLAYER_8_NAME);
        AWAY_PLAYER_9_NAME.textProperty().bind(GameTool.AWAY_PLAYER_9_NAME);
        AWAY_PLAYER_10_NAME.textProperty().bind(GameTool.AWAY_PLAYER_10_NAME);
        AWAY_PLAYER_11_NAME.textProperty().bind(GameTool.AWAY_PLAYER_11_NAME);
        AWAY_PLAYER_12_NAME.textProperty().bind(GameTool.AWAY_PLAYER_12_NAME);

        AWAY_PLAYER_1_NUMBER.textProperty().bind(GameTool.AWAY_PLAYER_1_NUMBER);
        AWAY_PLAYER_2_NUMBER.textProperty().bind(GameTool.AWAY_PLAYER_2_NUMBER);
        AWAY_PLAYER_3_NUMBER.textProperty().bind(GameTool.AWAY_PLAYER_3_NUMBER);
        AWAY_PLAYER_4_NUMBER.textProperty().bind(GameTool.AWAY_PLAYER_4_NUMBER);
        AWAY_PLAYER_5_NUMBER.textProperty().bind(GameTool.AWAY_PLAYER_5_NUMBER);
        AWAY_PLAYER_6_NUMBER.textProperty().bind(GameTool.AWAY_PLAYER_6_NUMBER);
        AWAY_PLAYER_7_NUMBER.textProperty().bind(GameTool.AWAY_PLAYER_7_NUMBER);
        AWAY_PLAYER_8_NUMBER.textProperty().bind(GameTool.AWAY_PLAYER_8_NUMBER);
        AWAY_PLAYER_9_NUMBER.textProperty().bind(GameTool.AWAY_PLAYER_9_NUMBER);
        AWAY_PLAYER_10_NUMBER.textProperty().bind(GameTool.AWAY_PLAYER_10_NUMBER);
        AWAY_PLAYER_11_NUMBER.textProperty().bind(GameTool.AWAY_PLAYER_11_NUMBER);
        AWAY_PLAYER_12_NUMBER.textProperty().bind(GameTool.AWAY_PLAYER_12_NUMBER);
        setGamestateFromJson(g);

    }

    public void PLAYHORNPELIKELLO() {
        sound.playHorn1(GameTool.getSettings().getPelikellonSummeri());
    }

    public void PLAYHORNHEITOKELLO() {
        sound.playHorn2(GameTool.getSettings().getHeittokellonSummeri());
    }

    public void resetAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Haluatko varmasti resetoida pelin?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            GameTool.reset();
            if (GameTool.getSettings().getKellonSuunta()) {
                GameTool.timeMillis.setValue(GameTool.getPeriodInfo().getSeconds() * 1000L);
            } else {
                GameTool.timeMillis.setValue(0L);
            }
            timeline.pause();
        }

    }

    /**
     * Decreases home score by 1
     */
    public void HOME_SCOREMINUS() {
        Calculations.score_calculations(-1, true, -1, GameTool);

    }

    /**
     * Decreases away score by 1
     */
    public void AWAY_SCOREMINUS() {
        Calculations.score_calculations(-1, false, -1, GameTool);

    }

    /**
     * Decreases home player's score by 1
     */
    public void HOME_PLAYER_1_POINTSMINUS() throws IOException {
        Calculations.score_calculations(-1, true, 1, GameTool);
        Border borderVisible = mainGrid.getBorder();
        Border border2 = new Border((BorderImage) null);
        System.out.println(borderVisible.getInsets());
        System.out.println(borderVisible.getOutsets());

        Insets testInsets = new Insets(13.0, 13.0, 13.0, 13.0);
        BorderWidths testWidth = new BorderWidths(13);
        // BorderImage image = new BorderImage(null,testWidth,testInsets,testWidth,true,null,null);
        // Border border = new Border(image);

        System.out.println(mainGrid.getBorder());
        // gson.toJson(border2, new FileWriter("border"));

        // Border border1 = new Border(image);
        // mainGrid.setBorder(border1);
    }

    /**
     * Increases home player's score by 1
     */
    public void HOME_PLAYER_1_POINTSPLUS() {
        Calculations.score_calculations(1, true, 1, GameTool);
        Border border = new Border((BorderImage) null);
        ;
    }

    /**
     * Decreases home player's score by 1
     */
    public void HOME_PLAYER_2_POINTSMINUS() {
        Calculations.score_calculations(-1, true, 2, GameTool);
    }

    /**
     * Increases home player's score by 1
     */
    public void HOME_PLAYER_2_POINTSPLUS() {
        Calculations.score_calculations(1, true, 2, GameTool);
    }

    /**
     * Decreases home player's score by 1
     */
    public void HOME_PLAYER_3_POINTSMINUS() {
        Calculations.score_calculations(-1, true, 3, GameTool);
    }

    /**
     * Increases home player's score by 1
     */
    public void HOME_PLAYER_3_POINTSPLUS() {
        Calculations.score_calculations(1, true, 3, GameTool);
    }

    /**
     * Decreases home player's score by 1
     */
    public void HOME_PLAYER_4_POINTSMINUS() {
        Calculations.score_calculations(-1, true, 4, GameTool);
    }

    /**
     * Increases home player's score by 1
     */
    public void HOME_PLAYER_4_POINTSPLUS() {
        Calculations.score_calculations(1, true, 4, GameTool);
    }

    /**
     * Decreases home player's score by 1
     */
    public void HOME_PLAYER_5_POINTSMINUS() {
        Calculations.score_calculations(-1, true, 5, GameTool);
    }

    /**
     * Increases home player's score by 1
     */
    public void HOME_PLAYER_5_POINTSPLUS() {
        Calculations.score_calculations(1, true, 5, GameTool);
    }

    /**
     * Decreases home player's score by 1
     */
    public void HOME_PLAYER_6_POINTSMINUS() {
        Calculations.score_calculations(-1, true, 6, GameTool);
    }

    public void HOME_PLAYER_6_POINTSPLUS() {
        Calculations.score_calculations(1, true, 6, GameTool);
    }

    /**
     * Decreases home player's score by 1
     */
    public void HOME_PLAYER_7_POINTSMINUS() {
        Calculations.score_calculations(-1, true, 7, GameTool);
    }

    /**
     * Increases home player's score by 1
     */
    public void HOME_PLAYER_7_POINTSPLUS() {
        Calculations.score_calculations(1, true, 7, GameTool);
    }

    /**
     * Decreases home player's score by 1
     */
    public void HOME_PLAYER_8_POINTSMINUS() {
        Calculations.score_calculations(-1, true, 8, GameTool);
    }

    /**
     * Increases home player's score by 1
     */
    public void HOME_PLAYER_8_POINTSPLUS() {
        Calculations.score_calculations(1, true, 8, GameTool);
    }

    /**
     * Decreases home player's score by 1
     */
    public void HOME_PLAYER_9_POINTSMINUS() {
        Calculations.score_calculations(-1, true, 9, GameTool);
    }

    /**
     * Increases home player's score by 1
     */
    public void HOME_PLAYER_9_POINTSPLUS() {
        Calculations.score_calculations(1, true, 9, GameTool);
    }

    /**
     * Decreases home player's score by 1
     */
    public void HOME_PLAYER_10_POINTSMINUS() {
        Calculations.score_calculations(-1, true, 10, GameTool);
    }

    /**
     * Increases home player's score by 1
     */
    public void HOME_PLAYER_10_POINTSPLUS() {
        Calculations.score_calculations(1, true, 10, GameTool);
    }

    /**
     * Decreases home player's score by 1
     */
    public void HOME_PLAYER_11_POINTSMINUS() {
        Calculations.score_calculations(-1, true, 11, GameTool);
    }

    /**
     * Increases home player's score by 1
     */
    public void HOME_PLAYER_11_POINTSPLUS() {
        Calculations.score_calculations(1, true, 11, GameTool);
    }

    /**
     * Decreases home player's score by 1
     */
    public void HOME_PLAYER_12_POINTSMINUS() {
        Calculations.score_calculations(-1, true, 12, GameTool);
    }

    /**
     * Increases home player's score by 1
     */
    public void HOME_PLAYER_12_POINTSPLUS() {
        Calculations.score_calculations(1, true, 12, GameTool);
    }

    /**
     * Decreases away player's score by 1
     */
    public void AWAY_PLAYER_1_POINTSMINUS() {
        Calculations.score_calculations(-1, false, 1, GameTool);
    }

    /**
     * Increases away player's score by 1
     */
    public void AWAY_PLAYER_1_POINTSPLUS() {
        Calculations.score_calculations(1, false, 1, GameTool);
    }

    /**
     * Decreases away player's score by 1
     */
    public void AWAY_PLAYER_2_POINTSMINUS() {
        Calculations.score_calculations(-1, false, 2, GameTool);
    }

    /**
     * Increases away player's score by 1
     */
    public void AWAY_PLAYER_2_POINTSPLUS() {
        Calculations.score_calculations(1, false, 2, GameTool);
    }

    /**
     * Decreases away player's score by 1
     */
    public void AWAY_PLAYER_3_POINTSMINUS() {
        Calculations.score_calculations(-1, false, 3, GameTool);
    }

    /**
     * Increases away player's score by 1
     */
    public void AWAY_PLAYER_3_POINTSPLUS() {
        Calculations.score_calculations(1, false, 3, GameTool);
    }

    /**
     * Decreases away player's score by 1
     */
    public void AWAY_PLAYER_4_POINTSMINUS() {
        Calculations.score_calculations(-1, false, 4, GameTool);
    }

    /**
     * Increases away player's score by 1
     */
    public void AWAY_PLAYER_4_POINTSPLUS() {
        Calculations.score_calculations(1, false, 4, GameTool);
    }

    /**
     * Decreases away player's score by 1
     */
    public void AWAY_PLAYER_5_POINTSMINUS() {
        Calculations.score_calculations(-1, false, 5, GameTool);
    }

    /**
     * Increases away player's score by 1
     */
    public void AWAY_PLAYER_5_POINTSPLUS() {
        Calculations.score_calculations(1, false, 5, GameTool);
    }

    /**
     * Decreases away player's score by 1
     */
    public void AWAY_PLAYER_6_POINTSMINUS() {
        Calculations.score_calculations(-1, false, 6, GameTool);
    }

    /**
     * Increases away player's score by 1
     */
    public void AWAY_PLAYER_6_POINTSPLUS() {
        Calculations.score_calculations(1, false, 6, GameTool);
    }

    /**
     * Decreases away player's score by 1
     */
    public void AWAY_PLAYER_7_POINTSMINUS() {
        Calculations.score_calculations(-1, false, 7, GameTool);
    }

    /**
     * Increases away player's score by 1
     */
    public void AWAY_PLAYER_7_POINTSPLUS() {
        Calculations.score_calculations(1, false, 7, GameTool);
    }

    /**
     * Decreases away player's score by 1
     */
    public void AWAY_PLAYER_8_POINTSMINUS() {
        Calculations.score_calculations(-1, false, 8, GameTool);
    }

    /**
     * Increases away player's score by 1
     */
    public void AWAY_PLAYER_8_POINTSPLUS() {
        Calculations.score_calculations(1, false, 8, GameTool);
    }

    /**
     * Decreases away player's score by 1
     */
    public void AWAY_PLAYER_9_POINTSMINUS() {
        Calculations.score_calculations(-1, false, 9, GameTool);
    }

    /**
     * Increases away player's score by 1
     */
    public void AWAY_PLAYER_9_POINTSPLUS() {
        Calculations.score_calculations(1, false, 9, GameTool);
    }

    /**
     * Decreases away player's score by 1
     */
    public void AWAY_PLAYER_10_POINTSMINUS() {
        Calculations.score_calculations(-1, false, 1, GameTool);
    }

    /**
     * Increases away player's score by 1
     */
    public void AWAY_PLAYER_10_POINTSPLUS() {
        Calculations.score_calculations(1, false, 10, GameTool);
    }

    /**
     * Decreases away player's score by 1
     */
    public void AWAY_PLAYER_11_POINTSMINUS() {
        Calculations.score_calculations(-1, false, 1, GameTool);
    }

    /**
     * Increases away player's score by 1
     */
    public void AWAY_PLAYER_11_POINTSPLUS() {
        Calculations.score_calculations(1, false, 11, GameTool);
    }

    /**
     * Decreases away player's score by 1
     */
    public void AWAY_PLAYER_12_POINTSMINUS() {
        Calculations.score_calculations(-1, false, 1, GameTool);
    }

    /**
     * Increases away player's score by 1
     */
    public void AWAY_PLAYER_12_POINTSPLUS() {
        Calculations.score_calculations(1, false, 12, GameTool);
    }

    /**
     * Increases home player's fouls by 1
     */
    public void HOME_PLAYER_1_FOULSPLUS() {

        Calculations.foul_calculations(true, true, 1, GameTool);

    }

    /**
     * Decreases home player's fouls by 1
     */
    public void HOME_PLAYER_1_FOULSMINUS() {

        Calculations.foul_calculations(false, true, 1, GameTool);
    }

    /**
     * Increases home player's fouls by 1
     */
    public void HOME_PLAYER_2_FOULSPLUS() {

        Calculations.foul_calculations(true, true, 2, GameTool);
    }

    /**
     * Decreases home player's fouls by 1
     */
    public void HOME_PLAYER_2_FOULSMINUS() {

        Calculations.foul_calculations(false, true, 2, GameTool);
    }

    /**
     * Increases home player's fouls by 1
     */
    public void HOME_PLAYER_3_FOULSPLUS() {

        Calculations.foul_calculations(true, true, 3, GameTool);
    }

    /**
     * Decreases home player's fouls by 1
     */
    public void HOME_PLAYER_3_FOULSMINUS() {

        Calculations.foul_calculations(false, true, 3, GameTool);
    }

    /**
     * Increases home player's fouls by 1
     */
    public void HOME_PLAYER_4_FOULSPLUS() {

        Calculations.foul_calculations(true, true, 4, GameTool);
    }

    /**
     * Decreases home player's fouls by 1
     */
    public void HOME_PLAYER_4_FOULSMINUS() {

        Calculations.foul_calculations(false, true, 4, GameTool);
    }

    /**
     * Increases home player's fouls by 1
     */
    public void HOME_PLAYER_5_FOULSPLUS() {

        Calculations.foul_calculations(true, true, 5, GameTool);
    }

    /**
     * Decreases home player's fouls by 1
     */
    public void HOME_PLAYER_5_FOULSMINUS() {

        Calculations.foul_calculations(false, true, 5, GameTool);
    }

    /**
     * Increases home player's fouls by 1
     */
    public void HOME_PLAYER_6_FOULSPLUS() {

        Calculations.foul_calculations(true, true, 6, GameTool);
    }

    /**
     * Decreases home player's fouls by 1
     */
    public void HOME_PLAYER_6_FOULSMINUS() {

        Calculations.foul_calculations(false, true, 6, GameTool);
    }

    /**
     * Increases home player's fouls by 1
     */
    public void HOME_PLAYER_7_FOULSPLUS() {

        Calculations.foul_calculations(true, true, 7, GameTool);
    }

    /**
     * Decreases home player's fouls by 1
     */
    public void HOME_PLAYER_7_FOULSMINUS() {
        Calculations.foul_calculations(false, true, 7, GameTool);
    }

    /**
     * Increases home player's fouls by 1
     */
    public void HOME_PLAYER_8_FOULSPLUS() {

        Calculations.foul_calculations(true, true, 8, GameTool);
    }

    /**
     * Decreases home player's fouls by 1
     */
    public void HOME_PLAYER_8_FOULSMINUS() {

        Calculations.foul_calculations(false, true, 8, GameTool);
    }

    /**
     * Increases home player's fouls by 1
     */
    public void HOME_PLAYER_9_FOULSPLUS() {

        Calculations.foul_calculations(true, true, 9, GameTool);
    }

    /**
     * Decreases home player's fouls by 1
     */
    public void HOME_PLAYER_9_FOULSMINUS() {

        Calculations.foul_calculations(false, true, 9, GameTool);
    }

    /**
     * Increases home player's fouls by 1
     */
    public void HOME_PLAYER_10_FOULSPLUS() {

        Calculations.foul_calculations(true, true, 10, GameTool);
    }

    /**
     * Decreases home player's fouls by 1
     */
    public void HOME_PLAYER_10_FOULSMINUS() {

        Calculations.foul_calculations(false, true, 10, GameTool);
    }

    /**
     * Increases home player's fouls by 1
     */
    public void HOME_PLAYER_11_FOULSPLUS() {

        Calculations.foul_calculations(true, true, 11, GameTool);
    }

    /**
     * Decreases home player's fouls by 1
     */
    public void HOME_PLAYER_11_FOULSMINUS() {

        Calculations.foul_calculations(false, true, 11, GameTool);
    }

    /**
     * Increases home player's fouls by 1
     */
    public void HOME_PLAYER_12_FOULSPLUS() {

        Calculations.foul_calculations(true, true, 12, GameTool);
    }

    /**
     * Decreases home player's fouls by 1
     */
    public void HOME_PLAYER_12_FOULSMINUS() {

        Calculations.foul_calculations(false, true, 12, GameTool);
    }

    /**
     * Increases away player's fouls by 1
     */
    public void AWAY_PLAYER_1_FOULSPLUS() {

        Calculations.foul_calculations(true, false, 1, GameTool);
    }

    /**
     * Decreases away player's fouls by 1
     */
    public void AWAY_PLAYER_1_FOULSMINUS() {

        Calculations.foul_calculations(false, false, 1, GameTool);
    }

    /**
     * Increases away player's fouls by 1
     */
    public void AWAY_PLAYER_2_FOULSPLUS() {

        Calculations.foul_calculations(true, false, 2, GameTool);
    }

    /**
     * Decreases away player's fouls by 1
     */
    public void AWAY_PLAYER_2_FOULSMINUS() {

        Calculations.foul_calculations(false, false, 2, GameTool);
    }

    /**
     * Increases away player's fouls by 1
     */
    public void AWAY_PLAYER_3_FOULSPLUS() {

        Calculations.foul_calculations(true, false, 3, GameTool);
    }

    /**
     * Decreases away player's fouls by 1
     */
    public void AWAY_PLAYER_3_FOULSMINUS() {

        Calculations.foul_calculations(false, false, 3, GameTool);
    }

    /**
     * Increases away player's fouls by 1
     */
    public void AWAY_PLAYER_4_FOULSPLUS() {

        Calculations.foul_calculations(true, false, 4, GameTool);
    }

    /**
     * Decreases away player's fouls by 1
     */
    public void AWAY_PLAYER_4_FOULSMINUS() {

        Calculations.foul_calculations(false, false, 4, GameTool);
    }

    /**
     * Increases away player's fouls by 1
     */
    public void AWAY_PLAYER_5_FOULSPLUS() {

        Calculations.foul_calculations(true, false, 5, GameTool);
    }

    /**
     * Decreases away player's fouls by 1
     */
    public void AWAY_PLAYER_5_FOULSMINUS() {

        Calculations.foul_calculations(false, false, 5, GameTool);
    }

    /**
     * Increases away player's fouls by 1
     */
    public void AWAY_PLAYER_6_FOULSPLUS() {

        Calculations.foul_calculations(true, false, 6, GameTool);
    }

    /**
     * Decreases away player's fouls by 1
     */
    public void AWAY_PLAYER_6_FOULSMINUS() {

        Calculations.foul_calculations(false, false, 6, GameTool);
    }

    /**
     * Increases away player's fouls by 1
     */
    public void AWAY_PLAYER_7_FOULSPLUS() {

        Calculations.foul_calculations(true, false, 7, GameTool);
    }

    /**
     * Decreases away player's fouls by 1
     */
    public void AWAY_PLAYER_7_FOULSMINUS() {

        Calculations.foul_calculations(false, false, 7, GameTool);
    }

    /**
     * Increases away player's fouls by 1
     */
    public void AWAY_PLAYER_8_FOULSPLUS() {

        Calculations.foul_calculations(true, false, 8, GameTool);
    }

    /**
     * Decreases away player's fouls by 1
     */
    public void AWAY_PLAYER_8_FOULSMINUS() {

        Calculations.foul_calculations(false, false, 8, GameTool);
    }

    /**
     * Increases away player's fouls by 1
     */
    public void AWAY_PLAYER_9_FOULSPLUS() {

        Calculations.foul_calculations(true, false, 9, GameTool);
    }

    /**
     * Decreases away player's fouls by 1
     */
    public void AWAY_PLAYER_9_FOULSMINUS() {

        Calculations.foul_calculations(false, false, 9, GameTool);
    }

    /**
     * Increases away player's fouls by 1
     */
    public void AWAY_PLAYER_10_FOULSPLUS() {

        Calculations.foul_calculations(true, false, 10, GameTool);
    }

    /**
     * Decreases away player's fouls by 1
     */
    public void AWAY_PLAYER_10_FOULSMINUS() {

        Calculations.foul_calculations(false, false, 10, GameTool);
    }

    /**
     * Increases away player's fouls by 1
     */
    public void AWAY_PLAYER_11_FOULSPLUS() {

        Calculations.foul_calculations(true, false, 11, GameTool);
    }

    /**
     * Decreases away player's fouls by 1
     */
    public void AWAY_PLAYER_11_FOULSMINUS() {

        Calculations.foul_calculations(false, false, 11, GameTool);
    }

    /**
     * Increases away player's fouls by 1
     */
    public void AWAY_PLAYER_12_FOULSPLUS() {

        Calculations.foul_calculations(true, false, 12, GameTool);
    }

    /**
     * Decreases away player's fouls by 1
     */
    public void AWAY_PLAYER_12_FOULSMINUS() {

        Calculations.foul_calculations(false, false, 12, GameTool);
    }

    /**
     * Listens keyboard and launches correct method.
     *
     * @param event KEYEVENT: Typing on the keyboard.
     * @throws InterruptedException
     */
    public void KeyboardListener(KeyEvent event) throws InterruptedException {
        switch (event.getCode()) {
            case T:
                HOME_1_POINT();
                sound.playHorn2(GameTool.getSettings().getPelikellonSummeri());
                break;

            case Y:
                HOME_2_POINTS();
                break;
            case U:
                HOME_3_POINTS();
                break;
            case I:
                HOME_FOUL();
                break;
            case O:
                if (!isTimeoutAlready) {
                    isTimeoutAlready = true;
                    HOME_TIMEOUT();
                }
                break;
            case G:
                AWAY_1_POINT();
                break;
            case H:
                AWAY_2_POINTS();
                break;
            case J:
                AWAY_3_POINTS();
                break;
            case K:
                AWAY_FOUL();
                break;
            case L:
                if (!isTimeoutAlready) {
                    isTimeoutAlready = true;
                    AWAY_TIMEOUT();
                }
                break;
            case SPACE:
                if (!isStarted) {
                    START();
                    isStarted = true;
                } else {
                    PAUSE();
                }
                break;
            default:
        }
    }

    /**
     * Increases home team's score by 1 and adds one point to maker.
     */
    public void HOME_1_POINT() {
        // TO DO
        // SELVITÄ XTH_PLAYER CONTROLLERISSA
        Calculations.score_calculations(1, true, 7, GameTool);
    }

    /**
     * Increases home team's score by 2 and adds one point to maker.
     */
    public void HOME_2_POINTS() {
        // TO DO
        // SELVITÄ XTH_PLAYER CONTROLLERISSA
        Calculations.score_calculations(2, true, 8, GameTool);

    }

    /**
     * Increases home team's score by 3 and adds one point to maker.
     */
    public void HOME_3_POINTS() {
        // TO DO
        // SELVITÄ XTH_PLAYER CONTROLLERISSA
        Calculations.score_calculations(3, true, 9, GameTool);
    }

    /**
     * Increases away team's score by 1 and adds one point to maker.
     */
    public void AWAY_1_POINT() {
        // TO DO
        // SELVITÄ XTH_PLAYER CONTROLLERISSA
        Calculations.score_calculations(1, false, 10, GameTool);
    }

    /**
     * Increases away team's score by 2 and adds one point to maker.
     */
    public void AWAY_2_POINTS() {
        // TO DO
        // SELVITÄ XTH_PLAYER CONTROLLERISSA
        Calculations.score_calculations(2, false, 11, GameTool);
    }

    /**
     * Increases away team's score by 3 and adds one point to maker.
     */
    public void AWAY_3_POINTS() {
        // TO DO
        // SELVITÄ XTH_PLAYER CONTROLLERISSA
        Calculations.score_calculations(3, false, 1, GameTool);
    }

    /**
     * Adds one foul to home team's fouls if limit is not yet met. Also adds one
     * foul to player.
     */
    public void HOME_FOUL() {
        // TO DO
        // SELVITÄ XTH_PLAYER CONTROLLERISSA
        Calculations.foul_calculations(true, true, -1, GameTool);
    }

    /**
     * Decreases one foul to home team's fouls if limit is not yet met.
     */
    public void HOME_FOULMINUS() {
        // TO DO
        // SELVITÄ XTH_PLAYER CONTROLLERISSA
        Calculations.foul_calculations(false, true, -1, GameTool);
    }

    /**
     * Adds one foul to away team's fouls if limit is not yet met. Also adds one
     * foul to player.
     */
    public void AWAY_FOUL() {
        // TO DO
        // SELVITÄ XTH_PLAYER CONTROLLERISSA
        Calculations.foul_calculations(true, false, -1, GameTool);
    }

    /**
     * Decreases one foul to away team's fouls if limit is not yet met.
     */
    public void AWAY_FOULMINUS() {
        // TO DO
        // SELVITÄ XTH_PLAYER CONTROLLERISSA
        Calculations.foul_calculations(false, false, -1, GameTool);
    }

    /*
    Pauses or continues any clock running.
     */
    public void PAUSE() {
        if ("RUNNING".equals(timeline.getStatus().toString())) {
            timeline.pause();
        } else {
            if (onBreak) {
                GameTool.getBoard().nextTimePeriod();
                time_label_calculations();
                START();
                onBreak = false;
            }
            timeline.play();

        }
    }

    /**
     * Starts and creates the clock instance.
     */
    public void START() {

        long duration;
        long end;
        if (GameTool.getSettings().getKellonSuunta()) {
            GameTool.timeMillis.setValue(GameTool.getPeriodInfo().getSeconds() * 1000L);
            duration = GameTool.timeMillis.longValue();
            end = 0;
        } else {
            GameTool.timeMillis.setValue(0L);
            duration = GameTool.getPeriodInfo().getSeconds() * 1000L - GameTool.timeMillis.longValue();
            end = GameTool.getPeriodInfo().getSeconds() * 1000L;
        }

        time_label_calculations();
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(duration),
                        new KeyValue(GameTool.timeMillis, end)));
        timeline.playFromStart();
        timeline_ending_checks();
        TIME.textProperty().bind(generator.getTimeFormat(GameTool.timeMillis, !GameTool.getPeriodInfo().isBreak(), GameTool));
        startTimer();

    }

    /**
     * Checks if timeouts or team fouls should be reset. Also sets next
     * timePeriod to start manually if wanted.
     */
    private void timeline_ending_checks() {
        timeline.setOnFinished(e -> {
            if (GameTool.getPeriodInfo().resetTimeouts) {
                resetTimeous();
            }
            if (GameTool.getPeriodInfo().resetTeamFouls) {
                resetTeamFouls();
            }
            if (!GameTool.isNextPeriodBreak()) {
                GameTool.getBoard().nextTimePeriod();
                time_label_calculations();

                START();
            } else {
                onBreak = true;
            }

            if (GameTool.getPeriodInfo().isManual()) {
                timeline.pause();
            }
        });
    }

    /**
     * Sets Labels PERIOD and TIME to the correct values.
     */
    private void time_label_calculations() {
        // GameTool.timeMillis.setValue(1000L*GameTool.getPeriodInfo().getSeconds());
        GameTool.PERIOD.setValue(GameTool.getPeriodInfo().getLabel());
    }

    /**
     * Starts home team's timeout instantly.
     */
    public void HOME_TIMEOUT() {
        Calculations.alternate_timeouts(true, true, GameTool);
    }

    /**
     * Starts away team's timeout instantly.
     */
    public void AWAY_TIMEOUT() {
        Calculations.alternate_timeouts(true, false, GameTool);
    }

    /**
     * Decreases timeout count for away team
     */
    public void AWAY_TIMEOUTMINUS() {
        Calculations.alternate_timeouts(false, false, GameTool);
    }

    /**
     * Decreases timeout count for home team
     */
    public void HOME_TIMEOUTMINUS() {
        Calculations.alternate_timeouts(false, true, GameTool);
    }

    /**
     * Adds timeout marker and initiates timeout if its correct to do so.
     *
     * @param isHome BOOLEAN: Is team home or not.
     */
    private void timeout_calculations(boolean isHome) {
        String tmp = "●";
        int count;
        if (isHome) {
            count = GameTool.HOME_TIMEOUTS.getValue().length();
        } else {
            count = GameTool.AWAY_TIMEOUTS.getValue().length();
        }
        if (count < GameTool.getSettings().getAikalisaLkm()) {
            TIME.textProperty().bind(generator.getTimeFormat(GameTool.timeMillis, false, GameTool));
            if (isHome) {
                GameTool.HOME_TIMEOUTS.setValue(GameTool.HOME_TIMEOUTS.getValue() + tmp);
            } else {
                GameTool.AWAY_TIMEOUTS.setValue(GameTool.AWAY_TIMEOUTS.getValue() + tmp);
            }
            GameTool.setTmpDuration(timeline.getCurrentTime());
            start_timeout();
        }
    }

    /**
     * Creates new timeline for timeout and starts running it.
     */
    public void start_timeout() {

        long end = 0;
        if (GameTool.getSettings().getKellonSuunta()) {
            GameTool.timeMillis.setValue(1000L * GameTool.getSettings().getAikalisa());
            end = 0;
        } else {
            GameTool.timeMillis.setValue(0L);
            end = 1000L * GameTool.getSettings().getAikalisa();
        }

        isTimeoutAlready = true;
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000L * GameTool.getSettings().getAikalisa()),
                        new KeyValue(GameTool.timeMillis, end)));

        GameTool.PERIOD.setValue("TIMEOUT");
        timeline.playFromStart();

        timeline.setOnFinished(e -> {
            TIME.textProperty().bind(generator.getTimeFormat(GameTool.timeMillis, !GameTool.getPeriodInfo().isBreak(), GameTool));
            test();
            isTimeoutAlready = false;
            timeline_ending_checks();
        });
    }

    private void test() {

        long end = 0;
        if (GameTool.getSettings().getKellonSuunta()) {
            GameTool.timeMillis.setValue(1000L * (GameTool.getPeriodInfo().getSeconds() - GameTool.getTmpDuration().toSeconds()));
            end = 0;
        } else {
            GameTool.timeMillis.setValue(1000L * GameTool.getTmpDuration().toSeconds());
            end = 1000L * (GameTool.getPeriodInfo().getSeconds());
        }

        // GameTool.timeMillis.setValue(1000L*(GameTool.getTmpDuration().toSeconds()));
        //GameTool.timeMillis.setValue(1000L*(GameTool.getPeriodInfo().getSeconds() - GameTool.getTmpDuration().toSeconds()));
        System.out.println(GameTool.getTmpDuration());
        GameTool.PERIOD.setValue(GameTool.getPeriodInfo().getLabel());
        timeline = new Timeline();
        // timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000L * (GameTool.getPeriodInfo().getSeconds() - GameTool.getTmpDuration().toSeconds())),
                        new KeyValue(GameTool.timeMillis, end)));

    }

    /**
     * Reset both teams fouls.
     */
    private void resetTeamFouls() {
        GameTool.HOME_FOULS.setValue("0");
        GameTool.AWAY_FOULS.setValue("0");
    }

    /**
     * Reset both teams timeouts.
     */
    private void resetTimeous() {
        GameTool.AWAY_TIMEOUTS.setValue("");
        GameTool.HOME_TIMEOUTS.setValue("");
    }

    public void startTimer() {
        java.util.Timer t = new java.util.Timer();
        {
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        writeJsonGameState();
                    });

                }
            ;
            }
    ;  
t.scheduleAtFixedRate(tt, 10, 100);
        }
    }

    private void writeJsonGameState() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        GameState gameState = new GameState(GameTool);
        ArrayList<player> HomePlayerList = new ArrayList();
        ArrayList<player> AwayPlayerList = new ArrayList();
        playerList away = new playerList("Away", returnPlayers(AwayPlayerList, GameTool, false));
        playerList home = new playerList("Home", returnPlayers(HomePlayerList, GameTool, true));
        gameObject g = new gameObject("Game", gameState, home, away, settings);
        String game = gson.toJson(g);
        // System.out.println(g.awayPlayers.rooster);

        try {
            Writer writer = new FileWriter(FilePath);
            writer.write(game);
            writer.close();
        } catch (IOException ex) {

            Logger.getLogger(GameState.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    gameObject returnGameObject() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(FilePath));
        gameObject data = gson.fromJson(reader, gameObject.class);
        return data;
    }

    public ArrayList<player> returnPlayers(ArrayList<player> team, Game G, boolean isHome) {
        for (int n = 0; n < 12; n++) {
            player p = new player(G.getPlayerName(isHome, n), G.getPlayerNumber(isHome, n), G.getPlayerFouls(isHome, n), G.getPlayerPoints(isHome, n));

            team.add(p);
        }

        return team;

    }

    void setGamestateFromJson(gameObject g) {
        
        GameTool.getPeriodInfo().seconds = g.settings.getNeljanneksenPituus();
        GameTool.timeMillis.set(g.gameState.timePassed);
        settings = g.settings;
        for (int i = 0; i < g.homePlayers.rooster.size(); i++) {
            GameTool.setPlayerName(g.homePlayers.rooster.get(i).name, true, i);
            GameTool.setPlayerNumber(g.homePlayers.rooster.get(i).number, true, i);
            GameTool.setPlayerPoints(g.homePlayers.rooster.get(i).points, true, i);
            GameTool.setPlayerFouls(g.homePlayers.rooster.get(i).fouls, true, i);
        }

        for (int i = 0; i < g.awayPlayers.rooster.size(); i++) {
            GameTool.setPlayerName(g.awayPlayers.rooster.get(i).name, false, i);
            GameTool.setPlayerNumber(g.awayPlayers.rooster.get(i).number, false, i);
            GameTool.setPlayerPoints(g.awayPlayers.rooster.get(i).points, false, i);
            GameTool.setPlayerFouls(g.awayPlayers.rooster.get(i).fouls, false, i);
        }
        GameTool.setScore(g.gameState.score);
        GameTool.AWAY_FOULS.set(g.gameState.awayFouls);
        GameTool.HOME_FOULS.set(g.gameState.homeFouls);
        GameTool.AWAY_TIMEOUTS.set(g.gameState.awayTimeouts);
        GameTool.HOME_TIMEOUTS.set(g.gameState.homeTimeouts);
        GameTool.timeMillis.set(g.gameState.timePassed);
        GameTool.PERIOD.set(g.gameState.period);
        board.setPeriod(g.gameState.periodi);
        board.setTimeSeconds(g.settings.getNeljanneksenPituus());
        
    }

}

class gameObject {

    String name;
    Settings settings;
    GameState gameState;
    playerList homePlayers;
    playerList awayPlayers;

    gameObject(String n, GameState gamestate, playerList homePlayers, playerList awayPlayers, Settings s) {
        this.name = n;
        this.gameState = gamestate;
        this.homePlayers = homePlayers;
        this.awayPlayers = awayPlayers;
        this.settings = s;
    }

}

class playerList {

    String name;
    List<player> rooster;

    playerList(String name, List<player> rooster) {
        this.name = name;
        this.rooster = rooster;

    }

}
