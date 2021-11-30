package BLL;

import BE.Author;
import BLL.util.SongPlayer;
import DAL.DB.AuthorDAO;
import DAL.DB.CategoryDAO;
import DAL.DB.PlayListDAO;
import DAL.DB.SongDAO;

import java.io.IOException;
import java.util.List;

public class MyTunesManager {
    SongPlayer songPlayer;

    AuthorDAO authorDAO;
    CategoryDAO categoryDAO;
    PlayListDAO playListDAO;
    SongDAO songDAO;

    public MyTunesManager() throws IOException {
        songPlayer = new SongPlayer();

        authorDAO = new AuthorDAO();
        categoryDAO = new CategoryDAO();
        playListDAO = new PlayListDAO();
        songDAO = new SongDAO();
    }

    public Author getAuthor(int id) {
        return authorDAO.getAuthor(id);
    }

    public List<Author> getALlAuthors() {
        return authorDAO.getALlAuthors();
    }

    public Author createAuthor(String name) {
        return authorDAO.createAuthor(name);
    }

    public void updateAuthor(Author author) {
        authorDAO.updateAuthor(author);
    }
    public void deleteAuthor(Author author) {
        authorDAO.updateAuthor(author);
    }

}
