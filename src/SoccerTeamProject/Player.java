package SoccerTeamProject;

import java.time.LocalDate;
import java.time.Period;

/**
 * This class represents a soccer player on a soccer team. Each player has a first name, last name,
 * birthdate (which is used to calculate age), preferred {@link Position}, skill level, assigned
 * {@link Position} if the player is on the starting line up, jersey number, and an indicator to
 * show if the player is benched or on the starting line up.
 */
public class Player {

  private static final int MAX_AGE = 10;
  private final String firstName;
  private final String lastName;
  private final LocalDate birthdate;
  private final Position preferredPosition;
  private final int skillLevel;

  /**
   * This is a constructor of a Player class.
   *
   * @param firstName          String, first name of player.
   * @param lastName           String, last name of player.
   * @param birthdate          LocalDate, birthdate of player.
   * @param preferredPosition, Position, preferred position of player.
   * @param skillLevel         int, skill level of player (1 to 5).
   * @throws IllegalArgumentException if player's age is less than 0 or greater than 10.
   * @throws IllegalArgumentException if skill leve is not between 1 and 5 (inclusive).
   */
  public Player(String firstName, String lastName, LocalDate birthdate, Position preferredPosition,
      int skillLevel) throws IllegalArgumentException {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthdate = birthdate;
    // Throw exception if age is less than 10.
    int age = this.getAge();
    if (age > MAX_AGE || age < 0) {
      throw new IllegalArgumentException("Player must be between 0 and 10 years old (inclusive).");
    }
    this.preferredPosition = preferredPosition;
    // Throw exception if skill level is not between 1 and 5.
    if (skillLevel > 5 || skillLevel < 1) {
      throw new IllegalArgumentException("Skill level must be between 1 and 5 (inclusive).");
    }
    this.skillLevel = skillLevel;
  }

  /**
   * This method gets the first name of the player.
   *
   * @return String, first name of player.
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * This method gets the last name of the player.
   *
   * @return String, last name of player.
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * This method gets the age of the player.
   *
   * @return int, age of player.
   */
  public int getAge() {
    return Period.between(this.birthdate, LocalDate.now()).getYears();
  }

  /**
   * This method gets the preferred position of the player.
   *
   * @return {@link Position}, preferred position of the player.
   */
  public Position getPreferredPosition() {
    return this.preferredPosition;
  }

  /**
   * This method gets the skill level of the player.
   *
   * @return int, skill level of the player.
   */
  public int getSkillLevel() {
    return this.skillLevel;
  }

  /**
   * This method builds a string which contains the last and first name of the player.
   *
   * @return String, last and first names of player.
   */
  public String nameToString() {
    return getLastName() + ", " + getFirstName();
  }

  @Override
  public String toString() {
    return getLastName() + ", " + getFirstName() + "; AGE: " + this.getAge() + "; SKILL: "
        + this.skillLevel;
  }
}
