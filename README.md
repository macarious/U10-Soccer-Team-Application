# Soccer Team Application

## 1. About/Overview

This is a Java application that allows users to register soccer players and create teams based on
their skill levels and preferred positions.
It also provides options to reset all registered players and team information, toggle between dark
and light mode, and exit the application.

## 2. List of Features

1. __Register Player__:
   Registers a new player to be a candidate for team creation.
   A list of registered players are available for view.<br><br>
2. __Create Team__:
   Creates a soccer team when at least 10 registered players.
   Team creation automatically assigns random jersey number to the top 20 players with the highest
   skill level.
   Assignment of positions prioritizes players with the highest skill levels, then preferred
   positions.
   A list of all team players and a list of all players on the starting line up are available for
   view.<br><br>
3. __Use Sample Players__:
   Registers a preset list of 25 players. Players that are already registered will be removed.
   This does not automatically creates a team.<br><br>
4. __Reset All__:
   Clear all registered players and team information.<br><br>
5. __Toggle Dark/Light Mode__:
   Changes the colour scheme of the application between Dark Mode and Light Mode.<br><br>
6. __Exit__:
   Closes the application window and exit.<br>

## 3. How to Run

To run `CS-5004-Soccer-Team-Application_jar.jar` in the terminal, use the command from the
directory:
> java -jar ./CS-5004-Soccer-Team-Application_jar.jar

For more information,
see [Oracle Java Documentation](https://docs.oracle.com/javase/tutorial/deployment/jar/run.html).

## 4. How to Use the Program

1. __Register Player__<br>
   The user fills out the approapriate fields in the left pane by providing the following
   information:
    * first name
    * last name
    * date of birth (__yyyy-mm-dd__)
    * preferred positions (`GOALIE`, `DEFENDER`, `MIDFIELDER`, `FORWARD`)
    * skill level (1 to 5)<br><br>

   Alternatively, the user can use the __"Use Sample List"__ button to register a list of 25 preset
   players.
   Note that by doing this, players who are already registered will be removed.<br><br>
2. __Create Team__<br>
   Once there are at least 10 registered players, the user can use the __"Create Team"__ button to
   generate a team.
   The team will be formed with up to 20 players. If there are more than 20 players, the top 20
   players with the highest skill levels will be selected.
   Jersey numbers from 1 to 20 are randomly and uniquely assigned to each team player.
   Positions (1 `GOALIE`, 2 `DEFENDER`, 3 `MIDFIELDER`, 1 `FORWARD`) are assigned prioritizing
   players with the highest skill level.<br><br>

3. __Viewing Team Information__<br>
   Once a team created, the application automatically selects the __"Team Players"__ tab on the
   right pane.
   To view the starting line-up, select the __"Starting Line Up"__ tab.
   To view a list of registered players, select the __"Registered Players"__ tab.<br><br>

4. __Register Player After Team Creation__<br>
   The user can continue to add players after team creation.
   However, the team information will not be updated until the __"Create Team"__ button is clicked
   again.<br><br>

5. __Reset All__<br>
   The user can remove all registered players and the generated team by clicking the __"Reset All"__
   button.<br><br>

6. __Toggle Dark/Light Mode__<br>
   The user can toggle the colour scheme of the application windown between Dark Mode and Light Mode
   by clicking the __"Toggle Dark/Light Mode"__ button.<br><br>

7. __Exit__<br>
   The user can exit the application by clicking the __"Exit"__ button.<br>

## 5. Design/Model Changes

* The following has been changed since the previous version:
    * detection of duplicate players when adding a new player
    * removal of all registered players and team information as a new public method

## 6. Assumptions

* Multiple players with the exact same information do not exist.
* The minimum number of players to form a team is 10.
* The maximum number of players in a team is 20.
* The maximum age of players is 10.
* Players of a very young age (as low as 0) is able to play soccer.
* There are exactly 1 goalie, 2 defenders, 3 midfielders, and 1 forward on a team.

## 7. Limitations

* The current version does not support the following:
    * removal of registered players
    * editing of registered players
    * registration of a second player with the exact same information
    * changing of the number of players on the team
    * changing of the number of each position on the team
    * identication of players with identical names
    * editing of age restriction of players
    * retrieval of players/team information after exiting
    * manual assignment of jersey numbers and positions

## 8. Citations
1. Oracle Corporation. (n.d.). How to Use Panels. Oracle. https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html (Accessed March 17, 2023)

2. Oracle Corporation. (n.d.). How to Use Tabbed Panes. Oracle. https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html (Accessed March 17, 2023)

3. Oracle Corporation. (n.d.). A Visual Guide to Layout Managers. Oracle. https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html (Accessed April 17, 2023)

4. Educba.com. (2022). Java Swing Layout | A Concise Guide to Swing Layout in Java - EduCBA. https://www.educba.com/java-swing-layout/ (Accessed April 18, 2023)

5. Stack Overflow. (2013, December 6). Java - Add two tabs into JPanel. https://stackoverflow.com/questions/20429840/java-add-two-tabs-into-jpanel (Accessed April 18, 2023)

6. GeeksforGeeks.org. (n.d.). Java Swing - JPanel With Examples - GeeksforGeeks. https://www.geeksforgeeks.org/java-swing-jpanel-with-examples/ (Accessed April 18, 2023)

7. Oracle Corporation. (n.d.). Getting Started with Swing (The Java™ Tutorials > Creating a GUI With JFC/Swing > Learning Swing with the NetBeans IDE). Oracle. https://docs.oracle.com/javase/tutorial/uiswing/start/index.html  (Accessed April 19, 2023)

8. Section.io Engineering Education Team. (2021, June 22). Introduction to Java Swing - Section.io Engineering Education Team - Medium. Medium. https://www.section.io/engineering-education/introduction-to-java-swing/ (Accessed April 19, 2023)

9. JetBrains s.r.o.. (n.d.). Compiling Applications - Help | IntelliJ IDEA. JetBrains s.r.o.. https://www.jetbrains.com/help/idea/compiling-applications.html (Accessed April 21, 2023)

10. Markdown Guide Editors & Contributors (2022). Getting Started | Markdown Guide Editors & Contributors - Markdown Guide Editors & Contributors . Markdown Guide Editors & Contributors . https://www.markdownguide.org/getting-started/ (Accessed April 21, 2023)