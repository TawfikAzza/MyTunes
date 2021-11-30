package BLL;

import BE.Author;
import BE.CategorySong;
import BE.PlayList;
import BE.Song;
import BLL.exception.*;
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

    public MyTunesManager() throws MyTunesManagerException {
        songPlayer = new SongPlayer();

        try
        {
            authorDAO = new AuthorDAO();
            categoryDAO = new CategoryDAO();
            playListDAO = new PlayListDAO();
            songDAO = new SongDAO();
        } catch (Exception e)
        {
            throw new MyTunesManagerException("Failed to initalize MyTunesManager class!", e);
        }
    }

    public Author getAuthor(int id) throws AuthorDAOException {
        try {
            return authorDAO.getAuthor(id);
        } catch (Exception e) {
            throw new AuthorDAOException("Cannot get author!", e);
        }
    }

    public List<Author> getALlAuthors() throws AuthorDAOException {
        try {
            return authorDAO.getALlAuthors();
        } catch (Exception e) {
            throw new AuthorDAOException("Cannot get all authors!", e);
        }
    }

    public Author createAuthor(String name) throws AuthorDAOException {
        try {
            return authorDAO.createAuthor(name);
        } catch (Exception e) {
            throw new AuthorDAOException("Cannot create the author!", e);
        }
    }

    public void updateAuthor(Author author) throws AuthorDAOException {
        try {
            authorDAO.updateAuthor(author);
        } catch (Exception e) {
            throw new AuthorDAOException("Cannot update author!", e);
        }
    }
    public void deleteAuthor(Author author) throws AuthorDAOException {
        try {
            authorDAO.updateAuthor(author);
        } catch (Exception e) {
            throw new AuthorDAOException("Cannot delete author!", e);
        }
    }

    public CategorySong getCategorySong(int id) throws CategorySongDAOException {
        try {
            return categoryDAO.getCategorySong(id);
        } catch (Exception e) {
            throw new CategorySongDAOException("Cannot get song category!", e);
        }
    }

    public List<CategorySong> getALlCategorySong() throws CategorySongDAOException {
        try {
            return categoryDAO.getALlCategorySong();
        } catch (Exception e) {
            throw new CategorySongDAOException("Cannot get all song categories!", e);
        }
    }

    public CategorySong createCategorySong(CategorySong category) throws CategorySongDAOException {
        try {
            return categoryDAO.createCategorySong(category);
        } catch (Exception e) {
            throw new CategorySongDAOException("Cannot create song category!", e);
        }
    }

    public void updateCategorySong(CategorySong category) throws CategorySongDAOException {
        try {
            categoryDAO.updateCategorySong(category);
        } catch (Exception e) {
            throw new CategorySongDAOException("Cannot update song category!", e);
        }
    }

    public void deleteCategorySong(CategorySong category) throws CategorySongDAOException {
        try {
            categoryDAO.deleteCategorySong(category);
        } catch (Exception e) {
            throw new CategorySongDAOException("Cannot create song category!", e);
        }
    }

    public PlayList getPlayList(int id) throws PlayListDAOException {
        try {
            return playListDAO.getPlayList(id);
        } catch (Exception e) {
            throw new PlayListDAOException("Cannot get playlist!", e);
        }
    }

    public List<PlayList> getALlPlayLists() throws PlayListDAOException {
        try {
            return playListDAO.getALlPlayLists();
        } catch (Exception e) {
            throw new PlayListDAOException("Cannot get all playlists!", e);
        }
    }

    public PlayList createPlayList(PlayList playList) throws PlayListDAOException {
        try {
            return playListDAO.createPlayList(playList);
        } catch (Exception e) {
            throw new PlayListDAOException("Cannot create playlist!", e);
        }
    }

    public void updatePlayList(PlayList playList) throws PlayListDAOException {
        try {
            playListDAO.updatePlayList(playList);
        } catch (Exception e) {
            throw new PlayListDAOException("Cannot update playlist!", e);
        }
    }

    public void deletePlayList(PlayList playList) throws PlayListDAOException {
        try {
            playListDAO.deletePlayList(playList);
        } catch (Exception e) {
            throw new PlayListDAOException("Cannot delete playlist!", e);
        }
    }

    public Song getSong(int id) throws SongDAOException {
        try {
            return songDAO.getSong(id);
        } catch (Exception e) {
            throw new SongDAOException("Unable to get song!", e);
        }
    }

    public List<Song> getALlSongs() throws SongDAOException {
        try {
            return songDAO.getALlSongs();
        } catch (Exception e) {
            throw new SongDAOException("Unable to get song!", e);
        }
    }

    public Song createSong(Song song) throws SongDAOException {
        try {
            return songDAO.createSong(song);
        } catch (Exception e) {
            throw new SongDAOException("Unable to create song!", e);
        }
    }

    public void updateSong(Song song) throws SongDAOException {
        try {
            songDAO.updateSong(song);
        } catch (Exception e) {
            throw new SongDAOException("Unable to update song!", e);
        }
    }

    public void deleteSong(Song song) throws SongDAOException {
        try {
            songDAO.deleteSong(song);
        } catch (Exception e) {
            throw new SongDAOException("Unable to delete song!", e);
        }
    }
}
