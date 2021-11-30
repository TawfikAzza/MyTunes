package DAL;

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
import DAL.interfaces.IAuthorDataAccess;
import DAL.interfaces.ICategorySongDataAccess;
import DAL.interfaces.IPlayListDataAccess;
import DAL.interfaces.ISongDataAccess;

import java.io.IOException;
import java.util.List;

public class DALMyTunesFacade implements IDALMyTunesFacade {
    IAuthorDataAccess iAuthorDataAccess;
    ICategorySongDataAccess iCategorySongDataAccess;
    IPlayListDataAccess iPlayListDataAccess;
    ISongDataAccess iSongDataAccess;

    public DALMyTunesFacade() throws Exception {
        iAuthorDataAccess = new AuthorDAO();
        iCategorySongDataAccess = new CategoryDAO();
        iPlayListDataAccess = new PlayListDAO();
        iSongDataAccess = new SongDAO();
    }

    @Override
    public Author getAuthor(int idAuthor) throws Exception{
        return iAuthorDataAccess.getAuthor(idAuthor);
    }

    @Override
    public List<Author> getALlAuthors() throws Exception {
        return iAuthorDataAccess.getALlAuthors();
    }

    @Override
    public Author createAuthor(String authorName) throws Exception {
        return iAuthorDataAccess.createAuthor(authorName);
    }

    @Override
    public void updateAuthor(Author author) throws Exception {
        iAuthorDataAccess.updateAuthor(author);
    }

    @Override
    public void deleteAuthor(Author author) throws Exception {
        iAuthorDataAccess.deleteAuthor(author);
    }

    @Override
    public CategorySong getCategorySong(int idCategory) throws Exception {
        return iCategorySongDataAccess.getCategorySong(idCategory);
    }

    @Override
    public List<CategorySong> getALlCategorySong() throws Exception {
        return iCategorySongDataAccess.getALlCategorySong();
    }

    @Override
    public CategorySong createCategorySong(CategorySong category) throws Exception {
        return iCategorySongDataAccess.createCategorySong(category);
    }

    @Override
    public void updateCategorySong(CategorySong categorySong) throws Exception {
        iCategorySongDataAccess.updateCategorySong(categorySong);
    }

    @Override
    public void deleteCategorySong(CategorySong categorySong) throws Exception {
        iCategorySongDataAccess.deleteCategorySong(categorySong);
    }

    @Override
    public PlayList getPlayList(int idPlayList) throws Exception {
        return iPlayListDataAccess.getPlayList(idPlayList);
    }

    @Override
    public List<PlayList> getALlPlayLists() throws Exception {
        return iPlayListDataAccess.getALlPlayLists();
    }

    @Override
    public PlayList createPlayList(PlayList playList) throws Exception {
        return iPlayListDataAccess.createPlayList(playList);
    }

    @Override
    public void updatePlayList(PlayList playList) throws Exception {
        iPlayListDataAccess.updatePlayList(playList);
    }

    @Override
    public void deletePlayList(PlayList playList) throws Exception {
        iPlayListDataAccess.deletePlayList(playList);
    }

    @Override
    public Song getSong(int idSong) throws Exception {
        return iSongDataAccess.getSong(idSong);
    }

    @Override
    public List<Song> getALlSongs() throws Exception {
        return iSongDataAccess.getALlSongs();
    }

    @Override
    public Song createSong(Song song) throws Exception {
        return iSongDataAccess.createSong(song);
    }

    @Override
    public void updateSong(Song song) throws Exception {
        iSongDataAccess.updateSong(song);
    }

    @Override
    public void deleteSong(Song song) throws Exception {
        iSongDataAccess.deleteSong(song);
    }
}
