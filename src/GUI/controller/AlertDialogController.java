package GUI.controller;

import BE.Author;
import BE.CategorySong;
import BE.Song;
import BLL.exception.AuthorDAOException;
import BLL.exception.CategorySongDAOException;
import BLL.exception.MyTunesManagerException;
import BLL.exception.SongDAOException;
import GUI.model.AuthorModel;
import GUI.model.SongsModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AlertDialogController implements Initializable {

    SongsModel songsModel;
    AuthorModel authorModel;
    @FXML
    private TextField fileTextField, timeTextField, artistTextField, titleTextField;
    @FXML
    private Button cancelButton;
    @FXML
    private ComboBox comboBoxCategory;

    public AlertDialogController() throws MyTunesManagerException {
        songsModel = new SongsModel();
        authorModel = new AuthorModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            comboBoxCategory.setItems(songsModel.getAllCategories());
        } catch (CategorySongDAOException e) {
            e.printStackTrace();
        }

    }

    public void isSaved(ActionEvent event) throws AuthorDAOException, Exception, SongDAOException {
        File file = new File(fileTextField.getText());
        Author author = authorModel.createNewAuthor(artistTextField.getText());
        Song song = new Song(titleTextField.getText(), author, (CategorySong) comboBoxCategory.getSelectionModel().getSelectedItem(), file, timeTextField.getText());
        songsModel.addSong(song);
    }

    public void isCanceled(ActionEvent event) {
        if (cancelButton.getScene().getWindow() != null) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }

    public void isFileChooserPressed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null){
            fileTextField.setText(selectedFile.toURI().toString());
        }
    }
}
