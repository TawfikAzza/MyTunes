package DAL.interfaces;

import BE.PlayList;

import java.util.List;

public interface IPlayListDataAccess {
    /**
     * Returns a Playlist with the specified ID.
     * See Also (PlayList Object to know what is returned)
     * */
    PlayList getPlayList(int idPlayList) throws Exception;

    List<PlayList> getALlPlayLists() throws Exception;
    PlayList createPlayList(PlayList playList) throws Exception;
    void updatePlayList(PlayList playList) throws Exception;
    void deletePlayList(PlayList playList) throws Exception;
}
