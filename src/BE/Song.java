package BE;



import BLL.exception.SongException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class Song {
    private int id;
    private String name;
    private Author author;
    private CategorySong category;
    private File songFile;
    private int duration;
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

    public Song(String name, Author author, CategorySong category, File file, int duration) throws Exception {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        setSongFile(file);
        this.duration=duration;
    }
    /***
     * Constructor used when creating the Object Song from the UI, as the UI will take the duration as an Int
     * With the format mm:ss, this method will simply calculate a duration in seconds according to the
     * <String entered, convert it into an int and return the value which will in turn be stored as an instance variable
     * and used for database operation as the Song Table stores an int type value for it to be used
     * later on to calculate the total duration of a PlayList.
     * */
    public Song(int id,String name, Author author, CategorySong category, File file, String duration) throws Exception {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        setSongFile(file);
        this.duration=setStringToIntDuration(duration);
    }

    private int setStringToIntDuration(String duration) {
        int transformedDuration=0;
        String[] arrayStringDuration = duration.split(":");
        if(arrayStringDuration.length==2) {
            int minutes = Integer.parseInt(arrayStringDuration[0]);
            int seconds = Integer.parseInt(arrayStringDuration[1]);
            transformedDuration=(minutes*60)+seconds;
        } else {
            transformedDuration = 0;
        }
        return transformedDuration;
    }

    public String getStringDuration() {
        int minutes = this.duration/60;
        int seconds = this.duration % 60;
        return minutes+":"+seconds;
    }

    /**
     * This constructor will be used when retrieving info from the database.
     * As the database will only store the name of the file and not its path or the file itself
     * */
    public Song(int id, String name, Author author, CategorySong category, String songfile, int duration) throws Exception {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        setSongFile(new File(songfile));
        this.duration=duration;
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

    public int getIntDuration() {
        return duration;
    }

    public void setIntDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("%s by %s",getName(),getAuthor());
    }
}
