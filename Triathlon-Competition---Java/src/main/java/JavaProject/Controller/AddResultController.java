package JavaProject.Controller;

import JavaProject.Service.TriatlonService;
import JavaProject.Utils.DependencyProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddResultController implements Initializable {


    private final TriatlonService service = DependencyProvider.getInstance().getSharedService();
    ObservableList<String> participantsName = FXCollections.observableArrayList();


    @FXML
    public ComboBox<String> participantComboBox;

    @FXML
    public TextField pointsTextfield;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pointsTextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                pointsTextfield.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        service.getParticipants().forEach(participant -> participantsName.add(participant.getId().toString() + " - " + participant.getFullName()));
        if(participantComboBox != null)
            participantComboBox.setItems(participantsName);
    }

    private void dismissScreen() {
        Stage stage = (Stage) pointsTextfield.getScene().getWindow();
        stage.close();
    }

    private void showEmptyAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Eroare");
        alert.setHeaderText("Niciun participant selectat");
        alert.setContentText("Te rugam sa alegi un participant pentru a introduce un rezultat.");
        alert.showAndWait();
    }

    public void onAddButtonClicked(ActionEvent event) {
        if (participantComboBox.getSelectionModel().isEmpty()) { showEmptyAlert(); return; }
        String value = participantComboBox.getValue();
        var end = 1;
        for (int i = 2; i < value.length(); i++) {
            if (value.charAt(i-1) == ' ') break;
            end = i;
        }
        Integer participantID = Integer.parseInt(value.substring(0, end));
        int points = 0;
        try {
            points = Integer.parseInt(pointsTextfield.getText());
        } catch (Exception ignored) {}
        service.addResult(participantID, points);
        dismissScreen();
    }

    public void onCloseButtonClicked(ActionEvent event) {
        dismissScreen();
    }
}
