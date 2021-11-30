package DAL.interfaces;

import BE.Author;
import BE.CategorySong;
import BE.PlayList;

import java.util.List;

public interface IPlayListDataAccess {
    PlayList getPlayList(int idPlayList) throws Exception;
    List<PlayList> getALlPlayLists() throws Exception;
    PlayList createPlayList(PlayList playList) throws Exception;
    void updatePlayList(PlayList playList) throws Exception;
    void deletePlayList(PlayList playList) throws Exception;
}
