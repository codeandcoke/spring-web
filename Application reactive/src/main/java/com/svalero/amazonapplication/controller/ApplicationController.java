package com.svalero.amazonapplication.controller;

import com.svalero.amazonapplication.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

import static com.svalero.amazonapplication.api.Constants.API_BASE_URL;

public class ApplicationController implements Initializable {

    public TextField productSearchTextField;
    public Button searchButton;
    public TableView<User> dataTable;
    public Label statusLabel;
    public ProgressBar progressBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepareTableView();
        listAllUsers();
    }

    private void prepareTableView() {
        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<User, String> descriptionColumn = new TableColumn<>("Surname");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        TableColumn<User, String> categoryColumn = new TableColumn<>("Email");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<User, String> priceColumn = new TableColumn<>("DNI");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));

        dataTable.getColumns().add(nameColumn);
        dataTable.getColumns().add(descriptionColumn);
        dataTable.getColumns().add(categoryColumn);
        dataTable.getColumns().add(priceColumn);
    }

    private void listAllUsers() {
        WebClient webClient = WebClient.create(API_BASE_URL);
        Flux<User> usersFlux = webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(User.class);

        usersFlux
                .subscribeOn(Schedulers.fromExecutor(Executors.newCachedThreadPool()))
                .subscribe(dataTable.getItems()::add);
    }

    @FXML
    public void searchProduct(ActionEvent event) {

    }

    @FXML
    public void closeApp(ActionEvent event) {

    }
}
