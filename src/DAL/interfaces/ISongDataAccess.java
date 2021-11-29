package DAL.interfaces;

import BE.PlayList;
import BE.Song;

import java.io.IOException;
import java.util.List;

public interface ISongDataAccess {
    Song getSong(int idSong) throws IOException;
    List<Song> getALlSongs() throws IOException;
    Song createSong(Song song);
    void updateSong(Song song);
    void deleteSong(Song song);
}
