package BLL;

import BE.Author;
import DAL.AuthorDAO;
import DAL.CategoryDAO;

import java.io.IOException;

public class PlayerManager implements PlayerFacade{
    AuthorDAO authorDAO;
    CategoryDAO categoryDAO;

    public PlayerManager() throws IOException {
        authorDAO = new AuthorDAO();
    }
    @Override
    public void modifyAuthor(Author author) {
        authorDAO.updateAuthor(author);
    }
}
