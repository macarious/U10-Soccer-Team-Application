package soccerteamproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
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
import javax.swing.UIManager;
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
  private static final String[] TABLE_HEADINGS_ALL = { "#", "Last Name", "First Name",
      "Preferred Position", "Age", "Skill" };
  private static final String[] TABLE_HEADINGS_TEAM = { "Jersey", "Last Name", "First Name",
      "Assigned Position", "Age", "Skill", };
  private static final String[] TABLE_HEADINGS_LINE_UP = { "Jersey", "Last Name", "First Name",
      "Assigned Position", "Age", "Skill", };

  private final JTabbedPane paneRightOutput;
  private final JTextField textFieldFirstName;
  private final JTextField textFieldLastName;
  private final JFormattedTextField textFieldBirthDate;
  private final JComboBox<Position> comboBoxPosition;
  private final JComboBox<Integer> comboBoxSkillLevel;
  private final JButton buttonAddPlayer;
  private final JButton buttonCreateTeam;
  private final JButton buttonUseSample;
  private final JButton buttonReset;
  private final JLabel messageToUser;
  private final JTable tableAllPlayers;
  private final JTable tableTeamPlayers;
  private final JTable tableStartingLineUp;
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
  private final Font buttonFont;

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
    titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
    buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 16);

    // Set up main application frame.
    setTitle("Soccer Team Application");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    setLocation(200, 200);
    getContentPane().setBackground(colourBG1);
    setResizable(false);

    // Create two panes side-by-side.
    JPanel paneLeftInput = new JPanel(); // User input players information and contains buttons which create teams.
    paneRightOutput = new JTabbedPane(); // Container for pane2, pane3, and pane4.

    // Customize Left Pane.
    paneLeftInput.setPreferredSize(new Dimension(380, 480));
    paneLeftInput.setBackground(colourBG2);
    EmptyBorder emptyBorderPaneLeft = new EmptyBorder(10, 10, 10, 10);
    TitledBorder titledBorderpaneLeft = new TitledBorder("Player Registration");
    titledBorderpaneLeft.setTitleFont(titleFont);
    titledBorderpaneLeft.setTitleColor(colourFont);
    paneLeftInput.setBorder(
        BorderFactory.createCompoundBorder(titledBorderpaneLeft, emptyBorderPaneLeft));
    paneLeftInput.setLayout(new GridLayout(10, 1, 10, 10));

    // Create sub-panels for Left Pane.
    JPanel panelFirstName = new JPanel();
    JPanel panelLastName = new JPanel();
    JPanel panelBirthDate = new JPanel();
    JPanel panelPosition = new JPanel();
    JPanel panelSkillLevel = new JPanel();

    EmptyBorder emptyBorderSubPanel = new EmptyBorder(5, 5, 5, 5);
    LineBorder lineBorderSubPanel = new LineBorder(colourBorder, 1);
    panelFirstName.setBorder(
        BorderFactory.createCompoundBorder(lineBorderSubPanel, emptyBorderSubPanel));
    panelLastName.setBorder(
        BorderFactory.createCompoundBorder(lineBorderSubPanel, emptyBorderSubPanel));
    panelBirthDate.setBorder(
        BorderFactory.createCompoundBorder(lineBorderSubPanel, emptyBorderSubPanel));
    panelPosition.setBorder(
        BorderFactory.createCompoundBorder(lineBorderSubPanel, emptyBorderSubPanel));
    panelSkillLevel.setBorder(
        BorderFactory.createCompoundBorder(lineBorderSubPanel, emptyBorderSubPanel));

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

    // Create buttons.
    buttonAddPlayer = new JButton("Add Player");
    buttonAddPlayer.setBackground(colourButtonBG);
    buttonAddPlayer.setForeground(colourButtonFont);
    buttonAddPlayer.setBorder(BorderFactory.createRaisedBevelBorder());
    buttonAddPlayer.setFont(buttonFont);
    buttonCreateTeam = new JButton("Create Team");
    buttonCreateTeam.setBackground(colourButtonBG);
    buttonCreateTeam.setForeground(colourButtonFont);
    buttonCreateTeam.setBorder(BorderFactory.createRaisedBevelBorder());
    buttonCreateTeam.setFont(buttonFont);
    buttonUseSample = new JButton("Register List of Sample Players");
    buttonUseSample.setBackground(colourButtonBG);
    buttonUseSample.setForeground(colourButtonFont);
    buttonUseSample.setBorder(BorderFactory.createRaisedBevelBorder());
    buttonUseSample.setFont(buttonFont);
    buttonReset = new JButton("Reset All");
    buttonReset.setBackground(colourButtonBG);
    buttonReset.setForeground(colourButtonFont);
    buttonReset.setBorder(BorderFactory.createRaisedBevelBorder());
    buttonReset.setFont(buttonFont);

    buttonAddPlayer.setActionCommand("Add Player");
    buttonCreateTeam.setActionCommand("Create Team");
    buttonUseSample.setActionCommand("Register List of Sample Players");
    buttonReset.setActionCommand("Reset All");

    // Create a label to display messages to user.
    messageToUser = new JLabel("");
    displayMessage("Please add a player.", colourFont);

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
    paneLeftInput.add(messageToUser);
    paneLeftInput.add(buttonAddPlayer);
    paneLeftInput.add(buttonCreateTeam);
    paneLeftInput.add(buttonUseSample);
    paneLeftInput.add(buttonReset);

    // Customize Right Panel (Tab #1, 2, 3).
    // Remove the white line under a tab button
    Insets insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
    insets.top = 0;
    insets.bottom = 1;
    insets.left = 1;
    insets.right = 1;
    UIManager.put("TabbedPane.contentBorderInsets", insets);

    paneRightOutput.setPreferredSize(new Dimension(600, 480));
    JPanel tab1RegisteredPlayers = new JPanel(); // Display all registered players.
    JPanel tab2TeamPlayers = new JPanel(); // Display the list of team players after team has been created.
    JPanel tab3StartingLineUp = new JPanel(); // Display the starting line up after team has been created.
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
    LineBorder lineBorderPane1 = new LineBorder(colourBorder, 0);
    tab1RegisteredPlayers.setBorder(
        BorderFactory.createCompoundBorder(lineBorderPane1, emptyBorderPane1));
    modelAllPlayers = new DefaultTableModel(TABLE_HEADINGS_ALL, 0);
    tableAllPlayers = new JTable(modelAllPlayers);
    tableAllPlayers.setAutoCreateRowSorter(true);
    tableAllPlayers.getColumnModel().getColumn(0).setPreferredWidth(20); // Index
    tableAllPlayers.getColumnModel().getColumn(1).setPreferredWidth(100); // Last Name
    tableAllPlayers.getColumnModel().getColumn(2).setPreferredWidth(100); // First Name
    tableAllPlayers.getColumnModel().getColumn(3).setPreferredWidth(100); // Position
    tableAllPlayers.getColumnModel().getColumn(4).setPreferredWidth(50); // Age
    tableAllPlayers.getColumnModel().getColumn(5).setPreferredWidth(50); // Skill Level
    JScrollPane scrollPane1 = new JScrollPane(tableAllPlayers);
    scrollPane1.setPreferredSize(new Dimension(550, 425));
    tab1RegisteredPlayers.add(scrollPane1);

    // Customize Tab 2.
    tab2TeamPlayers.setBackground(colourTabBGSelected);
    EmptyBorder emptyBorderPane2 = new EmptyBorder(10, 10, 10, 10);
    LineBorder lineBorderPane2 = new LineBorder(colourBorder, 0);
    tab2TeamPlayers.setBorder(
        BorderFactory.createCompoundBorder(lineBorderPane2, emptyBorderPane2));
    modelTeamPlayers = new DefaultTableModel(TABLE_HEADINGS_TEAM, 0);
    tableTeamPlayers = new JTable(modelTeamPlayers);
    tableTeamPlayers.setAutoCreateRowSorter(true);
    tableTeamPlayers.getColumnModel().getColumn(0).setPreferredWidth(20); // Jersey
    tableTeamPlayers.getColumnModel().getColumn(1).setPreferredWidth(100); // Last Name
    tableTeamPlayers.getColumnModel().getColumn(2).setPreferredWidth(100); // First Name
    tableTeamPlayers.getColumnModel().getColumn(3).setPreferredWidth(100); // Position
    tableTeamPlayers.getColumnModel().getColumn(4).setPreferredWidth(50); // Age
    tableTeamPlayers.getColumnModel().getColumn(5).setPreferredWidth(50); // Skill Level
    JScrollPane scrollPane2 = new JScrollPane(tableTeamPlayers);
    scrollPane2.setPreferredSize(new Dimension(550, 425));
    tab2TeamPlayers.add(scrollPane2);

    // Customize Tab 3.
    tab3StartingLineUp.setBackground(colourTabBGSelected);
    EmptyBorder emptyBorderPane3 = new EmptyBorder(10, 10, 10, 10);
    LineBorder lineBorderPane3 = new LineBorder(colourBorder, 0);
    tab3StartingLineUp.setBorder(
        BorderFactory.createCompoundBorder(lineBorderPane3, emptyBorderPane3));
    modelStartingLineUp = new DefaultTableModel(TABLE_HEADINGS_LINE_UP, 0);
    tableStartingLineUp = new JTable(modelStartingLineUp);
    tableStartingLineUp.setAutoCreateRowSorter(true);
    tableStartingLineUp.getColumnModel().getColumn(0).setPreferredWidth(20); // Jersey
    tableStartingLineUp.getColumnModel().getColumn(1).setPreferredWidth(100); // Last Name
    tableStartingLineUp.getColumnModel().getColumn(2).setPreferredWidth(100); // First Name
    tableStartingLineUp.getColumnModel().getColumn(3).setPreferredWidth(100); // Position
    tableStartingLineUp.getColumnModel().getColumn(4).setPreferredWidth(50); // Age
    tableStartingLineUp.getColumnModel().getColumn(5).setPreferredWidth(50); // Skill Level
    JScrollPane scrollPane3 = new JScrollPane(tableStartingLineUp);
    scrollPane3.setPreferredSize(new Dimension(550, 425));
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
    int skillLevel = 0;
    if (Objects.nonNull(comboBoxSkillLevel.getSelectedItem())) {
      skillLevel = (int) comboBoxSkillLevel.getSelectedItem();
    }
    newUserInput.setSkillLevel(skillLevel);
    this.userInput = newUserInput;
    displayMessage("Player successfully added.", colourFont);
  }

  @Override
  public UserInput getUserInput() {
    return this.userInput;
  }

  @Override
  public void displayAllPlayer(Set<Player> allPlayerList) {
    // Remove all rows in the table.
    modelAllPlayers.setRowCount(0);

    Set<Player> sortedList = new TreeSet<>(allPlayerList);

    // Add each player in the list as a new row in the table.
    int rowIndex = 1;
    for (Player player : sortedList) {
      Object[] rowData = { String.format("%02d", rowIndex), player.getLastName(),
          player.getFirstName(), player.getPreferredPosition(), player.getAge(),
          player.getSkillLevel() };
      modelAllPlayers.addRow(rowData);
      rowIndex++;
    }
  }

  @Override
  public void displayTeamPlayer(Map<PlayerIdentifier, Player> teamPlayerList) {
    displayMessage("Team has been created.", colourFont);

    // Remove all rows in the table.
    modelTeamPlayers.setRowCount(0);

    // Add each team player in the list as a new row in the table.
    for (Map.Entry<PlayerIdentifier, Player> entry : teamPlayerList.entrySet()) {
      Object[] rowData = { String.format("%02d", entry.getKey().getJerseyNumber()),
          entry.getValue().getLastName(), entry.getValue().getFirstName(),
          entry.getKey().getAssignedPosition(), entry.getValue().getAge(),
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
          entry.getValue().getLastName(), entry.getValue().getFirstName(),
          entry.getKey().getAssignedPosition(), entry.getValue().getAge(),
          entry.getValue().getSkillLevel() };
      modelStartingLineUp.addRow(rowData);
    }
  }

  @Override
  public void addFeatures(ControllerInterface features) {
    // Action for "Add Player" button.
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
      paneRightOutput.setSelectedIndex(0);
    });

    // Action for "Create Team" button.
    buttonCreateTeam.addActionListener(event -> {
      try {
        features.createTeam();
      } catch (InsufficientPlayerException e) {
        displayMessage(e.getMessage(), Color.RED);
        return;
      }
      features.displayTeamPlayer();
      features.displayStartingLineUp();
      paneRightOutput.setSelectedIndex(1);
    });

    // Action for "Use Sample List" button.
    buttonUseSample.addActionListener(event -> {
      features.addSamplePlayers();
      paneRightOutput.setSelectedIndex(0);
    });

    // Action for "Reset ALl" button.
    buttonReset.addActionListener(event -> {
      features.resetAll();
      paneRightOutput.setSelectedIndex(1);
    });
  }

  @Override
  public boolean isNameInputComplete() {
    return !(textFieldFirstName.getText().isEmpty() || textFieldLastName.getText().isEmpty());
  }

  @Override
  public void resetAllFields() {
    textFieldFirstName.setText("");
    textFieldLastName.setText("");
    textFieldBirthDate.setValue(new Date());
    comboBoxPosition.setSelectedIndex(0);
    comboBoxSkillLevel.setSelectedIndex(skillLevels.length - 1);
  }

  @Override
  public void resetAllTables() {
    modelAllPlayers.setRowCount(0);
    modelTeamPlayers.setRowCount(0);
    modelStartingLineUp.setRowCount(0);
    displayMessage("Please enter a player.", colourFont);
  }

  /**
   * This method displays a message in the message box given a message and a colour.
   *
   * @param message,    String, message to display.
   * @param fontColour, {@link Color}, colour of the font.
   */
  private void displayMessage(String message, Color fontColour) {
    EmptyBorder emptyBorder = new EmptyBorder(5, 5, 5, 5);
    LineBorder lineBorder = new LineBorder(fontColour, 1);
    messageToUser.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
    messageToUser.setText(message);
    messageToUser.setHorizontalAlignment(SwingConstants.CENTER);
    messageToUser.setForeground(fontColour);
  }
}
