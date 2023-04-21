package soccerteamproject;

/**
 * Indicates a duplicate player has been input.
 */
public class DuplicatePlayerException extends RuntimeException {

  /**
   * This is a constructor of a DuplicatePlayerException.
   *
   * @param message String, custom message for the exception.
   */
  public DuplicatePlayerException(String message) {
    super(message);
  }
}
