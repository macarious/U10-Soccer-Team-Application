package soccerteamproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableModel;

/**
 * This is an implementation of {@link ApplicationInterface} which uses a graphical user interface.
 * It provides an application program where the user can interact with the text fields and drop-down
 * menus to input a new player information. It also has panes and tables which displays information
 * about all the players, tha players assigned to the team, and the starting line up.
 */
public class Application extends JFrame implements ApplicationInterface {

  private static final Integer[] skillLevels = { 1, 2, 3, 4, 5 };
  private static final String[] TABLE_HEADINGS_ALL =
      { "#", "Last Name", "First Name", "Position", "Age", "Skill" };
  private static final String[] TABLE_HEADINGS_TEAM =
      { "Last Name", "First Name", "Jersey", "Position", "Skill", };
  private static final String[] TABLE_HEADINGS_LINE_UP =
      { "Jersey", "Position", "Last Name", "First Name", "Skill", };

  private final JTextField textFieldFirstName;
  private final JTextField textFieldLastName;
  private final JFormattedTextField textFieldBirthDate;
  private final JComboBox<Position> comboBoxPosition;
  private final JComboBox<Integer> comboBoxSkillLevel;
  private final JButton buttonAddPlayer;
  private final JButton buttonCreateTeam;
  private final JButton buttonReset;
  private final JLabel messageToUser;
  private final DefaultTableModel modelAllPlayers;
  private final DefaultTableModel modelTeamPlayers;
  private final DefaultTableModel modelStartingLineUp;
  private UserInput userInput;
  private final Color colourBG1;
  private final Color colourBG2;
  private final Color colourBorder;
  private final Color colourFont;
  private final Color colourButtonBG;
  private final Color colourButtonFont;
  private final Color colourTabBG;
  private final Color colourTabBGSelected;
  private final Color colourTabFont;
  private final Font titleFont;

