package com.example.cv;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class previewcontroller {
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label skills;
    @FXML
    private Label projects;
    @FXML
    private Label education;
    @FXML
    private Label work;

    @FXML
    public void setValue(CV mycv) {
        name.setText(mycv.getName());
        email.setText(mycv.getEmail());
        phone.setText(mycv.getPhone());
        address.setText(mycv.getAddress());
        skills.setText(mycv.getSkills());
        projects.setText(mycv.getProjects());
        education.setText(mycv.getEducation());
        work.setText(mycv.getWork());
    }

    @FXML
    private void handleBack(javafx.event.ActionEvent event) throws java.io.IOException {
        javafx.stage.Stage stage = (javafx.stage.Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("cv-list-view.fxml"));
        javafx.scene.Scene scene = new javafx.scene.Scene(root);
        stage.setTitle("My CVs");
        stage.setScene(scene);
        stage.show();
    }
}
