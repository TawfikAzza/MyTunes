import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainWindow.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
      /*  primaryStage.setTitle("Media");
        Group root = new Group();
        Media media = new Media("http://www.youtube.com/watch?v=k0BWlvnBmIE");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        MediaView mediaView = new MediaView(mediaPlayer);

        root.getChildren().add(mediaView);
        Scene scene = SceneBuilder.create().width(500).height(500).root(root)
                .fill(Color.WHITE).build();
        primaryStage.setScene(scene);
        primaryStage.show();*/

    }

    public static void main(String[] args) {
        Application.launch();
    }
}
