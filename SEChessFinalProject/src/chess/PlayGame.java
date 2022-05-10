package chess;

import java.util.Iterator;
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
                    while(MatchReal.ALLPLAYERS.size() != 1){
                        for(Iterator<Players> p = MatchReal.ALLPLAYERS.iterator(); p.hasNext(); ){
                            Players play = p.next();
                            System.out.println(MatchReal.ALLPLAYERS);
                            MatchingAlgo ma = new MatchingAlgo();
                            String nameBestMatch = ma.optimalMatch(play);
                            System.out.println("Current Player White: "+play.getName()+"\n"); 
                            System.out.println("Current Player Black: "+nameBestMatch+"\n"); 
                            
                            
                            // x1 = 1 for win, 2 for loss, 3 for draw
                            int x1 = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                            System.out.println("\n");
                            if(x1 == 1){
                                System.out.println("Win");
                                int kScore = ma.getKScore(play.getElo());
                                int opponentElo = ACPlayer.ELONAMES.get(nameBestMatch);
                                int newElo = ma.newPlayerRating(play.getElo(), kScore, ma.currentPercentage, 1);
                                
                                System.out.println("The new elo: "+newElo);
                                play.updateElo(opponentElo - Math.abs(newElo - play.getElo()), nameBestMatch);
                                play.updateElo(newElo, play.getName());
                                
                                // Players bye = this.match.getPlayer(nameBestMatch);
                                p.remove();
                                System.out.println(MatchReal.ALLPLAYERS);
                                
                            } 
                            
                            else if (x1 == 2){
                                System.out.println("Loss");
                                int opponentElo = ACPlayer.ELONAMES.get(nameBestMatch);
                                int kScore = ma.getKScore(opponentElo);
                                int newElo = ma.newPlayerRating(opponentElo, kScore, ma.currentPercentage, 0);
                                
                                System.out.println("The new elo: "+newElo);
                                play.updateElo(newElo, nameBestMatch);
                                play.updateElo((play.getElo() - Math.abs(newElo - opponentElo)), play.getName()); 
                                p.remove();
                                System.out.println(MatchReal.ALLPLAYERS);
                            }
                            
                            else if (x1 == 3){
                                System.out.println("Draw");
                                //DRAW FUNCTION
                                ;
                            }
                            System.out.println(MatchReal.ALLPLAYERS);
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
                    System.out.println(MatchReal.ALLPLAYERS);
                    System.out.println("Quitting the game now... ");
                    b = false;
                }

            }
        }
    }
    
}
