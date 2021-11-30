package DAL.interfaces;

import BE.PlayList;
import BE.Song;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ISongDataAccess {
    Song getSong(int idSong) throws Exception;
    List<Song> getALlSongs() throws Exception;
    Song createSong(Song song) throws Exception;
    void updateSong(Song song) throws Exception;
    void deleteSong(Song song) throws Exception;
}
