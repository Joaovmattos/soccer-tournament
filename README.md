# Soccer Tournament Simulator 

A console based Java application that simulates a mini soccer tournament. 
The user picks a team, choose a formation strategy, and competes through an elimination bracket with match results decided by risk based random logic. 

## How to run

1. Clone the repository:
   git clone https://github.com/Joaovmattos/soccer-tournament.git

2. Navigate to the project folder:
   cd soccer-tournament

3. Compile:
   javac *.java

4. Run and Enjoy it:
   java Game

## Features 

-Choose your team from a list of available clubs
-Pick a formation strategy: Aggressive, Balanced or Defensive
-Random match results influenced by your chosen risk level
-Single elimination tournament bracket with semifinals and final
-win/loss stats tracked throughout the tournament 

## Project Structure 

soccer-tournament/
├── Game.java          # Main controller, user interaction
├── Tournament.java    # Bracket logic and progression
├── Match.java         # Match simulation and winner logic
├── Team.java          # Team attributes (name, attack, defense)
└── TournamentStats.java  # Stats, sorting, and file output

## Technologies 

-Java OOP - classes, encapsulation and methods. 
-ArrayList - team roster and match history. 
-Queue via LinkedList - tournament bracket order
-Stack - match history 
-Arrays - formations bonuses. 
-Console I/O - Scanner. 
-File I/O - match results saved to .txt 

## What I learned 

Building this project helped me understand how to structure a multi-class Java program from scratch. I practiced applying OOP concepts to real-world entities (teams,matches, tournaments) and learned how to manage shared state across classes using data structures like ArrayList, Queue, and Stack.

## Future Improvements 

-Save match results to a text file (File I/O) 
- Sortable leaderboard by wins
- Simple GUI using Java Swing (strech goal)
   
   
