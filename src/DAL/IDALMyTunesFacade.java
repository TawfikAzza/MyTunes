package DAL;

import BE.Author;
import BE.CategorySong;
import BE.PlayList;
import BE.Song;

import java.io.IOException;
import java.util.List;

public interface IDALMyTunesFacade {
    /**
     * Author Section
     * */
    /**
     * Get an Author from the database based on the id in parameter
     * Return an author
     * */
    Author getAuthor(int idAuthor) throws Exception;
    /**
     * Get all the author contained in the database
     * return a List of object author
     * */
    List<Author> getALlAuthors() throws Exception;
    /**
     * Create an Author in the Database
     * return the author object created.
     * */
    Author createAuthor(String authorName) throws Exception;
    /**
     * Update an existing author,
     * return nothing
     * */
    void updateAuthor(Author author) throws Exception;

    /**
     * Delete an existing author from the database
     *
     * */
    void deleteAuthor(Author author) throws Exception;


    /**
     * Get a category
     * */
    CategorySong getCategorySong(int idCategory) throws Exception;

    /***
     * Retrieve all Catergories from the database
     */

    List<CategorySong> getALlCategorySong() throws Exception;
    /**
     * Create a new Category
     * */
    CategorySong createCategorySong(CategorySong category) throws Exception;
    /**
     * Update an existing category
     * */
    void updateCategorySong(CategorySong categorySong) throws Exception;

    /**
     * Delete a category from the database
     * */
    void deleteCategorySong(CategorySong categorySong) throws Exception;

    /**
     * PlayList Section
     * */



    /***
     * Retrieve a playlist
     */
    PlayList getPlayList(int idPlayList) throws Exception;
    /**
     * Get all the playlists
     * */
    List<PlayList> getALlPlayLists() throws Exception;

    /**
     * Create a new Playlist
     * */
    PlayList createPlayList(PlayList playList) throws Exception;

    /**
     * Update a playlist in the database
     * */
    void updatePlayList(PlayList playList) throws Exception;

    /**
     * Delete a playlist from the database
     * */
    void deletePlayList(PlayList playList) throws Exception;


    /***
     * Song Section
     *
     * */


    /**
     * Get a song
     * */
    Song getSong(int idSong) throws IOException, Exception;

    /**
     * Get all the songs from the database
     * */
    List<Song> getALlSongs() throws IOException, Exception;

    /**
     * Create a song in the database
     * */
    Song createSong(Song song) throws Exception;

    /**
     * Update a song from the database
     * */
    void updateSong(Song song) throws Exception;

    /**
     * Delete a song from the database
     * */
    void deleteSong(Song song) throws Exception;
}
