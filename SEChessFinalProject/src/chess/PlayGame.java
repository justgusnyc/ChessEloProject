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


                        // x1 = 1 for win, 2 for loss, 3 for draw
                        int x1 = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                        System.out.println("\n");
                        if(x1 == 1){
                            System.out.println("Win");
                            int kScore = ma.getKScore(p.getElo());
                            int opponentElo = ACPlayer.ELONAMES.get(nameBestMatch);
                            int newElo = ma.newPlayerRating(p.getElo(), kScore, ma.currentPercentage, 1);
                            
                            System.out.println("The new elo: "+newElo);
                            p.updateElo(opponentElo - Math.abs(newElo - p.getElo()), nameBestMatch);
                            p.updateElo(newElo, p.getName());
                            this.match.removePlayerFromTournament(nameBestMatch);
                        } 
    
                        else if (x1 == 2){
                            System.out.println("Loss");
                            int opponentElo = ACPlayer.ELONAMES.get(nameBestMatch);
                            int kScore = ma.getKScore(opponentElo);
                            int newElo = ma.newPlayerRating(opponentElo, kScore, ma.currentPercentage, 0);
                            
                            System.out.println("The new elo: "+newElo);
                            p.updateElo(newElo, nameBestMatch);
                            p.updateElo((p.getElo() - Math.abs(newElo - opponentElo)), p.getName()); 
                            MatchReal.ALLPLAYERS.remove(p);
                        }
                        
                        else if (x1 == 3){
                            System.out.println("Draw");
                            //DRAW FUNCTION
                            ;
                        }
                    }
                    
                    // should we make it randomized as to who wins or loses?
                    // or should we allow the user to decide? Currently the user will decide
                    
                    

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
