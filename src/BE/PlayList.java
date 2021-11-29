package BE;

import java.util.HashMap;

public class PlayList {
    //private int dataBaseId;
    private int idPlaylist;
   // private Song song;
    private String name;
   // private int rankSong;
    private HashMap<Integer,Song> listSong;
    public PlayList(int dataBaseId, int idPlaylist, HashMap<Integer,Song> songList, String name) {


        this.idPlaylist = idPlaylist;

        this.name = name;

    }

   // public int getDataBaseId() {
    //    return dataBaseId;
   // }


    public int getIdPlaylist() {
        return idPlaylist;
    }

   // public Song getSong() {
  //      return song;
   // }

   // public void setSong(Song song) {
   //     this.song = song;
    //}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public int getRankSong() {
     //   return rankSong;
   // }

   // public void setRankSong(int rankSong) {
    //    this.rankSong = rankSong;
   // }

    // really really cool comment

}
