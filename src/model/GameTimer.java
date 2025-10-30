package model;

import java.util.Timer;
import java.util.TimerTask;


public class GameTimer {
    public static int TimeElapsed;
    private Timer timer;
    private TimerTask task;
    public static int finalTime;

    public GameTimer() {
        
        
        task = new TimerTask() {
        
            public void run() {
                TimeElapsed++;
                System.out.println("timer: " + TimeElapsed + "s");
                
            }
        };
    }

    public void start() {
        TimeElapsed = 0;
        timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void stop() {
        if (TimeElapsed >= 0) {
            finalTime = TimeElapsed;
        	timer.cancel();
			System.out.println("Timer stopped at: " + TimeElapsed + "s");
        }
    }
}