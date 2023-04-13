package soccerteamproject;

/**
 * This class represents a controller for a soccer team application. It talks to a model:
 * {@link SoccerTeamInterface} and a view {@link ApplicationInterface}. The model register players,
 * creates teams, and provides team information. The view receive inputs from users and displays the
 * requested information.
 */
public interface ControllerInterface {
  /**
   * Exits the application.
   */
  void exitProgram();

  /**
   * Add a new player, using the information input from the user, to the application.
   */
  void addNewPlayer();

  /**
   * Displays a list of all the registered players and their information.
   */
  void displayAllPlayer();

  /**
   * Displays a list of all the players assigned to the team, along with their jersey numbers and
   * assigned positions.
   */
  void displayTeamPlayer();

  /**
   * Displays a list of all the players assigned to the starting line up, along with their jersey
   * numbers and assigned positions.
   */
  void displayStartingLineUp();
}
