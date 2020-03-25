package sample;

public class SettingsString {

    private String neljanneksenPituus;
    private String neljannesMaara;
    private String jatkoeraPituus;
    private String lyhytTauko;
    private String pitkaTauko;
    private String aikalisa;
    private String aikalisaLkm;
    private String hyokkausAika1;
    private String hyokkausAika2;
    private String virheetLkm;
    private String pelikellonSummeri;
    private String heittokellonSummeri;
    private boolean kellonSuunta; // true = down | false = up
    private String waitingBeforeStart; // seconds

    public SettingsString() {}

    public SettingsString(String neljanneksenPituus, String neljannesMaara, String jatkoeraPituus, String lyhytTauko,
                    String pitkaTauko, String aikalisa, String aikalisaLkm, String hyokkausAika1, String hyokkausAika2,
                    String virheetLkm, String pelikellonSummeri, String heittokellonSummeri, boolean kellonSuunta,
                    String waitingBeforeStart) {
        this.neljanneksenPituus = neljanneksenPituus;
        this.neljannesMaara = neljannesMaara;
        this.jatkoeraPituus = jatkoeraPituus;
        this.lyhytTauko = lyhytTauko;
        this.pitkaTauko = pitkaTauko;
        this.aikalisa = aikalisa;
        this.aikalisaLkm = aikalisaLkm;
        this.hyokkausAika1 = hyokkausAika1;
        this.hyokkausAika2 = hyokkausAika2;
        this.virheetLkm = virheetLkm;
        this.pelikellonSummeri = pelikellonSummeri;
        this.heittokellonSummeri = heittokellonSummeri;
        this.kellonSuunta = kellonSuunta;
        this.waitingBeforeStart = waitingBeforeStart;
    }

    public String getNeljanneksenPituus() {
        return neljanneksenPituus;
    }

    public void setNeljanneksenPituus(String neljanneksenPituus) {
        this.neljanneksenPituus = neljanneksenPituus;
    }

    public String getNeljannesMaara() {
        return neljannesMaara;
    }

    public void setNeljannesMaara(String neljannesMaara) {
        this.neljannesMaara = neljannesMaara;
    }

    public String getJatkoeraPituus() {
        return jatkoeraPituus;
    }

    public void setJatkoeraPituus(String jatkoeraPituus) {
        this.jatkoeraPituus = jatkoeraPituus;
    }

    public String getLyhytTauko() {
        return lyhytTauko;
    }

    public void setLyhytTauko(String lyhytTauko) {
        this.lyhytTauko = lyhytTauko;
    }

    public String getPitkaTauko() {
        return pitkaTauko;
    }

    public void setPitkaTauko(String pitkaTauko) {
        this.pitkaTauko = pitkaTauko;
    }

    public String getAikalisa() {
        return aikalisa;
    }

    public void setAikalisa(String aikalisa) {
        this.aikalisa = aikalisa;
    }

    public String getAikalisaLkm() {
        return aikalisaLkm;
    }

    public void setAikalisaLkm(String aikalisaLkm) {
        this.aikalisaLkm = aikalisaLkm;
    }

    public String getHyokkausAika1() {
        return hyokkausAika1;
    }

    public void setHyokkausAika1(String hyokkausAika1) {
        this.hyokkausAika1 = hyokkausAika1;
    }

    public String getHyokkausAika2() {
        return hyokkausAika2;
    }

    public void setHyokkausAika2(String hyokkausAika2) {
        this.hyokkausAika2 = hyokkausAika2;
    }

    public String getVirheetLkm() {
        return virheetLkm;
    }

    public void setVirheetLkm(String virheetLkm) {
        this.virheetLkm = virheetLkm;
    }

    public String getPelikellonSummeri() {
        return pelikellonSummeri;
    }

    public void setPelikellonSummeri(String pelikellonSummeri) {
        this.pelikellonSummeri = pelikellonSummeri;
    }

    public String getHeittokellonSummeri() {
        return heittokellonSummeri;
    }

    public void setHeittokellonSummeri(String heittokellonSummeri) {
        this.heittokellonSummeri = heittokellonSummeri;
    }

    public boolean getKellonSuunta() {
        return kellonSuunta;
    }

    public void setKellonSuunta(boolean kellonSuunta) {
        this.kellonSuunta = kellonSuunta;
    }

    public String getWaitingBeforeStart() { return waitingBeforeStart; }

    public void setWaitingBeforeStart(String waitingBeforeStart) { this.waitingBeforeStart = waitingBeforeStart; }
}
