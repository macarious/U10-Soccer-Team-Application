# Soccer Team Application

## 1. About/Overview

This is a Java application that allows users to register soccer players of age 10 and under and
create teams based on their skill levels and preferred positions.
A team must have at least 10 players and at most 20 players.
Jersey numbers and positions are assigned to the players with the highest skill levels when a team is created.

## 2. List of Features

1. __Register Player__:
   Registers a new player to be a candidate for team creation.
   A list of registered players are available for view.
   ![Registered Players Display](https://github.com/macarious/U10-Soccer-Team-Application/blob/master/res/01-register-player.png "List of Registered Players")
   <br>
2. __Create Team__:
   Creates a soccer team when at least 10 registered players.
   Team creation automatically assigns random jersey number to the top 20 players with the highest
   skill level.
   Assignment of positions prioritizes players with the highest skill levels, then preferred
   positions.
   A list of all team players and a list of all players on the starting line up are available for
   view.
   ![Team Players Display](https://github.com/macarious/U10-Soccer-Team-Application/blob/master/res/02-team-display.png "List of Players on the Team")
   <br>
3. __Use Sample Players__:
   Registers a preset list of 25 players. Players that are already registered will be removed.
   This does not automatically creates a team.
   ![Starting Line Up Display](https://github.com/macarious/U10-Soccer-Team-Application/blob/master/res/03-starting-line-up.png "List of Players on Starting Line Up")
   <br>
4. __Reset All__:
   Clear all registered players and team information.<br><br>
5. __Toggle Dark/Light Mode__:
   Changes the colour scheme of the application between Dark Mode and Light Mode.<br><br>
6. __Exit__:
   Closes the application window and exit.<br>

## 3. How to Run

To run `U10-Soccer-Team-Application.jar` in the terminal, use the command from the
directory:
> java -jar ./U10-Soccer-Team-Application.jar

For more information,
see [Oracle Java Documentation](https://docs.oracle.com/javase/tutorial/deployment/jar/run.html).

Alternatively, to run the `.jar` file in IntelliJ, press `Ctrl+Shirt+A`, find and run the __Edit
Configuration__ action.
In the __Run/Debug Configuration__ dialog, click __+__ and select __JAR Application__.
Add a name for the new configuration.
In the __Path to JAR__ field, specify the path to the JAR file on your computer.
Under __Before launch__, click __+__, select __Build Artifacts__ in the dialog that opens.
On the toolbar, select the created configuration and run the JAR file.
For more information,
see [Compilation and building | IntelliJ IDEA](https://www.jetbrains.com/help/idea/compiling-applications.html#run_packaged_jar)

## 4. How to Use the Program

1. __Register Player__<br>
   The user fills out the appropriate fields in the left pane by providing the following
   information:
    * first name
    * last name
    * date of birth (format: __yyyy-mm-dd__)
    * preferred positions (`GOALIE`, `DEFENDER`, `MIDFIELDER`, `FORWARD`)
    * skill level (1=lowest to 5=highest)<br><br>

   Alternatively, the user can use the __Use Sample List__ button to register a list of 25 preset
   players.
   Note that by doing this, players who are already registered will be removed.<br><br>
2. __Create Team__<br>
   Once there are at least 10 registered players, the user can use the __Create Team__ button to
   generate a team.
   The team will be formed with up to 20 players. If there are more than 20 players, the top 20
   players with the highest skill levels will be selected.
   Jersey numbers from 1 to 20 are randomly and uniquely assigned to each team player.
   Positions (1 `GOALIE`, 2 `DEFENDER`, 3 `MIDFIELDER`, 1 `FORWARD`) are assigned prioritizing
   players with the highest skill level.<br><br>

3. __Viewing Team Information__<br>
   Once a team created, the application automatically selects the __Team Players__ tab on the
   right pane.
   To view the starting line-up, select the __Starting Line Up__ tab.
   To view a list of registered players, select the __Registered Players__ tab.<br><br>

4. __Register Player After Team Creation__<br>
   The user can continue to add players after team creation.
   However, the team information will not be updated until the __Create Team__ button is clicked
   again.<br><br>

5. __Reset All__<br>
   The user can remove all registered players and the generated team by clicking the __Reset All__
   button.<br><br>

6. __Toggle Dark/Light Mode__<br>
   The user can toggle the colour scheme of the application window between Dark Mode and Light Mode
   by clicking the __Toggle Dark/Light Mode__ button.<br><br>

7. __Exit__<br>
   The user can exit the application by clicking the __Exit__ button.<br>

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
    * identification of players with identical names
    * editing of age restriction of players
    * retrieval of players/team information after exiting
    * manual assignment of jersey numbers and positions

## 8. Citations

1. Oracle Corporation. (n.d.). How to Use Panels.
   Oracle. https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html (Accessed March 17, 2023)

2. Oracle Corporation. (n.d.). How to Use Tabbed Panes.
   Oracle. https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html (Accessed March 17, 2023)

3. Oracle Corporation. (n.d.). A Visual Guide to Layout Managers.
   Oracle. https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html (Accessed April 17, 2023)

4. Educba.com. (2022). Java Swing Layout | A Concise Guide to Swing Layout in Java -
   EduCBA. https://www.educba.com/java-swing-layout/ (Accessed April 18, 2023)

5. Stack Overflow. (2013, December 6). Java - Add two tabs into
   JPanel. https://stackoverflow.com/questions/20429840/java-add-two-tabs-into-jpanel (Accessed April 18, 2023)

6. GeeksforGeeks.org. (n.d.). Java Swing - JPanel With Examples -
   GeeksforGeeks. https://www.geeksforgeeks.org/java-swing-jpanel-with-examples/ (Accessed April 18, 2023)

7. Oracle Corporation. (n.d.). Getting Started with Swing (The Java™ Tutorials > Creating a GUI With
   JFC/Swing > Learning Swing with the NetBeans IDE).
   Oracle. https://docs.oracle.com/javase/tutorial/uiswing/start/index.html  (Accessed April 19, 2023)

8. Section.io Engineering Education Team. (2021, June 22). Introduction to Java Swing - Section.io
   Engineering Education Team - Medium.
   Medium. https://www.section.io/engineering-education/introduction-to-java-swing/ (Accessed April 19, 2023)

9. JetBrains s.r.o.. (n.d.). Compiling Applications - Help | IntelliJ IDEA. JetBrains
   s.r.o.. https://www.jetbrains.com/help/idea/compiling-applications.html (Accessed April 21, 2023)

10. Markdown Guide Editors & Contributors (2022). Getting Started | Markdown Guide Editors &
    Contributors - Markdown Guide Editors & Contributors . Markdown Guide Editors &
    Contributors . https://www.markdownguide.org/getting-started/ (Accessed April 21, 2023)
