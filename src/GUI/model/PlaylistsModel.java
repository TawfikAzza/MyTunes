package GUI.model;

import BE.PlayList;
import BE.Song;
import BLL.MyTunesFacade;
import BLL.MyTunesManager;
import BLL.exception.MyTunesManagerException;
import BLL.exception.PlayListDAOException;
import BLL.exception.SongDAOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PlaylistsModel {
    private ObservableList<PlayList> allPlayLists;
    private MyTunesFacade myTunesFacade;

    public PlaylistsModel() throws MyTunesManagerException {
        this.myTunesFacade = new MyTunesManager();
        allPlayLists = FXCollections.observableArrayList();
    }
    public ObservableList<PlayList> getAllPlayLists() throws PlayListDAOException {
        List<PlayList> allAvailablePlaylists = myTunesFacade.getALlPlayLists();
        allPlayLists.addAll(allAvailablePlaylists);
        return allPlayLists;
    }

}
