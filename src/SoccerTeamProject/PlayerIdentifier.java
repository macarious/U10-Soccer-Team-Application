package SoccerTeamProject;

import java.util.Objects;

/**
 * This class represents the identification of a soccer player who is assigned to the team. Each
 * team player is assigned a unique PlayerID object. It contains a jersey number and a
 * {@link Position} (if the player is in the starting lineup). These properties are assigned to each
 * team player when a team is created.
 */
public class PlayerIdentifier implements Comparable<PlayerIdentifier> {

  private final int jerseyNumber;
  private Position assignedPosition;

  /**
   * This is a constructor which takes a jersey number and an assigned position as the arguments.
   *
   * @param jerseyNumber int, assigned jersey number.
   * @throws IllegalArgumentException if jersey number is negative.
   */
  public PlayerIdentifier(int jerseyNumber) throws IllegalArgumentException{
    if (jerseyNumber < 0) {
      throw new IllegalArgumentException("Jersey number must be non-negative.");
    }
    this.jerseyNumber = jerseyNumber;
  }

  /**
   * This method gets the jersey number.
   *
   * @return int, jersey number.
   */
  public int getJerseyNumber() {
    return this.jerseyNumber;
  }

  /**
   * This method gets the assigned position.
   *
   * @return {@link Position}, assigned position.
   */
  public Position getAssignedPosition() {
    return this.assignedPosition;
  }

  /**
   * This method sets the assigned position.
   *
   * @param assignedPosition {@link Position}, assigned position to be set.
   */
  public void setAssignedPosition(Position assignedPosition) {
    this.assignedPosition = assignedPosition;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(String.format("%02d", this.jerseyNumber));
    str.append(" ");
    if (this.assignedPosition == null) {
      str.append("BENCHED");
    } else {
      str.append(this.assignedPosition);
    }
    return str.toString();
  }

  @Override
  public int compareTo(PlayerIdentifier other) {
    // Check the 3 cases where assignedPosition is null:
    if (this.assignedPosition == null && other.assignedPosition == null) {
      return 0;
    }
    if (this.assignedPosition == null) {
      return 1;
    }
    if (other.assignedPosition == null) {
      return -1;
    }

    // Compare PlayerIdentifier only based on the order of the Position.
    return this.assignedPosition.compareTo(other.assignedPosition);
  }
}
