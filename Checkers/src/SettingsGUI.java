import sun.applet.Main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jlt8213 on 5/3/15.
 */
public class SettingsGUI extends Container  implements ActionListener{

    private Facade theFacade;
    private int gameType;

    // Variables declaration
    private Checkbox timedGameBox;
    private JLabel playerOneLabel;
    private JLabel playerTwoLabel;
    private JTextField playerOneField;
    private JTextField playerTwoField;
    private JLabel turnLengthLabel;
    private JLabel WarningLengthLabel;
    private JButton okButton;
    private JButton cancelButton;
    private JSlider turnLengthField;
    private JSlider warningLengthField;
    // End of variables declaration


    public SettingsGUI(Facade facade){
        super();
        theFacade = facade;
        initComponents();
    }

    private void initComponents() {

        timedGameBox = new Checkbox("Timed game");
        playerOneLabel = new JLabel("Player 1:");
        playerTwoLabel = new JLabel("Player 2:");
        playerOneField = new JTextField("Enter name");
        playerTwoField = new JTextField("Enter name");
        WarningLengthLabel = new JLabel();
        okButton = new JButton();
        cancelButton = new JButton();
        turnLengthField = new JSlider( 10, 300, 120 );
        turnLengthLabel = new JLabel("Turn Length ( " + turnLengthField.getValue() + " seconds )");
        warningLengthField = new JSlider( 10, 300, 120 );
        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints1;


        timedGameBox.setBackground(new Color(204, 204, 204));
        timedGameBox.setName("timedGameBox");
        timedGameBox.setState( true );

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 5;
        gridBagConstraints1.ipadx = 7;
        gridBagConstraints1.ipady = 7;
        gridBagConstraints1.insets = new Insets(31, 0, 1, 0);
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        this.add(timedGameBox, gridBagConstraints1);


        playerOneLabel.setName("playerOneLabel");
        playerOneLabel.setBackground(new Color(204, 204, 204));

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.insets = new Insets(5, 0, 0, 0);
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        this.add(playerOneLabel, gridBagConstraints1);

        playerTwoLabel.setName("playerTwoLabel");
        playerTwoLabel.setBackground(new Color(204, 204, 204));
        playerTwoLabel.setForeground(Color.black);

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.insets = new Insets(4, 0, 0, 0);
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        this.add(playerTwoLabel, gridBagConstraints1);

        playerOneField.setBackground(Color.white);
        playerOneField.setName("textfield1");
        playerOneField.setForeground(Color.black);

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.insets = new Insets(5, 0, 0, 0);
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        this.add(playerOneField, gridBagConstraints1);

        playerTwoField.setBackground(Color.white);
        playerTwoField.setName("textfield2");
        playerTwoField.setForeground(Color.black);

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 2;
        gridBagConstraints1.insets = new Insets(4, 0, 0, 0);
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        this.add(playerTwoField, gridBagConstraints1);

        turnLengthLabel.setName("label3");
        turnLengthLabel.setBackground(new Color (204, 204, 204));
        turnLengthLabel.setForeground(Color.black);

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 6;
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        this.add(turnLengthLabel, gridBagConstraints1);

        WarningLengthLabel.setName("label4");
        WarningLengthLabel.setBackground(new Color (204, 204, 204));
        WarningLengthLabel.setForeground(Color.black);
        WarningLengthLabel.setText("Warning Length ( " + warningLengthField.getValue() + " seconds )");

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 8;
        gridBagConstraints1.anchor = GridBagConstraints.WEST;
        this.add(WarningLengthLabel, gridBagConstraints1);

        okButton.setText("OK");
        okButton.setName("button1");
        okButton.setBackground(new Color (212, 208, 200));
        okButton.setForeground(Color.black);
        okButton.setActionCommand("ok");
        okButton.addActionListener( this );

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 11;
        gridBagConstraints1.insets = new Insets(20, 0, 10, 12);
        gridBagConstraints1.anchor = GridBagConstraints.EAST;
        this.add(okButton, gridBagConstraints1);

        cancelButton.setText("Cancel");
        cancelButton.setName("button2");
        cancelButton.setBackground(new Color (212, 208, 200));
        cancelButton.setForeground(Color.black);
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener( this );

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 11;
        gridBagConstraints1.insets = new Insets(20, 0, 10, 0);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        this.add(cancelButton, gridBagConstraints1);

        turnLengthField.setName("textfield3");
        turnLengthField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                turnLengthLabel.setText("Turn Length ( "
                        + turnLengthField.getValue()
                        + " seconds )");
            }
        });

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 6;
        this.add(turnLengthField, gridBagConstraints1);

        warningLengthField.setName("textfield4");
        warningLengthField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                WarningLengthLabel.setText("Warning Length ( "
                        + warningLengthField.getValue()
                        + " seconds )");
            }
        });

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 8;
        this.add(warningLengthField, gridBagConstraints1);

        //determine what components should be disabled
        //depending on the game mode
        if ( gameType == theFacade.LOCALGAME ) {
        } else if ( gameType == theFacade.HOSTGAME ) {
            playerTwoLabel.setEnabled( false );
            playerTwoField.setEnabled( false );
        } else if ( gameType == theFacade.CLIENTGAME ) {
            playerOneLabel.setEnabled( false );
            playerOneField.setEnabled( false );

            timedGameBox.setEnabled( false );
            turnLengthLabel.setEnabled( false );
            WarningLengthLabel.setEnabled( false );
            turnLengthField.setEnabled( false );
            warningLengthField.setEnabled( false );
        }
    }


    /**
     * This takes care of when an action takes place. It will check the
     * action command of all components and then deicde what needs to be done.
     *
     * @param e the event fired
     */

    public void actionPerformed( ActionEvent e ){
        try{

            if ( (e.getActionCommand()).equals( "ok" ) ) {

                //take note of all selections and go to game startup
                if ( playerOneField.isEnabled() ) {
                    if ( ( playerOneField.getText() ).equalsIgnoreCase( "" ) ) {
                        playerOneField.setText( "player1" );
                    }
                }

                if ( playerTwoField.isEnabled() ) {
                    if ( ( playerTwoField.getText() ).equalsIgnoreCase( "" ) ) {
                        playerTwoField.setText( "player2" );
                    }
                }

                theFacade.setPlayerName( 1, playerOneField.getText() );
                theFacade.setPlayerName( 2, playerTwoField.getText() );

                //if a timer is desired
                if ( timedGameBox.isEnabled() ) {
                    if( timedGameBox.getState() ){

                        //set the 2 timer values
                        try {

                            theFacade.setTimer( turnLengthField.getValue(),
                                    warningLengthField.getValue() );

                        } catch ( Exception x ) {

                            JOptionPane.showMessageDialog( null,
                                    "Invalid Timer value(s)",
                                    "Error",
                                    JOptionPane.INFORMATION_MESSAGE );
                        }
                        //else set timer values to a no timer constant
                    } else {
                        theFacade.setTimer( -1, -1 );

                    }
                } else {
                    theFacade.setTimer( -1, -1 );

                }

                //start the game
                theFacade.startGame();
                //hide this screen, make and show the GUI
                MainWindow.nextView(theFacade);
                /*
                this.hide();
                CheckerGUI GUI = new CheckerGUI( theFacade, theFacade.getPlayerName( 1 ),
                        theFacade.getPlayerName( 2 ) );
                GUI.show();*/

                //if they hit cancel go to the previous screen
            } else if( e.getActionCommand().equals( "cancel" ) ) {
                MainWindow.prevView(theFacade);

                //handle whether or not a timer is desired
            } else if ( e.getSource() instanceof Checkbox ) {

                if( timedGameBox.getState() ){
                    turnLengthField.setEnabled( true );
                    warningLengthField.setEnabled( true );
                } else {
                    turnLengthField.setEnabled( false );
                    warningLengthField.setEnabled( false );
                }
            }

        } catch( Exception x ){
            x.printStackTrace();
        }

    }//end of actionPerformed

}
