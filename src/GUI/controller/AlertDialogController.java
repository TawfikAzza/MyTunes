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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Objects;
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
    @FXML
    private Label lblSysMsg;
    @FXML
    private AnchorPane dialogPane;
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

    @FXML
    private void isSaved(ActionEvent event) throws AuthorDAOException, Exception, SongDAOException, CategorySongDAOException {
        if(operationType.equals("creation")){
            if(checkInputs()) {
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
            }
        } else {
            if (checkInputs()) {
                File file = new File(fileTextField.getText());
                Author author = authorModel.createNewAuthor(artistTextField.getText().trim());
                Song song = new Song(idSongModified, titleTextField.getText().trim(), author, (CategorySong) comboBoxCategory.getSelectionModel().getSelectedItem(), file, timeTextField.getText().trim());
                songsModel.updateSong(song);
                if (saveButton.getScene().getWindow() != null) {
                    // mainController.isSearchButtonPressed(new ActionEvent());
                    mainController.updateSongTableView();
                    Stage stage = (Stage) saveButton.getScene().getWindow();
                    stage.close();
                }
            }
        }

    }
    public void setOperationType(String aString) {
        operationType = aString;
    }
    @FXML
    private void isCanceled(ActionEvent event) {
        if (cancelButton.getScene().getWindow() != null) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }

    @FXML
    private void isFileChooserPressed(ActionEvent event) {
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

    @FXML
    private void newCategory(ActionEvent actionEvent) {
        lblNewCategory.setVisible(true);
        comboBoxCategory.getSelectionModel().select(null);
        comboBoxCategory.setVisible(false);
    }
    private boolean checkInputs() {
        String msg = "";
        boolean flagInputs = false;
        if(titleTextField.getText().equals("")) {
            msg+="- Title is missing \n";
        }
        if(artistTextField.getText().equals("")) {
            msg+="- Artist name is missing \n";
        }
        if(comboBoxCategory.getSelectionModel().getSelectedItem() == null && lblNewCategory.getText().equals("")) {
            msg+="- Category is missing \n";
        }
        if(timeTextField.getText().equals("")) {
            msg+="- Length of the song is missing \n";
        }
        if(fileTextField.getText().equals("")){
            msg+="- File is missing \n";
        }
        if(msg.equals("")){
            flagInputs=true;
        } else {
            lblSysMsg.setText(msg);
        }
        return flagInputs;
    }


    public void setTheme(AnchorPane topPane) {
        dialogPane.getStylesheets().add(topPane.getStylesheets().get(0));
    }
}
