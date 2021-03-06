package GUI.controller;

import BE.PlayList;
import BLL.exception.MyTunesManagerException;
import BLL.exception.PlayListDAOException;
import GUI.model.PlaylistsModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PlaylistDialogController implements Initializable {
    private PlaylistsModel playlistsModel;
    @FXML
    private Button cancelButton, saveButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private Label lblSystemMsg;
    @FXML
    private AnchorPane dialogPane;
    private MainController mainController;
    private PlayList playListToBeUpdated;
    private String operationType="creation";
    public PlaylistDialogController() throws MyTunesManagerException {
        playlistsModel = new PlaylistsModel();
    }
    public void setMainController(MainController mainController) {
        this.mainController= mainController;
    }
    public void setPlayListToBeUpdated(PlayList playList){
        this.playListToBeUpdated=playList;
        nameTextField.setText(playListToBeUpdated.getName());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setOperationType(String aString) {
        this.operationType = aString;
    }
    public void isCancelPressed(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void isSavedPressed(ActionEvent event) throws PlayListDAOException, Exception {
        if(nameTextField.getText()==null || nameTextField.getText()=="") {
            lblSystemMsg.setText("Please enter a playlist name.");
        } else {
            if (operationType.equals("creation")) {
                playlistsModel.createNewPlaylist(nameTextField.getText().trim());
            } else {
                playListToBeUpdated.setName(nameTextField.getText().trim());
                playlistsModel.updatePlayListName(playListToBeUpdated);
            }
            mainController.updatePlayListTableView();
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
        }
    }

    public void setTheme(AnchorPane topPane) {
        dialogPane.getStylesheets().clear();
        dialogPane.getStylesheets().add(topPane.getStylesheets().get(0));
    }
}
