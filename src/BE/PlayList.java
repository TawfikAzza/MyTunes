package BE;

import java.util.HashMap;

public class PlayList {
    private int idPlaylist;
    private String name;
    private HashMap<Integer,Song> listSong;
    public PlayList(int idPlaylist, String name ,HashMap<Integer,Song> songList ) {
        this.idPlaylist = idPlaylist;
        this.name = name;
        listSong=songList;
    }

    public HashMap<Integer, Song> getListSong() {
        return listSong;
    }

    public void setListSong(HashMap<Integer, Song> listSong) {
        this.listSong = listSong;
    }

    public int getIdPlaylist() {
        return idPlaylist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
