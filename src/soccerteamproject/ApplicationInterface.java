package soccerteamproject;

/**
 * This class represents a user interface for the Soccer Team application. It is used by the
 * {@link ControllerInterface} class. It allows user to input information about a new player and add
 * them to the system. It can display all the registered players, players assigned on the team, and
 * players on the starting line up. It can also display messages informing errors or status of the
 * application.
 */
public interface ApplicationInterface {
  /**
   * Register a new player when all the player information has been input by the user.
   *
   * @throws MissingInfoException if there is missing information from the user.
   * @throws DuplicatePlayerException if a player of the exact information has already been input.
   */
  void addPlayer() throws MissingInfoException, DuplicatePlayerException;

  /**
   * Display a list of all registered players.
   */

  void displayAllPlayer();

  /**
   * Display a list of all players selected to be on the team.
   */

  void displayTeamPlayer();

  /**
   * Display a list of all players on the starting line up.
   */

  void displayStartingLineUp();

  /**
   * Add the features from the controller to the appropriate components in the view.
   *
   * @param features {@link ControllerInterface} controller using this class.
   */

  void addFeatures(ControllerInterface features);

  /**
   * Resets the focus of the application to default.
   */

  void resetFocus();
}
