package GUI.model;

import BE.Author;
import BLL.MyTunesFacade;
import BLL.MyTunesManager;
import BLL.exception.AuthorDAOException;
import BLL.exception.MyTunesManagerException;
import javafx.collections.FXCollections;

public class AuthorModel {
    private MyTunesFacade myTunesFacade;

    public AuthorModel() throws MyTunesManagerException {
        this.myTunesFacade = new MyTunesManager();
    }
    public Author createNewAuthor(String name) throws AuthorDAOException {
        return myTunesFacade.createAuthor(name);
    }
}
