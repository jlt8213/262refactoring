/*
 * Firstscreen.java
 *
 *  * Version:
 *   $Id: Firstscreen.java,v 1.1 2002/10/22 21:12:52 se362 Exp $
 *
 * Revisions:
 *   $Log: Firstscreen.java,v $
 *   Revision 1.1  2002/10/22 21:12:52  se362
 *   Initial creation of case study
 *
 */
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author
 * @version
 */

public class StartGUI extends Container implements ActionListener{

    private Facade theFacade;

    // Variables declaration - do not modify
    private JRadioButton LocalGameButton;
    private JRadioButton HostGameButton;
    private JRadioButton JoinGameButton;
    private JTextField IPField;
    private JLabel IPLabel;
    private JButton OKButton;
    private JButton CancelButton;
    private JLabel IPExampleLabel;
    private ButtonGroup gameModes;
    // End of variables declaration


    /**
     * Creates new form Firstscreen
     *
     * @param facade a facade object for the GUI to interact with
     *
     */

    public StartGUI( Facade facade ) {

        super();
        theFacade = facade;
        initComponents();
    }


    /**
     * This method is called from within the constructor to
     * initialize the form.
     *
     */

    private void initComponents() {

        LocalGameButton = new JRadioButton();
        HostGameButton = new JRadioButton();
        JoinGameButton = new JRadioButton();
        gameModes = new ButtonGroup();
        IPField = new JTextField();
        IPLabel = new JLabel();
        OKButton = new JButton();
        CancelButton = new JButton();
        IPExampleLabel = new JLabel();
        this.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;

        gameModes.add(LocalGameButton);
        gameModes.add(HostGameButton);
        gameModes.add(JoinGameButton);

        LocalGameButton.setActionCommand("local");
        LocalGameButton.setText("Local game");
        LocalGameButton.addActionListener(this);
        LocalGameButton.setSelected( true );

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        this.add(LocalGameButton, gridBagConstraints1);


        HostGameButton.setActionCommand("host");
        HostGameButton.setText("Host game");
        HostGameButton.addActionListener(this);

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        this.add(HostGameButton, gridBagConstraints1);


        JoinGameButton.setActionCommand("join");
        JoinGameButton.setText("Join game");
        JoinGameButton.addActionListener(this);

        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 2;
        this.add(JoinGameButton, gridBagConstraints1);


        IPField.setBackground( Color.white );
        IPField.setName("textfield5");
        IPField.setForeground( Color.black);
        IPField.setText("IP address goes here");
        IPField.setEnabled( false );
        IPField.addActionListener(this);

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 3;
        this.add(IPField, gridBagConstraints1);

        IPLabel.setName("label10");
        IPLabel.setBackground(new Color (204, 204, 204));
        IPLabel.setForeground(Color.black);
        IPLabel.setText("IP address:");

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 3;
        this.add(IPLabel, gridBagConstraints1);

        OKButton.setText("OK");
        OKButton.setActionCommand("ok");
        OKButton.setName("button6");
        OKButton.setBackground(new Color (212, 208, 200));
        OKButton.setForeground(Color.black);
        OKButton.addActionListener(this);

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 5;
        gridBagConstraints1.insets = new Insets(30, 0, 0, 0);
        this.add(OKButton, gridBagConstraints1);

        CancelButton.setText("Cancel");
        CancelButton.setActionCommand("cancel");
        CancelButton.setName("button7");
        CancelButton.setBackground(new Color (212, 208, 200));
        CancelButton.setForeground(Color.black);
        CancelButton.addActionListener(this);

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 5;
        gridBagConstraints1.insets = new Insets(30, 0, 0, 0);
        this.add(CancelButton, gridBagConstraints1);

        IPExampleLabel.setName("label11");
        IPExampleLabel.setBackground(new Color (204, 204, 204));
        IPExampleLabel.setForeground(Color.black);
        IPExampleLabel.setText("Ex: 123.456.789.123");

        gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 4;
        this.add(IPExampleLabel, gridBagConstraints1);


    }

    public void actionPerformed( ActionEvent e ){

        try{
            //this code handles disabling the IP field unless
            //the join game radio button is selected
            if ( ( e.getActionCommand() ).equals( "join" ) ){
                IPField.setEnabled( true );
            }else if( (e.getActionCommand() ).equals( "local" ) ){
                IPField.setEnabled( false );
            }else if( ( e.getActionCommand() ).equals( "host" ) ){
                IPField.setEnabled( false );

                //this next if statement takes care of when the
                //OK button is selected and goes to the second
                //screen settign the desired options

            }else if( ( e.getActionCommand() ).equals( "ok" ) ){

                //a temporary button to use for determining the game type
                ButtonModel tempButton = gameModes.getSelection();

                //if check to see of the local radio button is selected
                if( tempButton.getActionCommand().equals( "local" ) ){

                    //set up a local game
                    theFacade.setGameMode( theFacade.LOCALGAME );

                    theFacade.createPlayer( 1, theFacade.LOCALGAME );
                    theFacade.createPlayer( 2, theFacade.LOCALGAME );

                    //hide the Firstscreen, make a Secondscreen and show it
                    MainWindow.nextView(theFacade);
                    /*this.hide();
                    next = new Secondscreen( theFacade, this, theFacade.LOCALGAME );
                    next.show();
                    */
                    //if the host game button is selected
                } else if( tempButton.getActionCommand().equals( "host" ) ){

                    //set up to host a game
                    theFacade.setGameMode( theFacade.HOSTGAME );

                    theFacade.createPlayer( 1, theFacade.HOSTGAME );
                    theFacade.createPlayer( 2, theFacade.HOSTGAME );

                    //hide the Firstscreen, make the Secondscreen and show it
                    MainWindow.nextView(theFacade);
                    /*
                    this.hide();
                    next = new Secondscreen( theFacade, this, theFacade.HOSTGAME );
                    next.show();
                    */
                    //if the join game button is selected
                } else if( tempButton.getActionCommand().equals( "join" ) ){

                    //set up to join a game
                    theFacade.setGameMode( theFacade.CLIENTGAME );

                    theFacade.createPlayer( 1, theFacade.CLIENTGAME );
                    theFacade.createPlayer( 2, theFacade.CLIENTGAME );

                    //try to connect
                    try {

                        //create a URL from the IP address in the IPfield
                        URL address = new URL( "http://" + IPField.getText() );
                        //set the host
                        theFacade.setHost( address );

                        //hide the Firstscreen, make and show the Second screen
                        MainWindow.nextView(theFacade);
                        /*
                        this.hide();
                        next = new Secondscreen( theFacade, this, theFacade.CLIENTGAME );
                        next.show();*/

                        //catch any exceptions
                    } catch ( MalformedURLException x ) {
                        JOptionPane.showMessageDialog( null,
                                "Invalid host address",
                                "Error",
                                JOptionPane.INFORMATION_MESSAGE );
                    }//end of networking catch statement

                    //set up to connect to another person
                }


                //if they hit cancel exit the game
            } else if( e.getActionCommand().equals( "cancel" ) ){
                System.exit( 0 );
            }

        } catch( Exception x ) {
            System.err.println( x.getMessage() );
        }//end of general catch statement

    }//end of actionPerformed

}

