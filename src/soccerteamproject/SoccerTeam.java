package soccerteamproject;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * This class represents a soccer team. It can do the following: add players to the team, assign
 * player positions, assign jersey numbers to the players, retrieve a list of players, and retrieve
 * a list of players on the starting line up.
 */
public class SoccerTeam implements SoccerTeamInterface {
  private static final int MAX_GOALIE_COUNT = 1;
  private static final int MAX_DEFENDER_COUNT = 2;
  private static final int MAX_MIDFIELDER_COUNT = 3;
  private static final int MAX_FORWARD_COUNT = 1;
  private static final int MIN_TEAM_PLAYERS_COUNT = 10;
  private static final int MAX_TEAM_PLAYERS_COUNT = 20;
  private static final int MAX_JERSEY_NUMBER = 20;
  // Map of position and the remaining assignments.
  private final Map<Position, Integer> positionAssignmentCount;
  // All players input, sorted by skill level.
  private final Set<Player> allPlayerList;
  // Map of players and their identifiers.
  private final Map<PlayerIdentifier, Player> teamPlayerList;
  // Random generated used for all methods.
  private final Random randomGenerator;

  /**
   * This is a constructor of a SoccerTeam. It creates an empty player list and an empty list of
   * players assigned to the team. The player list (Tree Set) is sorted by skill level because they
   * are the most valuable/prioritized players on the list.
   *
   * @param randomGenerator Random, random generator to be used for the team creation.
   */
  public SoccerTeam(Random randomGenerator) {
    this.allPlayerList = new TreeSet<>(Comparator
        .comparingInt(Player::getSkillLevel)
        .reversed()
        .thenComparing(Player::getLastName)
        .thenComparing(Player::getFirstName)
        .thenComparing(Player::getAge));
    this.teamPlayerList = new LinkedHashMap<>();
    this.positionAssignmentCount = new TreeMap<>();
    this.populatePositionAssignmentCount();
    this.randomGenerator = randomGenerator;
  }

  @Override
  public void registerPlayer(String firstName, String lastName, LocalDate birthdate,
      Position preferredPosition, int skillLevel) throws IllegalArgumentException {
    // IllegalArgumentException may be thrown when instantiating Player.
    Player newPlayer = new Player(firstName, lastName, birthdate, preferredPosition, skillLevel);
    this.allPlayerList.add(newPlayer);
  }

  @Override
  public void createTeam() throws InsufficientPlayerException {
    if (this.allPlayerList.size() < MIN_TEAM_PLAYERS_COUNT) {
      throw new InsufficientPlayerException("There are not enough players to form a team.");
    }

    // Creates a team by assigning jersey numbers to up to 20 players (w/the highest skill level).
    this.assignJerseyNumberToTeam();
    this.assignPositions();
  }

  @Override
  public Set<Player> getAllPlayerList() {
    TreeSet<Player> sortedSet = new TreeSet<>(
        Comparator.comparing(Player::getLastName).thenComparing(Player::getFirstName));
    sortedSet.addAll(this.allPlayerList);
    return sortedSet;
  }

