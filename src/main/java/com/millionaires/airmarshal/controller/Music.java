package com.millionaires.airmarshal.controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {

    private final static String AUDIO_PATH = "resources/music/mainTrack.wav";
    private static boolean isPlaying = false;
    private static Clip clip;
    private static File audioFile;
    private static AudioInputStream audioStream;

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
