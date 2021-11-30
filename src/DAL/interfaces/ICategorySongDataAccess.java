package DAL.interfaces;

import BE.Author;
import BE.CategorySong;

import java.util.List;

public interface ICategorySongDataAccess {
    CategorySong getCategorySong(int idCategory) throws Exception;
    List<CategorySong> getALlCategorySong() throws Exception;
    CategorySong createCategorySong(CategorySong category) throws Exception;
    void updateCategorySong(CategorySong categorySong) throws Exception;
    void deleteCategorySong(CategorySong categorySong) throws Exception;
}
