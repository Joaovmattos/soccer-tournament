// UPDATED (Feedback): Three changes were made to this file based on peer and instructor feedback:
//
// showBracket() now displays ATK and DEF stats next to each team name.
// Multiple classmates and the instructor pointed out that having to scroll up
// to check stats before picking a formation was annoying. Now all the info
// you need is right there when the bracket is shown.
//
// showResults() now shows the formation each team used after every match.
// A classmate suggested showing the opponent's formation so you can learn
// from it and adjust your strategy in the next round.
//
// Formation input is now case-insensitive.
// Before this fix, typing "aggressive" instead of "Aggressive" would fail.
// Now the input is normalized — first letter capitalized, rest lowercase —
// so any variation like "AGGRESSIVE" or "aggressive" works fine.

import java.util.*;
import java.util.Scanner;

public class Tournament {

    // Attributes
    private Queue<Team> bracket;       // Queue keeps match order — first in, first out
    private Stack<Match> matchHistory; // Stack stores match history — last match on top
    private String currentRound;
    private Team champion;
    private Team playerTeam;
    private boolean playerEliminated = false;
    private ArrayList<Team> allTeams;

    // Constructor
    public Tournament(ArrayList<Team> teams, Team playerTeam) {
        this.playerTeam = playerTeam;
        this.matchHistory = new Stack<>();
        this.bracket = new LinkedList<>(teams); // LinkedList implements Queue
        this.currentRound = "Quarterfinals";
        this.champion = null;
        this.allTeams = teams;
    }

    public void playRound() {
        int numMatches = bracket.size() / 2;
        String playerFormation = null;
        List<Match> roundResults = new ArrayList<>();

        // Only ask for formation if the player's team is still in the tournament
        if (bracket.contains(playerTeam)) {
            Scanner scanner = new Scanner(System.in);

            // UPDATED (Feedback): Input is now case-insensitive.
            // We normalize it so "aggressive", "AGGRESSIVE", and "Aggressive" all work.
            // Before this fix, any lowercase input was rejected as invalid.
            while (playerFormation == null) {
                System.out.println("Choose your formation (Aggressive / Balanced / Defensive): ");
                String input = scanner.nextLine();
                String normalized = input.trim().substring(0, 1).toUpperCase() + input.trim().substring(1).toLowerCase();
                if (normalized.equals("Aggressive") || normalized.equals("Balanced") || normalized.equals("Defensive")) {
                    playerFormation = normalized;
                } else {
                    System.out.println("Invalid formation! Please try again.");
                }
            }
        }

        // Save whether the player was in this round before matches run
        boolean wasPlaying = bracket.contains(playerTeam);

        for (int i = 0; i < numMatches; i++) {
            playNextMatch(playerFormation, roundResults);
        }

        showResults(roundResults);

        // Show elimination message only once — right after the round the player lost
        if (playerEliminated && wasPlaying) {
            System.out.println("\n" + playerTeam.getName() + " has been eliminated!");
            System.out.println("Better luck next time — see you in 4 years!");
        }
    }

    public boolean playNextMatch(String playerFormation, List<Match> roundResults) {
        // poll() removes and returns the first team in the queue
        Team home = bracket.poll();
        Team away = bracket.poll();

        // Small pause between matches so it doesn't all print at once
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String[] formations = {"Aggressive", "Balanced", "Defensive"};
        Random rand = new Random();
        String homeFormation;
        String awayFormation;

        // Use the players chosen formation machine picks randomly for all others
        if (home == playerTeam) {
            homeFormation = playerFormation;
            awayFormation = formations[rand.nextInt(3)];
        } else if (away == playerTeam) {
            awayFormation = playerFormation;
            homeFormation = formations[rand.nextInt(3)];
        } else {
            // Neither team is the player — machine picks both formations
            homeFormation = formations[rand.nextInt(3)];
            awayFormation = formations[rand.nextInt(3)];
        }

        Match match = new Match(home, away, homeFormation, awayFormation);
        Team winner = match.playMatch();
        matchHistory.push(match); // push adds to the top of the stack
        bracket.add(winner);      // winner goes back into the bracket for next round
        roundResults.add(match);

        // Check if the player was in this match and lost
        if ((home == playerTeam || away == playerTeam) && winner != playerTeam) {
            playerEliminated = true;
            return true;
        }
        return false;
    }

    public void showBracket() {
        // Copy the bracket to a list so we can loop without removing teams
        List<Team> temp = new ArrayList<>(bracket);
        System.out.println("\n=== BRACKET - " + currentRound + " ===");

        // UPDATED (Feedback): Now shows ATK and DEF stats for each team in the bracket.
        // Before this change, only team names were displayed, which meant the player
        // had to scroll all the way up to check stats before picking a formation.
        // Now everything needed to make a decision is visible right here.
        for (int i = 0; i < temp.size(); i += 2) {
            Team home = temp.get(i);
            Team away = temp.get(i + 1);
            System.out.println("Match " + (i / 2 + 1) + " : "
                    + home.getName() + " (ATK: " + home.getAttack() + " | DEF: " + home.getDefense() + ")"
                    + " vs "
                    + away.getName() + " (ATK: " + away.getAttack() + " | DEF: " + away.getDefense() + ")"
                    + (home == playerTeam || away == playerTeam ? "  <-- YOUR TEAM!" : ""));
        }
    }

    public void advanceRound() {
        // Update the round name based on how many teams are left
        if (bracket.size() == 4) {
            currentRound = "Semifinals";
        } else if (bracket.size() == 2) {
            currentRound = "Final";
        }
    }

    public void runTournament() {
        // Keep playing rounds until only one team is left
        while (bracket.size() > 1) {
            showBracket();
            playRound();
            advanceRound();
        }

        champion = bracket.poll();

        if (playerEliminated) {
            System.out.println("\nTournament Champion: " + champion.getName());
            System.out.println("Unfortunately your team didn't win the tournament!");
        } else {
            System.out.println("\nCongratulations! Your team won the tournament!");
            System.out.println("Champion: " + champion.getName());
        }

        // Save results and show final rankings
        TournamentStats stats = new TournamentStats(matchHistory, allTeams);
        stats.saveResults();
        stats.sortTeams();
    }

    public void showResults(List<Match> roundResults) {
        System.out.println("\n=== RESULTS - " + currentRound + " ===");

        for (int i = 0; i < roundResults.size(); i++) {
            Match m = roundResults.get(i);

            // UPDATED (Feedback): Now shows the formation used by each team after the match.
            // A classmate suggested showing the opponents formation so the player
            // can see what strategy was used against them and adjust for the next round.
            // Only the player's match shows formation info — AI vs AI matches don't need it.
            String formation = "";
            if (m.getHome() == playerTeam) {
                formation = "  (Your formation: " + m.getHomeFormation() + " | Opponent: " + m.getAwayFormation() + ")";
            } else if (m.getAway() == playerTeam) {
                formation = "  (Your formation: " + m.getAwayFormation() + " | Opponent: " + m.getHomeFormation() + ")";
            }

            System.out.println("Match " + (i + 1) + " : " + m.toString() + formation);
        }
    }
}