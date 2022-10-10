package com.svalero.amazonapplication;

import com.svalero.amazonapplication.controller.ApplicationController;
import com.svalero.amazonapplication.domain.User;
import com.svalero.amazonapplication.util.Resources;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

import static com.svalero.amazonapplication.api.Constants.API_BASE_URL;

public class AmazonApplication extends Application {

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Resources.getUI("AmazonApplication.fxml"));
        loader.setController(new ApplicationController());
        BorderPane pane = loader.load();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("BikesAPI Application");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}
