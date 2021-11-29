package BLL.util;

import BE.Song;
import BLL.exception.SongPlayerException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SongPlayer {
    private final static float MAX_VOLUME = 100;

    MediaPlayer player;
    private Song currentSong;
    private float volume;

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
            Media media = currentSong.getSongFile();
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
        catch (NullPointerException e)
        {
            throw new SongPlayerException("No song is selected!");
        }
    }

    public void setCurrentSong(Song song)
    {
        this.currentSong = song;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

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
