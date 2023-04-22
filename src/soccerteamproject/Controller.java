package soccerteamproject;

import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a controller (implementing {@link ControllerInterface}) for a soccer team
 * application. It communicates with the model: {@link SoccerTeamInterface} and the view:
 * {@link ApplicationInterface}. The model register players, creates teams, and provides team
 * information. The view receive inputs from users and displays the requested information.
 */
public class Controller implements ControllerInterface {

  private final SoccerTeamInterface model;
  private final ApplicationInterface view;

  /**
   * This is a constructor. It creates an object representing a controller which communicates with
   * the specified model and view.
   *
   * @param model {@link SoccerTeamInterface} the model used by the controller.
   * @param view  {@link ApplicationInterface} the view used by the controller.
   */
  public Controller(SoccerTeamInterface model, ApplicationInterface view) {
    this.model = model;
    this.view = view;
    view.addFeatures(this);
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void addNewPlayer()
      throws MissingInfoException, DateTimeParseException, IllegalArgumentException,
             DuplicatePlayerException {
    if (!view.isNameInputComplete()) {
      throw new MissingInfoException("The name fields must be completed to register a player.");
    }

    // May throw DateTimeParseException.
    view.addPlayer();
    UserInput userInput = view.getUserInput();

    // May throw IllegalArgumentException or DuplicatePlayerException.
    model.registerPlayer(userInput.getFirstName(),
                         userInput.getLastName(),
                         userInput.getBirthDate(),
                         userInput.getPreferredPosition(),
                         userInput.getSkillLevel());
    view.resetAllFields();
  }

  @Override
  public void displayAllPlayer() {
    Set<Player> allPlayers = model.getAllPlayerList();
    view.displayAllPlayer(allPlayers);
  }

  @Override
  public void createTeam() {
    model.createTeam();
  }

  @Override
  public void displayTeamPlayer() {
    Map<PlayerIdentifier, Player> teamPlayers = model.getTeamPlayerList();
    view.displayTeamPlayer(teamPlayers);
  }

  @Override
  public void displayStartingLineUp() {
    Map<PlayerIdentifier, Player> startingLineUp = model.getStartingLineUp();
    view.displayStartingLineUp(startingLineUp);
  }

  @Override
  public void addSamplePlayers() {
    resetAll();
    model.registerSampleList();
    displayAllPlayer();
  }

  @Override
  public void resetAll() {
    model.resetSoccerTeam();
    view.resetAllFields();
    view.resetAllTables();
  }
}
