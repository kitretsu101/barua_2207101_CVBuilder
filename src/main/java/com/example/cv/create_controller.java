package com.example.cv;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class create_controller {
    CV mycv = new CV();
    @FXML
    private TextArea skills;
    @FXML
    private TextArea work;
    @FXML
    private TextArea projects;
    @FXML
    private TextArea edu;
    @FXML
    private TextArea name;
    @FXML
    private TextArea phone;
    @FXML
    private TextArea address;
    @FXML
    private TextArea email;

    public void setCV(CV cv) {
        this.mycv = cv;
        name.setText(cv.getName());
        email.setText(cv.getEmail());
        phone.setText(cv.getPhone());
        address.setText(cv.getAddress());
        edu.setText(cv.getEducation());
        skills.setText(cv.getSkills());
        work.setText(cv.getWork());
        projects.setText(cv.getProjects());
    }

    @FXML
    private void generatecv(ActionEvent event) throws IOException {
        mycv.education = edu.getText();
        mycv.work = work.getText();
        mycv.projects = projects.getText();
        mycv.address = address.getText();
        mycv.email = email.getText();
        mycv.name = name.getText();
        mycv.phone = phone.getText();
        mycv.skills = skills.getText();

        if (mycv.getId() == 0) {
            DatabaseHandler.addCV(mycv);
        } else {
            DatabaseHandler.updateCV(mycv);
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("cv-list-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setTitle("My CVs");
        stage.setScene(scene);
        stage.show();

    }

}
