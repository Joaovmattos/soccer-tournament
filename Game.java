/*
 After the presentation, I looked at all the feedback from my classmates and instructor
and picked the changes that made the most sense to improve the project.

CHANGE 1  Show team stats in the bracket
Feedback: Almost everyone mentioned having to scroll up to check ATK and DEF stats
before picking a formation. It was one of the most common complaints.
What I did: Updated showBracket() in Tournament.java to display ATK and DEF
next to each team name every time the bracket is shown.
Why: It makes the game easier to play and removes a frustrating step.

CHANGE 2 Show opponent's formation after each match
Feedback: A few classmates suggested showing what formation the opponent used
 so the player can learn from it and adjust strategy.
What I did: Updated showResults() in Tournament.java to display both formations
after any match that involves the player's team.
Why: It adds useful info without making the screen too busy.

CHANGE 3 Fix the score bug
Feedback: The instructor and two classmates noticed that most games were ending
with the same scoreline. It looked like a bug.
What I did: Fixed the strength calculation in Match.java. The original code was
comparing attack vs attack for both teams. I changed it to compare
home attack vs away defense, and away attack vs home defense.
 Why: That's how a real match works, and now scores are more realistic and varied.

CHANGE 4Caseinsensitive formation input
Feedback: During the demo I accidentally typed "Aggresive" and the game rejected it.
A classmate also pointed out the input validation could be more flexible.
What I did: Updated the input logic in Tournament.java to normalize whatever
the player types — first letter uppercase, rest lowercase — before checking it.
So "aggressive", "AGGRESSIVE", and "Aggressive" all work now.
Why: Small fix but it makes the game less frustrating to play.

WHAT I LEARNED
Looking at the feedback helped me see things I missed because I was too close to the code.
The score bug is a good example I didn't notice it during testing, but multiple people
caught it right away. Reading comments from people who never saw the code before
 is actually one of the best ways to find problems.
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        // Creating all 8 teams with their attack and defense stats
        Team Brazil = new Team("Brazil", 82, 80);
        Team Argentina = new Team("Argentina", 81, 79);
        Team France = new Team("France", 80, 82);
        Team German = new Team("German", 78, 84);
        Team Spain = new Team("Spain", 79, 78);
        Team Portugal = new Team("Portugal", 80, 76);
        Team Italy = new Team("Italy", 77, 83);
        Team England = new Team("England", 78, 79);

        System.out.println("=======================================");
        System.out.println("     SOCCER TOURNAMENT SIMULATOR  ");
        System.out.println("=======================================");
        System.out.println("  8 teams - 3 rounds - 1 champion ");
        System.out.println("=======================================");

        // Adding all teams to the list
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(Brazil);
        teams.add(Argentina);
        teams.add(France);
        teams.add(German);
        teams.add(Spain);
        teams.add(Portugal);
        teams.add(Italy);
        teams.add(England);

        // Showing each team with their stats so the player can choose
        for (int i = 0; i < teams.size(); i++){
            System.out.println((i + 1) + ". " + teams.get(i).getName() + " | ATK: " + teams.get(i).getAttack() + " | DEF: " + teams.get(i).getDefense());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSelect your team (1-8): ");
        int choice = scanner.nextInt();

        // choice - 1 because ArrayList index starts at 0
        Team playerTeam = teams.get(choice - 1);

        // Shuffle the teams so the bracket is random every game
        Collections.shuffle(teams);

        Tournament tournament = new Tournament(teams, playerTeam);
        tournament.runTournament();
    }
}