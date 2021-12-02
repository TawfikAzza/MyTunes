package GUI.model;

import BE.CategorySong;
import BE.Song;
import BLL.MyTunesFacade;
import BLL.MyTunesManager;
import BLL.exception.CategorySongDAOException;
import BLL.exception.MyTunesManagerException;
import BLL.exception.SongDAOException;
import BLL.exception.SongPlayerException;
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

    public void playSong() throws MyTunesManagerException, SongPlayerException {
        MyTunesManager myTunesManager = new MyTunesManager();
        myTunesManager.playSong();
    }
    public void addSong(Song song) throws SongDAOException {
        myTunesFacade.createSong(song);

    }


    public ObservableList getAllCategories() throws CategorySongDAOException {
        ObservableList<CategorySong> listCategories = FXCollections.observableArrayList();
        listCategories.addAll(myTunesFacade.getALlCategorySong());
        return listCategories;
    }
}
