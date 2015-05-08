import java.util.Observer;

/**
 * This interface is used for implementing the observer pattern
 * for a timer and to make sure the observer has the proper
 * functionality
 */
public interface TimerObserver extends Observer {

	/**
	 * This function is used to notify when a player
	 * has run out of time for their turn
	 */
	public void notifyTimeUp();
	
	/**
	 * This function is used to notify the observers 
	 * of a time left change to that any resulting
	 * activities can be activated I.E. updating the 
	 * timer label
	 */
	public void notifyUpdateTime(int newTimeLeft);
	
}
