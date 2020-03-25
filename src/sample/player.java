package sample;


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

    player(String name, String number) {
        this.name = name;
        this.number = number;
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










/*
public class Player {

    private Integer numero;
    private String nimi;
    private Integer virheet;
    private Integer pisteet;
    private String joukkue;

    public Player() {}

    public Player(Integer numero, String nimi,  String joukkue) {
        this.numero = numero;
        this.nimi = nimi;
        this.joukkue = joukkue;
    }

    public Player(Integer numero, String nimi, Integer virheet, Integer pisteet, String joukkue) {
        this.numero = numero;
        this.nimi = nimi;
        this.virheet = virheet;
        this.pisteet = pisteet;
        this.joukkue = joukkue;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Integer getVirheet() {
        return virheet;
    }

    public void setVirheet(Integer virheet) {
        this.virheet = virheet;
    }

    public Integer getPisteet() {
        return pisteet;
    }

    public void setPisteet(Integer pisteet) {
        this.pisteet = pisteet;
    }

    public String getJoukkue() {
        return joukkue;
    }

    public void setJoukkue(String joukkue) {
        this.joukkue = joukkue;
    }


}
 */

