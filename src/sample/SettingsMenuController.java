package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;

public class SettingsMenuController {

    /**
     * TextFields and quantities
     */

    private final String neljannesMaaraString = "neljannesMaaraTxt";
    private final String aikalisaLkmString = "aikalisaLkmTxt";
    private final String virheetLkmString = "virheetLkmTxt";
    private final String neljannesLkmExclusive = "5";
    private final String aikalisaLkmExclusive = "4";
    private final String virheetLkmExclusive = "6";
    private final String FILEPATH = "./json/settings.json";
    @FXML
    private TextField neljanneksenPituusTxt;
    @FXML
    private TextField neljannesMaaraTxt;
    @FXML
    private TextField jatkoeraPituusTxt;
    @FXML
    private TextField lyhytTaukoTxt;
    @FXML
    private TextField pitkaTaukoTxt;
    @FXML
    private TextField aikalisaTxt;
    @FXML
    private TextField aikalisaLkmTxt;
    @FXML
    private TextField hyokkausAika1Txt;
    @FXML
    private TextField hyokkausAika2Txt;
    @FXML
    private TextField virheetLkmTxt;
    @FXML
    private TextField pelikellonSummeriTxt;
    @FXML
    private TextField heittokellonSummeriTxt;
    @FXML
    private TextField waitingBeforeStartTxt;
    @FXML
    private ToggleGroup kellonKayntisuuntaGrp;
    @FXML
    private RadioButton kelloAlasRdoBtn;
    @FXML
    private RadioButton kelloYlosRdoBtn;
    @FXML
    private Label muutoksetTallennettuLbl;
    @FXML
    private AnchorPane settingMenuAP;
    @FXML
    private GridPane settingsTxtFldGrid;



    // Getters for the setting text fields

    public TextField getNeljanneksenPituusTxt() {
        return neljanneksenPituusTxt;
    }

    public TextField getNeljannesMaaraTxt() {
        return neljannesMaaraTxt;
    }

    public TextField getJatkoeraPituusTxt() {
        return jatkoeraPituusTxt;
    }

    public TextField getLyhytTaukoTxt() {
        return lyhytTaukoTxt;
    }

    public TextField getPitkaTaukoTxt() {
        return pitkaTaukoTxt;
    }

    public TextField getAikalisaTxt() {
        return aikalisaTxt;
    }

    public TextField getAikalisaLkmTxt() {
        return aikalisaLkmTxt;
    }

    public TextField getHyokkausAika1Txt() {
        return hyokkausAika1Txt;
    }

    public TextField getHyokkausAika2Txt() {
        return hyokkausAika2Txt;
    }

    public TextField getVirheetLkmTxt() {
        return virheetLkmTxt;
    }

    public TextField getPelikellonSummeriTxt() {
        return pelikellonSummeriTxt;
    }

    public TextField getHeittokellonSummeriTxt() { return heittokellonSummeriTxt; }

    public TextField getWaitingBeforeStartTxt() {
        return waitingBeforeStartTxt;
    }



    /**
     * When this method is called, it will change the scene to
     * a MainMenu
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent scoreboardMenuParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scoreboardMenuScene = new Scene(scoreboardMenuParent);

        // Get the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scoreboardMenuScene);
        settingMenuAP.requestFocus();
        window.show();

    }


    /**
     * Checks that the time fields get only appropriate input (mm:ss)
     * @param event
     */

    @FXML
    private void timeTextFieldInputValidator(MouseEvent event) {

        TextField textField = (TextField) event.getSource();

        textField.setTextFormatter(new TextFormatter<String>(change -> {

            if (change.getText().matches("\\d+|\\b*")) {

                final int oldLength = change.getControlText().length();
                int newLength = change.getControlNewText().length();

                // Handle backspace
                if (newLength < oldLength) return change;

                // Add : after 2 numbers
                // Do not accept more than 4 numbers and one : character
                switch (newLength) {
                    case 2:
                        change.setText(change.getText() + ":");
                        newLength++;
                        break;
                    case 6:
                        return null;
                }
                // Set caret position
                change.setCaretPosition(newLength);
                change.setAnchor(newLength);
                return change;
            }
            return null;
        }));
    }

    /**
     * Resets the settings to defaults
     * @param event
     */

