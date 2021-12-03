package GUI.controller;

import BE.PlayList;
import BE.Song;
import BLL.exception.MyTunesManagerException;
import BLL.exception.PlayListDAOException;
import BLL.exception.SongDAOException;
import BLL.exception.SongPlayerException;
import GUI.model.PlaylistsModel;
import GUI.model.SongsModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {
    private final SongsModel songsModel;
    private final PlaylistsModel playlistsModel;
    @FXML
    private Label lblSongPlaying;
    @FXML
    private Button goBack, goForward, play, leftButton, searchButton, upButton, downButton, newSongButton, closeButton, editSongButton, deleteButton,deleteFromPlayListButton, newPlayListButton, updatePlayListButton,deletePlayList;
    @FXML
    private ImageView volumeImage;
    @FXML
    private TextField searchBar;
    @FXML
    private TableView<Song> songsTableView;
    @FXML
    private TableView<PlayList> playlistsTableView;
    @FXML
    private TableColumn<Song, String> titleColumn, artistColumn, categoryColumn, timeColumn;
    @FXML
    private TableColumn<PlayList, String> nameColumn, timePlaylistColumn, songsColumn;
    @FXML
    private ListView<Song> songListFromPlayList;
    private int currentPlayList;
    public MainController() throws MyTunesManagerException, SongDAOException {
        this.songsModel = new SongsModel();
        this.playlistsModel = new PlaylistsModel();
    }
    private void setLabelSongPlaying() {
        if(songsTableView.getSelectionModel().getSelectedItem()!=null)
            lblSongPlaying.setText(songsTableView.getSelectionModel().getSelectedItem().getName());
        else
            lblSongPlaying.setText(songListFromPlayList.getSelectionModel().getSelectedItem().getName());
    }
    public void playStopSong(ActionEvent event) throws SongPlayerException, MyTunesManagerException {
        setLabelSongPlaying();
        songsModel.playStopSong();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        newSongButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/view/AlertDialogView.fxml"), resources);
                    Stage stage = new Stage();
                    stage.setTitle("New/Edit Song");
                    stage.setScene(new Scene(root));
                    stage.show();
                    // Hide this current window (if this is what you want)
//                    ((Node)(event.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        deleteButton.setOnAction(event -> {
            try {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Delete " + songsTableView.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.setHeaderText("You are about to delete a song");
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    songsModel.deleteSong(songsTableView.getSelectionModel().getSelectedItem());
//                        songsTableView.refresh();
                }
               // updateSongTableView();
            } catch (SongDAOException e) {
                e.printStackTrace();
            }
        });
        closeButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Close the Application ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText("You have chosen to close the Application");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Stage stage = (Stage) closeButton.getScene().getWindow();
                stage.close();
            }

        });

