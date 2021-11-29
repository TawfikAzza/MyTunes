package DAL;

import BE.Author;
import BE.CategorySong;
import BE.PlayList;
import BE.Song;
import DAL.interfaces.ISongDataAccess;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public void updateSong(int idSong) {

    }

    @Override
    public void deleteSong(int idSong) {

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
