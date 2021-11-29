package DAL.interfaces;

import BE.Author;
import BE.CategorySong;
import BE.PlayList;

import java.util.List;

public interface IPlayListDataAccess {
    PlayList getPlayList(int idPlayList);
    List<PlayList> getALlPlayLists();
    PlayList createPlayList(PlayList playList);
    void updatePlayList(int idPlayList);
    void deletePlayList(int idPlayList);
}
