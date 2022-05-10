package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class MatchReal {
    static final List<Players> ALLPLAYERS = new ArrayList<>();

    // This is meant to encapsulate things about the match itself, and record what happens in the matches

    // right now some basic brackets are defined in the player class, but they should possibly be moved here
	public List<Integer> getPlayerStats(Players p){
		return p.getCurrentPlayerStats(p);
	}
	
    public void addPlayerToTournament(Players p){
        ALLPLAYERS.add(p);
    }

	public void getPlayerStatus(){
		// this should return who is left in the current tournament, as people will get knocked out
        // I was trying to think of a use case for all of the "is in game" logic, but the only reason
        // we would care if they are still in the tournament, is to see whether they are knocked out 
        // or not
        List<String> leftInTournament = new ArrayList<>();
        List<String> notInTournament = new ArrayList<>();

        for(Players i : ALLPLAYERS){
            if(i.isInGame()){
                leftInTournament.add(i.getName());
            }
            else{
                notInTournament.add(i.getName());
            }

        }

        for(String k : notInTournament){
            System.out.println("Knocked out of the tournament:"+"\t"+k);
        }
        for(String j : leftInTournament){
            System.out.println("Still in the tournament:"+"\t"+j);
        }

        
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
