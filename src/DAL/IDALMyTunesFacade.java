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
    Author getAuthor(int idAuthor);
    /**
     * Get all the author contained in the database
     * return a List of object author
     * */
    List<Author> getALlAuthors();
    /**
     * Create an Author in the Database
     * return the author object created.
     * */
    Author createAuthor(String authorName);
    /**
     * Update an existing author,
     * return nothing
     * */
    void updateAuthor(Author author);

    /**
     * Delete an existing author from the database
     *
     * */
    void deleteAuthor(Author author);


    /**
     * Get a category
     * */
    CategorySong getCategorySong(int idCategory);

    /***
     * Retrieve all Catergories from the database
     */

    List<CategorySong> getALlCategorySong();
    /**
     * Create a new Category
     * */
    CategorySong createCategorySong(CategorySong category);
    /**
     * Update an existing category
     * */
    void updateCategorySong(int idCategory);

    /**
     * Delete a category from the database
     * */
    void deleteCategorySong(int idCategory);

    /**
     * PlayList Section
     * */



    /***
     * Retrieve a playlist
     */
    PlayList getPlayList(int idPlayList);
    /**
     * Get all the playlists
     * */
    List<PlayList> getALlPlayLists();

    /**
     * Create a new Playlist
     * */
    PlayList createPlayList(PlayList playList);

    /**
     * Update a playlist in the database
     * */
    void updatePlayList(int idPlayList);

    /**
     * Delete a playlist from the database
     * */
    void deletePlayList(int idPlayList);


    /***
     * Song Section
     *
     * */


    /**
     * Get a song
     * */
    Song getSong(int idSong) throws IOException;

    /**
     * Get all the songs from the database
     * */
    List<Song> getALlSongs() throws IOException;

    /**
     * Create a song in the database
     * */
    Song createSong(Song song);

    /**
     * Update a song from the database
     * */
    void updateSong(int idSong);

    /**
     * Delete a song from the database
     * */
    void deleteSong(int idSong);
}
