package BLL.util;

import BE.Author;
import BE.CategorySong;
import BE.Song;
import BLL.exception.SongPlayerException;
import DAL.DB.SongDAO;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
/*
To play a song, we need to set a song either from a playlist or a list of songs, when you choose a song from the list of songs, then the "playlist" will be discarded
 */

public class SongPlayer {
    private final static float MAX_VOLUME = 100;
    private static SongPlayer single_instance=null;
    private MediaPlayer player=null;
    private Song currentSong;
    private float volume;

    public static SongPlayer getInstance()
    {
        if (single_instance == null)
            single_instance = new SongPlayer();

        return single_instance;
    }
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
    public MediaPlayer getPlayer() {
        return player;
    }
    public void playStopSong() {
        if (currentSong == null)
        {
            return;
        }
        if(player != null && player.getStatus() == MediaPlayer.Status.PLAYING) {
            player.pause();
            return;
        }
        //System.out.println(player);

        //assert player != null;
        player.play();
    }

    public void setCurrentSong(Song song)
    {
        if(player != null && player.getStatus() == MediaPlayer.Status.PLAYING) {
            player.stop();
        }
        this.currentSong = song;
        final Media media = new Media(toValidPath(currentSong.getStringSongFile()));
        this.player = new MediaPlayer(media);
        //playStopSong();
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

    public String toValidPath(String path)
    {
        return path.replace("\\","/").replace(" ", "%20");
    }
}
