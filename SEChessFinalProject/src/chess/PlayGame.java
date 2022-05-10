package chess;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom; 

public class PlayGame {
	MatchReal match;
	
    PlayGame(){
    	this.match = new MatchReal();
    }

    public void beginTournament(){
        try (Scanner s = new Scanner(System.in)) {
            boolean b = true;

            while(b){
            	
//                System.out.println("Please select one of the options below: ");
//                System.out.println("Enter 1 to match with a new opponent: ");
//                System.out.println("Enter 2 to check your current stats: ");
//                System.out.println("Enter 3 to see all players currently in the tournament: ");
//                System.out.println("Enter 4 to quit game: ");
            	
            	System.out.println("Please enter 1-4: \n");
            	System.out.println("1) Begin Tournament ");
              	System.out.println("2) Check your current stats ");
              	System.out.println("3) See all players currently in the tournament ");
              	System.out.println("4) Quit game \n");
              	System.out.println("Your input: ");
          	     	
                
                int x = s.nextInt();
                if(x == 1){
                    for(Players p:MatchReal.ALLPLAYERS){
                        MatchingAlgo ma = new MatchingAlgo();
                        String nameBestMatch = ma.optimalMatch(p);
                        System.out.println("Current Player White: "+p.getName()+"\n"); 
                        System.out.println("Current Player Black: "+nameBestMatch+"\n"); 

                        int x1 = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                        System.out.println("\n");
                        if(x1 == 1){
                            int kScore = ma.getKScore(p.getElo());
                            int opponentElo = ACPlayer.ELONAMES.get(nameBestMatch);
                            int newElo = ma.newPlayerRating(p.getElo(), kScore, ma.currentPercentage, 1);
                            
                            System.out.println("The new elo: "+newElo);
                            p.updateElo(opponentElo - Math.abs(newElo - p.getElo()), nameBestMatch);
                            p.updateElo(newElo, p.getName());
                            
                            
                        } 
    
                        else if (x1 == 2){
                            int kScore = ma.getKScore(p.getElo());
                            int opponentElo = ACPlayer.ELONAMES.get(nameBestMatch);
                            int newElo = ma.newPlayerRating(p.getElo(), kScore, ma.currentPercentage, 0);
    
                            System.out.println("The new elo: "+newElo);
                            p.updateElo(opponentElo + Math.abs(newElo - p.getElo()), nameBestMatch);
                            p.updateElo(newElo, p.getName()); 
                            //
                        }
                        
                        else if (x1 == 3){
                            //DRAW FUNCTION
                            ;
                        }
                    }
                    
                    // should we make it randomized as to who wins or loses?
                    // or should we allow the user to decide? Currently the user will decide
                    
                    // System.out.println("1) if you won the match: "); 
                    // System.out.println("2) if you lost the match: "); 
                    // System.out.println("3) if it was a draw: \n");
                    // System.out.println("Your input: ");
                    
                    

                    // p.addMatchStat(x1);
                }
                else if(x == 2){
                	// this.match.getPlayerStats(p);
                }
                else if(x == 3){
                    this.match.getPlayerStatus();
                }
                else{
                    System.out.println("Quitting the game now... ");
                    b = false;
                }

            }
        }
    }
    
}
