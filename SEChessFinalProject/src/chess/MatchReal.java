package chess;

public class MatchReal {

    // This is meant to encapsulate things about the match itself, and record what happens in the matches

    // right now some basic brackets are defined in the player class, but they should possibly be moved here
	public void getPlayerStats(Players p){
		int[] stats = p.getStats();
		System.out.println("\nWins: " + stats[0] + " Losses: " + stats[1] + " Draws: " + stats[2] + "\n");
	}
	
	public void getPlayers(Players p){
		p.getPlayers();
	}
	
	

    // TO-DO:
    // - make a function to calculate points to add for a draw, range should be from +- 0-3 points 
    // - beef up the math and legitness of the matching algorithm 
    // - finish the play game flow
    // - think about how to beef up the play game flow
    // - do some slight data structure changes, structurally improve the entire application 
    // - (I think?) implement singleton design pattern on ELONAMES in ACPlayer
    // - look through the application and think about where design patterns could apply in general
    // - actually fill out stuff for this class:
    //      - this class needs to keep track of everything that happens during the PlayGame class, basically we need stats how many wins (currently) does each player have, if it was a tournament what would their scores be?
    //      - this will help complete the PlayGame class as well
    
}
