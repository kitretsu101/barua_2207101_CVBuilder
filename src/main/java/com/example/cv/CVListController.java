package com.example.cv;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CVListController {

    @FXML
    private TableView<CV> cvTable;
    @FXML
    private TableColumn<CV, String> nameColumn;
    @FXML
    private TableColumn<CV, String> emailColumn;
    @FXML
    private TableColumn<CV, String> phoneColumn;

    private ObservableList<CV> cvList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        loadCVs();
    }

    private void loadCVs() {
        cvList.clear();
        cvList.addAll(DatabaseHandler.getAllCVs());
        cvTable.setItems(cvList);
    }

    @FXML
    private void handleAddCV(ActionEvent event) throws IOException {
        navigateToCreate(event, null);
    }

    @FXML
    private void handleEditCV(ActionEvent event) throws IOException {
        CV selectedCV = cvTable.getSelectionModel().getSelectedItem();
        if (selectedCV != null) {
            navigateToCreate(event, selectedCV);
        } else {
            showAlert("No Selection", "Please select a CV to edit.");
        }
    }

    @FXML
    private void handlePreviewCV(ActionEvent event) throws IOException {
        CV selectedCV = cvTable.getSelectionModel().getSelectedItem();
        if (selectedCV != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("preview.fxml"));
            Parent root = loader.load();

            previewcontroller controller = loader.getController();
            controller.setValue(selectedCV);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            showAlert("No Selection", "Please select a CV to preview.");
        }
    }

    @FXML
    private void handleDeleteCV(ActionEvent event) {
        CV selectedCV = cvTable.getSelectionModel().getSelectedItem();
        if (selectedCV != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete CV");
            alert.setHeaderText("Are you sure you want to delete this CV?");
            alert.setContentText("This action cannot be undone.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                DatabaseHandler.deleteCV(selectedCV.getId());
                loadCVs();
            }
        } else {
            showAlert("No Selection", "Please select a CV to delete.");
        }
    }

    private void navigateToCreate(ActionEvent event, CV cv) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("create.fxml"));
        Parent root = loader.load();

        create_controller controller = loader.getController();
        if (cv != null) {
            controller.setCV(cv);
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
