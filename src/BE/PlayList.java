package BE;

import java.util.HashMap;

public class PlayList {
    private int idPlaylist;
    private String name;
    private HashMap<Integer,Song> listSong;
    private Integer sizeList=0;
    public PlayList(int idPlaylist, String name ,HashMap<Integer,Song> songList ) {
        this.idPlaylist = idPlaylist;
        this.name = name;
        listSong=songList;
        sizeList=songList.size();
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

    @Override
    public String toString() {
        return String.format("%s",getName());
    }
}
