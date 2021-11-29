package BLL;

import BE.Author;
import DAL.AuthorDAO;
import DAL.CategoryDAO;

import java.io.IOException;

public class MyTunesManager {
    AuthorDAO authorDAO;
    CategoryDAO categoryDAO;

    public MyTunesManager() throws IOException {
        authorDAO = new AuthorDAO();
    }
}
