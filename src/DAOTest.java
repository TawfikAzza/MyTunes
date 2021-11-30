import BE.Author;
import BE.CategorySong;
import BE.Song;
import DAL.DB.AuthorDAO;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DAOTest {
    public static void main(String[] args) throws IOException {
        //getAllAuthors();
       // updateAuthor();
        testFile();
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
    public static void testFile() throws IOException {


       /* File file = new File("C:/Users/EASV/Desktop/Downloads/Old-cat.jpg");
        System.out.println(file);
        //System.out.println(getClass().getResource("/").getHost());
        Author author = new Author(1,"Jeppe");
        CategorySong categorySong = new CategorySong(1,"testCat");
        Song mySong = new Song(1,"test song",author,categorySong,file);*/
        System.out.println("test res: ");
       /* Path src = Paths.get(file.getAbsolutePath());
        Path dest = Paths.get("C:/Users/EASV/Desktop/SCO/MyTunes/resources/" + file.getName().toString());*/
        //
        // Path dest = Paths.get(Objects.requireNonNull(getClass().getResource("/")).getPath()+file.getName().toString());
        //Path dest = Paths.get("/"+file.getName().toString());
      /*  System.out.println("src:" + src + "  dest:" + dest);
        Files.copy(src, dest);*/
    }
}
