package DAL.DB;

import BE.Author;
import BE.CategorySong;
import BE.Song;
import DAL.ConnectionManager;
import DAL.interfaces.ISongDataAccess;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Song Databse management Class, it work with the table:
 * SONG, AUTHOR and CATEGORY
 * and modify the content of SONG
 * The table AUTHOR and CATEGORY are only used in order to return the references of author or
 * category associated with the song returned in order to provide Author and Category objects references.
 *
 * */
public class SongDAO implements ISongDataAccess {
    private ConnectionManager cm;
    public SongDAO() throws Exception {
        cm = new ConnectionManager();
    }
    @Override
    public Song getSong(int idSong) throws Exception {
        Song songSearched = null;
        HashMap<Integer,Author> allAuthorMap = getMapAuthor();
        HashMap<Integer,CategorySong> allCategoryMap = getMapCategory();
        try (Connection con = cm.getConnection()) {
            String sqlcommandSelect = "SELECT * FROM Song WHERE id=?;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);
            pstmtSelect.setInt(1,idSong);
            ResultSet rs = pstmtSelect.executeQuery();
            while(rs.next())
            {
                songSearched= new Song(
                        rs.getInt("id"),
                        rs.getString("name"),
                        allAuthorMap.get(rs.getInt("authorID")),
                        allCategoryMap.get(rs.getInt("categoryID")),
                        rs.getString("songFile"),
                        rs.getInt("duration")
                );
            }
        }

        return songSearched;
    }

    @Override
    public List<Song> getALlSongs() throws Exception {
        List<Song> allSongs = new ArrayList<>();
        HashMap<Integer,Author> allAuthorMap = getMapAuthor();
        HashMap<Integer,CategorySong> allCategoryMap = getMapCategory();
        try (Connection con = cm.getConnection()) {
            String sqlcommandSelect = "SELECT * FROM Song;";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);

            ResultSet rs = pstmtSelect.executeQuery();
            while(rs.next())
            {
                allSongs.add(new Song(
                        rs.getInt("id"),
                        rs.getString("name"),
                        allAuthorMap.get(rs.getInt("authorID")),
                        allCategoryMap.get(rs.getInt("categoryID")),
                        rs.getString("songFile"),
                        rs.getInt("duration")
                        )
                );
            }
        }
        return allSongs;
    }

    @Override
    public Song createSong(Song song) throws Exception {
        Song songCreated = null;
        try (Connection con = cm.getConnection()) {
            String sqlcommandInsert = "INSERT INTO SONG VALUES (?,?,?,?,?);";
            PreparedStatement pstmtInsert = con.prepareStatement(sqlcommandInsert, Statement.RETURN_GENERATED_KEYS);
            pstmtInsert.setString(1,song.getName());
            pstmtInsert.setInt(2,song.getAuthor().getId());
            pstmtInsert.setInt(3,song.getCategory().getId());
            pstmtInsert.setString(4,song.getStringSongFile());
            pstmtInsert.setInt(5,song.getIntDuration());
            pstmtInsert.execute();
            ResultSet rs = pstmtInsert.getGeneratedKeys();
            while(rs.next())
            {
                songCreated = new Song(
                                rs.getInt(1),
                                song.getName(),
                                song.getAuthor(),
                                song.getCategory(),
                                song.getStringSongFile(),
                                song.getIntDuration()
                    );
            }
        }

        return songCreated;
    }

    @Override
    public void updateSong(Song song) throws Exception {
        try (Connection con = cm.getConnection()) {
            String sqlcommandUpdate = "UPDATE SONG SET name=?, authorID=?,categoryID=?,songFile=?,duration=? WHERE id = ?;";
            PreparedStatement pstmtUpdate = con.prepareStatement(sqlcommandUpdate);
            pstmtUpdate.setString(1,song.getName());
            pstmtUpdate.setInt(2,song.getAuthor().getId());
            pstmtUpdate.setInt(3,song.getCategory().getId());
            pstmtUpdate.setString(4,song.getStringSongFile());
            pstmtUpdate.setInt(5,song.getIntDuration());
            pstmtUpdate.setInt(6,song.getId());
            pstmtUpdate.executeUpdate();

        }

    }

    @Override
    public void deleteSong(Song song) throws Exception {
        try (Connection con = cm.getConnection()) {
            String sqlCommandDeleteListSong = "DELETE FROM CORR_SONG_PLAYLIST WHERE songID=?;";
            PreparedStatement pstmtDeleteListSong = con.prepareStatement(sqlCommandDeleteListSong);
            pstmtDeleteListSong.setInt(1, song.getId());
            pstmtDeleteListSong.execute();

            String sqlcommandDelete = "DELETE FROM  SONG WHERE id = ?;";
            PreparedStatement pstmtDelete = con.prepareStatement(sqlcommandDelete);
            pstmtDelete.setInt(1,song.getId());
            pstmtDelete.execute();
        }

    }

    private HashMap<Integer,Author> getMapAuthor() throws Exception {
        AuthorDAO authorDAO = new AuthorDAO();
        List<Author> allAuthors = authorDAO.getALlAuthors();
        HashMap<Integer,Author> authorMap = new HashMap<>();
        for (Author aut: allAuthors) {
            if(!authorMap.containsKey(aut.getId())) {
                authorMap.put(aut.getId(),aut);
            }
        }
        return authorMap;
    }
    private HashMap<Integer, CategorySong> getMapCategory() throws Exception {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<CategorySong> allCategories = categoryDAO.getALlCategorySong();
        HashMap<Integer,CategorySong> categoryMap = new HashMap<>();
        for (CategorySong cat: allCategories) {
            if(!categoryMap.containsKey(cat.getId())) {
                categoryMap.put(cat.getId(),cat);
            }
        }
        return categoryMap;
    }
}
