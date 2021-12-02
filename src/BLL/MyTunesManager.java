package BLL;

import BE.Author;
import BE.CategorySong;
import BE.PlayList;
import BE.Song;
import BLL.exception.*;
import BLL.util.SongPlayer;
import DAL.DALMyTunesFacade;
import DAL.DB.AuthorDAO;
import DAL.DB.CategoryDAO;
import DAL.DB.PlayListDAO;
import DAL.DB.SongDAO;
import DAL.IDALMyTunesFacade;
import DAL.interfaces.IAuthorDataAccess;
import DAL.interfaces.ICategorySongDataAccess;
import DAL.interfaces.IPlayListDataAccess;
import DAL.interfaces.ISongDataAccess;

import java.util.List;

public class MyTunesManager implements MyTunesFacade{
    SongPlayer songPlayer;

    IDALMyTunesFacade idalMyTunesFacade;

    public MyTunesManager() throws MyTunesManagerException {
        songPlayer = SongPlayer.getInstance();
        try
        {
            idalMyTunesFacade = new DALMyTunesFacade();
        } catch (Exception e)
        {
            throw new MyTunesManagerException("Failed to initialize MyTunesManager class!", e);
        }
    }

    public Author getAuthor(int id) throws AuthorDAOException {
        try {
            return idalMyTunesFacade.getAuthor(id);
        } catch (Exception e) {
            throw new AuthorDAOException("Cannot get author!", e);
        }
    }

    public List<Author> getALlAuthors() throws AuthorDAOException {
        try {
            return idalMyTunesFacade.getALlAuthors();
        } catch (Exception e) {
            throw new AuthorDAOException("Cannot get all authors!", e);
        }
    }

    public Author createAuthor(String name) throws AuthorDAOException {
        try {
            return idalMyTunesFacade.createAuthor(name);
        } catch (Exception e) {
            throw new AuthorDAOException("Cannot create the author!", e);
        }
    }

    public void updateAuthor(Author author) throws AuthorDAOException {
        try {
            idalMyTunesFacade.updateAuthor(author);
        } catch (Exception e) {
            throw new AuthorDAOException("Cannot update author!", e);
        }
    }
    public void deleteAuthor(Author author) throws AuthorDAOException {
        try {
            idalMyTunesFacade.updateAuthor(author);
        } catch (Exception e) {
            throw new AuthorDAOException("Cannot delete author!", e);
        }
    }

    public CategorySong getCategorySong(int id) throws CategorySongDAOException {
        try {
            return idalMyTunesFacade.getCategorySong(id);
        } catch (Exception e) {
            throw new CategorySongDAOException("Cannot get song category!", e);
        }
    }

    public List<CategorySong> getALlCategorySong() throws CategorySongDAOException {
        try {
            return idalMyTunesFacade.getALlCategorySong();
        } catch (Exception e) {
            throw new CategorySongDAOException("Cannot get all song categories!", e);
        }
    }

    public CategorySong createCategorySong(CategorySong category) throws CategorySongDAOException {
        try {
            return idalMyTunesFacade.createCategorySong(category);
        } catch (Exception e) {
            throw new CategorySongDAOException("Cannot create song category!", e);
        }
    }

    public void updateCategorySong(CategorySong category) throws CategorySongDAOException {
        try {
            idalMyTunesFacade.updateCategorySong(category);
        } catch (Exception e) {
            throw new CategorySongDAOException("Cannot update song category!", e);
        }
    }

    public void deleteCategorySong(CategorySong category) throws CategorySongDAOException {
        try {
            idalMyTunesFacade.deleteCategorySong(category);
        } catch (Exception e) {
            throw new CategorySongDAOException("Cannot create song category!", e);
        }
    }

    public PlayList getPlayList(int id) throws PlayListDAOException {
        try {
            return idalMyTunesFacade.getPlayList(id);
        } catch (Exception e) {
            throw new PlayListDAOException("Cannot get playlist!", e);
        }
    }

    public List<PlayList> getALlPlayLists() throws PlayListDAOException {
        try {
            return idalMyTunesFacade.getALlPlayLists();
        } catch (Exception e) {
            throw new PlayListDAOException("Cannot get all playlists!", e);
        }
    }

    public PlayList createPlayList(PlayList playList) throws PlayListDAOException {
        try {
            return idalMyTunesFacade.createPlayList(playList);
        } catch (Exception e) {
            throw new PlayListDAOException("Cannot create playlist!", e);
        }
    }

    public void updatePlayList(PlayList playList) throws PlayListDAOException {
        try {
            idalMyTunesFacade.updatePlayList(playList);
        } catch (Exception e) {
            throw new PlayListDAOException("Cannot update playlist!", e);
        }
    }

    public void deletePlayList(PlayList playList) throws PlayListDAOException {
        try {
            idalMyTunesFacade.deletePlayList(playList);
        } catch (Exception e) {
            throw new PlayListDAOException("Cannot delete playlist!", e);
        }
    }

    public Song getSong(int id) throws SongDAOException {
        try {
            return idalMyTunesFacade.getSong(id);
        } catch (Exception e) {
            throw new SongDAOException("Unable to get song!", e);
        }
    }

    public List<Song> getALlSongs() throws SongDAOException {
        try {
            return idalMyTunesFacade.getALlSongs();
        } catch (Exception e) {
            throw new SongDAOException("Unable to get song!", e);
        }
    }

    public Song createSong(Song song) throws SongDAOException {
        try {
            return idalMyTunesFacade.createSong(song);
        } catch (Exception e) {
            throw new SongDAOException("Unable to create song!", e);
        }
    }

    public void updateSong(Song song) throws SongDAOException {
        try {
            idalMyTunesFacade.updateSong(song);
        } catch (Exception e) {
            throw new SongDAOException("Unable to update song!", e);
        }
    }

    public void deleteSong(Song song) throws SongDAOException {
        try {
            idalMyTunesFacade.deleteSong(song);
        } catch (Exception e) {
            throw new SongDAOException("Unable to delete song!", e);
        }
    }

    public void playStopSong() throws SongPlayerException {
        songPlayer.playStopSong();
    }

    public void setCurrentSong(Song song)
    {
        songPlayer.setCurrentSong(song);
    }

    public Song getCurrentSong()
    {
        return songPlayer.getCurrentSong();
    }
}
