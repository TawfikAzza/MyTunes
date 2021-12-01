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
     * Create a category with the name specified in the Category object
     * Return a CategorySong object containing the ID retrieved from
     * the database after the insert
     * */
    CategorySong createCategorySong(CategorySong category) throws Exception;

    /**
     * Update the name of the category
     * Take a CategorySong object as argument
     * */
    void updateCategorySong(CategorySong categorySong) throws Exception;
    /**
     * Delete a Category
     * returns nothing
     * */
    void deleteCategorySong(CategorySong categorySong) throws Exception;
}
