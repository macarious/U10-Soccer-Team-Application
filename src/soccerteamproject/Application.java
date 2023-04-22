package soccerteamproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * This is an implementation of {@link ApplicationInterface} which uses a graphical user interface.
 * It provides an application program where the user can interact with the text fields and drop-down
 * menus to input a new player information. It also has panes and tables which displays information
 * about all the players, tha players assigned to the team, and the starting line up.
 */
public class Application extends JFrame implements ApplicationInterface {

  private static final Integer[] SKILL_LEVELS = { 1, 2, 3, 4, 5 };
  private static final String[] TABLE_HEADINGS_ALL =
      { "#", "Last Name", "First Name", "Preferred Position", "Age", "Skill" };
  private static final String[] TABLE_HEADINGS_TEAM =
      { "Jersey", "Last Name", "First Name", "Assigned Position", "Age", "Skill", };

  // Components in the application.
  private JPanel paneLeftInput;
  private JTabbedPane paneRightOutput;
  private JPanel panelFirstName;
  private JPanel panelLastName;
  private JPanel panelBirthDate;
  private JPanel panelPosition;
  private JPanel panelSkillLevel;
  private JLabel labelFirstName;
  private JLabel labelLastName;
  private JLabel labelBirthDate;
  private JLabel labelPosition;
  private JLabel labelSkillLevel;
  private JTextField textFieldFirstName;
  private JTextField textFieldLastName;
  private JFormattedTextField textFieldBirthDate;
  private JComboBox<Position> comboBoxPosition;
  private JComboBox<Integer> comboBoxSkillLevel;
  private JButton buttonAddPlayer;
  private JButton buttonCreateTeam;
  private JButton buttonUseSample;
  private JButton buttonReset;
  private JButton buttonDarkLightMode;
  private JButton buttonExit;
  private JLabel messageToUser;
  private JPanel containerButtonGroup1;
  private JPanel containerButtonGroup2;
  private DefaultTableModel modelAllPlayers;
  private DefaultTableModel modelTeamPlayers;
  private DefaultTableModel modelStartingLineUp;
  private JPanel tab1RegisteredPlayers;
  private JPanel tab2TeamPlayers;
  private JPanel tab3StartingLineUp;
  private JTable tableAllPlayers;
  private JTable tableTeamPlayers;
  private JTable tableStartingLineUp;
  private JScrollPane scrollPane1;
  private JScrollPane scrollPane2;
  private JScrollPane scrollPane3;
  private UserInput userInput;

  // Colours used in application.
  private boolean isDarkMode;
  private Color colourBG1;
  private Color colourBG2;
  private Color colourBorder;
  private Color colourFont;
  private Color colourButtonBG1;
  private Color colourButtonFont1;
  private Color colourButtonBG2;
  private Color colourButtonFont2;
  private Color colourTabBG;
  private Color colourTabBGSelected;
  private Color colourTabFont;
  private Color colourError;
  private Font titleFont;
  private Font buttonFont1;
  private Font buttonFont2;

