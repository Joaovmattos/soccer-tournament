    // Attributes — each team has a name, stats, and a win/loss record
     
    public class Team{
    private String name;
    private int attack;
    private int defense;
    private int wins;
    private int losses;

    // Constructor — wins and losses always start at 0
    public Team(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.wins = 0;
        this.losses = 0;
    }

    // Getters — other classes can read but not directly change these values
    public String getName() { return name; }
    public int getAttack()  { return attack; }
    public int getDefense() { return defense; }
    public int getWins()    { return wins; }
    public int getLosses()  { return losses; }

    // Instead of setters, we use these methods to safely update the record
    // This way no one can set wins to a random number — it only goes up by 1
    public void addWin()  { this.wins++; }
    public void addLoss() { this.losses++; }

    @Override
    public String toString() {
        return name + " | ATK: " + attack + " | DEF: " + defense + " | Wins: " + wins + " | Losses: " + losses;
    }
}