package BLL.util;

import BE.Song;
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

    public void playAudio()
    {
        Media media = currentSong.getSongFile();
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
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