package GUI.controller;

import BE.PlayList;
import BE.Song;
import BE.Theme;
import BLL.exception.MyTunesManagerException;
import BLL.exception.PlayListDAOException;
import BLL.exception.SongDAOException;
import BLL.exception.SongPlayerException;
import BLL.util.SongPlayer;
import GUI.model.PlaylistsModel;
import GUI.model.SongsModel;
import GUI.model.ThemeModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private final SongsModel songsModel;
    private final PlaylistsModel playlistsModel;
    private final ThemeModel themeModel;

    @FXML
    private AnchorPane topPane;
    @FXML
    private ComboBox<Theme> comboTheme;
    @FXML
    private Slider slider,volumeSlider;
    @FXML
    private Label lblSongPlaying,lblTextSongTrack;
    @FXML
    private Button goBack, goForward, play, leftButton, searchButton, upButton, downButton, newSongButton, closeButton, editSongButton, deleteButton, deleteFromPlayListButton, newPlayListButton, updatePlayListButton, deletePlayList;
    @FXML
    private ImageView volumeImage, durationImage;
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
    private int currentPlayList = -1;
    //Piece of code and idea given to us by Renars the genius!
    ChangeListener<Duration> changeListener;
    //End piece of code from Renars
    MediaPlayer player;
    private String textToChange;
    public MainController() throws MyTunesManagerException {
        this.themeModel = new ThemeModel();
        this.songsModel = new SongsModel();
        this.playlistsModel = new PlaylistsModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupButtons();
        updateSongTableView();
        updatePlayListTableView();
        setupUI();
    }


    /**
     * The two following methods : moveProgressSlider and setProgress
     * are in charge of the gestion of the slider when the user interact directly with it.
     * the first one is triggered when the mouse is pressed on the slider it removes the listener no longer necessary
     * and when the user release the mouse button, setProgress is called
     * which will recreate the listener and set the offset of the timestamp of the media
     * to the new value of the slider chosen.
     */
    @FXML
    private void moveProgressSlider(MouseEvent mouseEvent) {
        player.currentTimeProperty().removeListener(changeListener);
    }

    @FXML
    private void setProgress(MouseEvent mouseEvent) {
        player.seek(Duration.seconds(slider.getValue()));
        player.currentTimeProperty().addListener(changeListener);
    }


    /**
     * This method generates a Listener on the slider which will serve several purposes,
     * The first is to allows the moving of the progress slider according to the time the song has been played
     * The second is to allow the user to change the value of the slider and change the progress of the song play
     * according to the position of the song selected by him/her.
     * Finally the third is to be able to keep track of the moment the song if finished playing and if the song is
     * part of a PlayList, advance to the next one.
     **/
    @FXML
    private void generateListener() {
        final int[] textIndex = {0};
        final int[] indexDisplay = {0};
        SongPlayer songPlayer = SongPlayer.getInstance();
        if (changeListener != null)
            player.currentTimeProperty().removeListener(changeListener);
        player = songPlayer.getPlayer();
        player.setOnReady(() ->{
            slider.maxProperty().set(player.getTotalDuration().toSeconds());
            textToChange = songPlayer.getCurrentSong().getName();
        });
        //Have to do it twice as the maxProperty.set method doesn't seems to initialize the slider at all when inside the
        //lambda of setOnReady() it forces out the value of player.getTotalDuration().toSeconds() though as if I don't do that
        //the value stays at NaN, so the setOnReady method of the player is necessary there.
        slider.maxProperty().set(player.getTotalDuration().toSeconds());
        textToChange = songPlayer.getCurrentSong().getName();
        final String[] scrollingText = {""};
        int textLength = textToChange.length();
        changeListener = new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                slider.setValue(((double) newValue.toSeconds()));
                int hours = (int)player.getTotalDuration().toSeconds()/3600;
                int minutes = ((int)player.getTotalDuration().toSeconds()%3600)/60;
                int seconds = (int)player.getTotalDuration().toSeconds()%60;
                int hoursSong = (int)(player.getCurrentTime().toSeconds()/3600);
                int minutesSong = ((int)(player.getCurrentTime().toSeconds()%3600)/60);
                int secondsSong = (int)(player.getCurrentTime().toSeconds()%60);

                int index = 0;
                if(textIndex[0]!=secondsSong%(textLength)) {
                    textIndex[0] = secondsSong%textLength;
                    index = textIndex[0];
                }
                /**
                 * This part was somewhat tricky and truthfully, we hesitated to put it in the project, as obviously it can be optimized
                 * further, however, the amount of effort it took, and the timely help of Nedas convinced us to let it there.
                 * */
                if(index!=0) {
                    scrollingText[0] += ""+textToChange.charAt(index-1);
                    int finalIndex = index;
                    int indexBegin = index;
                    int indexEnd = (textLength-(textLength-indexBegin))%textLength;
                    String scrollingMessage = textToChange+" ::::::::::::: "+textToChange;
                    if(indexDisplay[0]==(scrollingMessage.length())) {
                        indexDisplay[0]=0;
                    }
                    String finalScrollingMessage = scrollingMessage+" ::::::::::::: " +scrollingMessage;
                    Platform.runLater(new Runnable() {
                        public void run() {
                            indexDisplay[0]++;
                            String message = finalScrollingMessage.substring(indexDisplay[0]/2, (finalScrollingMessage.length()+indexDisplay[0])/2);
                            lblSongPlaying.setText(message);
                        }});
                }

                String time="0";
                if(hours<1) {
                    time = String.format("%02d:%02d / %02d:%02d",minutesSong,secondsSong
                            ,minutes,seconds);
                } else {
                    if(hoursSong<1) {
                        time = String.format("%02d:%02d / %02d:%02d:%02d", minutesSong, secondsSong
                                , hours, minutes, seconds);
                    } else {
                        time = String.format("%02d:%02d:%02d / %02d:%02d:%02d", hoursSong, minutesSong,secondsSong
                                , hours, minutes, seconds);
                    }
                }
                lblTextSongTrack.setText(time);
                if (slider.getValue() + 1 >= player.getTotalDuration().toSeconds()) {

                    if (songListFromPlayList.getSelectionModel().getSelectedIndex() != -1)
                        nextSong(new ActionEvent());
                }
            }
        };
        player.currentTimeProperty().addListener(changeListener);
    }

    /**
     * The next three method playStopSong, previousSong as well as nextSong are in charge of
     * managing the action of the mouse on the three controls that governs the playback as well as
     * the feeding of the media to the Media player.
     * The method are straightforward and use the SongsModel as foundation
     */
    @FXML
    private void playStopSong(ActionEvent event) {
        try {
            songsModel.playStopSong();
        } catch (MyTunesManagerException | SongPlayerException e) {
            displayError(e);
        }
        player = SongPlayer.getInstance().getPlayer();
        if (player == null)
            return;
        generateListener();
        player.setVolume(volumeSlider.getValue());
        player.volumeProperty().bindBidirectional(volumeSlider.valueProperty());
        setLabelSongPlaying();
        setupPlayButton();
     }

    @FXML
    private void previousSong(ActionEvent actionEvent) {
        if (songListFromPlayList.getSelectionModel().getSelectedIndex() > 0) {
            songListFromPlayList.getSelectionModel().select(songListFromPlayList.getSelectionModel().getSelectedIndex() - 1);
            try {
                songsModel.setCurrentSong(songListFromPlayList.getSelectionModel().getSelectedItem());
            } catch (SongPlayerException e) {
                displayError(e);
            }
            playStopSong(actionEvent);
        }
    }

    @FXML
    private void nextSong(ActionEvent actionEvent) {
        if (songListFromPlayList.getSelectionModel().getSelectedIndex() < songListFromPlayList.getItems().size() - 1) {
            songListFromPlayList.getSelectionModel().select(songListFromPlayList.getSelectionModel().getSelectedIndex() + 1);
            try {
                songsModel.setCurrentSong(songListFromPlayList.getSelectionModel().getSelectedItem());
            } catch (SongPlayerException e) {
                displayError(e);
            }
            playStopSong(actionEvent);
        }
    }

    /***
     * The next 5 methods are in charge of the UI management, they set up the right values of the label,
     * refresh the differents lists and tableViews as well as setup up the differents images contained inside the
     * MainWindow
     * Their name are explicit and the content is as well.
     * */
    @FXML
    private void setLabelSongPlaying() {
        if (songsTableView.getSelectionModel().getSelectedIndex() != -1) {
            lblSongPlaying.setText(songsTableView.getSelectionModel().getSelectedItem().getName());

        }
        if (songListFromPlayList.getSelectionModel().getSelectedIndex() != -1) {
            lblSongPlaying.setText(songListFromPlayList.getSelectionModel().getSelectedItem().getName());

        }
    }
    @FXML
    private void setupPlayButton() {
        if(player!= null && player.getStatus() == MediaPlayer.Status.PLAYING) {
            ImageView playImage = new ImageView(getClass().getResource("/css/"+comboTheme.getSelectionModel().getSelectedItem().getNameTheme()+"/images/play.png").toExternalForm());

            playImage.setFitHeight(40);
            playImage.setFitWidth(40);
            play.setGraphic(playImage);
        }

        if (player != null && (player.getStatus() == MediaPlayer.Status.PAUSED || player.getStatus() == MediaPlayer.Status.READY)) {
            ImageView pauseImage = new ImageView(getClass().getResource("/css/"+comboTheme.getSelectionModel().getSelectedItem().getNameTheme()+"/images/pause.png").toExternalForm());
            pauseImage.setStyle(topPane.getStylesheets().get(0));
            pauseImage.setFitHeight(40);
            pauseImage.setFitWidth(40);

            play.setGraphic(pauseImage);
        }
    }
    @FXML
    private void setupButtons() {
        deleteButton.setOnAction(event -> {
            try {
                if (songsTableView.getSelectionModel().getSelectedIndex() != -1) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Delete " + songsTableView.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                    alert.setHeaderText("You are about to delete a song");
                    alert.showAndWait();

                    if (alert.getResult() == ButtonType.YES) {
                        songsModel.deleteSong(songsTableView.getSelectionModel().getSelectedItem());
                        songsTableView.getItems().remove(songsTableView.getSelectionModel().getSelectedIndex());
                        songsTableView.getSelectionModel().clearSelection();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Confirm", ButtonType.OK);
                    alert.setHeaderText("Please select a Song");
                    alert.showAndWait();
                }

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

        leftButton.setOnAction(event -> {
            if (songsTableView.getSelectionModel().getSelectedItem() != null) {
                songListFromPlayList.getItems().add(songsTableView.getSelectionModel().getSelectedItem());
                songListFromPlayList.refresh();
                updatePlayListButton.setVisible(true);
            }
        });

        upButton.setOnAction(event -> {
            if(songListFromPlayList.getSelectionModel().getSelectedItem()==null) {
                return;
            }
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
                //index "IndexChosen" -1 to pinpoint the new Index of the selected song after the deletion if the targetted for removal song
                //in the list
                songListFromPlayList.getItems().remove(indexChosen - 1);
                //All I have to do now is populate the ListView whith the song at the index I want
                songListFromPlayList.getItems().add(indexToMoveTo, songSelected);
                //This can be optimized, but as I am now, I don't now ListView functionalities very well. so this is the best I could do.
                //After havng moved the songs in the listView, I replace the cursor on the right object in the ListView
                //This way if the user click multiple times on the up Arrow, it will consecutively move the song first selected up
                songListFromPlayList.getSelectionModel().select(indexToMoveTo);
                updatePlayListButton.setVisible(true);
            }

        });
        downButton.setOnAction(event -> {
            if(songListFromPlayList.getSelectionModel().getSelectedItem()== null) {
                return;
            }
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
                updatePlayListButton.setVisible(true);
            }

        });

        deleteFromPlayListButton.setOnAction(event -> {
            songListFromPlayList.getItems().remove(songListFromPlayList.getSelectionModel().getSelectedItem());
            updatePlayListTableView();
            updatePlayListButton.setVisible(true);
        });

        updatePlayListButton.setOnAction(event -> {
            PlayList playList = null;
            if (playlistsTableView.getSelectionModel().getSelectedItem() != null) {
                playList = playlistsTableView.getSelectionModel().getSelectedItem();
            } else {
                try {
                    playList = playlistsModel.getPlayList(currentPlayList);
                } catch (PlayListDAOException e) {
                    e.printStackTrace();
                }
            }

            try {
                playlistsModel.updatePlayList(playList, songListFromPlayList.getItems());
                updatePlayListTableView();
            } catch (PlayListDAOException | MalformedURLException e) {
                e.printStackTrace();
            }
            updatePlayListButton.setVisible(false);
        });

        deletePlayList.setOnAction(event -> {
            if (playlistsTableView.getSelectionModel().getSelectedIndex() != -1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Delete " + playlistsTableView.getSelectionModel().getSelectedItem().getName() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.setHeaderText("You are about to delete a Playlist");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    try {
                        playlistsModel.deletePlayList(playlistsTableView.getSelectionModel().getSelectedItem());
                        playlistsTableView.getItems().remove(playlistsTableView.getSelectionModel().getSelectedIndex());
                        updatePlayListTableView();
                        songListFromPlayList.getItems().clear();
                    } catch (PlayListDAOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Confirm", ButtonType.OK);
                alert.setHeaderText("Please select a PlayList");
                alert.showAndWait();
            }
        });
        updatePlayListButton.setVisible(false);
    }
    @FXML
    private void filterSearch() {
        FilteredList<Song> listOfSongs = null;
        try {
            listOfSongs = new FilteredList<>(songsModel.getAllSongs(), a -> true);
        } catch (SongDAOException e) {
            displayError(e);
        }
        listOfSongs.setPredicate(songSearched -> {
            String inputSearch = searchBar.getText().toLowerCase();
            if (songSearched.getName().toLowerCase().contains(inputSearch)){
                return true;
            }
            return songSearched.getAuthor().getName().toLowerCase().contains(inputSearch);
        });
        SortedList<Song> listOfSongSorted = new SortedList<>(listOfSongs);
        songsTableView.getItems().clear();
        songsTableView.getItems().addAll(listOfSongSorted);
    }
    @FXML
    private void setupUI() {
       Image volumeImageView = new Image("/volume.png");
        volumeImage.setImage(volumeImageView);
        volumeImage.setFitWidth(20);

        Image durationImageView = new Image("/duration.png");
        durationImage.setImage(durationImageView);
        durationImage.setFitWidth(20);

        ImageView goBackImage = new ImageView();
        goBackImage.setFitHeight(30);
        goBackImage.setFitWidth(30);
        goBack.setGraphic(goBackImage);

        ImageView goForwardImage = new ImageView();
        goForwardImage.setFitHeight(30);
        goForwardImage.setFitWidth(30);
        goForward.setGraphic(goForwardImage);

        ImageView playImage = new ImageView();
        playImage.setFitHeight(40);
        playImage.setFitWidth(40);
        play.setGraphic(playImage);

        ImageView leftButtonImage = new ImageView();
        leftButtonImage.setFitWidth(25);
        leftButtonImage.setFitHeight(25);
        leftButton.setGraphic(leftButtonImage);

        ImageView searchImage = new ImageView();
        searchImage.setFitHeight(25);
        searchImage.setFitWidth(25);
        searchButton.setGraphic(searchImage);

        ImageView downImage = new ImageView();
        downImage.setFitHeight(20);
        downImage.setFitWidth(20);
        downButton.setGraphic(downImage);

        ImageView upImage = new ImageView();
        upImage.setFitWidth(20);
        upImage.setFitHeight(20);
        upButton.setGraphic(upImage);
        comboTheme.setItems(themeModel.getListThemes());
        comboTheme.getSelectionModel().select(0);
        topPane.getStylesheets().add(getClass().getResource("/css/DefaultTheme/DefaultTheme.css").toExternalForm());
        System.out.println("Style:" + topPane.getStylesheets());

    }

    public void updatePlayListTableView() {
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            songsColumn.setCellValueFactory(new PropertyValueFactory<>("sizeListString"));
            timePlaylistColumn.setCellValueFactory(new PropertyValueFactory<>("totalDuration"));
        try {
            playlistsTableView.getItems().setAll(playlistsModel.getAllPlayLists());
        } catch (PlayListDAOException e) {
            displayError(e);
        }
    }

    public void updateSongTableView() {
        songsTableView.getItems().clear();
        songsTableView.refresh();
        titleColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("name"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("category"));
        timeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStringDuration()));
        try {
            songsTableView.getItems().setAll(songsModel.getAllSongs());
        } catch (SongDAOException e) {
            displayError(e);
        }
    }

    /***
     * The next 7 methods governs the action taken by the app according to the choices of the user,
     * be it clicking on a particular list/TableView, or clicking on a particular button to edit, delete or
     * update an entity.
     * Note that some of the button interactions are defined in the setupButton method
     * but some actions do necessitate the passing of the MainController object reference, and this is
     * not possible when using lambda expression, well, at least we didn't figure out how...
     * */
    @FXML
    private void handleChooseSong() {
        if(songsTableView.getSelectionModel().getSelectedIndex()!=-1){
            songListFromPlayList.getSelectionModel().clearSelection();
            try {
                    songsModel.setCurrentSong(songsTableView.getSelectionModel().getSelectedItem());
                    setLabelSongPlaying();
                    setupPlayButton();
            } catch (SongPlayerException e) {
                displayError(e);
            }
            lblSongPlaying.setText("");
            setupPlayButton();
        }
    }

    @FXML
    private void handleChooseSongPlayList(MouseEvent mouseEvent) {
        if (songListFromPlayList.getSelectionModel().getSelectedIndex() != -1) {
            try {
                songsModel.setCurrentSong(songListFromPlayList.getSelectionModel().getSelectedItem());
                    setLabelSongPlaying();
                    setupPlayButton();
                    songsTableView.getSelectionModel().clearSelection();
            } catch (SongPlayerException e) {
                displayError(e);
            }
            lblSongPlaying.setText("");
            setupPlayButton();
            songsTableView.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void handleDisplayPlayList(MouseEvent mouseEvent) {
        if(playlistsTableView.getSelectionModel().getSelectedIndex()!=-1) {
            songsTableView.getSelectionModel().clearSelection();
            currentPlayList = playlistsTableView.getSelectionModel().getSelectedItem().getIdPlaylist();
            try {
                songListFromPlayList.setItems(playlistsModel.getPlayListSelected(playlistsTableView.getSelectionModel().getSelectedItem()));
                    songListFromPlayList.getSelectionModel().select(0);
                    if(songListFromPlayList.getSelectionModel().getSelectedItem()!=null) {
                        try {
                            songsModel.setCurrentSong(songListFromPlayList.getSelectionModel().getSelectedItem());
                            setLabelSongPlaying();
                            setupPlayButton();
                        } catch (SongPlayerException e) {
                            displayError(e);
                        }
                    }
            } catch (PlayListDAOException e) {
                displayError(e);
            }
            lblSongPlaying.setText("");
            setupPlayButton();
        }
    }

    @FXML
    private void newPlayListName(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GUI/view/PlaylistDialogView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            displayError(e);
        }
        PlaylistDialogController playlistDialogController = loader.getController();
        playlistDialogController.setMainController(this);
        playlistDialogController.setTheme(topPane);
        try {
            playlistDialogController.setPlayListToBeUpdated(playlistsModel.getPlayList(currentPlayList));
        } catch (PlayListDAOException e) {
            displayError(e);
        }
        Stage stage = new Stage();
        stage.setTitle("New/Edit Playlist");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void editSong(ActionEvent actionEvent) {
            if (songsTableView.getSelectionModel().getSelectedIndex() != -1) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("GUI/view/AlertDialogView.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    displayError(e);
                }
                AlertDialogController alertDialogController = loader.getController();
                alertDialogController.setValue(songsTableView.getSelectionModel().getSelectedItem());
                alertDialogController.setTheme(topPane);
                alertDialogController.setMainController(this);
                alertDialogController.setOperationType("modification");
                Stage stage = new Stage();
                stage.setTitle("New/Edit Song");
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Confirm", ButtonType.OK);
                alert.setHeaderText("Please select a Song");
                alert.showAndWait();
            }
            // Hide this current window (if this is what you want)
            //                    ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void editPlayListName(ActionEvent actionEvent) {
        if (currentPlayList != -1) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GUI/view/PlaylistDialogView.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                displayError(e);
            }
            PlaylistDialogController playlistDialogController = loader.getController();
            playlistDialogController.setMainController(this);
            playlistDialogController.setTheme(topPane);
            try {
                playlistDialogController.setPlayListToBeUpdated(playlistsModel.getPlayList(currentPlayList));
            } catch (PlayListDAOException e) {
                displayError(e);
            }
            playlistDialogController.setOperationType("modification");
            Stage stage = new Stage();
            stage.setTitle("New/Edit Playlist");
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Confirm", ButtonType.OK);
            alert.setHeaderText("Please select a PlayList");
            alert.showAndWait();
        }
    }


    @FXML
    private void newSong(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("GUI/view/AlertDialogView.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            displayError(e);
        }
        AlertDialogController alertDialogController = loader.getController();
        alertDialogController.setMainController(this);
        alertDialogController.setTheme(topPane);
        Stage stage = new Stage();
        stage.setTitle("New/Edit Song");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void isKeyPressed(KeyEvent keyEvent) {
        ImageView deleteImage = new ImageView();
        deleteImage.setFitHeight(25);
        deleteImage.setFitWidth(25);
        searchButton.setGraphic(deleteImage);
        filterSearch();
    }

    @FXML
    private void isSearchButtonPressed(ActionEvent event) {
        searchBar.setText("");

        ImageView searchImage = new ImageView();
        searchImage.setFitHeight(25);
        searchImage.setFitWidth(25);
        searchButton.setGraphic(searchImage);
        updateSongTableView();

    }

    @FXML
    private void setSoundVolume(MouseEvent mouseEvent) {
        if(player!=null) {
            player.setOnReady(()-> player.setVolume(volumeSlider.getValue()));
        }
    }

    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong...");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }
    @FXML
    private void changeTheme(ActionEvent actionEvent) {
        if(comboTheme.getSelectionModel().getSelectedItem()!=null) {
            String themeChosen = comboTheme.getSelectionModel().getSelectedItem().getThemePath();
            topPane.getStylesheets().clear();
            topPane.getStylesheets().add(themeChosen);
            setupPlayButton();
        }
    }
}
