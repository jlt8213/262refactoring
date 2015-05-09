import java.util.ArrayList;
/**
 *  This timer runs on the separate thread and during
 *  the simulation tells facade to update the warning and normal times
 */

public class Timer extends Thread{

    /**The time limit for a player's single turn*/
    private long timeLimit;
    /**This stores the last time there was a change of turns*/
    private long lastTurnSwap;
    /**This is used for continuing the timer loop until the end of the game*/
    private boolean running;
    /**This is a list of all the observers that need to be updated of timer changes*/
    private ArrayList<TimerObserver> observers;
	
	/**This constructor creates a new Timer with the given timelimit
	 * and no default observers
	 */
	public Timer(long timeLimit){
    	super();
    	init(timeLimit, null);
    }
    
    /**This constructor creates a new Timer with the default time limit
	 * and no default observers
	 */
    public Timer(){
    	super();
    	init(30000L, null);
    }
    
    /**This constructor creates a new Timer with the given timelimit
	 * and the given default observer
	 */
    public Timer(TimerObserver initialTimerObserver){
    	super();
    	init(30000L, initialTimerObserver);
    }
    
    /**This constructor creates a new Timer with the default time limit
	 * and the given default observer
	 */
    public Timer(long timeLimit, TimerObserver initialTimerObserver){
    	super();
    	init(timeLimit, initialTimerObserver);
    }
    
    /**
     * This function is the init used by all the contructors to 
     *  - Set the time limit, default or not
     *  - Add the initial observer if it is not null
     */
    private void init(long timeLimit, TimerObserver initialTimerObserver){
    	this.timeLimit = timeLimit;
    	this.observers = new ArrayList<TimerObserver>(1);
    	if(initialTimerObserver != null){
    		this.observers.add(initialTimerObserver);
    	}
    }
    
    /**
     * Used to add more observers of the timer after the creation of it
     */
    public void addTimerObserver(TimerObserver newTimerObserver){
    	this.observers.add(newTimerObserver);
    }
    
    /**
     * This function force stops the running of the timer
     */
    public void stopRunning(){
    	this.running = false;
    }
    
    @Override
    public void run(){
    	lastTurnSwap = System.currentTimeMillis();
    	running = true;
    	TimerLoop:while(running){
    		if((System.currentTimeMillis() - lastTurnSwap) > timeLimit){
    			for(TimerObserver to : observers){
    				to.notifyTimeUp();
    				running = false;
    				break TimerLoop;
    			}
    			lastTurnSwap = System.currentTimeMillis();
    		}
    		for(TimerObserver to : observers){
				to.notifyUpdateTime((int)((timeLimit-(System.currentTimeMillis() - lastTurnSwap))/1000));
			}
    		try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
    
    /**
     * This function resets the timer on a turn swap 
     */
    public void reset(){
        lastTurnSwap = System.currentTimeMillis();
    }
    
}// Timer.java
