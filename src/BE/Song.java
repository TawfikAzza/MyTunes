package BE;

import javafx.scene.media.Media;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Song {
    private int id;
    private String name;
    private Author author;
    private CategorySong category;
    private File songFile;

    /***
     * This constructor is to be used when creating the song
     * this will launch the copy of the file to the right folder
     * i.e: root_resources/category/fileName
     * and assign the fileName to the String attribute songFile of the song object.
     * this attribute is the value saved in the databse.
     * Each time the object is instanciated through a query to the database,
     * the path is constructed using the category attribute of the song as well as its name and
     * returns the File as a media to the caller of the getSong method.
     */

    public Song(String name, Author author, CategorySong category, File file) throws Exception {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        setSongFile(file);
    }
    /**
     * This constructor will be used when retrieving info from the database.
     * As the database will only store the name of the file and not its path or the file itself
     * */
    public Song(int id, String name, Author author, CategorySong category, String songfile) throws Exception {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        setSongFile(new File(songfile));
    }



    public int getId() {
        return id;
    }
    /**
     * This method is used only for the database operations, it contains the file name as a String and not the file itself
     * */



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
    /****
     * This method construct the filePath using the category as well as the file name to identify the song
     * in the folder hierarchy and return the Media constructed with the path.
     */

    public File getSongFile() {
        return songFile;
    }

    /**
     * Method used at the creation of the song, it separate the file path from the file received from the
     * FileChooser object as well as making a copy of it in the right folder and assigning the file name
     * to the songFile String attribute.
     * If the target folder doesn't exist, it will create it.
     * */
    public void setSongFile(File file) throws Exception {
        songFile = file;
    }

    public String getStringSongFile() {
        return songFile.toString();
    }
}
