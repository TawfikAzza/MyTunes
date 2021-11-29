package BLL;

import BE.Author;
import DAL.AuthorDAO;
import DAL.CategoryDAO;

import java.io.IOException;

public class PlayerFacade implements MyTunesFacade {
    AuthorDAO authorDAO;
    CategoryDAO categoryDAO;

    public PlayerFacade() throws IOException {
        authorDAO = new AuthorDAO();
    }
    @Override
    public void modifyAuthor(Author author) {
        authorDAO.updateAuthor(author);
    }
}
