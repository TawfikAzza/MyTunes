package BE;

import java.util.HashMap;
import java.util.Map;

public class PlayList {
    private int idPlaylist;
    private String name;
    //private String sizeListString="test";
    private Integer sizeList=0;
    private String totalDuration;
    private HashMap<Integer,Song> listSong;

    public PlayList(int idPlaylist, String name ,HashMap<Integer,Song> songList ) {
        this.idPlaylist = idPlaylist;
        this.name = name;
        listSong=songList;
        sizeList=songList.size();
        totalDuration=totalDurationCount();
    }
    public String getTotalDuration() {
        return totalDuration;
    }
    public String getSizeListString() {
        return ""+sizeList;
    }

    public HashMap<Integer, Song> getListSong() {
        return listSong;
    }

    public String totalDurationCount() {
        int total=0;
        for (Map.Entry entry: listSong.entrySet()) {
            Song song = (Song) entry.getValue();
            total+=song.getIntDuration();
        }
        int hours = total/3600;
        int minutes = total/60;
        int seconds = total%60;
        return String.format("%02d:%02d:%02d",hours,minutes,seconds);
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
