package soccerteamproject;

import java.util.Random;

/**
 * This is the Driver class. It is used to run the application.
 */
public class Driver {

  /**
   * This is the main method which acts as the Driver.
   *
   * @param args String[], arguments for main.
   */
  public static void main(String[] args) {
    // Create a soccer team (empty).
    Random randomGenerator = new Random();
    SoccerTeam soccerTeam = new SoccerTeam(randomGenerator);

    // Application with gui.
    ApplicationInterface gui = new Application();
    new Controller(soccerTeam, gui);
  }
}
