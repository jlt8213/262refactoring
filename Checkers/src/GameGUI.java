/**
 * Created by jlt8213 on 5/3/15.
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.net.*;

/**
 *
 * @author
 * @version
 */

public class GameGUI extends Container implements ActionListener, TimerObserver{

    //the facade for the game

    private static Facade theFacade; //the facade
    private Vector possibleSquares = new Vector();//a vector of the squares
    private int timeRemaining;//the time remaining
    private ArrayList<JButton> buttons;
    private JLabel PlayerOnelabel;
    private JLabel playerTwoLabel;
    private JLabel timeRemainingLabel;
    private JLabel secondsLeftLabel;
    private JButton ResignButton;
    private JButton DrawButton;
    private JLabel warningLabel, whosTurnLabel;
    
    private Timer timer = null;

    //the names and time left
    private static String playerOnesName="", playerTwosName="", timeLeft="";

    /**
     *
     * Constructor, creates the GUI and all its components
     *
     * @param facade the facade for the GUI to interact with
     *
     */

    public GameGUI( Facade facade ) {

        super();
        String name1 = facade.getPlayerName(1);
        String name2 = facade.getPlayerName(2);
        //long names mess up the way the GUI displays
        //this code shortens the name if it is too long
        String nameOne="", nameTwo="";
        if(name1.length() > 7 ){
            nameOne = name1.substring(0,7);
        }else{
            nameOne = name1;
        }
        if(name2.length() > 7 ){
            nameTwo = name2.substring(0,7);
        }else{
            nameTwo = name2;
        }

        playerOnesName = nameOne;
        playerTwosName = nameTwo;
        theFacade = facade;
        register();

        initComponents ();
        update();
        
        if(timer != null){
            timer.start();
        }
    }


    /*
     * This method handles setting up the timer
     */
    private void register() {

        try{
            theFacade.addActionListener( this );

        }catch( Exception e ){

            System.err.println( e.getMessage() );

        }
    }

    /**
     * This method is called from within the constructor to
     * initialize the form. It initializes the components
     * adds the buttons to the Vecotr of squares and adds
     * an action listener to the components
     *
     */
    private void initComponents() {

        //sets the layout and adds listener for closing window
        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints1;
        int position = 8;
        Color color1 = new Color(204, 204, 153);
        Color color2 = Color.white;
        Color colorSwap;
        for(int i=1; i<65; i++){

            JButton newButton = new JButton();
            possibleSquares.add(newButton);
            newButton.addActionListener(this);

            newButton.setPreferredSize(new java.awt.Dimension(80, 80));
            newButton.setActionCommand("" + (i-1));
            if( position % 8 == 0){
                colorSwap = color1;
                color1 = color2;
                color2 = colorSwap;
            }
            if(i % 2 == 1)
                newButton.setBackground(color1);
            else{ newButton.setBackground(color2); }

            gridBagConstraints1 = new java.awt.GridBagConstraints();
            gridBagConstraints1.gridx = position % 8;
            gridBagConstraints1.gridy = position++ / 8;
            this.add(newButton, gridBagConstraints1);

        }
        PlayerOnelabel = new JLabel("Player 1:     " + playerOnesName );
        playerTwoLabel = new JLabel("Player 2:     " + playerTwosName );
        whosTurnLabel = new JLabel();

        warningLabel = new JLabel( );
        timeRemainingLabel = new JLabel("Time Remaining:");
        secondsLeftLabel = new JLabel( timeLeft + " sec.");

        ResignButton = new JButton("Resign");
        ResignButton.addActionListener( this );

        DrawButton = new JButton("Draw");
        DrawButton.addActionListener(this);


        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.gridwidth = 4;
        this.add(PlayerOnelabel, gridBagConstraints1);


        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 9;
        gridBagConstraints1.gridwidth = 4;
        this.add(playerTwoLabel, gridBagConstraints1);

        whosTurnLabel.setText("");
        whosTurnLabel.setForeground( new Color( 0, 100 , 0 ) );

        gridBagConstraints1.gridx=8;
        gridBagConstraints1.gridy= 1;
        this.add(whosTurnLabel, gridBagConstraints1 );

        warningLabel.setText( "" );
        warningLabel.setForeground( Color.red );

        gridBagConstraints1.gridx = 8;
        gridBagConstraints1.gridy = 2;
        this.add(warningLabel, gridBagConstraints1 );

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 8;
        gridBagConstraints1.gridy = 3;
        this.add(timeRemainingLabel, gridBagConstraints1);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 8;
        gridBagConstraints1.gridy = 4;
        this.add(secondsLeftLabel, gridBagConstraints1);

        ResignButton.setActionCommand("resign");

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 8;
        gridBagConstraints1.gridy = 7;
        this.add(ResignButton, gridBagConstraints1);

        DrawButton.setActionCommand("draw");

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 8;
        gridBagConstraints1.gridy = 6;
        this.add(DrawButton, gridBagConstraints1);

        int time = theFacade.getTimer();
        if(time > 0){
            timer = new Timer(time, this);
        }
    }


    /**
     * Takes care of input from users, handles any actions performed
     *
     * @param e  the event that has occured
     */

