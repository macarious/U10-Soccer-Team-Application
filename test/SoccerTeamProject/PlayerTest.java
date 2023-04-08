package SoccerTeamProject;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

/**
 * Test which tests the {@link Player} class using JUnit4.
 */
public class PlayerTest {
  private Player player1;
  private Player player2;
  private Player player3;
  private Player player4;

  /**
   * Sets up {@link Player} used for various tests.
   */
  @Before
  public void setUp() {
    player1 = new Player("Lionel", "Messi", LocalDate.of(2018, 6, 24), Position.FORWARD, 5);
    player2 = new Player("Kevin", "De Bruyne", LocalDate.of(2021, 6, 28), Position.MIDFIELDER, 4);
    player3 = new Player("Manuel", "Neuer", LocalDate.of(2018, 3, 27), Position.GOALIE, 2);
    player4 = new Player("Sergio", "Ramos", LocalDate.of(2018, 3, 30), Position.DEFENDER, 3);
  }

  /**
   * This test tests the constructor when age is greater than 10.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIllegalAge1() {
    new Player("First", "Last", LocalDate.of(2012, 4, 1), Position.FORWARD, 5);
  }

  /**
   * This test tests the constructor when the birthdate is in the future (negative age).
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIllegalAge2() {
    new Player("First", "Last", LocalDate.of(2033, 4, 1), Position.FORWARD, 5);
  }

  /**
   * This test tests the constructor when the skill level is less than 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIllegalSkillLevel1() {
    new Player("First", "Last", LocalDate.of(2018, 4, 1), Position.FORWARD, 0);
  }

  /**
   * This test tests the constructor when the skill level is greater than 5.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorIllegalSkillLevel2() {
    new Player("First", "Last", LocalDate.of(2018, 4, 1), Position.FORWARD, 6);
  }

  /**
   * This test tests the getFirstName method.
   */
  @Test
  public void testGetFirstName() {
    assertEquals("Lionel", player1.getFirstName());
    assertEquals("Kevin", player2.getFirstName());
    assertEquals("Manuel", player3.getFirstName());
    assertEquals("Sergio", player4.getFirstName());
  }

  /**
   * This test tests the getLastName method.
   */
  @Test
  public void testGetLastName() {
    assertEquals("Messi", player1.getLastName());
    assertEquals("De Bruyne", player2.getLastName());
    assertEquals("Neuer", player3.getLastName());
    assertEquals("Ramos", player4.getLastName());
  }

  /**
   * This test tests the getAge method.
   */
  @Test
  public void testGetAge() {
    assertEquals(4, player1.getAge());
    assertEquals(1, player2.getAge());
    assertEquals(5, player3.getAge());
    assertEquals(5, player4.getAge());
  }

  /**
   * This test tests the getPreferredPosition method.
   */
  @Test
  public void testGetPreferredPosition() {
    assertEquals(Position.FORWARD, player1.getPreferredPosition());
    assertEquals(Position.MIDFIELDER, player2.getPreferredPosition());
    assertEquals(Position.GOALIE, player3.getPreferredPosition());
    assertEquals(Position.DEFENDER, player4.getPreferredPosition());
  }

  /**
   * This test tests the getSkillLevel method.
   */
  @Test
  public void testGetSkillLevel() {
    assertEquals(5, player1.getSkillLevel());
    assertEquals(4, player2.getSkillLevel());
    assertEquals(2, player3.getSkillLevel());
    assertEquals(3, player4.getSkillLevel());
  }

  @Test
  public void testNameToString() {
    assertEquals("Messi, Lionel", player1.nameToString());
    assertEquals("De Bruyne, Kevin", player2.nameToString());
    assertEquals("Neuer, Manuel", player3.nameToString());
    assertEquals("Ramos, Sergio", player4.nameToString());
  }

  /**
   * This test tests the toString method.
   */
  @Test
  public void testToString() {
    assertEquals("Messi, Lionel; AGE: 4; SKILL: 5", player1.toString());
    assertEquals("De Bruyne, Kevin; AGE: 1; SKILL: 4", player2.toString());
    assertEquals("Neuer, Manuel; AGE: 5; SKILL: 2", player3.toString());
    assertEquals("Ramos, Sergio; AGE: 5; SKILL: 3", player4.toString());
  }
}