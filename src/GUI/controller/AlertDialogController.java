package GUI.controller;

import BE.Song;
import BLL.exception.MyTunesManagerException;
import GUI.model.SongsModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertDialogController implements Initializable {

    SongsModel songsModel;
    @FXML
    private TextField fileTextField, timeTextField, artistTextField, titleTextField;
    @FXML
    private Button cancelButton;
    @FXML
    private ComboBox comboBoxCategory;

    public AlertDialogController() throws MyTunesManagerException {
        songsModel = new SongsModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxCategory.getItems().addAll();

    }

//    public void isSaved(ActionEvent event) {
//        Song newSong = new Song(titleTextField.getText(), artistTextField.getText(), );
//        songsModel.addSong();
//    }

    public void isCanceled(ActionEvent event) {
        if (cancelButton.getScene().getWindow() != null) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }
}
