package GUI.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertDialogController implements Initializable {
    @FXML
    private TextField fileTextField, timeTextField, artistTextField, titleTextField;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(fileTextField.getText());
    }
}
