import java.util.Observer;


public interface TimerObserver extends Observer {

	public void notifyTimeUp();
	
	public void notifyUpdateTime(int newTimeLeft);
	
}
