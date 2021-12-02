package GUI.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertDialogController implements Initializable {

    @FXML
    private TextField fileTextField, timeTextField, artistTextField, titleTextField;
    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void isSaved(ActionEvent event) {
        System.out.println(fileTextField.getText());
    }

    public void isCanceled(ActionEvent event) {
        if (cancelButton.getScene().getWindow() != null) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }
}
