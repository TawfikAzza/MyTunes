package GUI.model;

import BE.Song;
import BLL.MyTunesFacade;
import BLL.MyTunesManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class SongsModel {
    private ObservableList<Song> allSongs;
    private MyTunesFacade myTunesFacade;


    public SongsModel() throws IOException {
 //       this.myTunesFacade = new MyTunesManager();
        allSongs = FXCollections.observableArrayList();
    }
//    public ObservableList<Song> getAllSongs() throws IOException {
//       List<Song> allSongs = myTunesFacade.....;
//        this.allSongs.addAll(allSongs);
//        return this.allSongs;
//    }
}
