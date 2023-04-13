package soccerteamproject;

/**
 * Indicates there is missing information when a player is being added.
 */
public class MissingInfoException extends RuntimeException {

  /**
   * This is a constructor of a MissingInfoException.
   *
   * @param message String, custom message for the exception.
   */
  public MissingInfoException(String message) {
    super(message);
  }

}
