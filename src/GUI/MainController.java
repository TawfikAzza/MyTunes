package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    Button goBack, goForward, play, leftButton, searchButton;
    @FXML
    ImageView volumeImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
}