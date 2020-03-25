package sample;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class TeamInfo{


    private boolean home;
    private String path;
    private String name;
    private static final String filepath = "./json/team_info.json";
    //private static final String FILEPATH_HOME = "./json/team_info_home.json";
    //private static final String FILEPATH_AWAY = "./json/team_info_away.json";

    /*
    public TeamInfo() {

    }
    */


    public TeamInfo(String nm, boolean ht, String fp){
        this.name = nm;
        this.home = ht;
        this.path = fp;
    }


    public String getName(){
        return name;
    }

    public boolean getHome(){
        return home;
    }

    public String getPath(){
        return path;
    }




    /**
     * Luo json tiedoston
     */
    private static void filePath(){
        TeamInfo[] paths = new TeamInfo[]{
            new TeamInfo("",true, ""),
            new TeamInfo("",false, "")
        };
        try(Writer writer = new FileWriter(filepath)){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(paths, writer);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Vain nimen asetukseen
     *
     * @param nm Joukkueen nimi
     * @param hT Kotijoukkue true/false
     */
    public static void filePath(String nm, Boolean hT){
        TeamInfo[] paths;
        if(getFile() != null){
            paths = getFile();
        }else{
            filePath();
            paths = getFile();
        }
        if(paths[0].getHome() == hT){
            paths[0].name = nm;
        }else if(paths[1].getHome() == hT){
            paths[1].name = nm;
        }
        try(Writer writer = new FileWriter(filepath)){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(paths, writer);
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    public static String getTName(Boolean hT){
        Gson gson = new Gson();
        TeamInfo[] paths;
        String name = null;
        try {
            paths = gson.fromJson(new FileReader(filepath), TeamInfo[].class);
            //System.out.println(logo1);
            if(paths[0].getHome() == hT){
                name = paths[0].getName();
            }else if(paths[1].getHome() == hT){
                name = paths[1].getName();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(logo1);
        return name;
    }



    /**
     * Logon filepathin asetus
     *
     * @param hT   Kotijoukkue true/false
     * @param dT    filepath
     */
    public static void filePath(Boolean hT, String dT){
        TeamInfo[] paths;
        if(getFile() != null){
            paths = getFile();
        }else{
            filePath();
            paths = getFile();
        }
        //TeamInfo newPath = new TeamInfo("",hT, dT);
        if(paths[0].getHome() == hT){
            paths[0].path = dT;
        }else if(paths[1].getHome() == hT){
            paths[1].path = dT;
        }
        try(Writer writer = new FileWriter(filepath)){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(paths, writer);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Lukee team_info.jsonin olioiden joukoksi
     *
     * @return TeamInfo olioiden joukko
     */
    private static TeamInfo[] getFile(){
        Gson gson = new Gson();
        TeamInfo[] paths = null;
        try {
            paths = gson.fromJson(new FileReader(filepath), TeamInfo[].class);
        } catch (IOException e) {
            System.out.println("Existing team_info.json not found, new created");
        }
        return paths;
    }


    public static File getFile(Boolean hT){
        Gson gson = new Gson();
        TeamInfo[] paths;
        File logo1 = null;
        try {
            paths = gson.fromJson(new FileReader(filepath), TeamInfo[].class);
            //System.out.println(logo1);
            if(paths[0].getHome() == hT){
                logo1 = new File(paths[0].getPath());
            }else if(paths[1].getHome() == hT){
                logo1 = new File(paths[1].getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(logo1);
        return logo1;
    }


}