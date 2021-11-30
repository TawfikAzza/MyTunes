import BE.Author;
import BE.CategorySong;
import BE.PlayList;
import BE.Song;
import DAL.DB.AuthorDAO;
import DAL.DB.PlayListDAO;
import DAL.DB.SongDAO;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOTest {
    public static void main(String[] args) throws Exception {
        //getAllAuthors();
       // updateAuthor();
        // testFile();
        //testSongLength();
        //getAllSongs();
       // createSong();
       // getPlayList();
        getAllPlayList();
    }
    public static void getAllPlayList() throws Exception {
        PlayListDAO playListDAO = new PlayListDAO();
        List<PlayList> allPlayList = playListDAO.getALlPlayLists();
        for (PlayList p:allPlayList) {
            for (Map.Entry entry: p.getListSong().entrySet()) {
                Song song = (Song)entry.getValue();
                System.out.println("idPlayList: "+p.getName()+" Song name: "+song.getName()+" Path: "+song.getStringSongFile());
            }
        }
    }
    public static void getPlayList() throws Exception {
        PlayListDAO playListDAO = new PlayListDAO();
        PlayList playList = playListDAO.getPlayList(1);
        for (Map.Entry entry: playList.getListSong().entrySet()) {
            System.out.println(entry);
        }
    }
    public static void getAllAuthors() throws Exception {
        AuthorDAO authorDAO = new AuthorDAO();
        List<Author> authorList = authorDAO.getALlAuthors();
        for (Author a: authorList) {
            System.out.println("AuthorID: "+a.getId()+" Author Name:"+a.getName());
        }
    }

    public static void updateAuthor() throws Exception {
        AuthorDAO authorDAO = new AuthorDAO();
        Author author = new Author(1,"Jeppe Moritz");
        authorDAO.updateAuthor(author);

    }
    public static void getAllSongs() throws Exception {
        SongDAO songDAO = new SongDAO();
        List<Song> allSongs = songDAO.getALlSongs();
        for (Song so:allSongs) {
            System.out.println("Song name: "+so.getName()+" Author: "+so.getAuthor().getName()+" Category: "+so.getCategory().getName());
        }
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
    public static void createSong() throws Exception {
        SongDAO songDAO = new SongDAO();
        File file = new File("C:/Users/EASV/Desktop/Downloads/Old-cat.jpg");

        Author author = new Author(2,"Adam");
        Author author2 = new Author(3,"Jano");
        Author author3 = new Author(4,"Tawfik");
        Author author4 = new Author(1,"Jeppe");
        CategorySong categorySong = new CategorySong(2,"jhfksdhfk");
        CategorySong categorySong2 = new CategorySong(3,"jhfksdhfk");
        CategorySong categorySong3 = new CategorySong(4,"jhfksdhfk");
        CategorySong categorySong4 = new CategorySong(5,"jhfksdhfk");

        Song mySong1 = new Song("I am Atomic!!!!!",author2,categorySong2,file);
        Song mySong2 = new Song("Song of Love for Jano the artist",author3,categorySong3,file);
        Song mySong3 = new Song("No Tomorrows for the non Java developpers",author4,categorySong4,file);
        List<Song> testSongs = new ArrayList<>();
        testSongs.add(mySong1);
        testSongs.add(mySong2);
        testSongs.add(mySong3);
        for (Song so:testSongs) {
            songDAO.createSong(so);
        }

    }
    public static void testSongLength() {
        Media song = new Media("C:\\Users\\EASV\\Desktop\\SCO\\MyTunes\\resources\\finalBoss.mp3");
        System.out.println("Song length: "+song.getDuration().toMinutes());
    }
}
