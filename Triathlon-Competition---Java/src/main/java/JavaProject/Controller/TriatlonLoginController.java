package JavaProject.Controller;

import JavaProject.Main;
import JavaProject.Service.TriatlonService;
import JavaProject.Utils.DependencyProvider;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;



public class TriatlonLoginController {


    private final TriatlonService service = DependencyProvider.getInstance().getSharedService();


    @FXML
    public TextField emailTextfield;

    @FXML
    public PasswordField passwordTextfield;


    private void showDashboard() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/triatlon-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 480, 320);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Triatlon");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        emailTextfield.clear();
        passwordTextfield.clear();
    }
    @FXML
    void onLoginButtonClicked(ActionEvent event) {
        if (service.logInReferee(emailTextfield.getText(), passwordTextfield.getText()))
            showDashboard();
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Eroare Login");
            alert.setHeaderText("Credentiale gresite");
            alert.setContentText("Combinatia email - parola nu este asociata niciunui cont de arbitru.");
            alert.showAndWait();
        }
    }
}