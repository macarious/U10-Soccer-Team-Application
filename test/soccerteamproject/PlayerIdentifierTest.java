package soccerteamproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test which tests the {@link PlayerIdentifier} class using JUnit4.
 */
public class PlayerIdentifierTest {
  private PlayerIdentifier player1;
  private PlayerIdentifier player2;
  private PlayerIdentifier player3;
  private PlayerIdentifier player4;

  /**
   * Sets up {@link PlayerIdentifier} used for various tests.
   */
  @Before
  public void setUp() {
    player1 = new PlayerIdentifier(10);
    player2 = new PlayerIdentifier(15);
    player3 = new PlayerIdentifier(0);
    player4 = new PlayerIdentifier(15);
  }

  /**
   * This test tests the constructor when jersey number is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetJerseyNumberIllegalArgument() {
    new PlayerIdentifier(-1);
  }

  /**
   * This test tests the getJerseyNumber method.
   */
  @Test
  public void testGetJerseyNumber() {
    assertEquals(10, player1.getJerseyNumber());
    assertEquals(15, player2.getJerseyNumber());
    assertEquals(0, player3.getJerseyNumber());
    assertEquals(15, player4.getJerseyNumber());
  }

  /**
   * This test tests the getAssignedPosition and setAssignedPosition methods.
   */
  @Test
  public void testGetAssignedPosition() {
    player1.setAssignedPosition(Position.DEFENDER);
    player2.setAssignedPosition(Position.GOALIE);
    player3.setAssignedPosition(Position.MIDFIELDER);
    player4.setAssignedPosition(Position.FORWARD);

    assertEquals(Position.DEFENDER, player1.getAssignedPosition());
    assertEquals(Position.GOALIE, player2.getAssignedPosition());
    assertEquals(Position.MIDFIELDER, player3.getAssignedPosition());
    assertEquals(Position.FORWARD, player4.getAssignedPosition());
  }

  /**
   * This test tests the toString method.
   */
  @Test
  public void testToString() {
    player1.setAssignedPosition(Position.DEFENDER);
    player2.setAssignedPosition(Position.GOALIE);
    player3.setAssignedPosition(Position.MIDFIELDER);

    assertEquals("10 DEFENDER", player1.toString());
    assertEquals("15 GOALIE", player2.toString());
    assertEquals("00 MIDFIELDER", player3.toString());
    // When no positions are assigned.
    assertEquals("15 BENCHED", player4.toString());
  }

  /**
   * This test tests the equals method when the hashcode of two objects are equal.
   */
  @Test
  public void testHashCodeEqual() {
    player1.setAssignedPosition(Position.DEFENDER);

    PlayerIdentifier playerDuplicate = new PlayerIdentifier(10);
    playerDuplicate.setAssignedPosition(Position.DEFENDER);

    assertEquals(player1.hashCode(), player1.hashCode());
    assertEquals(player1.hashCode(), playerDuplicate.hashCode());
    assertEquals(playerDuplicate.hashCode(), player1.hashCode());
  }

  /**
   * This test tests the equals method when the hashcode of two objects are not equal.
   */
  @Test
  public void testHashCodeNotEqual() {
    player1.setAssignedPosition(Position.DEFENDER);
    player1.setAssignedPosition(Position.DEFENDER);
    player2.setAssignedPosition(Position.GOALIE);
    player3.setAssignedPosition(Position.MIDFIELDER);

    assertNotEquals(player1.hashCode(), player2.hashCode());
    assertNotEquals(player2.hashCode(), player3.hashCode());
    assertNotEquals(player3.hashCode(), player2.hashCode());
  }

  /**
   * This test tests the equals method when the two objects are equal.
   */
  @Test
  public void testEqualsTrue() {
    player1.setAssignedPosition(Position.DEFENDER);

    PlayerIdentifier playerDuplicate = new PlayerIdentifier(10);
    playerDuplicate.setAssignedPosition(Position.DEFENDER);

    assertEquals(player1, player1);
    assertEquals(player1, playerDuplicate);
    assertEquals(playerDuplicate, player1);
  }

  /**
   * This test tests the equals method when the two objects are not equal.
   */
  @Test
  public void testEqualsFalse() {
    player1.setAssignedPosition(Position.DEFENDER);
    player1.setAssignedPosition(Position.DEFENDER);
    player2.setAssignedPosition(Position.GOALIE);
    player3.setAssignedPosition(Position.MIDFIELDER);

    assertNotEquals(player1, player2);
    assertNotEquals(player2, player3);
    assertNotEquals(player3, player2);
  }

  /**
   * This test tests the equals method when an object is compared to null.
   */
  @Test
  public void testEqualsNull() {
    player1.setAssignedPosition(Position.DEFENDER);

    assertNotEquals(player1, null);
  }

  /**
   * This test tests the compareTo method. GOALIE < DEFENDER < MIDFIELDER < FORWARD < null.
   */
  @Test
  public void testCompareTo() {
    player1.setAssignedPosition(Position.DEFENDER);
    player2.setAssignedPosition(Position.GOALIE);
    player3.setAssignedPosition(Position.GOALIE);
    player4.setAssignedPosition(Position.FORWARD);

    assertTrue(player1.compareTo(player2) > 0);
    assertTrue(player2.compareTo(player1) < 0);
    assertTrue(player2.compareTo(player4) < 0);
    assertEquals(0, player1.compareTo(player1));
    assertEquals(0, player2.compareTo(player3));
    assertEquals(0, player3.compareTo(player2));

    // Compare PlayerIdentifier when positions are null.
    PlayerIdentifier playerNullPosition1 = new PlayerIdentifier(10);
    PlayerIdentifier playerNullPosition2 = new PlayerIdentifier(15);
    assertTrue(player1.compareTo(playerNullPosition1) < 0);
    assertTrue(playerNullPosition2.compareTo(player2) > 0);
    assertEquals(0, playerNullPosition1.compareTo(playerNullPosition2));
  }
}