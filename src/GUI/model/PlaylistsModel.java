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

import java.util.ArrayList;
import java.util.HashMap;
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

    public ObservableList<Song> getPlayListSelected(PlayList selectedItem) throws PlayListDAOException {
        ObservableList<Song> allSongList = FXCollections.observableArrayList();
        List<Song> allSongFromPlayList;
        allSongFromPlayList = new ArrayList<Song>(myTunesFacade.getPlayList(selectedItem.getIdPlaylist()).getListSong().values());

        allSongList.addAll(allSongFromPlayList);
        return allSongList;
    }
    public void createNewPlaylist(String name) throws PlayListDAOException {
        HashMap<Integer,Song> songList = null;
        myTunesFacade.createPlayList(new PlayList(name, songList));
    }
}
