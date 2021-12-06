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
import javafx.scene.control.TableView;

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
        allAvailableSongs.clear();
        List<Song> allSongs = myTunesFacade.getALlSongs();
        allAvailableSongs.addAll(allSongs);
        return allAvailableSongs;
    }

    public ObservableList<Song> getFromCache() {
        return allAvailableSongs;
    }

    public void playStopSong() throws MyTunesManagerException, SongPlayerException {
        myTunesFacade.playStopSong();
    }

    public void setCurrentSong(Song song) throws SongPlayerException {
        myTunesFacade.setCurrentSong(song);
    }

    public Song getCurrentSong()
    {
        return myTunesFacade.getCurrentSong();
    }

    public Song addSong(Song song) throws SongDAOException {
        Song songCreated = myTunesFacade.createSong(song);
        return songCreated;

    }


    public ObservableList getAllCategories() throws CategorySongDAOException {
        ObservableList<CategorySong> listCategories = FXCollections.observableArrayList();
        listCategories.addAll(myTunesFacade.getALlCategorySong());
        return listCategories;
    }

    public void updateSong(Song song) throws SongDAOException {
        myTunesFacade.updateSong(song);
    }

    public void deleteSong(Song song) throws SongDAOException {
        myTunesFacade.deleteSong(song);
    }

    public void setVolume(double value) {
        myTunesFacade.setVolume((int) value);
    }
}
