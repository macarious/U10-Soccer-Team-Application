package SoccerTeamProject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

/**
 * Test which tests the {@link SoccerTeam} class using JUnit4.
 */
public class SoccerTeamTest {

  private SoccerTeam soccerTeam;
  private static final int TEST_SEED = 19890604;

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
    assertEquals(1, soccerTeam.getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.GOALIE)
        .count());

    // Check number of defenders.
    assertEquals(2, soccerTeam.getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.DEFENDER)
        .count());

    // Check number of midfielders.
    assertEquals(3, soccerTeam.getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.MIDFIELDER)
        .count());

    // Check number of forwards.
    assertEquals(1, soccerTeam.getTeamPlayerList()
        .entrySet()
        .stream()
        .filter(entry -> entry.getKey().getAssignedPosition() == Position.FORWARD)
        .count());

    // Check unique jersey numbers.
    int playerCount = soccerTeam.getTeamPlayerList().size();
    int uniqueJerseyCount = soccerTeam.getTeamPlayerList()
        .keySet()
        .stream()
        .map(entry -> entry.getJerseyNumber())
        .collect(Collectors.toCollection(HashSet::new))
        .size();
    assertEquals(playerCount, uniqueJerseyCount);

    StringBuilder expected = new StringBuilder();
    expected.append("13 GOALIE -- Lewandowski, Robert\n");
    expected.append("20 DEFENDER -- Agüero, Sergio\n");
    expected.append("02 DEFENDER -- Messi, Lionel\n");
    expected.append("05 MIDFIELDER -- Jr., Neymar\n");
    expected.append("08 MIDFIELDER -- Kane, Harry\n");
    expected.append("18 MIDFIELDER -- Suárez, Luis\n");
    expected.append("12 FORWARD -- Mbappé, Kylian\n");
    expected.append("09 BENCHED -- De Bruyne, Kevin\n");
    expected.append("14 BENCHED -- Ronaldo, Cristiano\n");
    expected.append("04 BENCHED -- Salah, Mohamed");
    assertEquals(expected.toString(), soccerTeam.allPlayerListToString());
  }

  @Test
  public void getAllPlayerList() {
  }

  @Test
  public void getStartingLineUp() {
  }

  @Test
  public void getTeamPlayerList() {
  }

  @Test
  public void allPlayerListToString() {
  }

  @Test
  public void startingLineUpToString() {
  }
}