package DAL.interfaces;

import BE.Author;
import BE.CategorySong;

import java.util.List;

public interface ICategorySongDataAccess {
    /**
     * Get a category from the database based on the id in parameter
     * return a CategorySong object.
     * */
    CategorySong getCategorySong(int idCategory) throws Exception;
    /**
     * Return all the categories contained in the database
     * in a list
     * */
    List<CategorySong> getALlCategorySong() throws Exception;
    /**
     *
     * */
    CategorySong createCategorySong(CategorySong category) throws Exception;
    void updateCategorySong(CategorySong categorySong) throws Exception;
    void deleteCategorySong(CategorySong categorySong) throws Exception;
}
