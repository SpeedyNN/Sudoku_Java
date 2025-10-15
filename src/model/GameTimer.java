package model;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
	public static int TimeElapsed = 0;
	private static int BestTime = 0;
	
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int seconds = 0;
			
			public void run() {
				seconds++;
				System.out.println("timer: "+seconds+"s");
				
				// timer.cancel();
				
				if(TimeElapsed < BestTime) {
					
				}
				
				}
			
		};
		
		timer.scheduleAtFixedRate(task, 0, 1000);
		
	}
	
	
	
}