  public Application() {
    userInput = new UserInput(); // Empty user input.

    // Default colours (Dark Mode).
    colourBG1 = Color.BLACK;
    colourBG2 = Color.DARK_GRAY;
    colourBorder = Color.ORANGE;
    colourFont = Color.WHITE;
    colourButtonBG = Color.ORANGE;
    colourButtonFont = Color.DARK_GRAY;
    colourTabBG = Color.ORANGE;
    colourTabBGSelected = Color.GRAY;
    colourTabFont = Color.BLACK;
    titleFont = new Font("Arial", Font.BOLD, 16);

    // Set up main application frame.
    setTitle("Soccer Team Application");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    setLocation(200, 200);
    getContentPane().setBackground(colourBG1);

    // Create two panes side-by-side.
    JPanel paneLeftInput =
        new JPanel(); // User input players information and contains buttons which create teams.
    JTabbedPane paneRightOutput = new JTabbedPane(); // Container for pane2, pane3, and pane4.

    // Customize Left Pane.
    paneLeftInput.setPreferredSize(new Dimension(350, 480));
    paneLeftInput.setBackground(colourBG2);
    EmptyBorder emptyBorderPaneLeft = new EmptyBorder(10, 10, 10, 10);
    TitledBorder titledBorderpaneLeft = new TitledBorder("Player Registration");
    titledBorderpaneLeft.setTitleFont(titleFont);
    titledBorderpaneLeft.setTitleColor(colourFont);
    paneLeftInput.setBorder(BorderFactory.createCompoundBorder(titledBorderpaneLeft,
                                                               emptyBorderPaneLeft));
    paneLeftInput.setLayout(new GridLayout(9, 1, 10, 10));

    // Create sub-panels for Left Pane.
    JPanel panelFirstName = new JPanel();
    JPanel panelLastName = new JPanel();
    JPanel panelBirthDate = new JPanel();
    JPanel panelPosition = new JPanel();
    JPanel panelSkillLevel = new JPanel();

    EmptyBorder emptyBorderSubPanel = new EmptyBorder(5, 5, 5, 5);
    LineBorder lineBorderSubPanel = new LineBorder(colourBorder, 1);
    panelFirstName.setBorder(BorderFactory.createCompoundBorder(lineBorderSubPanel,
                                                                emptyBorderSubPanel));
    panelLastName.setBorder(BorderFactory.createCompoundBorder(lineBorderSubPanel,
                                                               emptyBorderSubPanel));
    panelBirthDate.setBorder(BorderFactory.createCompoundBorder(lineBorderSubPanel,
                                                                emptyBorderSubPanel));
    panelPosition.setBorder(BorderFactory.createCompoundBorder(lineBorderSubPanel,
                                                               emptyBorderSubPanel));
    panelSkillLevel.setBorder(BorderFactory.createCompoundBorder(lineBorderSubPanel,
                                                                 emptyBorderSubPanel));

    panelFirstName.setBackground(colourBG2);
    panelLastName.setBackground(colourBG2);
    panelBirthDate.setBackground(colourBG2);
    panelPosition.setBackground(colourBG2);
    panelSkillLevel.setBackground(colourBG2);

    panelFirstName.setLayout(new BorderLayout(5, 5));
    panelLastName.setLayout(new BorderLayout(5, 5));
    panelBirthDate.setLayout(new BorderLayout(5, 5));
    panelPosition.setLayout(new BorderLayout(5, 5));
    panelSkillLevel.setLayout(new BorderLayout(5, 5));

    // Create labels for each field.
    JLabel labelFirstName = new JLabel("First Name: ");
    JLabel labelLastName = new JLabel("Last Name: ");
    JLabel labelBirthDate = new JLabel("Birth Date (yyyy-mm-dd): ");
    JLabel labelPosition = new JLabel("Preferred Position: ");
    JLabel labelSkillLevel = new JLabel("Skill Level: ");

    labelFirstName.setForeground(colourFont);
    labelLastName.setForeground(colourFont);
    labelBirthDate.setForeground(colourFont);
    labelPosition.setForeground(colourFont);
    labelSkillLevel.setForeground(colourFont);

    // Create text fields for first and last names.
    textFieldFirstName = new JTextField(12);
    textFieldLastName = new JTextField(12);
    textFieldFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
    textFieldLastName.setHorizontalAlignment(SwingConstants.RIGHT);

    // Create formatted text field ranging from 80 years ago to current date.
    textFieldBirthDate = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
    textFieldBirthDate.setColumns(12);
    textFieldBirthDate.setHorizontalAlignment(SwingConstants.RIGHT);

    // Create a combo box for preferred position.
    Position[] positions = Position.values();
    comboBoxPosition = new JComboBox<>(positions);

    // Create combo box for skill levels (1 to 5).
    comboBoxSkillLevel = new JComboBox<>(skillLevels);

    // Create "Add Player" and "Create Team" buttons.
    buttonAddPlayer = new JButton("Add Player");
    buttonAddPlayer.setBackground(colourButtonBG);
    buttonAddPlayer.setForeground(colourButtonFont);
    buttonAddPlayer.setBorder(BorderFactory.createRaisedBevelBorder());
    buttonCreateTeam = new JButton("Create Team");
    buttonCreateTeam.setBackground(colourButtonBG);
    buttonCreateTeam.setForeground(colourButtonFont);
    buttonCreateTeam.setBorder(BorderFactory.createRaisedBevelBorder());
    buttonReset = new JButton("Reset");
    buttonReset.setBackground(colourButtonBG);
    buttonReset.setForeground(colourButtonFont);
    buttonReset.setBorder(BorderFactory.createRaisedBevelBorder());

    buttonAddPlayer.setActionCommand("Add Player");
    buttonCreateTeam.setActionCommand("Create Team");
    buttonReset.setActionCommand("Reset");

    // Create a label to display messages to user.
    messageToUser = new JLabel("Please add a player.");
    messageToUser.setForeground(colourFont);

    // Populate Left Panel.
    resetAllFields();
    panelFirstName.add(labelFirstName, BorderLayout.WEST);
    panelFirstName.add(textFieldFirstName, BorderLayout.EAST);
    panelLastName.add(labelLastName, BorderLayout.WEST);
    panelLastName.add(textFieldLastName, BorderLayout.EAST);
    panelBirthDate.add(labelBirthDate, BorderLayout.WEST);
    panelBirthDate.add(textFieldBirthDate, BorderLayout.EAST);
    panelSkillLevel.add(labelSkillLevel, BorderLayout.WEST);
    panelSkillLevel.add(comboBoxSkillLevel, BorderLayout.EAST);
    panelPosition.add(labelPosition, BorderLayout.WEST);
    panelPosition.add(comboBoxPosition, BorderLayout.EAST);
    paneLeftInput.add(panelFirstName);
    paneLeftInput.add(panelLastName);
    paneLeftInput.add(panelBirthDate);
    paneLeftInput.add(panelPosition);
    paneLeftInput.add(panelSkillLevel);
    paneLeftInput.add(buttonAddPlayer);
    paneLeftInput.add(buttonCreateTeam);
    paneLeftInput.add(buttonReset);
    paneLeftInput.add(messageToUser);

    // Customize Right Panel (Tab #1, 2, 3).
    paneRightOutput.setPreferredSize(new Dimension(500, 480));
    JPanel tab1RegisteredPlayers = new JPanel(); // Display all registered players.
    JPanel tab2TeamPlayers =
        new JPanel(); // Display the list of team players after team has been created.
    JPanel tab3StartingLineUp =
        new JPanel(); // Display the starting line up after team has been created.
    paneRightOutput.addTab("Registered Players", tab1RegisteredPlayers);
    paneRightOutput.addTab("Team Players", tab2TeamPlayers);
    paneRightOutput.addTab("Starting Line Up", tab3StartingLineUp);
    paneRightOutput.setBackgroundAt(0, colourTabBG);
    paneRightOutput.setForegroundAt(0, colourTabFont);
    paneRightOutput.setBackgroundAt(1, colourTabBG);
    paneRightOutput.setForegroundAt(1, colourTabFont);
    paneRightOutput.setBackgroundAt(2, colourTabBG);
    paneRightOutput.setForegroundAt(2, colourTabFont);

    // Customize Tab 1.
    tab1RegisteredPlayers.setBackground(colourTabBGSelected);
    EmptyBorder emptyBorderPane1 = new EmptyBorder(10, 10, 10, 10);
    LineBorder lineBorderPane1 = new LineBorder(colourBorder, 2);
    tab1RegisteredPlayers.setBorder(BorderFactory.createCompoundBorder(lineBorderPane1,
                                                                       emptyBorderPane1));
    modelAllPlayers = new DefaultTableModel(TABLE_HEADINGS_ALL, 0);
    JTable tableAllPlayers = new JTable(modelAllPlayers);
    tableAllPlayers.setAutoCreateRowSorter(true);
    tableAllPlayers.getColumnModel().getColumn(0).setPreferredWidth(15); // Index
    tableAllPlayers.getColumnModel().getColumn(1).setPreferredWidth(100); // Last Name
    tableAllPlayers.getColumnModel().getColumn(2).setPreferredWidth(100); // First Name
    tableAllPlayers.getColumnModel().getColumn(3).setPreferredWidth(95); // Position
    tableAllPlayers.getColumnModel().getColumn(4).setPreferredWidth(20); // Age
    tableAllPlayers.getColumnModel().getColumn(5).setPreferredWidth(20); // Skill Level
    JScrollPane scrollPane1 = new JScrollPane(tableAllPlayers);
    tab1RegisteredPlayers.add(scrollPane1);

    // Customize Tab 2.
    tab2TeamPlayers.setBackground(colourTabBGSelected);
    EmptyBorder emptyBorderPane2 = new EmptyBorder(10, 10, 10, 10);
    LineBorder lineBorderPane2 = new LineBorder(colourBorder, 2);
    tab2TeamPlayers.setBorder(BorderFactory.createCompoundBorder(lineBorderPane2,
                                                                       emptyBorderPane2));
    modelTeamPlayers = new DefaultTableModel(TABLE_HEADINGS_TEAM, 0);
    JTable tableTeamPlayers = new JTable(modelTeamPlayers);
    tableTeamPlayers.setAutoCreateRowSorter(true);
    tableTeamPlayers.getColumnModel().getColumn(0).setPreferredWidth(100); // Last Name
    tableTeamPlayers.getColumnModel().getColumn(1).setPreferredWidth(100); // First Name
    tableTeamPlayers.getColumnModel().getColumn(2).setPreferredWidth(35); // Jersey
    tableTeamPlayers.getColumnModel().getColumn(3).setPreferredWidth(95); // Position
    tableTeamPlayers.getColumnModel().getColumn(4).setPreferredWidth(20); // Skill Level
    JScrollPane scrollPane2 = new JScrollPane(tableTeamPlayers);
    tab2TeamPlayers.add(scrollPane2);

    // Customize Tab 3.
    tab3StartingLineUp.setBackground(colourTabBGSelected);
    EmptyBorder emptyBorderPane3 = new EmptyBorder(10, 10, 10, 10);
    LineBorder lineBorderPane3 = new LineBorder(colourBorder, 2);
    tab3StartingLineUp.setBorder(BorderFactory.createCompoundBorder(lineBorderPane3,
                                                                       emptyBorderPane3));
    modelStartingLineUp = new DefaultTableModel(TABLE_HEADINGS_LINE_UP, 0);
    JTable tableStartingLineUp = new JTable(modelStartingLineUp);
    tableStartingLineUp.setAutoCreateRowSorter(true);
    tableStartingLineUp.getColumnModel().getColumn(0).setPreferredWidth(35); // Jersey
    tableStartingLineUp.getColumnModel().getColumn(1).setPreferredWidth(95); // Position
    tableStartingLineUp.getColumnModel().getColumn(2).setPreferredWidth(100); // Last Name
    tableStartingLineUp.getColumnModel().getColumn(3).setPreferredWidth(100); // First Name
    tableStartingLineUp.getColumnModel().getColumn(4).setPreferredWidth(10); // Skill Level
    JScrollPane scrollPane3 = new JScrollPane(tableStartingLineUp);
    tab3StartingLineUp.add(scrollPane3);

    // Change colour of tab when selected by creating a custom UI for the JTabbedPane
    TabbedPaneUI customUI = new BasicTabbedPaneUI() {
      @Override
      protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y,
          int width, int height, boolean isSelected) {
        // Call the parent method to draw the default background
        super.paintTabBackground(g, tabPlacement, tabIndex, x, y, width, height, isSelected);

        // Change the color of the selected tab when it is selected
        if (isSelected) {
          g.setColor(colourTabBGSelected);
          g.fillRect(x, y, width, height);
        }
      }
    };
    paneRightOutput.setUI(customUI);

