package GUI.controller;

import BLL.exception.MyTunesManagerException;
import BLL.exception.PlayListDAOException;
import GUI.model.PlaylistsModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PlaylistDialogController implements Initializable {
    private PlaylistsModel playlistsModel;
    @FXML
    private Button cancelButton, saveButton;
    @FXML
    private TextField nameTextField;

    public PlaylistDialogController() throws MyTunesManagerException {
        playlistsModel = new PlaylistsModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void isCancelPressed(ActionEvent event) {
    }

    public void isSavedPressed(ActionEvent event) throws PlayListDAOException {
        playlistsModel.createNewPlaylist(nameTextField.getText());
    }
}
