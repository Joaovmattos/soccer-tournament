// UPDATED (Feedback): I tried Fixed a bug where all games were ending with the same scoreline
// professor found a bug which i havent noticed until receive the feedbacks
// The original code was using attack bonus for both teams and wasn't comparing
// attack vs defense correctly. Now homeStrength uses home attack vs away defense,
// and awayStrength uses away attack vs home defense — which is how a real match works.
//
// UPDATED (Feedback): Added getHomeFormation() and getAwayFormation() getters
// so Tournament.java can display each team's formation after the match ends.
// No setter was needed since formations are set in the constructor and don't change.

import java.util.Random;

public class Match {
    private Team home;
    private Team away;
    private String homeFormation;
    private String awayFormation;
    private int homeScore;
    private int awayScore;

    public Match(Team home, Team away, String homeFormation, String awayFormation) {
        this.home = home;
        this.away = away;
        this.homeFormation = homeFormation;
        this.awayFormation = awayFormation;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    // Returns attack and defense bonus based on the chosen formation
    // index [0] = attack bonus, index [1] = defense bonus
    public int[] getFormationBonus(String formation) {
        switch (formation) {
            case "Aggressive":
                return new int[]{15, -10};
            case "Balanced":
                return new int[]{5, 5};
            case "Defensive":
                return new int[]{-10, 15};
            default:
                // If formation is invalid, no bonus is applied
                return new int[]{0, 0};
        }
    }

    public Team playMatch() {
        Random rand = new Random();

        // UPDATED Bug Fix lol : homeStrength now compares home attack vs away defense.
        // awayStrength compares away attack vs home defense.
        // Before this fix, both teams were using attack stats, which caused
        // unrealistic and repetitive scorelines every game.
        int homeStrength = home.getAttack() + getFormationBonus(homeFormation)[0]
                - away.getDefense() - getFormationBonus(awayFormation)[1]
                + rand.nextInt(30) + 1;

        int awayStrength = away.getAttack() + getFormationBonus(awayFormation)[0]
                - home.getDefense() - getFormationBonus(homeFormation)[1]
                + rand.nextInt(30) + 1;

        // Difference determines the scoreline — bigger gap = bigger win
        int difference = Math.abs(homeStrength - awayStrength);

        if (homeStrength > awayStrength) {
            // Set the score based on how dominant the win was
            if (difference <= 10) {
                homeScore = 1; awayScore = 0;
            } else if (difference <= 20) {
                homeScore = 2; awayScore = 1;
            } else {
                homeScore = 3; awayScore = 1;
            }
            home.addWin();
            away.addLoss();
            return home;
        } else {
            if (difference <= 10) {
                awayScore = 1; homeScore = 0;
            } else if (difference <= 20) {
                awayScore = 2; homeScore = 1;
            } else {
                awayScore = 3; homeScore = 1;
            }
            away.addWin();
            home.addLoss();
            return away;
        }
    }

    // Getters to display match result after the game
    public int getHomeScore() { return homeScore; }
    public int getAwayScore() { return awayScore; }

    // UPDATED (Feedback): Added these two getters so we can show each team's
    // formation in the results screen after the match ends.
    public String getHomeFormation() { return homeFormation; }
    public String getAwayFormation() { return awayFormation; }

    public Team getHome() { return home; }
    public Team getAway() { return away; }

    @Override
    public String toString() {
        return home.getName() + " " + homeScore + " x " + awayScore + " " + away.getName();
    }
}