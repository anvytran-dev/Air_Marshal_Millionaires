package com.millionaires.airmarshal.controller;

import com.millionaires.airmarshal.Player;
import com.millionaires.airmarshal.music.MusicPlayer;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class GameTimeKeeper extends Timer {
    private Timer timer = new Timer();
    private String currentTime = "5:00";
    static GameTimeKeeper timeKeeper = null;
    private boolean timeLeft = true;

    // ---- CONSTRUCTORS ----
    private GameTimeKeeper(Player player, Scanner scanner){
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = Integer.parseInt("300");
            int displayMinutes;
            int displaySeconds;
            DecimalFormat formatter = new DecimalFormat("00");
            String secondsFormatted;
            public void run() {
                displayMinutes = (i / 60) % 60;
                displaySeconds = i % 60;
                secondsFormatted = formatter.format(displaySeconds);
                i--;
                currentTime = displayMinutes + ":" + secondsFormatted + " left";
                if (i< 0) {
                    setTimeLeft(false);
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    private GameTimeKeeper(Player player, Scanner scanner, int timeRemaining){
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = timeRemaining;
            int displayMinutes;
            int displaySeconds;
            DecimalFormat formatter = new DecimalFormat("00");
            String secondsFormatted;
            public void run() {

                displayMinutes = (i / 60) % 60;
                displaySeconds = i % 60;
                secondsFormatted = formatter.format(displaySeconds);
                i--;
                currentTime = displayMinutes + ":" + secondsFormatted + " left";
                if (i< 0) {
                    setTimeLeft(false);
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    // public access that calls private ctor if necessary
    public static GameTimeKeeper getInstance(Player player, Scanner scanner){
        if(timeKeeper == null){
            timeKeeper = new GameTimeKeeper(player, scanner);
        }

        return timeKeeper;
    }

    public static GameTimeKeeper getInstance(Player player, Scanner scanner, int timeRemaining){
        if(timeKeeper == null){
            timeKeeper = new GameTimeKeeper(player, scanner, timeRemaining);
        }
        return timeKeeper;
    }

    public boolean isTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(boolean timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void gameOver(Player player, Scanner scanner) {
        JSONObject gameOverDialogue = null;
        try {
            gameOverDialogue = new JSONObject(new FileReader("resources/endgame.json"));
            Files.readAllLines(Path.of("resources/data/game_over.txt")).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nAir Marshal " + player.getName() + gameOverDialogue.get("game over"));
        player.setPlaying(false);
        timeKeeper = null;
        MusicPlayer.controller(1);
        new Game().playAgain();
    }
}