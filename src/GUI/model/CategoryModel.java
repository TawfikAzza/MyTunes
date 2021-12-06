package GUI.model;

import BE.Author;
import BE.CategorySong;
import BLL.MyTunesFacade;
import BLL.MyTunesManager;
import BLL.exception.AuthorDAOException;
import BLL.exception.CategorySongDAOException;
import BLL.exception.MyTunesManagerException;

public class CategoryModel {
    private MyTunesFacade myTunesFacade;

    public CategoryModel() throws MyTunesManagerException {
        this.myTunesFacade = new MyTunesManager();
    }
    public CategorySong createNewCategory(String name) throws CategorySongDAOException {
        CategorySong categorySong = new CategorySong(0,name);
        return myTunesFacade.createCategorySong(categorySong);
    }
}
