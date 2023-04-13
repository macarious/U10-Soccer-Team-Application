package soccerteamproject;

import java.time.LocalDate;
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

    // Register players.
    soccerTeam.registerPlayer("Lionel", "Messi", LocalDate.of(2017, 6, 24), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Cristiano", "Ronaldo", LocalDate.of(2015, 2, 5), Position.FORWARD,
        1);
    soccerTeam.registerPlayer("Neymar", "Jr.", LocalDate.of(2018, 2, 5), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Kylian", "Mbappé", LocalDate.of(2018, 12, 20), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Mohamed", "Salah", LocalDate.of(2018, 6, 15), Position.FORWARD, 4);
    soccerTeam.registerPlayer("Sergio", "Agüero", LocalDate.of(2018, 6, 2), Position.FORWARD, 1);
    soccerTeam.registerPlayer("Harry", "Kane", LocalDate.of(2018, 7, 28), Position.FORWARD, 5);
    soccerTeam.registerPlayer("Robert", "Lewandowski", LocalDate.of(2018, 8, 21), Position.FORWARD,
        3);
    soccerTeam.registerPlayer("Kevin", "De Bruyne", LocalDate.of(2018, 6, 28), Position.MIDFIELDER,
        3);
    soccerTeam.registerPlayer("Luis", "Suárez", LocalDate.of(2017, 1, 24), Position.FORWARD, 1);
    soccerTeam.registerPlayer("Manuel", "Neuer", LocalDate.of(2016, 3, 27), Position.GOALIE, 2);
    soccerTeam.registerPlayer("Luka", "Modrić", LocalDate.of(2015, 9, 9), Position.MIDFIELDER, 4);
    soccerTeam.registerPlayer("Antoine", "Griezmann", LocalDate.of(2018, 3, 21),
        Position.MIDFIELDER, 2);
    soccerTeam.registerPlayer("Eden", "Hazard", LocalDate.of(2018, 1, 7), Position.MIDFIELDER, 5);
    soccerTeam.registerPlayer("Raheem", "Sterling", LocalDate.of(2018, 12, 8), Position.FORWARD, 3);
    soccerTeam.registerPlayer("Virgil", "van Dijk", LocalDate.of(2018, 7, 8), Position.DEFENDER, 2);
    soccerTeam.registerPlayer("Paulo", "Dybala", LocalDate.of(2018, 11, 15), Position.FORWARD, 4);
    soccerTeam.registerPlayer("Gareth", "Bale", LocalDate.of(2018, 7, 16), Position.MIDFIELDER, 2);
    soccerTeam.registerPlayer("Sergio", "Ramos", LocalDate.of(2016, 3, 30), Position.DEFENDER, 5);
    soccerTeam.registerPlayer("Thiago", "Alcântara", LocalDate.of(2018, 4, 11), Position.MIDFIELDER,
        5);
    soccerTeam.registerPlayer("Sadio", "Mane", LocalDate.of(2018, 4, 10), Position.FORWARD, 4);
    soccerTeam.registerPlayer("Jan", "Oblak", LocalDate.of(2018, 1, 7), Position.GOALIE, 1);
    soccerTeam.registerPlayer("Thomas", "Müller", LocalDate.of(2018, 9, 13), Position.MIDFIELDER,
        3);
    soccerTeam.registerPlayer("Marco", "Reus", LocalDate.of(2018, 5, 31), Position.MIDFIELDER, 1);
    soccerTeam.registerPlayer("Karim", "Benzema", LocalDate.of(2017, 12, 19), Position.FORWARD, 1);

    // Create a team from the registered players.
    // Assign jersey numbers and positions.
    soccerTeam.createTeam();

    // Print all registered players.
    System.out.print("All Registered Players:\n");
    System.out.print("-----------------------\n");
    System.out.print(soccerTeam);
    System.out.print("\n\n");

    System.out.print("All Players on Team:\n");
    System.out.print("--------------------\n");
    System.out.print(soccerTeam.allTeamPlayerListToString());
    System.out.print("\n\n");

    System.out.print("Starting Line Up:\n");
    System.out.print("-----------------\n");
    System.out.print(soccerTeam.startingLineUpToString());
    System.out.print("\n\n");

    // Application with gui.
    ApplicationInterface gui = new Application();
    ControllerInterface controller = new Controller(soccerTeam, gui);
  }
}
