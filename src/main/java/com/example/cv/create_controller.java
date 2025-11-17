package com.example.cv;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    @FXML
    private void generatecv(ActionEvent event) throws IOException {
        mycv.education=edu.getText();
        mycv.work=work.getText();
        mycv.projects=projects.getText();
        mycv.address=address.getText();
        mycv.email=email.getText();
        mycv.name=name.getText();
        mycv.phone=phone.getText();
        mycv.skills=skills.getText();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("preview.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

}
