package soccerteamproject;

import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 * This is a implementation of {@link ApplicationInterface} which uses a graphical user interface.
 * It provides a application program where the user can interact with the text fields and drop-down
 * menus to input a new player information. It also has panes and tables which displays information
 * about all the players, tha players assigned to the team, and the starting line up.
 */
public class Application extends JFrame implements ApplicationInterface {

  public Application() {
    this.setTitle("Soccer Team Application");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());
    this.setSize(800, 400);
    this.setLocation(200, 200);

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
