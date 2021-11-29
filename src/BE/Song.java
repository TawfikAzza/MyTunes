package BE;

import DAL.AuthorDAO;
import javafx.scene.media.Media;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Song {
    private int id;
    private String name;
    private Author author;
    private CategorySong category;
    private Media songFile;
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
//    public setSong

}
