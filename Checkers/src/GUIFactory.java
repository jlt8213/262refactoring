import java.awt.*;

/**
 * Created by jlt8213 on 5/3/15.
 */
public class GUIFactory {
    public static Component makeView(int numViews, Facade facade){
        Component newView;
        switch(numViews){
            case 0:
                newView = new StartGUI(facade);
                break;
            case 1:
                newView = new SettingsGUI(facade);
                break;
            case 2:
                newView = new GameGUI(facade);
                break;
            default:
                System.err.println("Only 3 views exist!");
                //at the moment this goes back to the first GUI
                newView = new StartGUI(facade);
                numViews = 0;
                break;
        }
        numViews++;
        return newView;

    }
}
