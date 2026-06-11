public class FeedbacksReflection {
    /*
    After the presentation, I looked at all the feedback from my classmates and instructor
    and picked the changes that made the most sense to improve the project.


    CHANGE  Show team stats in the bracket
    Feedback: Almost everyone mentioned having to scroll up to check ATK and DEF stats
    before picking a formation. It was one of the most common complaints.
    What I did: Updated showBracket() in Tournament.java to display ATK and DEF
    next to each team name every time the bracket is shown.
    Why: It makes the game easier to play and removes a frustrating step.

    CHANGE 2 Show opponents formation after each match
    Feedback: A few classmates suggested showing what formation the opponent used
    so the player can learn from it and adjust strategy.
    What I did: Updated showResults() in Tournament.java to display both formations
    after any match that involves the player's team.
    Why: It adds useful info without making the screen too busy.

    CHANGE 3Fix the score bug
    Feedback: The instructor and two classmates noticed that most games were ending
    with the same scoreline. It looked like a bug.
    What I did: Fixed the strength calculation in Match.java. The original code was
    comparing attack vs attack for both teams. I changed it to compare
    home attack vs away defense, and away attack vs home defense.
    Why: That's how a real match works, and now scores are more realistic and varied.

    CHANGE 4 Case insensitive formation input
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
}