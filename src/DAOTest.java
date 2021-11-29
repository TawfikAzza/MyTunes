import BE.Author;
import DAL.DB.AuthorDAO;

import java.io.IOException;
import java.util.List;

public class DAOTest {
    public static void main(String[] args) throws IOException {
        //getAllAuthors();
        updateAuthor();
    }

    public static void getAllAuthors() throws IOException {
        AuthorDAO authorDAO = new AuthorDAO();
        List<Author> authorList = authorDAO.getALlAuthors();
        for (Author a: authorList) {
            System.out.println("AuthorID: "+a.getId()+" Author Name:"+a.getName());
        }
    }

    public static void updateAuthor() throws IOException {
        AuthorDAO authorDAO = new AuthorDAO();
        Author author = new Author(1,"Jeppe Moritz");
        authorDAO.updateAuthor(author);

    }
}
