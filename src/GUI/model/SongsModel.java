package GUI.model;

import BE.Song;
import BLL.MyTunesFacade;
import BLL.MyTunesManager;
import BLL.exception.MyTunesManagerException;
import BLL.exception.SongDAOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class SongsModel {
    private ObservableList<Song> allAvailableSongs;
    private MyTunesFacade myTunesFacade;

    public SongsModel() throws MyTunesManagerException {
        this.myTunesFacade = new MyTunesManager();
        allAvailableSongs = FXCollections.observableArrayList();
    }
    public ObservableList<Song> getAllSongs() throws SongDAOException {
       List<Song> allSongs = myTunesFacade.getALlSongs();
        allAvailableSongs.addAll(allSongs);
        return allAvailableSongs;
    }
}
