package model;

import java.util.Timer;
import java.util.TimerTask;


public class GameTimer {
    public static int TimeElapsed = 0;

    private Timer timer;
    private TimerTask task;

    public GameTimer() {
        
        task = new TimerTask() {
        	
            public void run() {
                TimeElapsed++;
                System.out.println("timer: " + TimeElapsed + "s");//delete this when inlämning :DD
            }
        };
    }

    public void start() {
    	timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 1000);
        
    }

    public void stop() {
        if (TimeElapsed > 0) {
        	timer.cancel();
			System.out.println("Timer stopped at: " + TimeElapsed + "s");//delete this when inlämning :DD
			TimeElapsed = 0;
			timer = null;
        }
    }
}