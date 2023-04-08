package soccerteamproject;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

/**
 * Test which tests the {@link SoccerTeam} class using JUnit4.
 */
public class SoccerTeamTest {

  private static final int TEST_SEED = 19890604;
  private SoccerTeam soccerTeam;

  /**
   * Sets up {@link SoccerTeam} used for various tests.
   */
  @Before
  public void setUp() {
    soccerTeam = new SoccerTeam(new Random(TEST_SEED));
  }

  /**
   * This test tests the registerPlayer method.
   */
  @Test
  public void testRegisterPlayer() {
    soccerTeam.registerPlayer("Lionel", "Messi", LocalDate.of(2018, 6, 24), Position.FORWARD, 4);
    Set<Player> allPlayerList = soccerTeam.getAllPlayerList();

    // Check if player is added properly.
    Player player = allPlayerList.iterator().next();
    assertEquals("Lionel", player.getFirstName());
    assertEquals("Messi", player.getLastName());
    assertEquals(4, player.getAge());
    assertEquals(Position.FORWARD, player.getPreferredPosition());
    assertEquals(4, player.getSkillLevel());
    assertEquals("Messi, Lionel; AGE: 4; SKILL: 4", player.toString());

    // Check team.
    assertEquals(1, allPlayerList.size());
    assertEquals("Messi, Lionel; AGE: 4; SKILL: 4", soccerTeam.toString());
  }

  /**
   * This test tests the createTeam method when there are no players registered.
   */
  @Test(expected = InsufficientPlayerException.class)
  public void testCreateTeamInsufficientPlayer1() {
    // Create team with no registered players.
    soccerTeam.createTeam();
  }

  /**
   * This test tests the createTeam method when there are 9 players registered (1 short).
   */
  @Test(expected = InsufficientPlayerException.class)
  public void testCreateTeamInsufficientPlayer2() {
    soccerTeam.registerPlayer("Lionel", "Messi", LocalDate.of(2017, 6, 24), Position.FORWARD, 4);
    soccerTeam.registerPlayer("Cristiano", "Ronaldo", LocalDate.of(2015, 2, 5), Position.FORWARD,
        1);
    soccerTeam.registerPlayer("Neymar", "Jr.", LocalDate.of(2018, 2, 5), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Kylian", "Mbappé", LocalDate.of(2018, 12, 20), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Mohamed", "Salah", LocalDate.of(2018, 6, 15), Position.FORWARD, 1);
    soccerTeam.registerPlayer("Sergio", "Agüero", LocalDate.of(2018, 6, 2), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Harry", "Kane", LocalDate.of(2018, 7, 28), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Robert", "Lewandowski", LocalDate.of(2018, 8, 21), Position.FORWARD,
        4);
    soccerTeam.registerPlayer("Kevin", "De Bruyne", LocalDate.of(2018, 6, 28), Position.MIDFIELDER,
        1);

    // Create team with 9 registered players.
    soccerTeam.createTeam();
  }

  /**
   * This test tests the createTeam method when there are 10 players registered.
   */
  @Test
  public void testCreateTeamMinPlayers() {
    soccerTeam.registerPlayer("Lionel", "Messi", LocalDate.of(2017, 6, 24), Position.FORWARD, 4);
    soccerTeam.registerPlayer("Cristiano", "Ronaldo", LocalDate.of(2015, 2, 5), Position.FORWARD,
        1);
    soccerTeam.registerPlayer("Neymar", "Jr.", LocalDate.of(2018, 2, 5), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Kylian", "Mbappé", LocalDate.of(2018, 12, 20), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Mohamed", "Salah", LocalDate.of(2018, 6, 15), Position.FORWARD, 1);
    soccerTeam.registerPlayer("Sergio", "Agüero", LocalDate.of(2018, 6, 2), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Harry", "Kane", LocalDate.of(2018, 7, 28), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Robert", "Lewandowski", LocalDate.of(2018, 8, 21), Position.FORWARD,
        4);
    soccerTeam.registerPlayer("Kevin", "De Bruyne", LocalDate.of(2018, 6, 28), Position.MIDFIELDER,
        1);
    soccerTeam.registerPlayer("Luis", "Suárez", LocalDate.of(2017, 1, 24), Position.FORWARD, 2);

    // Create team with 10 registered players.
    soccerTeam.createTeam();

    // Check total number of players.
    assertEquals(10, soccerTeam.getTeamPlayerList().size());

    // Check number of goalies.
    assertEquals(1, soccerTeam
        .getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.GOALIE)
        .count());

