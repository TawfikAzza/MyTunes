package DAL.DB;

import BE.Author;
import BE.CategorySong;
import BE.Song;
import DAL.ConnectionManager;
import DAL.interfaces.ISongDataAccess;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SongDAO implements ISongDataAccess {
    private ConnectionManager cm;
    public SongDAO() throws IOException {
        cm = new ConnectionManager();
    }
    @Override
    public Song getSong(int idSong) throws IOException {
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
                        allCategoryMap.get(rs.getInt("categoryID"))
                );
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return songSearched;
    }

    @Override
    public List<Song> getALlSongs() throws IOException {
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
                        allCategoryMap.get(rs.getInt("categoryID"))
                        )
                );
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allSongs;
    }

    @Override
    public Song createSong(Song song) {
        Song songCreated = null;
        try (Connection con = cm.getConnection()) {
            String sqlcommandInsert = "INSERT INTO SONG VALUE(?,?,?);";
            PreparedStatement pstmtInsert = con.prepareStatement(sqlcommandInsert, Statement.RETURN_GENERATED_KEYS);
            pstmtInsert.setString(1,song.getName());
            pstmtInsert.setInt(2,song.getAuthor().getId());
            pstmtInsert.setInt(3,song.getCategory().getId());
            pstmtInsert.executeQuery();
            ResultSet rs = pstmtInsert.getGeneratedKeys();
            while(rs.next())
            {
                songCreated = new Song(
                                rs.getInt(1),
                                song.getName(),
                                song.getAuthor(),
                                song.getCategory()
                    );
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return songCreated;
    }

    @Override
    public void updateSong(Song song) {
        try (Connection con = cm.getConnection()) {
            String sqlcommandUpdate = "UPDATE SONG SET name=?, authorID=?,categoryID=? WHERE id = ?;";
            PreparedStatement pstmtUpdate = con.prepareStatement(sqlcommandUpdate);
            pstmtUpdate.setString(1,song.getName());
            pstmtUpdate.setInt(2,song.getAuthor().getId());
            pstmtUpdate.setInt(3,song.getCategory().getId());
            pstmtUpdate.setInt(4,song.getId());
            pstmtUpdate.executeUpdate();

        }
        catch (SQLException ex) {
            Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteSong(Song song) {
        try (Connection con = cm.getConnection()) {
            String sqlcommandDelete = "DELETE FROM  SONG WHERE id = ?;";
            PreparedStatement pstmtDelete = con.prepareStatement(sqlcommandDelete);
            pstmtDelete.setInt(1,song.getId());
        }
        catch (SQLException ex) {
            Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private HashMap<Integer,Author> getMapAuthor() throws IOException {
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
    private HashMap<Integer, CategorySong> getMapCategory() throws IOException {
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
