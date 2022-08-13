package resources;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The type Resource controller.
 */
public class ResourceController {
    private MediaPlayer mediaplayer;

    private List<String> playList = new ArrayList<>();
    private List<String> playListMenu = new ArrayList<>(Arrays.asList("/resources/music/Hadaka No Yusha.mp3", "/resources/music/dangerous-times-richard-bodgers-main-version-03-52-7781.mp3"));
    private List<String> playlistBattle = new ArrayList<>(Arrays.asList("/resources/music/epic-choir-of-the-damned-giulio-fazio-main-version-01-01-9283.mp3", "/resources/music/dance-of-devils-giulio-fazio-main-version-01-15-14356.mp3"));
    private List<String> playlistWorld = new ArrayList<>(Arrays.asList("/resources/music/rise-of-the-hero-vens-adams-main-version-02-27-454.mp3", "/resources/music/Adventure.mp3"));
    private List<String> playlistGameOver = new ArrayList<>(Arrays.asList("/resources/music/despair-and-triumph-kevin-macleod-main-version-04-40-7981.mp3", "/resources/music/Sadness_and_Sorrow.mp3"));
    private List<String> playlistBoss = new ArrayList<>(Arrays.asList("/resources/music/Rising Legend.mp3", "/resources/music/All_for_one.mp3"));
    private double currentVolume = 1;
    private int playListIndex = 0;


    /**
     * Play music.
     */
    public void playMusic() {
        mediaplayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaplayer.seek(Duration.ZERO);
            }
        });

        mediaplayer.play();
    }

    public void mainMenuMusic() {
        Collections.shuffle(playListMenu);
        playList = playListMenu;
        mediaplayer = new MediaPlayer(new Media(getClass().getResource(playListMenu.get(0)).toExternalForm()));
        mediaplayer.setVolume(currentVolume);
        playMusic();
    }

    /**
     * World music.
     */
//Play 8-bit music
    public void worldMusic() {
        mediaplayer.stop();
        Collections.shuffle(playlistWorld);
        playList = playlistWorld;
        mediaplayer = new MediaPlayer(new Media(getClass().getResource(playlistWorld.get(0)).toExternalForm()));
        mediaplayer.setVolume(currentVolume);
        playMusic();
    }

    public void battleMusic() {
        mediaplayer.stop();
        Collections.shuffle(playlistBattle);
        mediaplayer = new MediaPlayer(new Media(getClass().getResource(playlistBattle.get(0)).toExternalForm()));
        mediaplayer.setVolume(currentVolume);
        playMusic();
    }

    public void bossMusic() {
        mediaplayer.stop();
        Collections.shuffle(playlistBoss);
        mediaplayer = new MediaPlayer(new Media(getClass().getResource(playlistBoss.get(0)).toExternalForm()));
        mediaplayer.setVolume(currentVolume);
        playMusic();
    }

    /**
     * Game over.
     */
    public void gameOver() {
        mediaplayer.stop();
        Collections.shuffle(playlistGameOver);
        mediaplayer = new MediaPlayer(new Media(getClass().getResource(playlistGameOver.get(0)).toExternalForm()));
        mediaplayer.setVolume(currentVolume);
        playMusic();
    }

    /**
     * Play sound effect.
     *
     * @param effect the effect
     */
    public void playSoundEffect(String effect) {
        AudioClip buzzer = new AudioClip(getClass().getResource("/resources/music/lazer.mp3").toExternalForm());
        buzzer.play();

    }

    /**
     * Change volume.
     *
     * @param value the value
     */
    public void changeVolume(double value) {
        currentVolume = value;
        mediaplayer.setVolume(value);
    }


    /**
     * Gets current volume.
     *
     * @return the current volume
     */
    public double getCurrentVolume() {
        return currentVolume;
    }

    /**
     * Is paused boolean.
     *
     * @return the boolean
     */
    public boolean isPaused() {
        if (mediaplayer.getStatus() == Status.PAUSED) return true;
        return false;
    }

    /**
     * Pause music.
     */
    public void pauseMusic() {
        mediaplayer.pause();
    }

    /**
     * Un pause music.
     */
    public void unPauseMusic() {
        if (mediaplayer.getStatus() == Status.PAUSED) {
            mediaplayer.play();
        }
    }

    /**
     * Next song.
     */
    public void nextSong() {
        mediaplayer.stop();
        playListIndex++;
        if (playListIndex == playList.size()) {
            playListIndex = 0;
        }
        mediaplayer = new MediaPlayer(new Media(getClass().getResource(playList.get(playListIndex)).toExternalForm()));
        mediaplayer.setVolume(currentVolume);
        playMusic();
    }

    /**
     * Gets mediaplayer.
     *
     * @return the mediaplayer
     */
    public MediaPlayer getMediaplayer() {
        return mediaplayer;
    }

    /**
     * Stop world music.
     */
    public void stopMusic() {
        mediaplayer.dispose();
    }

}
