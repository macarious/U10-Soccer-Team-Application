package soccerteamproject;

import java.time.LocalDate;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.chrono.ChronoLocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * This is a implementation of {@link ApplicationInterface} which uses a graphical user interface.
 * It provides a application program where the user can interact with the text fields and drop-down
 * menus to input a new player information. It also has panes and tables which displays information
 * about all the players, tha players assigned to the team, and the starting line up.
 */
public class Application extends JFrame implements ApplicationInterface {

  private final JPanel panel1; // User input players information and contains buttons which create teams.
  private final JPanel panel2; // Display all registered players.
  private final JPanel panel3; // Display the list of team players after team has been created.
  private final JPanel panel4; // Display the starting line up after team has been created.
  private final JTextField textFieldFirstName;
  private final JTextField textFieldLastName;
  private final JSpinner spinnerBirthDate;
  private final JSpinner spinnerSKillLeve;
  private final JButton buttonAddPlayer;
  private final JButton buttonCreateTeam;

  private final Color colourBG1;
  private final Color colourBG2;
  private final Color colourBorder;

  public Application() {

    // Default colours (Dark Mode).
    colourBG1 = Color.BLACK;
    colourBG2 = Color.DARK_GRAY;
    colourBorder = Color.LIGHT_GRAY;

    // Set up main application frame.
    setTitle("Soccer Team Application");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    setLocation(200, 200);
    getContentPane().setBackground(colourBG1);

    // Create four panels side-by-side.
    panel1 = new JPanel();
    panel2 = new JPanel();
    panel3 = new JPanel();
    panel4 = new JPanel();
    add(panel1);
    add(panel2);
    add(panel3);
    add(panel4);

    // Customize Panel 1.
    panel1.setPreferredSize(new Dimension(350, 600));
    panel1.setBorder(new LineBorder(colourBorder, 2));
    panel1.setBackground(colourBG2);
    textFieldFirstName = new JTextField("Enter a first name", 25);
    textFieldLastName = new JTextField("Entre a last name", 25);


    // Customize Panel 2.
    panel2.setPreferredSize(new Dimension(350, 600));
    panel2.setBorder(new LineBorder(colourBorder, 2));
    panel2.setBackground(colourBG2);

    // Customize Panel 3.
    panel3.setPreferredSize(new Dimension(350, 600));
    panel3.setBorder(new LineBorder(colourBorder, 2));
    panel3.setBackground(colourBG2);

    // Customize Panel 4.
    panel4.setPreferredSize(new Dimension(350, 600));
    panel4.setBorder(new LineBorder(colourBorder, 2));
    panel4.setBackground(colourBG2);

    pack();
    setVisible(true);
  }

  @Override
  public void addPlayer() throws MissingInfoException, DuplicatePlayerException {

  }

  @Override
  public void displayAllPlayer() {

  }

  @Override
  public void displayTeamPlayer() {

  }

  @Override
  public void displayStartingLineUp() {

  }

  @Override
  public void addFeatures(ControllerInterface features) {

  }

  @Override
  public void resetFocus() {
  }
}
