import java.awt.*;

/**
 * Created by jlt8213 on 5/3/15.
 */
public class GUIFactory {
    public static Component makeView(int numViews, Facade facade){
        Component newView;
        switch(numViews){
            case 1:
                newView = new StartGUI(facade);
                break;
            case 2:
                newView = new SettingsGUI(facade);
                break;
            case 3:
                newView = new GameGUI(facade);
                break;
            default:
                System.err.println("Only 3 views exist!");
                newView = new StartGUI(facade);
                break;
        }
        return newView;

    }
}