    public void actionPerformed( ActionEvent e ) {
        try{
            if( e.getActionCommand().equals( "draw" ) ){
                //does sequence of events for a draw
                theFacade.pressDraw();

                //if resign is pressed
            }else if( e.getActionCommand().equals( "resign" ) ) {
                //does sequence of events for a resign
                theFacade.pressQuit();

                //if the source came from the facade
            }else if( e.getSource().equals( theFacade ) ) {

                //if its a player switch event
                if ( (e.getActionCommand()).equals(theFacade.playerSwitch) ) {
                    //set a new time
                    timer.reset();
                    //if it is an update event
                } else if ( (e.getActionCommand()).equals(theFacade.update) ) {
                    //update the GUI
                    //update();
                } else {
                    throw new Exception( "unknown message from facade" );
                }
            }
            else {
                ArrayList<Boolean> valids = new ArrayList<Boolean>();
                boolean result = true;
                for (int i = 1; i < 65; i++) {
                    if (i % 8 == 0)
                        result = !result;
                    if (i % 2 == 1)
                        valids.add(result);
                    else {
                        valids.add(!result);
                    }
                }
                //if a valid square gets clicked
                int inputSpace = Integer.parseInt(e.getActionCommand());

                if (valids.get(inputSpace - 1)) {
                    //call selectSpace with the button pressed
                    theFacade.selectSpace(Integer.parseInt(e.getActionCommand()));
                }
            }
        }catch( NumberFormatException excep ){
            System.out.println(excep.toString());
            System.err.println(
                    "GUI exception: Error converting a string to a number" );
        }catch( NullPointerException exception ){
            System.err.println( "GUI exception: Null pointerException "
                    + exception.getMessage() );
            exception.printStackTrace();
        }catch( Exception except ){
            System.err.println( "GUI exception: other: "
                    + except.getMessage() );
            except.printStackTrace();
        }

    }


    /**
     * Updates the GUI reading the pieces in the board
     * Puts pieces in correct spaces, updates whos turn it is
     *
     * @param the board
     */

    private void update(){

        if( checkEndConditions() ){
            theFacade.showEndGame(" ");
        }
        //the board to read information from
        Board board = theFacade.stateOfBoard();
        //a temp button to work with
        JButton temp =  new JButton();

        //go through the board
        for( int i = 1; i < board.sizeOf(); i++ ){

            // if there is a piece there
            if( board.occupied( i ) ){

                //check to see if color is blue
                if( board.colorAt( i ) == Color.blue ){

                    //if there is a  single piece there
                    if((board.getPieceAt(i)).getType() == board.SINGLE){

                        //show a blue single piece in that spot board
                        temp = (JButton)possibleSquares.get(i);

                        //get the picture from the web
                        try{
                            temp.setIcon(
                                    new ImageIcon( new URL("file:BlueSingle.gif") ));
                        }catch( MalformedURLException e ){
                            System.out.println(e.getMessage());
                        }

                        //if there is a kinged piece there
                    }else if((board.getPieceAt(i)).getType() == board.KING ){

                        //show a blue king piece in that spot board
                        temp= (JButton)possibleSquares.get(i);

                        //get the picture formt the web
                        try{
                            temp.setIcon(
                                    new ImageIcon(new URL("file:BlueKing.gif") ) );
                        }catch( Exception e ){}

                    }

                    //check to see if the color is white
                }else if( board.colorAt( i ) == Color.white ){

                    //if there is a single piece there
                    if((board.getPieceAt(i)).getType() == board.SINGLE){

                        //show a blue single piece in that spot board
                        temp = (JButton)possibleSquares.get(i);

                        //get the picture from the web
                        try{
                            temp.setIcon(
                                    new ImageIcon(new URL("file:WhiteSingle.gif")));
                        }catch( Exception e ){}

                        //if there is a kinged piece there
                    }else if((board.getPieceAt(i)).getType() == board.KING ){

                        //show a blue king piece in that spot board
                        temp = (JButton)possibleSquares.get(i);

                        //get the picture from the web
                        try{
                            temp.setIcon(
                                    new ImageIcon(new URL("file:WhiteKing.gif") ) );
                        }catch( Exception e ){}
                    }
                    //if there isnt a piece there
                }
            }else {
                //show no picture
                temp = (JButton)possibleSquares.get(i);
                temp.setIcon( null );
            }
        }

        //this code updates whos turn it is
        if(theFacade.whosTurn() == 2 ){
            playerTwoLabel.setForeground( Color.red );
            PlayerOnelabel.setForeground(Color.black );
            whosTurnLabel.setText( playerTwosName + "'s turn ");
        }else if( theFacade.whosTurn() == 1 ){
            PlayerOnelabel.setForeground( Color.red );
            playerTwoLabel.setForeground(Color.black );
            whosTurnLabel.setText( playerOnesName + "'s turn" );
        }
    }

    /**
     * Checks the ending condotions for the game
     * see if there a no pieces left
     *
     * @return the return value for the method
     *           true if the game should end
     *           false if game needs to continue
     */

    public boolean checkEndConditions(){

        //the return value
        boolean retVal = false;
        try{
            //the number of each piece left
            int whitesGone = 0 , bluesGone = 0;

            //the board to work with
            Board temp = theFacade.stateOfBoard();

            //go through all the spots on the board
            for( int i=1; i<temp.sizeOf(); i++ ){
                //if there is a piece there
                if( temp.occupied( i  ) ){
                    //if its a blue piece there
                    if( (temp.getPieceAt( i )).getColor() == Color.blue ){
                        // increment number of blues
                        bluesGone++;
                        //if the piece is white
                    }else if( (temp.getPieceAt( i )).getColor()
                            == Color.white ){
                        //increment number of whites
                        whitesGone++;
                    }
                }
            }//end of for loop

            //if either of the number are 0
            if( whitesGone == 0 || bluesGone == 0 ){
                retVal = true;
            }

        }catch( Exception e ){

            System.err.println(e.getMessage() );

        }
        return retVal;

    }//checkEndConditions

    @Deprecated
    @Override
	public void update(Observable arg0, Object arg1) {
        //pass
	}

	@Override
	public void notifyTimeUp() {
        theFacade.pressQuit();
	}
	
	@Override
	synchronized public void notifyUpdateTime(int newTimeLeft) {
		timeRemainingLabel.setText(Integer.toString(newTimeLeft));		
	}

}
