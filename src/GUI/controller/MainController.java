package GUI.controller;

import BE.PlayList;
import BE.Song;
import BLL.exception.MyTunesManagerException;
import BLL.exception.PlayListDAOException;
import BLL.exception.SongDAOException;
import BLL.exception.SongPlayerException;
import GUI.model.PlaylistsModel;
import GUI.model.SongsModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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

public class MainController implements Initializable {
    private final SongsModel songsModel;
    private final PlaylistsModel playlistsModel;
    @FXML
    private Button goBack, goForward, play, leftButton, searchButton, upButton, downButton, newSongButton, closeButton;
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


    public MainController() throws MyTunesManagerException, SongDAOException {
        this.songsModel = new SongsModel();
        this.playlistsModel = new PlaylistsModel();
        songsModel.setCurrentSong(songsModel.getAllSongs().get(0));
    }

    public void playStopSong(ActionEvent event) throws SongPlayerException, MyTunesManagerException {
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
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        });



//        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        try {
            titleColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("name"));
            artistColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("author"));
            categoryColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("category"));
            timeColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getIntDuration()/60+":"+String.format("%02d", data.getValue().getIntDuration()%60))));
            songsTableView.getItems().setAll(songsModel.getAllSongs());
        } catch (SongDAOException e) {
            e.printStackTrace();
        }


        try {
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            songsColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getListSong().size())));
            timePlaylistColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getListSong().values().stream().mapToInt(song -> song.getIntDuration()).sum()/60)+":"+String.format("%02d", data.getValue().getListSong().values().stream().mapToInt(song -> song.getIntDuration()).sum()%60)));
            playlistsTableView.getItems().setAll(playlistsModel.getAllPlayLists());
        } catch (PlayListDAOException e) {
            e.printStackTrace();
        }


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


//    private Integer getPlayListSize() throws PlayListDAOException {
//       final List<Song> jjj = PlayList::getListSong;
//    }
}
