package soccerteamproject;

/**
 * Indicates insufficient players in the team.
 */
public class InsufficientPlayerException extends RuntimeException {

  /**
   * This is a constructor of an InsufficientPlayerException.
   *
   * @param message String, custom message for the exception.
   */
  public InsufficientPlayerException(String message) {
    super(message);
  }
}
