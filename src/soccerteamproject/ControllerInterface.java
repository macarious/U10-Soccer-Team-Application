package soccerteamproject;

import java.time.format.DateTimeParseException;

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
   *
   * @throws MissingInfoException                    if there is missing information from the user.
   * @throws java.time.format.DateTimeParseException if birthdate is not in yyyy/MM/dd format.
   * @throws IllegalArgumentException                if the birthdate input does not correspond to a
   *                                                 valid age.
   * @throws DuplicatePlayerException                if a player of the exact information has
   *                                                 already been input.
   */
  void displayAllPlayer()
      throws MissingInfoException, DateTimeParseException, IllegalArgumentException,
             DuplicatePlayerException;

  /**
   * Creates a team with the registered players.
   */
  void createTeam();

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

  /**
   * Resets the application so user can start over again.
   */
  void reset();
}
