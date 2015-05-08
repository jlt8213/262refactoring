import javax.swing.*;
import java.awt.*;

/**
 * Created by jlt8213 on 5/3/15.
 * The main window for the checkers game.
 * Creates a manages different views through factory pattern
 */
public class MainWindow {
    static Facade checkersFacade;
    static JFrame checkersWindow;
    static int viewNum;
    public MainWindow(Facade checkersFacade){
        this.checkersFacade = checkersFacade;
        viewNum = 0;
        createWindow();
    }

    public void createWindow(){
        checkersWindow = new JFrame("Play Checkers!");
        checkersWindow.setLayout(new FlowLayout());
        checkersWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkersWindow.setResizable(false);
        nextView(checkersFacade);
        checkersWindow.setVisible(true);
    }

    public static void nextView(Facade facade){
        checkersWindow.getContentPane().removeAll();
        checkersWindow.getContentPane().add(GUIFactory.makeView(++viewNum, facade));
        checkersWindow.pack();
    }
    public static void prevView(Facade facade){
        checkersWindow.getContentPane().removeAll();
        checkersWindow.getContentPane().add(GUIFactory.makeView(--viewNum, facade));
        checkersWindow.pack();
    }


}
