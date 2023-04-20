package soccerteamproject;

import java.time.LocalDate;

/**
 * This class represents the input from the user. It contains getters and setters for the first
 * name, last name, preferred position, birthdate, and skill level.
 */
public class UserInput {
  private String firstName;
  private String lastName;
  private Position preferredPosition;
  private LocalDate birthDate;
  private int skillLevel;

  /**
   * This is constructor. By default, it has no fields until appropriate setter methods are used.
   */
  public UserInput() {
  }

  /**
   * This method sets the firstName field.
   *
   * @param firstName, String, first name of player.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * This method sets the lastName field.
   *
   * @param lastName String, last name of player.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * This method sets the birthDate field.
   *
   * @param birthDate, {@link LocalDate}, birthdate of player.
   */
  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  /**
   * This method sets the preferredPosition field.
   *
   * @param preferredPosition, {@link Position}, preferred position of player.
   */
  public void setPreferredPosition(Position preferredPosition) {
    this.preferredPosition = preferredPosition;
  }

  /**
   * This method sets the skillLevel field.
   *
   * @param skillLevel, int, skill level of player (1 to 5 inclusive).
   */
  public void setSkillLevel(int skillLevel) {
    this.skillLevel = skillLevel;
  }

  /**
   * This method gets the firstName field.
   *
   * @return String, first name of player.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * This method gets the lastName field.
   *
   * @return String, last name of player.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * This method gets the birthDate field.
   *
   * @return {@link LocalDate}, the birthdate of player.
   */
  public LocalDate getBirthDate() {
    return birthDate;
  }

  /**
   * This method gets the preferredPosition field.
   *
   * @return {@link Position}, preferred position of player.
   */
  public Position getPreferredPosition() {
    return preferredPosition;
  }

  /**
   * This method gets the skillLevel field.
   *
   * @return int, skill level of player (1 to 5 inclusive).
   */
  public int getSkillLevel() {
    return skillLevel;
  }
}
