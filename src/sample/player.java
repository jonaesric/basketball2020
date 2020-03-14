/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author joonass
 */
public class player {

    String name;
    String number;
    String fouls;
    String points;

    player(String name, String number, String fouls, String points) {
        this.name = name;
        this.number = number;
        this.fouls = fouls;
        this.points = points;

    }

    player returnPlayer(String name, String number, String fouls, String points) {
        player p = new player(name, number, fouls, points);

        return p;
    }

    public String returnFouls(boolean isHome, Game g, int val) {


            return g.getPlayerFouls(isHome, val);

        

    }

    public String returnPoints(boolean isHome, Game g, int val) {
        
            return g.getPlayerPoints(isHome, val);      
    }

    public String returnNumber(boolean isHome, Game g, int val) {   
            return g.getPlayerNumber(isHome, val);

    }



}

