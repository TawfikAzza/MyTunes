package BLL;

import BE.Author;
import BE.CategorySong;
import BE.PlayList;
import BE.Song;
import BLL.exception.SongDAOException;
import BLL.util.SongPlayer;
import DAL.DB.AuthorDAO;
import DAL.DB.CategoryDAO;
import DAL.DB.PlayListDAO;
import DAL.DB.SongDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MyTunesManager {
    SongPlayer songPlayer;

    AuthorDAO authorDAO;
    CategoryDAO categoryDAO;
    PlayListDAO playListDAO;
    SongDAO songDAO;

    public MyTunesManager() throws IOException {
        songPlayer = new SongPlayer();

        authorDAO = new AuthorDAO();
        categoryDAO = new CategoryDAO();
        playListDAO = new PlayListDAO();
        songDAO = new SongDAO();
    }

    public Author getAuthor(int id) {
        return authorDAO.getAuthor(id);
    }

    public List<Author> getALlAuthors() {
        return authorDAO.getALlAuthors();
    }

    public Author createAuthor(String name) {
        return authorDAO.createAuthor(name);
    }

    public void updateAuthor(Author author) {
        authorDAO.updateAuthor(author);
    }
    public void deleteAuthor(Author author) {
        authorDAO.updateAuthor(author);
    }

    public CategorySong getCategorySong(int id) {
        return categoryDAO.getCategorySong(id);
    }

    public List<CategorySong> getALlCategorySong() {
        return categoryDAO.getALlCategorySong();
    }

    public CategorySong createCategorySong(CategorySong category) {
        return categoryDAO.createCategorySong(category);
    }

    public void updateCategorySong(CategorySong category) {
        categoryDAO.updateCategorySong(category);
    }

    public void deleteCategorySong(CategorySong category) {
        categoryDAO.deleteCategorySong(category);
    }

    public PlayList getPlayList(int id) {
        return playListDAO.getPlayList(id);
    }

    public List<PlayList> getALlPlayLists() {
        return playListDAO.getALlPlayLists();
    }

    public PlayList createPlayList(PlayList playList) {
        return playListDAO.createPlayList(playList);
    }

    public void updatePlayList(PlayList playList) {
        playListDAO.updatePlayList(playList);
    }

    public void deletePlayList(PlayList playList) {
        playListDAO.deletePlayList(playList);
    }

    public Song getSong(int id) throws SongDAOException {
        try {
            return songDAO.getSong(id);
        } catch (IOException e) {
            throw new SongDAOException("Unable to get song!", e);
        }
    }

    public List<Song> getALlSongs() throws SongDAOException {
        try {
            return songDAO.getALlSongs();
        } catch (IOException e) {
            throw new SongDAOException("Unable to get song!", e);
        }
    }

    public Song createSong(Song song) {
        return songDAO.createSong(song);
    }

    public void updateSong(Song song) throws SongDAOException {
        try {
            songDAO.updateSong(song);
        } catch (SQLException e) {
            throw new SongDAOException("Unable to get song!", e);
        }
    }

    public void deleteSong(Song song) {
        songDAO.deleteSong(song);
    }
}
