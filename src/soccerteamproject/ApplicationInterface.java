package soccerteamproject;

import java.awt.Color;
import java.util.Map;
import java.util.Set;

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
   */
  void addPlayer();

  /**
   * This method gets the UserInput objects containing all the information that is currently in the
   * text fields and combo boxes.
   *
   * @return {@link UserInput}, user input currently on the application.
   */
  UserInput getUserInput();

  /**
   * Display a list of all registered players.
   *
   * @param allPlayerList {@link Set}<{@link Player}> List of soccer players on the team.
   */

  void displayAllPlayer(Set<Player> allPlayerList);

  /**
   * Display a list of all players selected to be on the team.
   *
   * @param teamPlayerList {@link Map} of {@link PlayerIdentifier}, {@link Player}, soccer players
   *                       on team.
   */

  void displayTeamPlayer(Map<PlayerIdentifier, Player> teamPlayerList);

  /**
   * Display a list of all players on the starting line up.
   *
   * @param startingLineUp {@link Map} of {@link PlayerIdentifier}, {@link Player}, starting line up
   *                       on team.
   */

  void displayStartingLineUp(Map<PlayerIdentifier, Player> startingLineUp);

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

  /**
   * This method displays a message to the user.
   */
  void displayMessage(String message, Color fontColour);

  /**
   * This method validate if the name inputs are completed.
   *
   * @return boolean, true if completed and false otherwise.
   */
  boolean isNameInputComplete();

  /**
   * This method resets all the user input fields.
   */
  void resetAllFields();
}