//        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        leftButton.setOnAction(event -> {
            if (songsTableView.getSelectionModel().getSelectedItem() != null) {
                songListFromPlayList.getItems().add(songsTableView.getSelectionModel().getSelectedItem());
                songListFromPlayList.refresh();
                for (Song song : songListFromPlayList.getItems()) {
                    System.out.println(song);
                }
            }
        });

        upButton.setOnAction(event -> {
            if (songListFromPlayList.getSelectionModel().getSelectedIndex() != 0) {
                int indexChosen = songListFromPlayList.getSelectionModel().getSelectedIndex();
                int indexToMoveTo = songListFromPlayList.getSelectionModel().getSelectedIndex() - 1;
                Song songSelected = songListFromPlayList.getSelectionModel().getSelectedItem();
                Song tmpSong = songListFromPlayList.getItems().get(indexToMoveTo);
                //I add the song which will be replaced by the song selected at the index of the song selected +1
                songListFromPlayList.getItems().add(indexChosen + 1, tmpSong);
                //I then remove the song which will be replaced by the song selected from the ListView
                songListFromPlayList.getItems().remove(indexToMoveTo);
                //I remove the now selected Song from the List as it has a new Index now, I have to take the previous selected
                //index "IndexChosen" -1 to pinpoint the new Index of the selected song after the deletion f the targetted for removal song
                //in the list
                songListFromPlayList.getItems().remove(indexChosen - 1);
                //All I have to do now is populate the ListView whith the song at the index I want
                songListFromPlayList.getItems().add(indexToMoveTo, songSelected);
                //This can be optimized, but as I am now, I don't now ListView functionalities very well. so this is the best I could do.
                //After havng moved the songs in the listView, I replace the cursor on the right object in the ListView
                //This way if the user click multiple times on the up Arrow, it will consecutively move the song first selected up
                songListFromPlayList.getSelectionModel().select(indexToMoveTo);
            }

        });
        downButton.setOnAction(event -> {
            if (songListFromPlayList.getSelectionModel().getSelectedIndex() > -1 && songListFromPlayList.getSelectionModel().getSelectedIndex() != songListFromPlayList.getItems().size() - 1) {
                int indexChosen = songListFromPlayList.getSelectionModel().getSelectedIndex();
                //IndexToMoveTo is the Index at which the selected song will be moved at
                int indexToMoveTo = songListFromPlayList.getSelectionModel().getSelectedIndex() + 1;
                Song songSelected = songListFromPlayList.getSelectionModel().getSelectedItem();
                Song tmpSong = songListFromPlayList.getItems().get(indexToMoveTo);
                //I add the song which will be replaced by the song selected at the index of the song selected
                songListFromPlayList.getItems().add(indexChosen, tmpSong);
                //I then remove the song which will be replaced by the song selected from the ListView
                songListFromPlayList.getItems().remove(indexToMoveTo);
                //I remove the now selected Song from the List as it has a new Index now, I have to take the previous selected
                //index "IndexChosen" +1 to pinpoint the new Index of the selected song after the deletion f the targetted for removal song
                //in the list
                songListFromPlayList.getItems().remove(indexChosen + 1);
                //All I have to do now is populate the ListView whith the song at the index I want
                songListFromPlayList.getItems().add(indexToMoveTo, songSelected);
                //This can be optimized, but as I am now, I don't now ListView functionalities very well. so this is the best I could do.
                //After havng moved the songs in the listView, I replace the cursor on the right object in the ListView
                //This way if the user click multiple times on the downArrow, it will consecutively move the song first selected down
                songListFromPlayList.getSelectionModel().select(indexToMoveTo);
            }

        });

        deleteFromPlayListButton.setOnAction(event -> {
            songListFromPlayList.getItems().remove(songListFromPlayList.getSelectionModel().getSelectedItem());
            updatePlayListTableView();
        });

        updatePlayListButton.setOnAction(event -> {
            PlayList playList=null;
            if(playlistsTableView.getSelectionModel().getSelectedItem()!=null){
                playList = playlistsTableView.getSelectionModel().getSelectedItem();
            } else {
                try {
                    playList = playlistsModel.getPlayList(currentPlayList);
                } catch (PlayListDAOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Playlist:" + playList.getIdPlaylist() + " name: " + playList.getName());
            for (Song song : songListFromPlayList.getItems()) {
                System.out.println(song);
            }
            try {
                playlistsModel.updatePlayList(playList, songListFromPlayList.getItems());
                updatePlayListTableView();
            } catch (PlayListDAOException e) {
                e.printStackTrace();
            }
        });

        deletePlayList.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Delete " + playlistsTableView.getSelectionModel().getSelectedItem().getName() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText("You are about to delete a Playlist");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                try {
                    playlistsModel.deletePlayList(playlistsTableView.getSelectionModel().getSelectedItem());
                    playlistsTableView.getItems().remove(playlistsTableView.getSelectionModel().getSelectedIndex());
                    updatePlayListTableView();
                } catch (PlayListDAOException e) {
                    e.printStackTrace();
                }
            }
        });

        updateSongTableView();
        updatePlayListTableView();
        setupUI();

    }
    private void setupUI() {

        Image image = new Image("/volume.png");
        volumeImage.setImage(image);
        volumeImage.setFitWidth(20);

        //System.out.println(getClass().getResource("../../../../").getPath());
        ImageView goBackImage = new ImageView(getClass().getResource("/back.png").toExternalForm());
        goBackImage.setFitHeight(30);
        goBackImage.setFitWidth(30);
        goBack.setGraphic(goBackImage);

        ImageView goForwardImage = new ImageView(getClass().getResource("/forward.png").toExternalForm());
        goForwardImage.setFitHeight(30);
        goForwardImage.setFitWidth(30);
        goForward.setGraphic(goForwardImage);

        ImageView playImage = new ImageView(getClass().getResource("/play.png").toExternalForm());
        playImage.setFitHeight(40);
        playImage.setFitWidth(40);
        play.setGraphic(playImage);

        ImageView leftButtonImage = new ImageView(getClass().getResource("/left.png").toExternalForm());
        leftButtonImage.setFitWidth(25);
        leftButtonImage.setFitHeight(25);
        leftButton.setGraphic(leftButtonImage);

        ImageView searchImage = new ImageView(getClass().getResource("/search.png").toExternalForm());
        searchImage.setFitHeight(25);
        searchImage.setFitWidth(25);
        searchButton.setGraphic(searchImage);

        ImageView downImage = new ImageView(getClass().getResource("/down.png").toExternalForm());
        downImage.setFitHeight(20);
        downImage.setFitWidth(20);
        downButton.setGraphic(downImage);

        ImageView upImage = new ImageView(getClass().getResource("/up.png").toExternalForm());
        upImage.setFitWidth(20);
        upImage.setFitHeight(20);
        upButton.setGraphic(upImage);
    }
    public void updatePlayListTableView() {

        try {
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            songsColumn.setCellValueFactory(new PropertyValueFactory<>("sizeListString"));
            //songsColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getListSong().values().stream().count())));
            //songsColumn.setCellValueFactory(data -> String.valueOf(data.getValue().getListSong().values().stream().collect(Collectors.toCollection(ObservableValue<String>::new))));
            // timePlaylistColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getListSong().values().stream().mapToInt(song -> song.getIntDuration()).sum()/60)+":"+String.format("%02d", data.getValue().getListSong().values().stream().mapToInt(song -> song.getIntDuration()).sum()%60)));
            timePlaylistColumn.setCellValueFactory(new PropertyValueFactory<>("totalDuration"));
            playlistsTableView.getItems().setAll(playlistsModel.getAllPlayLists());
        } catch (PlayListDAOException e) {
            e.printStackTrace();
        }
    }
    public void refreshTable() {
        songsTableView.getItems().clear();
    }
    public void updateSongTableView() {


       // songsTableView.getColumns().remove(0,songsColumn.getTableView().getItems().size());

        try {
            titleColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("name"));
            artistColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("author"));
            categoryColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("category"));
            //timeColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getIntDuration()/60+":"+String.format("%02d", data.getValue().getIntDuration()%60))));
            timeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStringDuration()));

            songsTableView.getItems().addAll(songsModel.getAllSongs());

        } catch (SongDAOException e) {
            e.printStackTrace();
        }

    }
    public void testP(ActionEvent actionEvent) {
        System.out.println("Before");
        System.out.println();
        //System.out.println(getClass().getResource("../../../"));
        Media media = new Media(getClass().getResource("/finalBoss.mp3").toString());
        // media.getSource();
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        System.out.println("Duration:" + mediaPlayer.totalDurationProperty());
        //Mediaplayer mediaPlayer = new MediaPlayer(media);
        //mediaPlayer.setAutoPlay(true);

       /* mediaPlayer.setOnReady(new Runnable() {

            @Override
            public void run() {

                System.out.println("Duration: "+media.getDuration().toSeconds());

                // display media's metadata
                for (Map.Entry<String, Object> entry : media.getMetadata().entrySet()){
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }

                // play if you want
                mediaPlayer.play();
            }
        });*/
        mediaPlayer.setOnReady(() -> {
            System.out.println(media.getDuration().toMinutes());
            mediaPlayer.play();
        });
    }

    public void enterFile(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Window stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file);
        System.out.println(getClass().getResource("/").getHost());
        Path src = Paths.get(file.getAbsolutePath());
        Path dest = Paths.get("C:/Users/EASV/Desktop/SCO/MyTunes/resources/" + file.getName().toString());
        //
        // Path dest = Paths.get(Objects.requireNonNull(getClass().getResource("/")).getPath()+file.getName().toString());
        //Path dest = Paths.get("/"+file.getName().toString());
        System.out.println("src:" + src + "  dest:" + dest);
        Files.copy(src, dest);
    }

    public void isClosed(ActionEvent event) {
        if (closeButton.getScene().getWindow() != null) {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
    }

    public void handleChooseSong()
    {

       songsModel.setCurrentSong(songsTableView.getSelectionModel().getSelectedItem());
       setLabelSongPlaying();
    }
    public void handleChooseSongPlayList(MouseEvent mouseEvent) {
        songsModel.setCurrentSong(songListFromPlayList.getSelectionModel().getSelectedItem());
        setLabelSongPlaying();
    }

    public void handleDisplayPlayList(MouseEvent mouseEvent) throws PlayListDAOException {
        currentPlayList=playlistsTableView.getSelectionModel().getSelectedItem().getIdPlaylist();
        songListFromPlayList.setItems(playlistsModel.getPlayListSelected(playlistsTableView.getSelectionModel().getSelectedItem()));
    }
    @FXML
    private void isNewPlayListPressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GUI/view/PlaylistDialogView.fxml"));
        Parent root = loader.load();
        PlaylistDialogController playlistDialogController = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("New/Edit Playlist");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void editSong(ActionEvent actionEvent) {
        try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("GUI/view/AlertDialogView.fxml"));
                Parent root = loader.load();
                AlertDialogController alertDialogController = loader.getController();
                alertDialogController.setValue(songsTableView.getSelectionModel().getSelectedItem());
                songsTableView.getItems().clear();
                songsTableView.refresh();


                alertDialogController.setMainController(this);
                alertDialogController.setOperationType("modification");
                //root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/view/AlertDialogView.fxml"), resources);
                Stage stage = new Stage();
                stage.setTitle("New/Edit Song");
                stage.setScene(new Scene(root));
                stage.show();
                // Hide this current window (if this is what you want)
    //                    ((Node)(event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
