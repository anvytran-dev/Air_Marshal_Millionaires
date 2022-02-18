package com.millionaires.airmarshal.controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * The source of audio control.
 */
public class Music {

    private final static String AUDIO_PATH = "resources/music/mainTrack.wav";
    private static boolean isPlaying = false;
    private static Clip clip;
    private static File audioFile;
    private static AudioInputStream audioStream;

    /**
     * Starts and stops the audio track. The initial call will load the audio into memory
     * and keep it there for the duration of the program. This lets subsequent restarts
     * begin nearly immediately instead of reloading the track. Keeps track of the internal
     * audio playback status to know whether to start or stop audio.
     * @throws Exception if the method is unable to play the audio
     */
    public static void toggle() throws Exception {
        if (clip == null)
            clip = AudioSystem.getClip();

        if (isPlaying) {
            clip.stop();
        } else {
            if (audioFile == null)
                audioFile = new File(AUDIO_PATH);

            if (audioStream == null)
                audioStream = AudioSystem.getAudioInputStream(audioFile);

            if (!clip.isOpen()) {
                clip.open(audioStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

            clip.start();
        }
        isPlaying = !isPlaying;
    }
}
