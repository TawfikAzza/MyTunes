package GUI.controller;

import BE.Author;
import BE.CategorySong;
import BE.Song;
import BLL.exception.AuthorDAOException;
import BLL.exception.CategorySongDAOException;
import BLL.exception.MyTunesManagerException;
import BLL.exception.SongDAOException;
import GUI.model.AuthorModel;
import GUI.model.CategoryModel;
import GUI.model.SongsModel;
import com.sun.tools.javac.Main;
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
    CategoryModel categoryModel;
    MainController mainController;
    @FXML
    private TextField fileTextField, timeTextField, artistTextField, titleTextField, lblNewCategory;
    @FXML
    private Button cancelButton, saveButton;
    @FXML
    private ComboBox comboBoxCategory;
    private String operationType="creation";
    private int idSongModified;
    public void setMainController(MainController mainController) {
        this.mainController=mainController;
    }
    public AlertDialogController() throws MyTunesManagerException {
        songsModel = new SongsModel();
        authorModel = new AuthorModel();
        categoryModel = new CategoryModel();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            comboBoxCategory.setItems(songsModel.getAllCategories());
        } catch (CategorySongDAOException e) {
            e.printStackTrace();
        }

    }

    public void isSaved(ActionEvent event) throws AuthorDAOException, Exception, SongDAOException, CategorySongDAOException {
        if(operationType.equals("creation")){
            Song songCreated = null;
            CategorySong categoryCreated= null;
            File file = new File(fileTextField.getText());
            Author author = authorModel.createNewAuthor(artistTextField.getText().trim());
            if(lblNewCategory.getText()!="" && comboBoxCategory.getSelectionModel().getSelectedItem()==null) {
                categoryCreated = categoryModel.createNewCategory(lblNewCategory.getText());
            } else {
                categoryCreated = (CategorySong) comboBoxCategory.getSelectionModel().getSelectedItem();
            }
            Song song = new Song(0,titleTextField.getText().trim(), author,categoryCreated , file, timeTextField.getText().trim());
            songCreated = songsModel.addSong(song);
            if (song != null){
                if (saveButton.getScene().getWindow() != null){
                    mainController.updateSongTableView();
                    Stage stage = (Stage) saveButton.getScene().getWindow();
                    stage.close();
                }
            }
        } else {
            File file = new File(fileTextField.getText());
            Author author = authorModel.createNewAuthor(artistTextField.getText().trim());
            Song song = new Song(idSongModified,titleTextField.getText().trim(), author, (CategorySong) comboBoxCategory.getSelectionModel().getSelectedItem(), file, timeTextField.getText().trim());
            songsModel.updateSong(song);
            if (saveButton.getScene().getWindow() != null){
               // mainController.isSearchButtonPressed(new ActionEvent());
                mainController.updateSongTableView();
                Stage stage = (Stage)saveButton.getScene().getWindow();
                stage.close();
            }
        }


    }
    public void setOperationType(String aString) {
        operationType = aString;
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
    public void setValue(Song song) {
        idSongModified = song.getId();
        fileTextField.setText(song.getStringSongFile());
        timeTextField.setText(song.getStringDuration());
        artistTextField.setText(song.getAuthor().getName());
        titleTextField.setText(song.getName());
        comboBoxCategory.getSelectionModel().select(song.getCategory());
    }

    public void newCategory(ActionEvent actionEvent) {
        lblNewCategory.setVisible(true);
        comboBoxCategory.getSelectionModel().select(null);
        comboBoxCategory.setVisible(false);
    }
}
