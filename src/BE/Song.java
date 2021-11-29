package BE;

import DAL.AuthorDAO;
import javafx.scene.media.Media;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Song {
    private int id;
    private String name;
    private Author author;
    private CategorySong category;
    private Media songFile;
    private static final String PATH_TO_FILE = "C:/Users/EASV/Desktop/SCO/MyTunes/resources/";
    public Song(int id, String name, Author author, CategorySong category) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public CategorySong getCategory() {
        return category;
    }

    public void setCategory(CategorySong category) {
        this.category = category;
    }

    public Media getSongFile() {
        return songFile;
    }
    public void setSongFile(File file) throws IOException {
        Path src = Paths.get(file.getAbsolutePath());
        Path dest = Paths.get(PATH_TO_FILE + file.getName().toString());
        Files.copy(src, dest);
        songFile = new Media(PATH_TO_FILE + category.getName() + "/" + file.getName());
    }

}