  @Override
  public Map<PlayerIdentifier, Player> getStartingLineUp() {
    Map<PlayerIdentifier, Player> startingLineUp = this.teamPlayerList
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() != null)
        .collect(
            Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (value1, value2) -> value2,
                LinkedHashMap::new));
    return sortMap(startingLineUp);
  }

  @Override
  public Map<PlayerIdentifier, Player> getTeamPlayerList() {
    return sortMap(this.teamPlayerList);
  }

  @Override
  public String toString() {
    return this.allPlayerList.stream().map(Player::toString).collect(Collectors.joining("\n"));
  }

  @Override
  public String allTeamPlayerListToString() {
    return convertMapToString(this.getTeamPlayerList());
  }

  @Override
  public String startingLineUpToString() {
    return convertMapToString(this.getStartingLineUp());
  }

  /**
   * This method populates the positionAssignmentCount map with the appropriate count.
   */
  private void populatePositionAssignmentCount() {
    positionAssignmentCount.put(Position.GOALIE, MAX_GOALIE_COUNT);
    positionAssignmentCount.put(Position.DEFENDER, MAX_DEFENDER_COUNT);
    positionAssignmentCount.put(Position.MIDFIELDER, MAX_MIDFIELDER_COUNT);
    positionAssignmentCount.put(Position.FORWARD, MAX_FORWARD_COUNT);
  }

  /**
   * This method sorts a list of {@link Player} by their assigned position. Last name and then first
   * name are used as tie-breakers.
   *
   * @param map {@link Map} of {@link PlayerIdentifier}, {@link Player}, a list of soccer players.
   * @return {@link Map} of {@link PlayerIdentifier}, {@link Player}, a list of sorted players.
   */
  private Map<PlayerIdentifier, Player> sortMap(Map<PlayerIdentifier, Player> map) {
    return map
        .entrySet()
        .stream()
        .sorted(Entry
            .<PlayerIdentifier, Player>comparingByKey()
            .thenComparing(entry -> entry.getValue().getLastName())
            .thenComparing(entry -> entry.getValue().getFirstName()))
        .collect(
            Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (value1, value2) -> value2,
                LinkedHashMap::new));
  }

  /**
   * This method assigns randomly generated numbers from 1 to 20 to each of the first 20 players
   * (already sorted by skill level) and form a team.
   */
  private void assignJerseyNumberToTeam() {
    // Create a hash set containing all the available jersey numbers.
    Set<Integer> availableNumbers = new HashSet<>();
    for (int i = 1; i <= MAX_JERSEY_NUMBER; i++) {
      availableNumbers.add(i);
    }

    for (Player player : allPlayerList) {

      // Only assign player to team if there are still available jersey numbers to choose from.
      if (!availableNumbers.isEmpty()) {

        // Choose a random integer from available numbers.
        Iterator<Integer> iterator = availableNumbers.iterator();
        int randomIndex = randomGenerator.nextInt(availableNumbers.size());
        int jerseyNumber = 0;
        for (int i = 0; i <= randomIndex; i++) {
          jerseyNumber = iterator.next();
        }
        iterator.remove();

        // Add player to the teamPlayerList with the chosen jersey number.
        this.teamPlayerList.put(new PlayerIdentifier(jerseyNumber), player);
      }
    }
  }

  /**
   * This method assign positions to the players who have already assigned a jersey number (which
   * means they are on the team). The positions are first assigned to the highest skilled players.
   * If the player's preferred position is still available, it will be assigned to the player;
   * another position will be assigned otherwise.
   */
  private void assignPositions() {
    int skillLevel = 5; // Start assigning positions at skill level 5, then move to 4, 3, etc.
    while (skillLevel > 0) {
      // Assign positions to players who have a matching preferred position.
      for (Position position : Position.values()) {
        int remaining = this.positionAssignmentCount.get(position);
        this.assignPositionPreferred(position, remaining, skillLevel);
      }

      // Assign positions to players to remaining players with the same skill levels.
      for (Position position : Position.values()) {
        int remaining = this.positionAssignmentCount.get(position);
        this.assignPositionRemained(position, remaining, skillLevel);
      }
      skillLevel--;
    }
  }

  /**
   * This method assigns a position a specified number of times to players who have a matching
   * preferred position, and returns the number of positions which are successfully assigned. The
   * team list should already be sorted by skill level.
   *
   * @param position {@link Position}, position to be assigned.
   * @param quantity int, the maximum number of players to be assigned.
   */
  private void assignPositionPreferred(Position position, int quantity, int skillLevel) {
    // Repeat assignment until desired number of players have been assigned, or when there are no
    // more players with the preferred positions to assign to.
    for (int i = 0; i < quantity; i++) {
      this.teamPlayerList
          .entrySet()
          .stream()
          .filter(entry -> entry.getKey().getAssignedPosition() == null)
          .filter(entry -> entry.getValue().getSkillLevel() == skillLevel)
          .filter(entry -> entry.getValue().getPreferredPosition() == position)
          .findAny()
          .ifPresent(entry -> {
            entry.getKey().setAssignedPosition(position);
            int positionCount = this.positionAssignmentCount.get(position);
            this.positionAssignmentCount.put(position, positionCount - 1);
          });
    }
  }

  /**
   * This method assigns a position a specified number of times to all remaining players and returns
   * the number of positions which are successfully assigned.
   *
   * @param position {@link Position}, position to be assigned.
   * @param quantity int, the number of players to be assigned.
   */
  private void assignPositionRemained(Position position, int quantity, int skillLevel) {
    for (int i = 0; i < quantity; i++) {
      this.teamPlayerList
          .entrySet()
          .stream()
          .filter(entry -> entry.getKey().getAssignedPosition() == null)
          .filter(entry -> entry.getValue().getSkillLevel() == skillLevel)
          .findAny()
          .ifPresent(entry -> {
            entry.getKey().setAssignedPosition(position);
            int positionCount = this.positionAssignmentCount.get(position);
            this.positionAssignmentCount.put(position, positionCount - 1);
          });
    }
  }

  /**
   * This method converts a map of <{@link PlayerIdentifier}, {@link Player}> and convert it into a
   * String.
   *
   * @param map Map<{@link PlayerIdentifier}, {@link Player}>, a map to be converted.
   * @return String, lists out the keys and values.
   */
  private String convertMapToString(Map<PlayerIdentifier, Player> map) {
    return map
        .entrySet()
        .stream()
        .map(entry -> entry.getKey() + " -- " + entry.getValue().nameToString())
        .collect(Collectors.joining("\n"));
  }
}