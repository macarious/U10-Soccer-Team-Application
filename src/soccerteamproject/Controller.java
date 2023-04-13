package soccerteamproject;

/**
 * This class represents a controller (implementing {@link ControllerInterface}) for a soccer team
 * application. It communicates with the model: {@link SoccerTeamInterface} and the view:
 * {@link ApplicationInterface}. The model register players, creates teams, and provides team
 * information. The view receive inputs from users and displays the requested information.
 */
public class Controller implements ControllerInterface {

  private final SoccerTeamInterface model;
  private final ApplicationInterface gui;
  /**
   * This is a constructor. It creates an object representing a controller which communicates with
   * the specified model and view.
   *
   * @param model {@link SoccerTeamInterface} the model used by the controller.
   * @param view {@link ApplicationInterface} the view used by the controller.
   */
  public Controller(SoccerTeamInterface model, ApplicationInterface view) {
    this.model = model;
    this.gui = view;
  }

  @Override
  public void exitProgram() {

  }

  @Override
  public void addNewPlayer() {

  }

  @Override
  public void displayAllPlayer() {

  }

  @Override
  public void displayTeamPlayer() {

  }

  @Override
  public void displayStartingLineUp() {

  }
}
