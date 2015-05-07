import java.util.ArrayList;
/**
 *  This timer runs on the separate thread and during
 *  the simulation tells fasade to update the warning and normal times 
 *
 *  @invariant all variables have valid values
 *
 *  @author
 */

public class Timer extends Thread{

    private long timeLimit;
    private long lastTurnSwap;
    private boolean running;
    private ArrayList<TimerObserver> observers;
	
    public Timer(long timeLimit){
    	super();
    	init(timeLimit, null);
    }
    
    public Timer(){
    	super();
    	init(30000L, null);
    }
    
    public Timer(TimerObserver initialTimerObserver){
    	super();
    	init(30000L, initialTimerObserver);
    }
    
    public Timer(long timeLimit, TimerObserver initialTimerObserver){
    	super();
    	init(timeLimit, initialTimerObserver);
    }
    
    private void init(long timeLimit, TimerObserver initialTimerObserver){
    	this.timeLimit = timeLimit;
    	this.observers = new ArrayList<TimerObserver>(1);
    	if(initialTimerObserver != null){
    		this.observers.add(initialTimerObserver);
    	}
    }
    
    public void addTimerObserver(TimerObserver newTimerObserver){
    	this.observers.add(newTimerObserver);
    }
    
    public void stopRunning(){
    	this.running = false;
    }
    
    @Override
    public void run(){
    	lastTurnSwap = System.currentTimeMillis();
    	running = true;
    	while(running){
    		if((System.currentTimeMillis() - lastTurnSwap) > timeLimit){
    			for(TimerObserver to : observers){
    				to.notifyTimeUp();
    			}
    			lastTurnSwap = System.currentTimeMillis();
    		}
    		for(TimerObserver to : observers){
				to.notifyUpdateTime((int)((timeLimit-(System.currentTimeMillis() - lastTurnSwap))%1000));
			}
    		try {
				sleep(750);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
    
}// Timer.java
