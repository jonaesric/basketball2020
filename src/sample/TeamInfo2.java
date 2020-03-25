package sample;

public class TeamInfo2 {
    private boolean home;
    private String path;
    private String name;

    public TeamInfo2(boolean home, String path, String name) {
        this.home = home;
        this.path = path;
        this.name = name;
    }


    public boolean isHome() {
        return home;
    }

    public String getLogoPath() {
        return path;
    }

    public String getTeamName() {
        return name;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public void setLogoPath(String path) {
        this.path = path;
    }

    public void setTeamName(String name) {
        this.name = name;
    }
}
