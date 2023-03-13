package com.example.triathloncompetitionjava;

import Domain.Competitor;
import Domain.Contest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();
        Competitor competitor = new Competitor("Mircea", "Badea", 21, "running");
        Contest contest = new Contest("running", "Istvan Kovacs");
        System.out.println(contest.toString());
        System.out.println(competitor.getId());
    }
}