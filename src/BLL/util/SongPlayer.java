package BLL.util;

import BE.Song;
import BLL.exception.SongPlayerException;
import DAL.DB.SongDAO;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SongPlayer {
    private final static float MAX_VOLUME = 100;

    MediaPlayer player;
    private Song currentSong;
    private float volume;

    /*
    The default volume should e 50
     */
    public SongPlayer()
    {
        volume = MAX_VOLUME/2;
    }

    /*
    Plays the current song, if there is any.
    TODO: play/pause function
    */
    public void playAudio() throws SongPlayerException {
        try
        {
            SongDAO songDAO = new SongDAO();
            Song testSong = songDAO.getSong(1);
            System.out.println("File: "+testSong.getStringSongFile().replace("\\","/"));
            File file = new File(testSong.getStringSongFile().replace("\\","/"));
            String path = file.toString();

            path= path.replace("\\","/").replace(" ","%20");
            System.out.println("Path: "+path);
            // path = file.toURI().toASCIIString();

            Media media = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
        catch (NullPointerException e)
        {
            throw new SongPlayerException("No song is selected!", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCurrentSong(Song song)
    {
        this.currentSong = song;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    /*
    Setting the volume from 0-100 and clamped accordingly to the needs of the media player
     */
    public void setVolume(int soundVolume)
    {
        volume = (float) (1 - (Math.log(MAX_VOLUME - soundVolume) / Math.log(MAX_VOLUME)));
        player.setVolume(volume);
    }

    public float getVolume()
    {
        return volume;
    }
}
