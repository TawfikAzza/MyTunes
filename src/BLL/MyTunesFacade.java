package BLL;

import BE.Author;
import BE.CategorySong;
import BE.PlayList;
import BE.Song;
import BLL.exception.AuthorDAOException;
import BLL.exception.CategorySongDAOException;
import BLL.exception.PlayListDAOException;
import BLL.exception.SongDAOException;

import java.util.List;

public interface MyTunesFacade {
    public Author getAuthor(int id) throws AuthorDAOException;
    public List<Author> getALlAuthors() throws AuthorDAOException;
    public Author createAuthor(String name) throws AuthorDAOException;
    public void updateAuthor(Author author) throws AuthorDAOException;
    public void deleteAuthor(Author author) throws AuthorDAOException;
    public CategorySong getCategorySong(int id) throws CategorySongDAOException;
    public List<CategorySong> getALlCategorySong() throws CategorySongDAOException;
    public CategorySong createCategorySong(CategorySong category) throws CategorySongDAOException;
    public void updateCategorySong(CategorySong category) throws CategorySongDAOException;
    public void deleteCategorySong(CategorySong category) throws CategorySongDAOException;
    public PlayList getPlayList(int id) throws PlayListDAOException;
    public List<PlayList> getALlPlayLists() throws PlayListDAOException;
    public PlayList createPlayList(PlayList playList) throws PlayListDAOException;
    public void updatePlayList(PlayList playList) throws PlayListDAOException;
    public void deletePlayList(PlayList playList) throws PlayListDAOException;
    public Song getSong(int id) throws SongDAOException;
    public List<Song> getALlSongs() throws SongDAOException;
    public Song createSong(Song song) throws SongDAOException;
    public void updateSong(Song song) throws SongDAOException;
    public void deleteSong(Song song) throws SongDAOException;
}
