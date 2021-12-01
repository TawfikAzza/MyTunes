package BLL.util;

import BE.Song;
import BLL.exception.SongPlayerException;
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
            //File file = new File("C:/Users/EASV/Desktop/SCO/MyTunes/resources/finalBoss.mp3");
            Media media = new Media("C:/Users/EASV/Desktop/SCO/MyTunes/resources/finalBoss.mp3");
        }
        catch (NullPointerException e)
        {
            throw new SongPlayerException("No song is selected!", e);
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
