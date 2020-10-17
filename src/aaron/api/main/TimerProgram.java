package aaron.api.main;

import java.util.*;

public class TimerProgram {
	
	private int timerSet;
	public long counter = 0;
	private Timer timer;
	private boolean isRunning = false;
	
	public TimerProgram() {
		
	}
	
	public TimerProgram(int timeInMinutes) {
		this.timerSet = timeInMinutes;
		setTimer(timerSet);
		isRunning=true;
	}
	
	public void startTimer(int timeInMinutes) {
		this.timerSet = timeInMinutes;
		setTimer(timerSet);
		isRunning = true;
	}
	
	public void stopTimer() {
		timer.cancel();
		isRunning = false;
	}
	
	public boolean isTimerRunning() {
		return isRunning;
	}
	
	public long getCount() {
		return counter;
	}

	private void setTimer(int timerSetTo) {
		timer = new Timer(true);
		long MinutesToMiliseconds = timerSetTo*60000;
		timer.schedule(new TimerTask() {
			public void run() {
				counter++;
			}
		}, 0, MinutesToMiliseconds);

	}

}
