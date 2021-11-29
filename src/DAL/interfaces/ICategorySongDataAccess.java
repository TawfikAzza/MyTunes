package DAL.interfaces;

import BE.Author;
import BE.CategorySong;

import java.util.List;

public interface ICategorySongDataAccess {
    CategorySong getCategorySong(int idCategory);
    List<CategorySong> getALlCategorySong();
    CategorySong createCategorySong(CategorySong category);
    void updateCategorySong(CategorySong categorySong);
    void deleteCategorySong(CategorySong categorySong);
}