  /**
   * This is a construction for the Application class. It builds and configures a graphical user
   * interface, and its main functions are to register players, create teams, and display created
   * team and starting line up.
   */
  public Application() {
    setDarkMode(); // Default colour scheme.
    createComponents();
    constructApplication();
    configureApplication();

    // Pack the whole application.
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
    displayMessage("Successfully registered a new player.", colourFont);
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
      Object[] rowData = { String.format("%02d", rowIndex),
                           player.getLastName(),
                           player.getFirstName(),
                           player.getPreferredPosition(),
                           player.getAge(),
                           player.getSkillLevel() };
      modelAllPlayers.addRow(rowData);
      rowIndex++;
    }
  }

  @Override
  public void displayTeamPlayer(Map<PlayerIdentifier, Player> teamPlayerList) {
    displayTeamInfo(teamPlayerList, modelTeamPlayers);
  }

  @Override
  public void displayStartingLineUp(Map<PlayerIdentifier, Player> startingLineUp) {
    displayTeamInfo(startingLineUp, modelStartingLineUp);
  }

  @Override
  public void addFeatures(ControllerInterface features) {
    // Action for "Add Player" button.
    buttonAddPlayer.addActionListener(event -> {
      try {
        features.addNewPlayer();
      } catch (MissingInfoException | IllegalArgumentException | DuplicatePlayerException e) {
        displayMessage(e.getMessage(), colourError);
        return;
      } catch (DateTimeParseException e) {
        displayMessage("Date must be in the format yyyy-mm-dd", colourError);
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
        displayMessage(e.getMessage(), colourError);
        return;
      }
      features.displayTeamPlayer();
      features.displayStartingLineUp();
      displayMessage("A team has been created.", colourFont);
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
      paneRightOutput.setSelectedIndex(0);
    });

    // Action for "Toggle Dark/Light Mode" button.
    buttonDarkLightMode.addActionListener(event -> toggleDarkLightMode());

    // Action for "Exit" button.
    buttonExit.addActionListener(event -> features.exitProgram());
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
    comboBoxSkillLevel.setSelectedIndex(SKILL_LEVELS.length - 1);
  }

  @Override
  public void resetAllTables() {
    modelAllPlayers.setRowCount(0);
    modelTeamPlayers.setRowCount(0);
    modelStartingLineUp.setRowCount(0);
    displayMessage("Please register a player.", colourFont);
  }

  /**
   * This method displays a message in the message box given a message and a colour.
   *
   * @param message,    String, message to display.
   * @param fontColour, {@link Color}, colour of the font.
   */
  private void displayMessage(String message, Color fontColour) {
    EmptyBorder emptyBorder = new EmptyBorder(5, 5, 5, 5);
    LineBorder lineBorder = new LineBorder(fontColour, 0);
    messageToUser.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
    messageToUser.setText("<html>" + message + "</html>");
    messageToUser.setHorizontalAlignment(SwingConstants.CENTER);
    messageToUser.setForeground(fontColour);
  }

  /**
   * This method displays team info from a map of {@link PlayerIdentifier} and {@link Player} to a
   * JTable.
   *
   * @param teamMap    Map<{@link PlayerIdentifier}, {@link Player}>, map representing soccer team
   *                   players.
   * @param tableModel {@link DefaultTableModel}, the model associated with the JTable.
   */
  private void displayTeamInfo(Map<PlayerIdentifier, Player> teamMap,
      DefaultTableModel tableModel) {
    // Remove all rows in the table.
    tableModel.setRowCount(0);

    // Add each team player in the list as a new row in the table.
    for (Map.Entry<PlayerIdentifier, Player> entry : teamMap.entrySet()) {
      Object[] rowData = { String.format("%02d", entry.getKey().getJerseyNumber()),
                           entry.getValue().getLastName(),
                           entry.getValue().getFirstName(),
                           entry.getKey().getAssignedPosition(),
                           entry.getValue().getAge(),
                           entry.getValue().getSkillLevel() };
      tableModel.addRow(rowData);
    }
  }

  /**
   * This method sets the default colours and font. The default colour mode is Dark Mode.
   */
  private void setDarkMode() {
    colourBG1 = Color.DARK_GRAY;
    colourBG2 = Color.DARK_GRAY;
    colourBorder = Color.ORANGE;
    colourFont = Color.WHITE;
    colourTabBGSelected = Color.DARK_GRAY;
    colourButtonBG2 = Color.LIGHT_GRAY;
    colourButtonFont2 = Color.BLACK;
    colourError = Color.PINK;
    isDarkMode = true;

    // Does not change between dark and light mode.
    colourButtonBG1 = Color.ORANGE;
    colourButtonFont1 = Color.BLACK;
    colourTabBG = Color.ORANGE;
    colourTabFont = Color.DARK_GRAY;
    titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
    buttonFont1 = new Font(Font.SANS_SERIF, Font.BOLD, 16);
    buttonFont2 = new Font(Font.SANS_SERIF, Font.BOLD, 12);
  }

  /**
   * This method toggles between dark and light mode.
   */
  private void toggleDarkLightMode() {
    if (isDarkMode) {
      // Convert to Light Mode.
      colourBG1 = Color.LIGHT_GRAY;
      colourBG2 = Color.LIGHT_GRAY;
      colourBorder = Color.BLACK;
      colourFont = Color.BLACK;
      colourTabBGSelected = Color.LIGHT_GRAY;
      colourButtonBG2 = Color.GRAY;
      colourButtonFont2 = Color.WHITE;
      colourError = Color.RED;
      isDarkMode = false;
    } else {
      // Convert back to Dark Mode.
      setDarkMode();
    }
    configureApplication();
  }

  /**
   * This method builds the components of the application frame.
   */
  private void createComponents() {
    userInput = new UserInput(); // Empty user input.

    // Fields and buttons for user interaction.
    // Container for pane2, pane3, and pane4.
    paneLeftInput = new JPanel();
    paneRightOutput = new JTabbedPane();

    // Field to input first name.
    // Field to input last name.
    // Field to input birthdate.
    // Field to select preferred positions.
    // Field to select skill level.
    panelFirstName = new JPanel();
    panelLastName = new JPanel();
    panelBirthDate = new JPanel();
    panelPosition = new JPanel();
    panelSkillLevel = new JPanel();
    labelFirstName = new JLabel("First Name: ");
    labelLastName = new JLabel("Last Name: ");
    labelBirthDate = new JLabel("Birth Date (yyyy-mm-dd): ");
    labelPosition = new JLabel("Preferred Position: ");
    labelSkillLevel = new JLabel("Skill Level (highest = 5): ");
    textFieldFirstName = new JTextField();
    textFieldLastName = new JTextField();
    textFieldBirthDate = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
    Position[] positions = Position.values();
    comboBoxPosition = new JComboBox<>(positions);
    comboBoxSkillLevel = new JComboBox<>(SKILL_LEVELS);

    // Button to register new player.
    // Button to create a team.
    // Button to register a list of sample players.
    // Button to reset all.
    // Button to configure between dark and light mode.
    // Button to exit application.
    buttonAddPlayer = new JButton("Register Player");
    buttonCreateTeam = new JButton("Create Team");
    buttonUseSample = new JButton("Use Sample Players");
    buttonReset = new JButton("Reset All");
    buttonDarkLightMode = new JButton("Toggle Dark/Light Mode");
    buttonExit = new JButton("Exit");
    containerButtonGroup1 = new JPanel();
    containerButtonGroup2 = new JPanel();

    // Message label to user.
    messageToUser = new JLabel("");

    // Set fields to default.
    resetAllFields();
    displayMessage("Please register a player.", colourFont);

    // Right pane for display.
    // Table for all registered players.
    // Table for all team players.
    // Table for starting line up.
    modelAllPlayers = createNewNotEditableTableModel(TABLE_HEADINGS_ALL);
    modelTeamPlayers = createNewNotEditableTableModel(TABLE_HEADINGS_TEAM);
    modelStartingLineUp = createNewNotEditableTableModel(TABLE_HEADINGS_TEAM);
    tab1RegisteredPlayers = new JPanel();
    tab2TeamPlayers = new JPanel();
    tab3StartingLineUp = new JPanel();
    tableAllPlayers = new JTable(modelAllPlayers);
    tableTeamPlayers = new JTable(modelTeamPlayers);
    tableStartingLineUp = new JTable(modelStartingLineUp);
    scrollPane1 = new JScrollPane(tableAllPlayers);
    scrollPane2 = new JScrollPane(tableTeamPlayers);
    scrollPane3 = new JScrollPane(tableStartingLineUp);
  }

  /**
   * This method constructs the main application frame.
   */
  private void constructApplication() {
    // Define layout of components.
    setLocation(200, 200);
    setResizable(false);
    setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

    // Construct left pane.
    panelFirstName.setLayout(new GridLayout(1, 2, 5, 5));
    panelFirstName.add(labelFirstName);
    panelFirstName.add(textFieldFirstName);

    panelLastName.setLayout(new GridLayout(1, 2, 5, 5));
    panelLastName.add(labelLastName);
    panelLastName.add(textFieldLastName);

    panelBirthDate.setLayout(new GridLayout(1, 2, 5, 5));
    panelBirthDate.add(labelBirthDate);
    panelBirthDate.add(textFieldBirthDate);

    panelSkillLevel.setLayout(new GridLayout(1, 2, 5, 5));
    panelSkillLevel.add(labelSkillLevel);
    panelSkillLevel.add(comboBoxSkillLevel);

    panelPosition.setLayout(new GridLayout(1, 2, 5, 5));
    panelPosition.add(labelPosition);
    panelPosition.add(comboBoxPosition);

    containerButtonGroup1.setLayout(new GridLayout(1, 2, 5, 5));
    containerButtonGroup1.add(buttonUseSample);
    containerButtonGroup1.add(buttonReset);

    containerButtonGroup2.setLayout(new GridLayout(1, 2, 5, 5));
    containerButtonGroup2.add(buttonDarkLightMode);
    containerButtonGroup2.add(buttonExit);

    paneLeftInput.setLayout(new GridLayout(10, 1, 10, 10));
    paneLeftInput.add(panelFirstName);
    paneLeftInput.add(panelLastName);
    paneLeftInput.add(panelBirthDate);
    paneLeftInput.add(panelPosition);
    paneLeftInput.add(panelSkillLevel);
    paneLeftInput.add(messageToUser);
    paneLeftInput.add(buttonAddPlayer);
    paneLeftInput.add(buttonCreateTeam);
    paneLeftInput.add(containerButtonGroup1);
    paneLeftInput.add(containerButtonGroup2);

    // Construct right pane.
    paneRightOutput.addTab("Registered Players", tab1RegisteredPlayers);
    paneRightOutput.addTab("Team Players", tab2TeamPlayers);
    paneRightOutput.addTab("Starting Line Up", tab3StartingLineUp);

    // Construct application by combing left and right panes.
    add(paneLeftInput);
    add(paneRightOutput);
  }

  /**
   * This method sets up the main application frame.
   */
  private void configureApplication() {
    setTitle("Soccer Team Application");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(colourBG1);

    // Customize Left Pane.
    paneLeftInput.setPreferredSize(new Dimension(360, 482));
    paneLeftInput.setBackground(colourBG1);
    EmptyBorder emptyBorderPaneLeft = new EmptyBorder(10, 10, 10, 10);
    TitledBorder titledBorderpaneLeft = new TitledBorder("Player Registration");
    titledBorderpaneLeft.setTitleFont(titleFont);
    titledBorderpaneLeft.setTitleColor(colourFont);
    titledBorderpaneLeft.setBorder(new LineBorder(colourFont, 2));
    paneLeftInput.setBorder(BorderFactory.createCompoundBorder(titledBorderpaneLeft,
                                                               emptyBorderPaneLeft));

    // Customize sub-panels for Left Pane.
    configureFieldPanel(panelFirstName);
    configureFieldPanel(panelLastName);
    configureFieldPanel(panelBirthDate);
    configureFieldPanel(panelPosition);
    configureFieldPanel(panelSkillLevel);

    // Customize labels for each field.
    labelFirstName.setForeground(colourFont);
    labelLastName.setForeground(colourFont);
    labelBirthDate.setForeground(colourFont);
    labelPosition.setForeground(colourFont);
    labelSkillLevel.setForeground(colourFont);

    // Customize text fields for first and last names.
    configureTextField(textFieldFirstName);
    configureTextField(textFieldLastName);

    // Customize formatted text field ranging from 80 years ago to current date.
    configureTextField(textFieldBirthDate);

    // Customize combo boxes.

    // Configure message label.
    if (messageToUser.getForeground() != colourFont) {
      messageToUser.setForeground(colourError);
    }

    // Customize buttons.
    configureButton1(buttonAddPlayer);
    configureButton1(buttonCreateTeam);
    configureButton2(buttonUseSample);
    configureButton2(buttonReset);
    configureButton2(buttonDarkLightMode);
    configureButton2(buttonExit);

    // Customize sub-panes for lesser buttons.
    containerButtonGroup1.setBackground(colourBG1);
    containerButtonGroup2.setBackground(colourBG1);

    // Customize Right Panel (Tab #1, 2, 3).
    paneRightOutput.setPreferredSize(new Dimension(600, 480));
    paneRightOutput.setFont(buttonFont1);

    // Remove the white line under a tab button
    Insets insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
    insets.top = 5;
    insets.bottom = 2;
    insets.left = 2;
    insets.right = 2;
    UIManager.put("TabbedPane.contentBorderInsets", insets);

    // Customize Tab 1.
    configureTabPanel(tab1RegisteredPlayers);
    configureTable(tableAllPlayers);
    configureScrollPane(scrollPane1);
    tab1RegisteredPlayers.add(scrollPane1);

    // Customize Tab 2.
    configureTabPanel(tab2TeamPlayers);
    configureTable(tableTeamPlayers);
    configureScrollPane(scrollPane2);
    tab2TeamPlayers.add(scrollPane2);

    // Customize Tab 3.
    configureTabPanel(tab3StartingLineUp);
    configureTable(tableStartingLineUp);
    configureScrollPane(scrollPane3);
    tab3StartingLineUp.add(scrollPane3);

    // Set the background color of the JViewport that contains the JTable
    JViewport viewport1 = (JViewport) tableAllPlayers.getParent();
    viewport1.setBackground(colourTabBGSelected);
    JViewport viewport2 = (JViewport) tableTeamPlayers.getParent();
    viewport2.setBackground(colourTabBGSelected);
    JViewport viewport3 = (JViewport) tableStartingLineUp.getParent();
    viewport3.setBackground(colourTabBGSelected);

    // Set tab colours.
    paneRightOutput.setBackgroundAt(0, colourTabBG);
    paneRightOutput.setForegroundAt(0, colourTabFont);
    paneRightOutput.setBackgroundAt(1, colourTabBG);
    paneRightOutput.setForegroundAt(1, colourTabFont);
    paneRightOutput.setBackgroundAt(2, colourTabBG);
    paneRightOutput.setForegroundAt(2, colourTabFont);
  }

  /**
   * This method configures a JPanel using consistent formatting.
   *
   * @param fieldPanel {@link JPanel}, field panel to configure.
   */
  private void configureFieldPanel(JPanel fieldPanel) {
    EmptyBorder emptyBorderSubPanel = new EmptyBorder(5, 5, 5, 5);
    LineBorder lineBorderSubPanel = new LineBorder(colourBorder, 1);
    fieldPanel.setBorder(BorderFactory.createCompoundBorder(lineBorderSubPanel,
                                                            emptyBorderSubPanel));
    fieldPanel.setBackground(colourBG2);
  }

  /**
   * This method configures a JTextField using consistent formatting.
   *
   * @param textField {@link JTextField}, text field to configure.
   */
  private void configureTextField(JTextField textField) {
    textField.setColumns(12);
    textField.setHorizontalAlignment(SwingConstants.LEFT);
  }

  /**
   * This method configures the major JButton using consistent formatting.
   *
   * @param button {@link JButton}, button to configure.
   */
  private void configureButton1(JButton button) {
    button.setActionCommand(button.getText());
    button.setBackground(colourButtonBG1);
    button.setForeground(colourButtonFont1);
    button.setBorder(BorderFactory.createRaisedBevelBorder());
    button.setFont(buttonFont1);
  }

  /**
   * This method configures the minor JButton using consistent formatting.
   *
   * @param button {@link JButton}, button to configure.
   */
  private void configureButton2(JButton button) {
    button.setActionCommand(button.getText());
    button.setBackground(colourButtonBG2);
    button.setForeground(colourButtonFont2);
    button.setBorder(BorderFactory.createRaisedBevelBorder());
    button.setFont(buttonFont2);
  }

  /**
   * This method configures a JTable using consistent formatting.
   *
   * @param table {@link JTable}, table to configure.
   */
  private void configureTable(JTable table) {
    table.getColumnModel().getColumn(0).setPreferredWidth(50); // Jersey
    table.getColumnModel().getColumn(1).setPreferredWidth(100); // Last Name
    table.getColumnModel().getColumn(2).setPreferredWidth(100); // First Name
    table.getColumnModel().getColumn(3).setPreferredWidth(100); // Position
    table.getColumnModel().getColumn(4).setPreferredWidth(50); // Age
    table.getColumnModel().getColumn(5).setPreferredWidth(50); // Skill Level
    table.setBorder(new LineBorder(colourBorder, 0));
    table.setGridColor(colourButtonFont1);
    table.setSelectionForeground(colourButtonFont1);
    table.setSelectionBackground(colourButtonBG1);
    table.setForeground(colourFont);
    table.setBackground(colourTabBGSelected);
    table.setFont(buttonFont2);
    table.setShowVerticalLines(false);
    table.setAutoCreateRowSorter(true);
  }

  /**
   * This method configures a JScrollPane using consistent formatting.
   *
   * @param scrollPane {@link JScrollPane}, scroll pane to configure.
   */
  private void configureScrollPane(JScrollPane scrollPane) {
    scrollPane.setPreferredSize(new Dimension(550, 425));
    scrollPane.setBackground(colourBG1);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
  }

  /**
   * This method configures a JScrollPane using consistent formatting.
   *
   * @param tabPanel {@link JPanel}, scroll pane to configure.
   */
  private void configureTabPanel(JPanel tabPanel) {
    tabPanel.setBackground(colourTabBGSelected);
    EmptyBorder emptyBorderPane1 = new EmptyBorder(5, 10, 10, 10);
    LineBorder lineBorderPane1 = new LineBorder(colourBorder, 0);
    tabPanel.setBorder(BorderFactory.createCompoundBorder(lineBorderPane1, emptyBorderPane1));
  }

  /**
   * This method creates a new not editable table model.
   *
   * @return {@link DefaultTableModel}, table model which is not editable.
   */
  private DefaultTableModel createNewNotEditableTableModel(String[] headings) {
    return new DefaultTableModel(headings, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
  }
}
