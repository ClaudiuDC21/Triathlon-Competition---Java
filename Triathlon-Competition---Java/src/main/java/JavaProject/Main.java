package JavaProject;

import JavaProject.Domain.Referee;
import JavaProject.Repository.IRefereeRepository;
import JavaProject.Utils.DependencyProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 250, 320);
        primaryStage.setTitle("Triatlon");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch();
    }
}
