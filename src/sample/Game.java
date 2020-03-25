package sample;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {

    //public IntegerProperty timeSeconds;
    public LongProperty timeMillis;
    public LongProperty test;
    private String[][] home_player_par;
    private String[][] away_player_par;


    public Game(Board board, Settings settings, String[][] home_player_par, String[][] away_player_par){
        this.home_player_par = home_player_par;
        this.away_player_par = away_player_par;
        //timeSeconds = new SimpleIntegerProperty(100);
        timeMillis = new SimpleLongProperty(100);
        this.settings = settings;
        this.board = board;

        home_player_list.add(hp1);
        home_player_list.add(hp2);
        home_player_list.add(hp3);
        home_player_list.add(hp4);
        home_player_list.add(hp5);
        home_player_list.add(hp6);
        home_player_list.add(hp7);
        home_player_list.add(hp8);
        home_player_list.add(hp9);
        home_player_list.add(hp10);
        home_player_list.add(hp11);
        home_player_list.add(hp12);
        away_player_list.add(ap1);
        away_player_list.add(ap2);
        away_player_list.add(ap3);
        away_player_list.add(ap4);
        away_player_list.add(ap5);
        away_player_list.add(ap6);
        away_player_list.add(ap7);
        away_player_list.add(ap8);
        away_player_list.add(ap9);
        away_player_list.add(ap10);
        away_player_list.add(ap11);
        away_player_list.add(ap12);

        for (int i = 0; i < 12; i = i + 1) {

            home_player_list.get(i)[0].setValue(home_player_par[i][0]);
            home_player_list.get(i)[1].setValue(home_player_par[i][1]);
            if("" != home_player_par[i][1]){
                home_player_list.get(i)[2].setValue("0");
            }
            home_player_list.get(i)[3].setValue("");
            home_player_list.get(i)[4].setValue("");

            away_player_list.get(i)[0].setValue(away_player_par[i][0]);
            away_player_list.get(i)[1].setValue(away_player_par[i][1]);
            if("" != away_player_par[i][1]){
                away_player_list.get(i)[2].setValue("0");
            }
            away_player_list.get(i)[3].setValue("");
            away_player_list.get(i)[4].setValue("");
        }

        Generator generator = new Generator();

        periodInfo = generator.getPeriodInfo(settings);

    }

    public Border getBorderVisible() {
        return borderVisible;
    }

    public void setBorderVisible(Border borderVisible) {
        this.borderVisible = borderVisible;
    }

    public Border getBorderInvisible() {
        return borderInvisible;
    }

    public void setBorderInvisible(Border borderInvisible) {
        this.borderInvisible = borderInvisible;
    }

    private Border borderVisible;
    private Border borderInvisible;
    private Duration tmp_time = null;

    private Settings settings;

    private Board board;

    private LinkedList<PeriodInfo> periodInfo;


    public StringProperty HOME_FOULS = new SimpleStringProperty("0");
    public StringProperty HOME_TIMEOUTS = new SimpleStringProperty("");
    public StringProperty AWAY_FOULS = new SimpleStringProperty("0");
    public StringProperty AWAY_TIMEOUTS = new SimpleStringProperty("");
    public StringProperty SCORE = new SimpleStringProperty("0-0");
    public StringProperty PERIOD = new SimpleStringProperty("WAITING");

    public StringProperty HOME_PLAYER_1_FOULS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_2_FOULS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_3_FOULS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_4_FOULS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_5_FOULS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_6_FOULS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_7_FOULS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_8_FOULS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_9_FOULS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_10_FOULS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_11_FOULS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_12_FOULS = new SimpleStringProperty("");

    public StringProperty HOME_PLAYER_1_RED_FOUL = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_2_RED_FOUL = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_3_RED_FOUL = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_4_RED_FOUL = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_5_RED_FOUL = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_6_RED_FOUL = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_7_RED_FOUL = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_8_RED_FOUL = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_9_RED_FOUL = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_10_RED_FOUL = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_11_RED_FOUL = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_12_RED_FOUL = new SimpleStringProperty("");

    public StringProperty HOME_PLAYER_1_POINTS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_2_POINTS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_3_POINTS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_4_POINTS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_5_POINTS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_6_POINTS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_7_POINTS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_8_POINTS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_9_POINTS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_10_POINTS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_11_POINTS = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_12_POINTS = new SimpleStringProperty("");

    public StringProperty HOME_PLAYER_1_NAME = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_2_NAME = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_3_NAME = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_4_NAME = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_5_NAME = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_6_NAME = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_7_NAME = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_8_NAME = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_9_NAME = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_10_NAME = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_11_NAME = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_12_NAME = new SimpleStringProperty("");

    public StringProperty HOME_PLAYER_1_NUMBER = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_2_NUMBER = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_3_NUMBER = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_4_NUMBER = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_5_NUMBER = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_6_NUMBER = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_7_NUMBER = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_8_NUMBER = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_9_NUMBER = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_10_NUMBER = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_11_NUMBER = new SimpleStringProperty("");
    public StringProperty HOME_PLAYER_12_NUMBER = new SimpleStringProperty("");

    public StringProperty AWAY_PLAYER_1_POINTS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_2_POINTS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_3_POINTS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_4_POINTS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_5_POINTS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_6_POINTS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_7_POINTS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_8_POINTS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_9_POINTS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_10_POINTS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_11_POINTS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_12_POINTS = new SimpleStringProperty("");

    public StringProperty AWAY_PLAYER_1_FOULS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_2_FOULS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_3_FOULS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_4_FOULS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_5_FOULS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_6_FOULS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_7_FOULS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_8_FOULS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_9_FOULS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_10_FOULS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_11_FOULS = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_12_FOULS = new SimpleStringProperty("");

    public StringProperty AWAY_PLAYER_1_RED_FOUL = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_2_RED_FOUL = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_3_RED_FOUL = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_4_RED_FOUL = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_5_RED_FOUL = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_6_RED_FOUL = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_7_RED_FOUL = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_8_RED_FOUL = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_9_RED_FOUL = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_10_RED_FOUL = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_11_RED_FOUL = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_12_RED_FOUL = new SimpleStringProperty("");

    public StringProperty AWAY_PLAYER_1_NAME = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_2_NAME = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_3_NAME = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_4_NAME = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_5_NAME = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_6_NAME = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_7_NAME = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_8_NAME = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_9_NAME = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_10_NAME = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_11_NAME = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_12_NAME = new SimpleStringProperty("");

    public StringProperty AWAY_PLAYER_1_NUMBER = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_2_NUMBER = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_3_NUMBER = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_4_NUMBER = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_5_NUMBER = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_6_NUMBER = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_7_NUMBER = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_8_NUMBER = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_9_NUMBER = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_10_NUMBER = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_11_NUMBER = new SimpleStringProperty("");
    public StringProperty AWAY_PLAYER_12_NUMBER = new SimpleStringProperty("");

    public LinkedList<StringProperty[]> home_player_list = new LinkedList<>();
    public LinkedList<StringProperty[]> away_player_list = new LinkedList<>();

    StringProperty[] hp1 = new StringProperty[]{HOME_PLAYER_1_NUMBER, HOME_PLAYER_1_NAME, HOME_PLAYER_1_POINTS, HOME_PLAYER_1_FOULS, HOME_PLAYER_1_RED_FOUL};
    StringProperty[] hp2 = new StringProperty[]{HOME_PLAYER_2_NUMBER, HOME_PLAYER_2_NAME, HOME_PLAYER_2_POINTS, HOME_PLAYER_2_FOULS, HOME_PLAYER_2_RED_FOUL};
    StringProperty[] hp3 = new StringProperty[]{HOME_PLAYER_3_NUMBER, HOME_PLAYER_3_NAME, HOME_PLAYER_3_POINTS, HOME_PLAYER_3_FOULS, HOME_PLAYER_3_RED_FOUL};
    StringProperty[] hp4 = new StringProperty[]{HOME_PLAYER_4_NUMBER, HOME_PLAYER_4_NAME, HOME_PLAYER_4_POINTS, HOME_PLAYER_4_FOULS, HOME_PLAYER_4_RED_FOUL};
    StringProperty[] hp5 = new StringProperty[]{HOME_PLAYER_5_NUMBER, HOME_PLAYER_5_NAME, HOME_PLAYER_5_POINTS, HOME_PLAYER_5_FOULS, HOME_PLAYER_5_RED_FOUL};
    StringProperty[] hp6 = new StringProperty[]{HOME_PLAYER_6_NUMBER, HOME_PLAYER_6_NAME, HOME_PLAYER_6_POINTS, HOME_PLAYER_6_FOULS, HOME_PLAYER_6_RED_FOUL};
    StringProperty[] hp7 = new StringProperty[]{HOME_PLAYER_7_NUMBER, HOME_PLAYER_7_NAME, HOME_PLAYER_7_POINTS, HOME_PLAYER_7_FOULS, HOME_PLAYER_7_RED_FOUL};
    StringProperty[] hp8 = new StringProperty[]{HOME_PLAYER_8_NUMBER, HOME_PLAYER_8_NAME, HOME_PLAYER_8_POINTS, HOME_PLAYER_8_FOULS, HOME_PLAYER_8_RED_FOUL};
    StringProperty[] hp9 = new StringProperty[]{HOME_PLAYER_9_NUMBER, HOME_PLAYER_9_NAME, HOME_PLAYER_9_POINTS, HOME_PLAYER_9_FOULS, HOME_PLAYER_9_RED_FOUL};
    StringProperty[] hp10 = new StringProperty[]{HOME_PLAYER_10_NUMBER, HOME_PLAYER_10_NAME, HOME_PLAYER_10_POINTS, HOME_PLAYER_10_FOULS, HOME_PLAYER_10_RED_FOUL};
    StringProperty[] hp11 = new StringProperty[]{HOME_PLAYER_11_NUMBER, HOME_PLAYER_11_NAME, HOME_PLAYER_11_POINTS, HOME_PLAYER_11_FOULS, HOME_PLAYER_11_RED_FOUL};
    StringProperty[] hp12 = new StringProperty[]{HOME_PLAYER_12_NUMBER, HOME_PLAYER_12_NAME, HOME_PLAYER_12_POINTS, HOME_PLAYER_12_FOULS, HOME_PLAYER_12_RED_FOUL};

    StringProperty[] ap1 = new StringProperty[]{AWAY_PLAYER_1_NUMBER, AWAY_PLAYER_1_NAME, AWAY_PLAYER_1_POINTS, AWAY_PLAYER_1_FOULS, AWAY_PLAYER_1_RED_FOUL};
    StringProperty[] ap2 = new StringProperty[]{AWAY_PLAYER_2_NUMBER, AWAY_PLAYER_2_NAME, AWAY_PLAYER_2_POINTS, AWAY_PLAYER_2_FOULS, AWAY_PLAYER_2_RED_FOUL};
    StringProperty[] ap3 = new StringProperty[]{AWAY_PLAYER_3_NUMBER, AWAY_PLAYER_3_NAME, AWAY_PLAYER_3_POINTS, AWAY_PLAYER_3_FOULS, AWAY_PLAYER_3_RED_FOUL};
    StringProperty[] ap4 = new StringProperty[]{AWAY_PLAYER_4_NUMBER, AWAY_PLAYER_4_NAME, AWAY_PLAYER_4_POINTS, AWAY_PLAYER_4_FOULS, AWAY_PLAYER_4_RED_FOUL};
    StringProperty[] ap5 = new StringProperty[]{AWAY_PLAYER_5_NUMBER, AWAY_PLAYER_5_NAME, AWAY_PLAYER_5_POINTS, AWAY_PLAYER_5_FOULS, AWAY_PLAYER_5_RED_FOUL};
    StringProperty[] ap6 = new StringProperty[]{AWAY_PLAYER_6_NUMBER, AWAY_PLAYER_6_NAME, AWAY_PLAYER_6_POINTS, AWAY_PLAYER_6_FOULS, AWAY_PLAYER_6_RED_FOUL};
    StringProperty[] ap7 = new StringProperty[]{AWAY_PLAYER_7_NUMBER, AWAY_PLAYER_7_NAME, AWAY_PLAYER_7_POINTS, AWAY_PLAYER_7_FOULS, AWAY_PLAYER_7_RED_FOUL};
    StringProperty[] ap8 = new StringProperty[]{AWAY_PLAYER_8_NUMBER, AWAY_PLAYER_8_NAME, AWAY_PLAYER_8_POINTS, AWAY_PLAYER_8_FOULS, AWAY_PLAYER_8_RED_FOUL};
    StringProperty[] ap9 = new StringProperty[]{AWAY_PLAYER_9_NUMBER, AWAY_PLAYER_9_NAME, AWAY_PLAYER_9_POINTS, AWAY_PLAYER_9_FOULS, AWAY_PLAYER_9_RED_FOUL};
    StringProperty[] ap10 = new StringProperty[]{AWAY_PLAYER_10_NUMBER, AWAY_PLAYER_10_NAME, AWAY_PLAYER_10_POINTS, AWAY_PLAYER_10_FOULS, AWAY_PLAYER_10_RED_FOUL};
    StringProperty[] ap11 = new StringProperty[]{AWAY_PLAYER_11_NUMBER, AWAY_PLAYER_11_NAME, AWAY_PLAYER_11_POINTS, AWAY_PLAYER_11_FOULS, AWAY_PLAYER_11_RED_FOUL};
    StringProperty[] ap12 = new StringProperty[]{AWAY_PLAYER_12_NUMBER, AWAY_PLAYER_12_NAME,AWAY_PLAYER_12_POINTS, AWAY_PLAYER_12_FOULS, AWAY_PLAYER_12_RED_FOUL};




    public void reset() {
        for (int i = 0; i < 12; i = i + 1) {

            home_player_list.get(i)[0].setValue(home_player_par[i][0]);
            home_player_list.get(i)[1].setValue(home_player_par[i][1]);
            if("" != home_player_par[i][1]){
                home_player_list.get(i)[2].setValue("0");
            }
            home_player_list.get(i)[3].setValue("");
            home_player_list.get(i)[4].setValue("");

            away_player_list.get(i)[0].setValue(away_player_par[i][0]);
            away_player_list.get(i)[1].setValue(away_player_par[i][1]);
            if("" != away_player_par[i][1]){
                away_player_list.get(i)[2].setValue("0");
            }
            away_player_list.get(i)[3].setValue("");
            away_player_list.get(i)[4].setValue("");
        }
        board.reset();

        HOME_FOULS.setValue("0");
        HOME_TIMEOUTS.setValue("");
        AWAY_FOULS.setValue("0");
        AWAY_TIMEOUTS.setValue("");
        SCORE.setValue("0-0");
        PERIOD.setValue("WAITING");

    }




    public String getPlayerNumber(boolean isHome, int i) {
        if(isHome){
            return home_player_list.get(i)[0].getValue();
        } else {
            return away_player_list.get(i)[0].getValue();
        }
    }

    public void setPlayerNumber(String number, boolean isHome, int i) {
        if(isHome) {
            home_player_list.get(i)[0].setValue(number);
        } else {
            away_player_list.get(i)[0].setValue(number);
        }
    }

    public String getPlayerName(boolean isHome, int i) {
        if(isHome){
            return home_player_list.get(i)[1].getValue();
        } else {
            return away_player_list.get(i)[1].getValue();
        }
    }

    public void setPlayerName(String name, boolean isHome, int i) {
        if(isHome) {
            home_player_list.get(i)[1].setValue(name);
        } else {
            away_player_list.get(i)[1].setValue(name);
        }
    }

    public String getPlayerPoints(boolean isHome, int i) {
        if(isHome){
            return home_player_list.get(i)[2].getValue();
        } else {
            return away_player_list.get(i)[2].getValue();
        }
    }

    public void setPlayerPoints(String points, boolean isHome, int i){
        if(isHome) {
            home_player_list.get(i)[2].setValue(points);
        } else {
            away_player_list.get(i)[2].setValue(points);
        }
    }

    public String getPlayerFouls(boolean isHome, int i) {
        if(isHome){
            return home_player_list.get(i)[3].getValue();
        } else {
            return away_player_list.get(i)[3].getValue();
        }
    }

    public void setPlayerFouls(String fouls, boolean isHome, int i){
        if(isHome){
            home_player_list.get(i)[3].setValue(fouls);
        } else {
            away_player_list.get(i)[3].setValue(fouls);
        }
    }

    public String getPlayerRedFoul(boolean isHome, int i) {
        if(isHome){
            return home_player_list.get(i)[4].getValue();
        } else {
            return away_player_list.get(i)[4].getValue();
        }
    }

    public void setPlayerRedFoul(String redFoul, boolean isHome, int i) {
        if(isHome){
            home_player_list.get(i)[4].setValue(redFoul);
        } else {
            away_player_list.get(i)[4].setValue(redFoul);
        }
    }

    public boolean isNextPeriodBreak() {
        if(periodInfo.get(board.getPeriod() + 1).isBreak()){
            return true;
        } else {
            return false;
        }
    }

    public void setTmpDuration(Duration X) {
        if(null == tmp_time) {
            tmp_time = X;
        } else {
            System.out.println("before total: " + tmp_time.toSeconds());
            tmp_time = tmp_time.add(X);
            System.out.println("time added: " + X.toSeconds());
            System.out.println("time total: " + tmp_time.toSeconds());

        }
    }

    public Duration getTmpDuration() {
        return tmp_time;
    }

    public Settings getSettings(){
        return settings;
    }

    public Board getBoard(){
        return board;
    }

    public PeriodInfo getPeriodInfo(){
        return periodInfo.get(board.getPeriod());
    }

    public String getScore() {
        return SCORE.toString();
    }

    public String getTeamFouls(boolean isHome) {
        if(isHome){
            return HOME_FOULS.getValue();
        } else {
            return AWAY_FOULS.getValue();
        }
    }

    public String getTeamTimeouts(boolean isHome) {
        if(isHome){
            return HOME_TIMEOUTS.getValue();
        } else {
            return AWAY_TIMEOUTS.getValue();
        }
    }

    public void setScore(String s) {
        this.SCORE.setValue(s);
    }

}