    @FXML
    private void setFactorySettings(ActionEvent event) {

        // Get all the text fields inside gridpane
        ObservableList<Node> childrens = settingsTxtFldGrid.getChildren();

        // Loop every text field and set the factory settings
        for(Node node : childrens) {
            System.out.println(node.getId());
            if (node instanceof TextField) {
                // clear the fields
                ((TextField) node).clear();

                // Set the factory settings matching the text field on concurrent iteration
                ((TextField) node).setTextFormatter(new TextFormatter<String>(change -> {

                    if (getNeljanneksenPituusTxt().equals(node)) {
                        change.setText("10:00");
                    }
                    else if (getNeljannesMaaraTxt().equals(node)) {
                        change.setText("4");
                    }
                    else if (getJatkoeraPituusTxt().equals(node)) {
                        change.setText("05:00");
                    }
                    else if (getLyhytTaukoTxt().equals(node)) {
                        change.setText("02:00");
                    }
                    else if (getPitkaTaukoTxt().equals(node)) {
                        change.setText("15:00");
                    }
                    else if (getAikalisaTxt().equals(node)) {
                        change.setText("60");
                    }
                    else if (getAikalisaLkmTxt().equals(node)) {
                        change.setText("3");
                    }
                    else if (getHyokkausAika1Txt().equals(node)) {
                        change.setText("24");
                    }
                    else if (getHyokkausAika2Txt().equals(node)) {
                        change.setText("12");
                    }
                    else if (getVirheetLkmTxt().equals(node)) {
                        change.setText("5");
                    }
                    else if (getPelikellonSummeriTxt().equals(node)) {
                        change.setText("2");
                    }
                    else if (getHeittokellonSummeriTxt().equals(node)) {
                        change.setText("2");
                    }
                    else if (getWaitingBeforeStartTxt().equals(node)) {
                        change.setText("10");
                    }

                    int newLength = change.getControlNewText().length();
                    change.setCaretPosition(newLength);
                    change.setAnchor(newLength);

                    // Check the lengths
                    if (getNeljanneksenPituusTxt().equals(node) && newLength > 5) {
                        return null;
                    }
                    else if (getNeljannesMaaraTxt().equals(node) && newLength > 1) {
                        return null;
                    }
                    else if (getJatkoeraPituusTxt().equals(node) && newLength > 5) {
                        return null;
                    }
                    else if (getLyhytTaukoTxt().equals(node) && newLength > 5) {
                        return null;
                    }
                    else if (getPitkaTaukoTxt().equals(node) && newLength > 5) {
                        return null;
                    }
                    else if (getAikalisaTxt().equals(node) && newLength > 2) {
                        return null;
                    }
                    else if (getAikalisaLkmTxt().equals(node) && newLength > 1) {
                        return null;
                    }
                    else if (getHyokkausAika1Txt().equals(node) && newLength > 2) {
                        return null;
                    }
                    else if (getHyokkausAika2Txt().equals(node) && newLength > 2) {
                        return null;
                    }
                    else if (getVirheetLkmTxt().equals(node) && newLength > 1) {
                        return null;
                    }
                    else if (getPelikellonSummeriTxt().equals(node) && newLength > 1) {
                        return null;
                    }
                    else if (getHeittokellonSummeriTxt().equals(node) && newLength > 1) {
                        return null;
                    }
                    else if (getWaitingBeforeStartTxt().equals(node) && newLength > 2) {
                        return null;
                    }

                    return change;
                }));
            }
        }

        // Resets the clock running type
        resetClockRunningType();

        // Requests the focus to the anchor pane (bg frame) so the changes apply
        // Acts like 'a refresh'
        settingMenuAP.requestFocus();

    }



    /**
     * Checks that the time fields get only appropriate input (quantity)
     * @param event
     */

    @FXML
    private void quantityTextFieldInputValidator(MouseEvent event) {
        TextField textField = (TextField) event.getSource();
        String textFieldId = textField.getId();
        textField.setTextFormatter(new TextFormatter<String>(change -> {

            if (change.getText().matches("\\d+|\\b*")) {

                final int oldLength = change.getControlText().length();
                int newLength = change.getControlNewText().length();

                // Handle backspace
                if (newLength < oldLength) return change;
                // Handle input length
                if (newLength > 1) return null;
                // Handle input size depending on the field
                if (textFieldId.equals(neljannesMaaraString) && !(change.getText().compareTo(neljannesLkmExclusive) < 0)
                        || (change.getText().compareTo("1")) < 0) {
                    return null;
                }
                else if (textFieldId.equals(aikalisaLkmString) && !(change.getText().compareTo(aikalisaLkmExclusive) < 0)
                        || (change.getText().compareTo("1")) < 0) {
                    return null;
                }
                else if (textFieldId.equals(virheetLkmString) && !(change.getText().compareTo(virheetLkmExclusive) < 0)
                        || (change.getText().compareTo("1")) < 0) {
                    return null;
                }

                // Set caret position
                change.setCaretPosition(newLength);
                change.setAnchor(newLength);
                return change;
            }
            return null;
        }));
    }


    /**
     * Checks that the time fields get only appropriate input (ss)
     * @param event
     */

