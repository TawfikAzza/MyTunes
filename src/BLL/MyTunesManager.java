package BLL;

import BLL.util.SongPlayer;
import DAL.DB.AuthorDAO;
import DAL.DB.CategoryDAO;

import java.io.IOException;

public class MyTunesManager {
    SongPlayer songPlayer;

    AuthorDAO authorDAO;
    CategoryDAO categoryDAO;

    public MyTunesManager() throws IOException {
        authorDAO = new AuthorDAO();
    }
}
