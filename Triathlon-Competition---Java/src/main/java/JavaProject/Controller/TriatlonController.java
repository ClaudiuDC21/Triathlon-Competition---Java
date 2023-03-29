package JavaProject.Controller;

import JavaProject.Domain.Result;
import JavaProject.Domain.Referee;
import JavaProject.Main;
import JavaProject.Service.TriatlonService;
import JavaProject.Utils.DependencyProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TriatlonController implements Initializable {


    private final TriatlonService service = DependencyProvider.getInstance().getSharedService();
    ObservableList<Result> participantResults = FXCollections.observableArrayList();

    @FXML
    public Label refereeNameLabel;

    @FXML
    public Label refereeSubtitleLabel;

    @FXML
    public TableColumn<Result, Integer> idColumn;

    @FXML
    public TableColumn<Result, String> nameColumn;

    @FXML
    public TableColumn<Result, Integer> pointsColumn;

    @FXML
    public TableView<Result> athletesTable;

    // Lifecycle

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        Referee referee = service.getCurrentReferee();
        refereeNameLabel.setText(referee.getFirstName() + " " + referee.getLastName());
        refereeSubtitleLabel.setText("Arbitru " + referee.getType());
        participantResults.setAll( service.getParticipantsWithTotalPoints());
        athletesTable.setItems(participantResults);
    }

    private void showAddResult() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/addresult-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 320, 240);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Adaugare rezultat");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void showLeaderboard() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/results-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Rezultate");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void onLogoutButtonClicked(ActionEvent event) {
        service.logOutReferee();
        Stage stage = (Stage) refereeNameLabel.getScene().getWindow();
        stage.close();
    }

    public void onAddResultButtonClicked(ActionEvent event) {
        showAddResult();
    }

    public void onLeaderboardButtonClicked(ActionEvent event) {
        showLeaderboard();
    }

}
