package model;

import java.util.Timer;
import java.util.TimerTask;


public class GameTimer {
    public static int TimeElapsed = 0;
    public int seconds = 0;
    private Timer timer;
    private TimerTask task;

    public GameTimer() {
        timer = new Timer();
        task = new TimerTask() {
           


            public void run() {
                seconds++;
                TimeElapsed = seconds;
                System.out.println("timer: " + seconds + "s");
            }
        };
    }

    public void start() {
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void stop() {
        if (seconds > 0) {
        	timer.cancel();
        }
    }
}
