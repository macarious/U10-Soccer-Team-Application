package soccerteamproject;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a soccer team. It can do the following: add players to the team, assign
 * player positions, assign jersey numbers to the players, retrieve a list of players, and retrieve
 * a list of players on the starting line up.
 */
public interface SoccerTeamInterface {
  /**
   * This method adds a {@link Player} object to the list of soccer players, given the player's
   * information.
   *
   * @param firstName         String, first name of player.
   * @param lastName          String, last name of player.
   * @param birthdate         LocalDate, birthdate of player.
   * @param preferredPosition Position, preferred position of player.
   * @param skillLevel        int, skill level from 1 to 5 of player.
   * @throws IllegalArgumentException if player's age is less than 0 or greater than 10.
   * @throws IllegalArgumentException if skill leve is not between 1 and 5 (inclusive).
   */
  void registerPlayer(String firstName, String lastName, LocalDate birthdate,
      Position preferredPosition, int skillLevel) throws IllegalArgumentException;

  /**
   * This method assigns the positions of all the players on the soccer team, and it also determined
   * which players are on the starting line up and which ones are benched. This also checks if there
   * are enough players on the team or if there are too many players on the team.
   *
   * @throws InsufficientPlayerException if the number of players is less than 10.
   */
  void createTeam() throws InsufficientPlayerException;

  /**
   * This method retrieves a set of all the soccer players on the soccer team.
   *
   * @return {@link Set}<{@link Player}> List of soccer players on the team.
   */
  Set<Player> getAllPlayerList();

  /**
   * This method retrieves a map of all (20) team players on the soccer team. The key value is the
   * unique player identifier (consisting of a jersey number and a position) and the value is the
   * {@link Player}.
   *
   * @return {@link Map} of {@link PlayerIdentifier}, {@link Player}, soccer players on team.
   */
  Map<PlayerIdentifier, Player> getTeamPlayerList();

  /**
   * This method retrieves a map of all the soccer players on the starting line up. The key value is
   * the unique player identifier (consisting of a jersey number and a position) and the value is
   * the {@link Player}.
   *
   * @return {@link Map} of {@link PlayerIdentifier}, {@link Player}, list of starting line up.
   */
  Map<PlayerIdentifier, Player> getStartingLineUp();

  /**
   * This method converts the list of all the players on the team into a string, listing the jersey
   * number, last name, and first name.
   *
   * @return String, list of team player represented as string.
   */
  String allTeamPlayerListToString();

  /**
   * This method converts the list of all the players on the starting lineup into a string, listing
   * the jersey number, assigned position, last name, and first name.
   *
   * @return String, list of starting line up represented as string.
   */
  String startingLineUpToString();

  /**
   * This method resets the list of players and team players to an empty list.
   */
  void resetSoccerTeam();
}