    // Pack the whole application.
    add(paneLeftInput);
    add(paneRightOutput);
    pack();
    setVisible(true);
  }

  @Override
  public void addPlayer() {
    UserInput newUserInput = new UserInput();
    newUserInput.setFirstName(textFieldFirstName.getText());
    newUserInput.setLastName(textFieldLastName.getText());
    newUserInput.setPreferredPosition((Position) comboBoxPosition.getSelectedItem());
    newUserInput.setBirthDate(LocalDate.parse(textFieldBirthDate.getText()));
    newUserInput.setSkillLevel((int) comboBoxSkillLevel.getSelectedItem());
    this.userInput = newUserInput;
    displayMessage("Player successfully added", colourFont);
    resetAllFields();
  }

  @Override
  public UserInput getUserInput() {
    return this.userInput;
  }

  @Override
  public void displayAllPlayer(Set<Player> allPlayerList) {
    // Remove all rows in the table.
    modelAllPlayers.setRowCount(0);

    // Add each player in the list as a new row in the table.
    int count = 1;
    for (Player player : allPlayerList) {
      Object[] rowData = { count,
                           player.getLastName(),
                           player.getFirstName(),
                           player.getPreferredPosition(),
                           player.getAge(),
                           player.getSkillLevel() };
      modelAllPlayers.addRow(rowData);
      count++;
    }
  }

  @Override
  public void displayTeamPlayer(Map<PlayerIdentifier, Player> teamPlayerList) {
    // Remove all rows in the table.
    modelTeamPlayers.setRowCount(0);

    // Add each team player in the list as a new row in the table.
    for (Map.Entry<PlayerIdentifier, Player> entry : teamPlayerList.entrySet()) {
      Object[] rowData =
          { entry.getValue().getLastName(),
            entry.getValue().getFirstName(),
            String.format("%02d",
                          entry
                              .getKey()
                              .getJerseyNumber()),
            entry.getKey().getAssignedPosition(),
            entry.getValue().getSkillLevel() };
      modelTeamPlayers.addRow(rowData);
    }
  }

  @Override
  public void displayStartingLineUp(Map<PlayerIdentifier, Player> startingLineUp) {
    // Remove all rows in the table.
    modelStartingLineUp.setRowCount(0);

    // Add each team player in the list as a new row in the table.
    for (Map.Entry<PlayerIdentifier, Player> entry : startingLineUp.entrySet()) {
      Object[] rowData = { String.format("%02d", entry.getKey().getJerseyNumber()),
                           entry.getKey().getAssignedPosition(),
                           entry.getValue().getLastName(),
                           entry.getValue().getFirstName(),
                           entry.getValue().getSkillLevel() };
      modelStartingLineUp.addRow(rowData);
    }
  }

  @Override
  public void addFeatures(ControllerInterface features) {
    buttonAddPlayer.addActionListener(event -> {
      try {
        features.addNewPlayer();
      } catch (MissingInfoException | IllegalArgumentException e) {
        displayMessage(e.getMessage(), Color.RED);
        return;
      } catch (DateTimeParseException e) {
        displayMessage("Date must be in the format yyyy-mm-dd", Color.RED);
        return;
      }
      features.displayAllPlayer();
    });
    buttonCreateTeam.addActionListener(event -> {
      try {
        features.createTeam();
      } catch (InsufficientPlayerException e) {
        displayMessage(e.getMessage(), Color.RED);
        return;
      }
      features.displayTeamPlayer();
      features.displayStartingLineUp();
    });
    buttonReset.addActionListener(event -> features.reset());
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void displayMessage(String message, Color fontColour) {
    messageToUser.setText(message);
    messageToUser.setForeground(fontColour);
  }

  @Override
  public boolean isNameInputComplete() {
    if (textFieldFirstName.getText().isEmpty() || textFieldLastName.getText().isEmpty()) {
      return false;
    }
    return true;
  }

  @Override
  public void resetAllFields() {
    textFieldFirstName.setText("");
    textFieldLastName.setText("");
    textFieldBirthDate.setValue(new Date());
    comboBoxPosition.setSelectedIndex(0);
    comboBoxSkillLevel.setSelectedIndex(skillLevels.length - 1);
  }
}