    @FXML
    private void timeSecondsTextFieldInputValidator(MouseEvent event) {
        TextField textField = (TextField) event.getSource();

        textField.setTextFormatter(new TextFormatter<String>(change -> {

            if (change.getText().matches("\\d+|\\b*")) {

                final int oldLength = change.getControlText().length();
                int newLength = change.getControlNewText().length();

                // Handle backspace
                if (newLength < oldLength) return change;
                // Handle input length
                if (newLength > 2) return null;
                // Set caret position
                change.setCaretPosition(newLength);
                change.setAnchor(newLength);
                return change;
            }
            return null;
        }));
    }


    /**
     * Resets the clock running type to up
     */

    @FXML
    private void resetClockRunningType() {
        kelloAlasRdoBtn.setToggleGroup(kellonKayntisuuntaGrp);
        kelloAlasRdoBtn.setSelected(true);
        kelloYlosRdoBtn.setToggleGroup(kellonKayntisuuntaGrp);
    }

    /**
     * Previously set clock running type
     */

    @FXML
    private void previouslySetClockRunningType(SettingsString settings) {
        if (settings.getKellonSuunta()) {
            kelloAlasRdoBtn.setToggleGroup(kellonKayntisuuntaGrp);
            kelloAlasRdoBtn.setSelected(true);
            kelloYlosRdoBtn.setToggleGroup(kellonKayntisuuntaGrp);
        } else {
            kelloYlosRdoBtn.setToggleGroup(kellonKayntisuuntaGrp);
            kelloYlosRdoBtn.setSelected(true);
            kelloAlasRdoBtn.setToggleGroup(kellonKayntisuuntaGrp);
        }

    }


    @FXML
    private void saveSettings(ActionEvent event) throws IOException {

        // Sets the clock running type, true = down | false = up
        boolean kellonKayntisuunta;
        if (kellonKayntisuuntaGrp.getSelectedToggle().toString().contains("'Alas'"))
            kellonKayntisuunta = true;
        else
            kellonKayntisuunta = false;

        // Creates new settings object
        SettingsString settings = new SettingsString(neljanneksenPituusTxt.getText(),
                                         neljannesMaaraTxt.getText(),
                                         jatkoeraPituusTxt.getText(),
                                         lyhytTaukoTxt.getText(),
                                         pitkaTaukoTxt.getText(),
                                         aikalisaTxt.getText(),
                                         aikalisaLkmTxt.getText(),
                                         hyokkausAika1Txt.getText(),
                                         hyokkausAika2Txt.getText(),
                                         virheetLkmTxt.getText(),
                                         pelikellonSummeriTxt.getText(),
                                         heittokellonSummeriTxt.getText(),
                                         kellonKayntisuunta,
                                         waitingBeforeStartTxt.getText()
                                        );

        // Create a json file and write to it
        try (Writer writer = new FileWriter(FILEPATH)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(settings, writer);
            writer.close();
        }

        // Shows the label for settings saved
        showChangesAppliedLbl();

        // Debug
        System.out.println("Settings saved and JSON created!");
    }


    @FXML
    public void setOldSettings() {
        // Debug
        System.out.println("Settings loaded!");

        // Set the text field values
        Gson gson = new Gson();
        try (Reader reader = new FileReader(FILEPATH)) {

            // Convert JSON File to Java Object
            SettingsString oldSettings = gson.fromJson(reader, SettingsString.class);

            // Set the text field values
            neljanneksenPituusTxt.setText(oldSettings.getNeljanneksenPituus());
            neljannesMaaraTxt.setText(oldSettings.getNeljannesMaara());
            jatkoeraPituusTxt.setText(oldSettings.getJatkoeraPituus());
            lyhytTaukoTxt.setText(oldSettings.getLyhytTauko());
            pitkaTaukoTxt.setText(oldSettings.getPitkaTauko());
            aikalisaTxt.setText(oldSettings.getAikalisa());
            aikalisaLkmTxt.setText(oldSettings.getAikalisaLkm());
            hyokkausAika1Txt.setText(oldSettings.getHyokkausAika1());
            hyokkausAika2Txt.setText(oldSettings.getHyokkausAika2());
            virheetLkmTxt.setText(oldSettings.getVirheetLkm());
            pelikellonSummeriTxt.setText(oldSettings.getPelikellonSummeri());
            heittokellonSummeriTxt.setText(oldSettings.getHeittokellonSummeri());
            previouslySetClockRunningType(oldSettings);
            waitingBeforeStartTxt.setText(oldSettings.getWaitingBeforeStart());

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showChangesAppliedLbl() {
        // Makes the label visible
        muutoksetTallennettuLbl.setVisible(true);

        // Hides the label after 2 seconds
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(2)
        );

        visiblePause.setOnFinished(
                event -> muutoksetTallennettuLbl.setVisible(false)
        );
        visiblePause.play();
    }

}