    // Check number of defenders.
    assertEquals(2, soccerTeam
        .getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.DEFENDER)
        .count());

    // Check number of midfielders.
    assertEquals(3, soccerTeam
        .getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.MIDFIELDER)
        .count());

    // Check number of forwards.
    assertEquals(1, soccerTeam
        .getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.FORWARD)
        .count());

    // Check unique jersey numbers.
    int playerCount = soccerTeam.getTeamPlayerList().size();
    int uniqueJerseyCount = soccerTeam
        .getTeamPlayerList()
        .keySet()
        .stream()
        .map(entry -> entry.getJerseyNumber())
        .collect(Collectors.toCollection(HashSet::new))
        .size();
    assertEquals(playerCount, uniqueJerseyCount);
  }

  /**
   * This test tests the createTeam method when there are 20 players registered.
   */
  @Test
  public void testCreateTeamMaxPlayers() {
    soccerTeam.registerPlayer("Lionel", "Messi", LocalDate.of(2017, 6, 24), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Cristiano", "Ronaldo", LocalDate.of(2015, 2, 5), Position.FORWARD,
        4);
    soccerTeam.registerPlayer("Neymar", "Jr.", LocalDate.of(2018, 2, 5), Position.FORWARD, 1);
    soccerTeam.registerPlayer("Kylian", "Mbappé", LocalDate.of(2018, 12, 20), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Mohamed", "Salah", LocalDate.of(2018, 6, 15), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Sergio", "Agüero", LocalDate.of(2018, 6, 2), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Harry", "Kane", LocalDate.of(2018, 7, 28), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Robert", "Lewandowski", LocalDate.of(2018, 8, 21), Position.FORWARD,
        5);
    soccerTeam.registerPlayer("Kevin", "De Bruyne", LocalDate.of(2018, 6, 28), Position.MIDFIELDER,
        3);
    soccerTeam.registerPlayer("Luis", "Suárez", LocalDate.of(2017, 1, 24), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Manuel", "Neuer", LocalDate.of(2016, 3, 27), Position.GOALIE, 4);
    soccerTeam.registerPlayer("Luka", "Modrić", LocalDate.of(2015, 9, 9), Position.MIDFIELDER, 3);
    soccerTeam.registerPlayer("Antoine", "Griezmann", LocalDate.of(2018, 3, 21),
        Position.MIDFIELDER, 3);
    soccerTeam.registerPlayer("Eden", "Hazard", LocalDate.of(2018, 1, 7), Position.MIDFIELDER, 5);
    soccerTeam.registerPlayer("Raheem", "Sterling", LocalDate.of(2018, 12, 8), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Virgil", "van Dijk", LocalDate.of(2018, 7, 8), Position.DEFENDER, 4);
    soccerTeam.registerPlayer("Paulo", "Dybala", LocalDate.of(2018, 11, 15), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Gareth", "Bale", LocalDate.of(2018, 7, 16), Position.MIDFIELDER, 4);
    soccerTeam.registerPlayer("Sergio", "Ramos", LocalDate.of(2016, 3, 30), Position.DEFENDER, 1);
    soccerTeam.registerPlayer("Thiago", "Alcântara", LocalDate.of(2018, 4, 11), Position.MIDFIELDER,
        2);

    // Create team with 20 registered players.
    soccerTeam.createTeam();

    // Check total number of players.
    assertEquals(20, soccerTeam.getTeamPlayerList().size());

    // Check number of goalies.
    assertEquals(1, soccerTeam
        .getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.GOALIE)
        .count());

    // Check number of defenders.
    assertEquals(2, soccerTeam
        .getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.DEFENDER)
        .count());

    // Check number of midfielders.
    assertEquals(3, soccerTeam
        .getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.MIDFIELDER)
        .count());

    // Check number of forwards.
    assertEquals(1, soccerTeam
        .getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.FORWARD)
        .count());

    // Check unique jersey numbers.
    int playerCount = soccerTeam.getTeamPlayerList().size();
    int uniqueJerseyCount = soccerTeam
        .getTeamPlayerList()
        .keySet()
        .stream()
        .map(PlayerIdentifier::getJerseyNumber)
        .collect(Collectors.toCollection(HashSet::new))
        .size();
    assertEquals(playerCount, uniqueJerseyCount);
  }

  /**
   * This test tests the createTeam method when there are 25 players registered (5 more than
   * maximum). Only top 20 players with highest skill levels are assigned to the team.
   */
  @Test
  public void testCreateTeamExceedMaxPlayers() {
    soccerTeam.registerPlayer("Lionel", "Messi", LocalDate.of(2017, 6, 24), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Cristiano", "Ronaldo", LocalDate.of(2015, 2, 5), Position.FORWARD,
        4);
    soccerTeam.registerPlayer("Neymar", "Jr.", LocalDate.of(2018, 2, 5), Position.FORWARD, 1);
    soccerTeam.registerPlayer("Kylian", "Mbappé", LocalDate.of(2018, 12, 20), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Mohamed", "Salah", LocalDate.of(2018, 6, 15), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Sergio", "Agüero", LocalDate.of(2018, 6, 2), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Harry", "Kane", LocalDate.of(2018, 7, 28), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Robert", "Lewandowski", LocalDate.of(2018, 8, 21), Position.FORWARD,
        5);
    soccerTeam.registerPlayer("Kevin", "De Bruyne", LocalDate.of(2018, 6, 28), Position.MIDFIELDER,
        3);
    soccerTeam.registerPlayer("Luis", "Suárez", LocalDate.of(2017, 1, 24), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Manuel", "Neuer", LocalDate.of(2016, 3, 27), Position.GOALIE, 4);
    soccerTeam.registerPlayer("Luka", "Modrić", LocalDate.of(2015, 9, 9), Position.MIDFIELDER, 3);
    soccerTeam.registerPlayer("Antoine", "Griezmann", LocalDate.of(2018, 3, 21),
        Position.MIDFIELDER, 3);
    soccerTeam.registerPlayer("Eden", "Hazard", LocalDate.of(2018, 1, 7), Position.MIDFIELDER, 5);
    soccerTeam.registerPlayer("Raheem", "Sterling", LocalDate.of(2018, 12, 8), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Virgil", "van Dijk", LocalDate.of(2018, 7, 8), Position.DEFENDER, 4);
    soccerTeam.registerPlayer("Paulo", "Dybala", LocalDate.of(2018, 11, 15), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Gareth", "Bale", LocalDate.of(2018, 7, 16), Position.MIDFIELDER, 4);
    soccerTeam.registerPlayer("Sergio", "Ramos", LocalDate.of(2016, 3, 30), Position.DEFENDER, 1);
    soccerTeam.registerPlayer("Thiago", "Alcântara", LocalDate.of(2018, 4, 11), Position.MIDFIELDER,
        2);
    soccerTeam.registerPlayer("Sadio", "Mane", LocalDate.of(2018, 4, 10), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Jan", "Oblak", LocalDate.of(2018, 1, 7), Position.GOALIE, 2);
    soccerTeam.registerPlayer("Thomas", "Müller", LocalDate.of(2018, 9, 13), Position.MIDFIELDER,
        3);
    soccerTeam.registerPlayer("Marco", "Reus", LocalDate.of(2018, 5, 31), Position.MIDFIELDER, 5);
    soccerTeam.registerPlayer("Karim", "Benzema", LocalDate.of(2017, 12, 19), Position.FORWARD, 2);

    // Create team with 25 registered players.
    soccerTeam.createTeam();

    // Check total number of players.
    assertEquals(20, soccerTeam.getTeamPlayerList().size());

    // Check number of goalies.
    assertEquals(1, soccerTeam
        .getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.GOALIE)
        .count());

    // Check number of defenders.
    assertEquals(2, soccerTeam
        .getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.DEFENDER)
        .count());

    // Check number of midfielders.
    assertEquals(3, soccerTeam
        .getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.MIDFIELDER)
        .count());

    // Check number of forwards.
    assertEquals(1, soccerTeam
        .getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.FORWARD)
        .count());

    // Check unique jersey numbers.
    int playerCount = soccerTeam.getTeamPlayerList().size();
    int uniqueJerseyCount = soccerTeam
        .getTeamPlayerList()
        .keySet()
        .stream()
        .map(PlayerIdentifier::getJerseyNumber)
        .collect(Collectors.toCollection(HashSet::new))
        .size();
    assertEquals(playerCount, uniqueJerseyCount);
  }

  /**
   * This test tests the getAllPlayList.
   */
  @Test
  public void testGetAllPlayerList() {
    soccerTeam.registerPlayer("Lionel", "Messi", LocalDate.of(2017, 6, 24), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Cristiano", "Ronaldo", LocalDate.of(2015, 2, 5), Position.FORWARD,
        4);
    soccerTeam.registerPlayer("Neymar", "Jr.", LocalDate.of(2018, 2, 5), Position.FORWARD, 1);
    soccerTeam.registerPlayer("Kylian", "Mbappé", LocalDate.of(2018, 12, 20), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Mohamed", "Salah", LocalDate.of(2018, 6, 15), Position.FORWARD, 2);

    // Add all expected players to the expected set.
    Set<Player> expectedSet = new TreeSet<>(
        Comparator.comparing(Player::getLastName).thenComparing(Player::getFirstName));
    expectedSet.add(new Player("Lionel", "Messi", LocalDate.of(2017, 6, 24), Position.FORWARD, 2));
    expectedSet.add(
        new Player("Cristiano", "Ronaldo", LocalDate.of(2015, 2, 5), Position.FORWARD, 4));
    expectedSet.add(new Player("Neymar", "Jr.", LocalDate.of(2018, 2, 5), Position.FORWARD, 1));
    expectedSet.add(
        new Player("Kylian", "Mbappé", LocalDate.of(2018, 12, 20), Position.FORWARD, 2));
    expectedSet.add(new Player("Mohamed", "Salah", LocalDate.of(2018, 6, 15), Position.FORWARD, 2));

    assertEquals(expectedSet, soccerTeam.getAllPlayerList());

  }

  /**
   * This test tests the getStartingLineUp method.
   */
  @Test
  public void testGetStartingLineUp() {
    soccerTeam.registerPlayer("Lionel", "Messi", LocalDate.of(2017, 6, 24), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Cristiano", "Ronaldo", LocalDate.of(2015, 2, 5), Position.FORWARD,
        4);
    soccerTeam.registerPlayer("Neymar", "Jr.", LocalDate.of(2018, 2, 5), Position.FORWARD, 1);
    soccerTeam.registerPlayer("Kylian", "Mbappé", LocalDate.of(2018, 12, 20), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Mohamed", "Salah", LocalDate.of(2018, 6, 15), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Sergio", "Agüero", LocalDate.of(2018, 6, 2), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Harry", "Kane", LocalDate.of(2018, 7, 28), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Robert", "Lewandowski", LocalDate.of(2018, 8, 21), Position.FORWARD,
        5);
    soccerTeam.registerPlayer("Kevin", "De Bruyne", LocalDate.of(2018, 6, 28), Position.MIDFIELDER,
        3);
    soccerTeam.registerPlayer("Luis", "Suárez", LocalDate.of(2017, 1, 24), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Manuel", "Neuer", LocalDate.of(2016, 3, 27), Position.GOALIE, 4);
    soccerTeam.registerPlayer("Luka", "Modrić", LocalDate.of(2015, 9, 9), Position.MIDFIELDER, 3);
    soccerTeam.registerPlayer("Antoine", "Griezmann", LocalDate.of(2018, 3, 21),
        Position.MIDFIELDER, 3);
    soccerTeam.registerPlayer("Eden", "Hazard", LocalDate.of(2018, 1, 7), Position.MIDFIELDER, 5);
    soccerTeam.registerPlayer("Raheem", "Sterling", LocalDate.of(2018, 12, 8), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Virgil", "van Dijk", LocalDate.of(2018, 7, 8), Position.DEFENDER, 4);
    soccerTeam.registerPlayer("Paulo", "Dybala", LocalDate.of(2018, 11, 15), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Gareth", "Bale", LocalDate.of(2018, 7, 16), Position.MIDFIELDER, 4);
    soccerTeam.registerPlayer("Sergio", "Ramos", LocalDate.of(2016, 3, 30), Position.DEFENDER, 1);
    soccerTeam.registerPlayer("Thiago", "Alcântara", LocalDate.of(2018, 4, 11), Position.MIDFIELDER,
        2);

    // Create team with 20 registered players.
    soccerTeam.createTeam();

    // Add all 7 expected players to the expected map.
    Map<PlayerIdentifier, Player> expectedMap = new LinkedHashMap<>();

    PlayerIdentifier playerIdentifier = new PlayerIdentifier(16);
    playerIdentifier.setAssignedPosition(Position.GOALIE);
    Player player = new Player("Raheem", "Sterling", LocalDate.of(2018, 12, 8), Position.FORWARD,
        5);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(2);
    playerIdentifier.setAssignedPosition(Position.DEFENDER);
    player = new Player("Luis", "Suárez", LocalDate.of(2017, 1, 24), Position.FORWARD, 5);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(10);
    playerIdentifier.setAssignedPosition(Position.DEFENDER);
    player = new Player("Virgil", "van Dijk", LocalDate.of(2018, 7, 8), Position.DEFENDER, 4);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(14);
    playerIdentifier.setAssignedPosition(Position.MIDFIELDER);
    player = new Player("Gareth", "Bale", LocalDate.of(2018, 7, 16), Position.MIDFIELDER, 4);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(12);
    playerIdentifier.setAssignedPosition(Position.MIDFIELDER);
    player = new Player("Eden", "Hazard", LocalDate.of(2018, 1, 7), Position.MIDFIELDER, 5);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(15);
    playerIdentifier.setAssignedPosition(Position.MIDFIELDER);
    player = new Player("Manuel", "Neuer", LocalDate.of(2016, 3, 27), Position.GOALIE, 4);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(19);
    playerIdentifier.setAssignedPosition(Position.FORWARD);
    player = new Player("Robert", "Lewandowski", LocalDate.of(2018, 8, 21), Position.FORWARD, 5);
    expectedMap.put(playerIdentifier, player);

    assertEquals(expectedMap, soccerTeam.getStartingLineUp());
  }

  /**
   * This test tests the getTeamPlayerList method.
   */
  @Test
  public void testGetTeamPlayerList() {
    soccerTeam.registerPlayer("Lionel", "Messi", LocalDate.of(2017, 6, 24), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Cristiano", "Ronaldo", LocalDate.of(2015, 2, 5), Position.FORWARD,
        4);
    soccerTeam.registerPlayer("Neymar", "Jr.", LocalDate.of(2018, 2, 5), Position.FORWARD, 1);
    soccerTeam.registerPlayer("Kylian", "Mbappé", LocalDate.of(2018, 12, 20), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Mohamed", "Salah", LocalDate.of(2018, 6, 15), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Sergio", "Agüero", LocalDate.of(2018, 6, 2), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Harry", "Kane", LocalDate.of(2018, 7, 28), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Robert", "Lewandowski", LocalDate.of(2018, 8, 21), Position.FORWARD,
        5);
    soccerTeam.registerPlayer("Kevin", "De Bruyne", LocalDate.of(2018, 6, 28), Position.MIDFIELDER,
        3);
    soccerTeam.registerPlayer("Luis", "Suárez", LocalDate.of(2017, 1, 24), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Manuel", "Neuer", LocalDate.of(2016, 3, 27), Position.GOALIE, 4);
    soccerTeam.registerPlayer("Luka", "Modrić", LocalDate.of(2015, 9, 9), Position.MIDFIELDER, 3);
    soccerTeam.registerPlayer("Antoine", "Griezmann", LocalDate.of(2018, 3, 21),
        Position.MIDFIELDER, 3);
    soccerTeam.registerPlayer("Eden", "Hazard", LocalDate.of(2018, 1, 7), Position.MIDFIELDER, 5);
    soccerTeam.registerPlayer("Raheem", "Sterling", LocalDate.of(2018, 12, 8), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Virgil", "van Dijk", LocalDate.of(2018, 7, 8), Position.DEFENDER, 4);
    soccerTeam.registerPlayer("Paulo", "Dybala", LocalDate.of(2018, 11, 15), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Gareth", "Bale", LocalDate.of(2018, 7, 16), Position.MIDFIELDER, 4);
    soccerTeam.registerPlayer("Sergio", "Ramos", LocalDate.of(2016, 3, 30), Position.DEFENDER, 1);
    soccerTeam.registerPlayer("Thiago", "Alcântara", LocalDate.of(2018, 4, 11), Position.MIDFIELDER,
        2);

    // Create team with 20 registered players.
    soccerTeam.createTeam();

    // Add all 20 expected players to the expected map.
    Map<PlayerIdentifier, Player> expectedMap = new LinkedHashMap<>();

    PlayerIdentifier playerIdentifier = new PlayerIdentifier(16);
    playerIdentifier.setAssignedPosition(Position.GOALIE);
    Player player = new Player("Raheem", "Sterling", LocalDate.of(2018, 12, 8), Position.FORWARD,
        5);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(2);
    playerIdentifier.setAssignedPosition(Position.DEFENDER);
    player = new Player("Luis", "Suárez", LocalDate.of(2017, 1, 24), Position.FORWARD, 5);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(10);
    playerIdentifier.setAssignedPosition(Position.DEFENDER);
    player = new Player("Virgil", "van Dijk", LocalDate.of(2018, 7, 8), Position.DEFENDER, 4);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(14);
    playerIdentifier.setAssignedPosition(Position.MIDFIELDER);
    player = new Player("Gareth", "Bale", LocalDate.of(2018, 7, 16), Position.MIDFIELDER, 4);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(12);
    playerIdentifier.setAssignedPosition(Position.MIDFIELDER);
    player = new Player("Eden", "Hazard", LocalDate.of(2018, 1, 7), Position.MIDFIELDER, 5);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(15);
    playerIdentifier.setAssignedPosition(Position.MIDFIELDER);
    player = new Player("Manuel", "Neuer", LocalDate.of(2016, 3, 27), Position.GOALIE, 4);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(19);
    playerIdentifier.setAssignedPosition(Position.FORWARD);
    player = new Player("Robert", "Lewandowski", LocalDate.of(2018, 8, 21), Position.FORWARD, 5);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(8);
    player = new Player("Sergio", "Agüero", LocalDate.of(2018, 6, 2), Position.FORWARD, 2);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(4);
    player = new Player("Thiago", "Alcântara", LocalDate.of(2018, 4, 11), Position.MIDFIELDER, 2);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(17);
    player = new Player("Kevin", "De Bruyne", LocalDate.of(2018, 6, 28), Position.MIDFIELDER, 3);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(9);
    player = new Player("Paulo", "Dybala", LocalDate.of(2018, 11, 15), Position.FORWARD, 3);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(6);
    player = new Player("Antoine", "Griezmann", LocalDate.of(2018, 3, 21), Position.MIDFIELDER, 3);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(11);
    player = new Player("Neymar", "Jr.", LocalDate.of(2018, 2, 5), Position.FORWARD, 1);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(5);
    player = new Player("Harry", "Kane", LocalDate.of(2018, 7, 28), Position.FORWARD, 2);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(1);
    player = new Player("Kylian", "Mbappé", LocalDate.of(2018, 12, 20), Position.FORWARD, 2);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(13);
    player = new Player("Lionel", "Messi", LocalDate.of(2017, 6, 24), Position.FORWARD, 2);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(7);
    player = new Player("Luka", "Modrić", LocalDate.of(2015, 9, 9), Position.MIDFIELDER, 3);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(18);
    player = new Player("Sergio", "Ramos", LocalDate.of(2016, 3, 30), Position.DEFENDER, 1);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(3);
    player = new Player("Cristiano", "Ronaldo", LocalDate.of(2015, 2, 5), Position.FORWARD, 4);
    expectedMap.put(playerIdentifier, player);

    playerIdentifier = new PlayerIdentifier(20);
    player = new Player("Mohamed", "Salah", LocalDate.of(2018, 6, 15), Position.FORWARD, 2);
    expectedMap.put(playerIdentifier, player);

    assertHashMapEquals(expectedMap, soccerTeam.getTeamPlayerList());
  }

  // Custom method to compare HashMaps based on their content
  private void assertHashMapEquals(Map<PlayerIdentifier, Player> expected,
      Map<PlayerIdentifier, Player> actual) {
    assertEquals(expected.keySet(), actual.keySet());
    for (PlayerIdentifier key : expected.keySet()) {
      assertEquals(expected.get(key), actual.get(key));
    }
  }

  /**
   * This test tests the allTeamPlayerListToString method.
   */
  @Test
  public void testAllTeamPlayerListToString() {
    soccerTeam.registerPlayer("Lionel", "Messi", LocalDate.of(2017, 6, 24), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Cristiano", "Ronaldo", LocalDate.of(2015, 2, 5), Position.FORWARD,
        4);
    soccerTeam.registerPlayer("Neymar", "Jr.", LocalDate.of(2018, 2, 5), Position.FORWARD, 1);
    soccerTeam.registerPlayer("Kylian", "Mbappé", LocalDate.of(2018, 12, 20), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Mohamed", "Salah", LocalDate.of(2018, 6, 15), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Sergio", "Agüero", LocalDate.of(2018, 6, 2), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Harry", "Kane", LocalDate.of(2018, 7, 28), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Robert", "Lewandowski", LocalDate.of(2018, 8, 21), Position.FORWARD,
        5);
    soccerTeam.registerPlayer("Kevin", "De Bruyne", LocalDate.of(2018, 6, 28), Position.MIDFIELDER,
        3);
    soccerTeam.registerPlayer("Luis", "Suárez", LocalDate.of(2017, 1, 24), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Manuel", "Neuer", LocalDate.of(2016, 3, 27), Position.GOALIE, 4);
    soccerTeam.registerPlayer("Luka", "Modrić", LocalDate.of(2015, 9, 9), Position.MIDFIELDER, 3);
    soccerTeam.registerPlayer("Antoine", "Griezmann", LocalDate.of(2018, 3, 21),
        Position.MIDFIELDER, 3);
    soccerTeam.registerPlayer("Eden", "Hazard", LocalDate.of(2018, 1, 7), Position.MIDFIELDER, 5);
    soccerTeam.registerPlayer("Raheem", "Sterling", LocalDate.of(2018, 12, 8), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Virgil", "van Dijk", LocalDate.of(2018, 7, 8), Position.DEFENDER, 4);
    soccerTeam.registerPlayer("Paulo", "Dybala", LocalDate.of(2018, 11, 15), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Gareth", "Bale", LocalDate.of(2018, 7, 16), Position.MIDFIELDER, 4);
    soccerTeam.registerPlayer("Sergio", "Ramos", LocalDate.of(2016, 3, 30), Position.DEFENDER, 1);
    soccerTeam.registerPlayer("Thiago", "Alcântara", LocalDate.of(2018, 4, 11), Position.MIDFIELDER,
        2);

    // Create team with 20 registered players.
    soccerTeam.createTeam();

    StringBuilder expected = new StringBuilder();
    expected.append("16 GOALIE -- Sterling, Raheem\n");
    expected.append("02 DEFENDER -- Suárez, Luis\n");
    expected.append("10 DEFENDER -- van Dijk, Virgil\n");
    expected.append("14 MIDFIELDER -- Bale, Gareth\n");
    expected.append("12 MIDFIELDER -- Hazard, Eden\n");
    expected.append("15 MIDFIELDER -- Neuer, Manuel\n");
    expected.append("19 FORWARD -- Lewandowski, Robert\n");
    expected.append("08 BENCHED -- Agüero, Sergio\n");
    expected.append("04 BENCHED -- Alcântara, Thiago\n");
    expected.append("17 BENCHED -- De Bruyne, Kevin\n");
    expected.append("09 BENCHED -- Dybala, Paulo\n");
    expected.append("06 BENCHED -- Griezmann, Antoine\n");
    expected.append("11 BENCHED -- Jr., Neymar\n");
    expected.append("05 BENCHED -- Kane, Harry\n");
    expected.append("01 BENCHED -- Mbappé, Kylian\n");
    expected.append("13 BENCHED -- Messi, Lionel\n");
    expected.append("07 BENCHED -- Modrić, Luka\n");
    expected.append("18 BENCHED -- Ramos, Sergio\n");
    expected.append("03 BENCHED -- Ronaldo, Cristiano\n");
    expected.append("20 BENCHED -- Salah, Mohamed");

    assertEquals(expected.toString(), soccerTeam.allTeamPlayerListToString());
  }

  /**
   * This test tests the startingLineUpToString method.
   */
  @Test
  public void startingLineUpToString() {
    soccerTeam.registerPlayer("Lionel", "Messi", LocalDate.of(2017, 6, 24), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Cristiano", "Ronaldo", LocalDate.of(2015, 2, 5), Position.FORWARD,
        4);
    soccerTeam.registerPlayer("Neymar", "Jr.", LocalDate.of(2018, 2, 5), Position.FORWARD, 1);
    soccerTeam.registerPlayer("Kylian", "Mbappé", LocalDate.of(2018, 12, 20), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Mohamed", "Salah", LocalDate.of(2018, 6, 15), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Sergio", "Agüero", LocalDate.of(2018, 6, 2), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Harry", "Kane", LocalDate.of(2018, 7, 28), Position.FORWARD, 2);
    soccerTeam.registerPlayer("Robert", "Lewandowski", LocalDate.of(2018, 8, 21), Position.FORWARD,
        5);
    soccerTeam.registerPlayer("Kevin", "De Bruyne", LocalDate.of(2018, 6, 28), Position.MIDFIELDER,
        3);
    soccerTeam.registerPlayer("Luis", "Suárez", LocalDate.of(2017, 1, 24), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Manuel", "Neuer", LocalDate.of(2016, 3, 27), Position.GOALIE, 4);
    soccerTeam.registerPlayer("Luka", "Modrić", LocalDate.of(2015, 9, 9), Position.MIDFIELDER, 3);
    soccerTeam.registerPlayer("Antoine", "Griezmann", LocalDate.of(2018, 3, 21),
        Position.MIDFIELDER, 3);
    soccerTeam.registerPlayer("Eden", "Hazard", LocalDate.of(2018, 1, 7), Position.MIDFIELDER, 5);
    soccerTeam.registerPlayer("Raheem", "Sterling", LocalDate.of(2018, 12, 8), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Virgil", "van Dijk", LocalDate.of(2018, 7, 8), Position.DEFENDER, 4);
    soccerTeam.registerPlayer("Paulo", "Dybala", LocalDate.of(2018, 11, 15), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Gareth", "Bale", LocalDate.of(2018, 7, 16), Position.MIDFIELDER, 4);
    soccerTeam.registerPlayer("Sergio", "Ramos", LocalDate.of(2016, 3, 30), Position.DEFENDER, 1);
    soccerTeam.registerPlayer("Thiago", "Alcântara", LocalDate.of(2018, 4, 11), Position.MIDFIELDER,
        2);

    // Create team with 20 registered players.
    soccerTeam.createTeam();

    StringBuilder expected = new StringBuilder();
    expected.append("16 GOALIE -- Sterling, Raheem\n");
    expected.append("02 DEFENDER -- Suárez, Luis\n");
    expected.append("10 DEFENDER -- van Dijk, Virgil\n");
    expected.append("14 MIDFIELDER -- Bale, Gareth\n");
    expected.append("12 MIDFIELDER -- Hazard, Eden\n");
    expected.append("15 MIDFIELDER -- Neuer, Manuel\n");
    expected.append("19 FORWARD -- Lewandowski, Robert");

    assertEquals(expected.toString(), soccerTeam.startingLineUpToString());
  }
}