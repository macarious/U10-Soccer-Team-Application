# Soccer Team Application
## 1. About/Overview

## 2. List of Features
1. __Register Player__<br>
Registers a new player to be a candidate for team creation.<br><br>
2. __Create Team__<br>
Creates a soccer team when at least 10 registered players.
Team creation automatically assigns random jersey number to the top 20 players with the highest skill level.
Assignment of positions prioritizes players with the highest skill levels, then preferred positions.<br><br>
3. __Use Sample Players__<br>
Registers a preset list of 25 players. Players that are already registered will be removed.
This does not automatically creates a team.<br><br>
4. __Reset All__<br>
Clear all registered players and team information.<br><br>
5. __Toggle Dark/Light Mode__<br>
Changes the colour scheme of the application between Dark Mode and Light Mode.<br><br>
6. __Exit__<br>
Closes the application window and exit.<br>

## 3. How to Run
To run `CS-5004-Soccer-Team-Application_jar.jar` in the terminal, use the command from the directory:
> java -jar ./CS-5004-Soccer-Team-Application_jar.jar

For more information, see [Oracle Java Documentation](https://docs.oracle.com/javase/tutorial/deployment/jar/run.html).

## 4. How to Use the Program
1. __Register Player__<br>
The user fills out the approapriate fields in the left pane by providing the following information:
   * first name
   * last name
   * date of birth (__yyyy-mm-dd__)
   * preferred positions (`GOALIE`, `DEFENDER`, `MIDFIELDER`, `FORWARD`)
   * skill level (1 to 5)<br><br>

   Alternatively, the user can use the __"Use Sample List"__ button to register a list of 25 preset players.
Note that by doing this, players who are already registered will be removed.<br><br>
2. __Create Team__<br>
Once there are at least 10 registered players, the user can use the __"Create Team"__ button to generate a team.
The team will be formed with up to 20 players. If there are more than 20 players, the top 20 players with the highest skill levels will be selected.
Jersey numbers from 1 to 20 are randomly and uniquely assigned to each team player.
Positions (1 `GOALIE`, 2 `DEFENDER`, 3 `MIDFIELDER`, 1 `FORWARD`) are assigned prioritizing players with the highest skill level.<br><br>

3. __Viewing Team Information__<br>
Once a team created, the application automatically selects the __"Team Players"__ tab on the right pane.
To view the starting line-up, select the __"Starting Line Up"__ tab.
To view a list of registered players, select the __"Registered Players"__ tab.<br><br>

4. __Register Player After Team Creation__<br>
The user can continue to add players after team creation.
However, the team information will not be updated until the __"Create Team"__ button is clicked again.<br><br>

5. __Reset All__<br>
   The user can remove all registered players and the generated team by clicking the __"Reset All"__ button.<br><br>

6. __Toggle Dark/Light Mode__<br>
   The user can toggle the colour scheme of the application windown between Dark Mode and Light Mode by clicking the __"Toggle Dark/Light Mode"__ button.<br><br>

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