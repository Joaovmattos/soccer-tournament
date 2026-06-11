import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class TournamentStats {

    private Stack<Match> matchHistory;
    private ArrayList<Team> teams;

    public TournamentStats(Stack<Match> matchHistory, ArrayList<Team> teams) {
        this.matchHistory = matchHistory;
        this.teams = teams;
    }

    // Loops through the match history and writes each result to a .txt file
    public void saveResults() {
        try {
            FileWriter writer = new FileWriter("tournament_results.txt");
            for (Match match : matchHistory) {
                writer.write(match.toString() + "\n");
            }
            writer.close();
            System.out.println("Results saved to tournament_results.txt!");
        } catch (IOException e) {
            // If something goes wrong with the file, we catch it here instead of crashing
            System.out.println("Error saving results to file!");
        }
    }

    // Searches for a team by name — case insensitive so "brazil" and "Brazil" both work
    public Team searchTeam(String name) {
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(name)) {
                return team;
            }
        }
        // Returns null if no team was found with that name
        return null;
    }

    // Sorts teams by wins using a lambda comparator — highest wins first
    public void sortTeams() {
        Collections.sort(teams, (t1, t2) -> t2.getWins() - t1.getWins());

        System.out.println("\n=== FINAL RANKINGS - TEAMS BY WINS ===");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println((i + 1) + ". " + teams.get(i).getName() + " - Wins: " + teams.get(i).getWins());
        }
    }
}