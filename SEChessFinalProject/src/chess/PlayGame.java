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
            // int sz = MatchReal.ALLPLAYERS.size();

            while(b){      
            	
            	System.out.println("Please enter 1-4: \n");
            	System.out.println("1) Begin Tournament ");
              	System.out.println("2) Check your current stats ");
              	System.out.println("3) See all players currently in the tournament ");
              	System.out.println("4) Quit game \n");
              	System.out.println("Your input: ");
          	     	
                
                int x = s.nextInt();
                if(x == 1){
                    
                    while(MatchReal.ALLPLAYERS.size() != 1){
                        System.out.println("Do I come here after the break?");
        
                        for(Iterator<Players> p = MatchReal.ALLPLAYERS.iterator(); p.hasNext(); ){

                            Players play = p.next();
                            if(play.isInGame() == false){
                                p.remove();
                                continue;
                            }
                            System.out.println(MatchReal.ALLPLAYERS);
                            MatchingAlgo ma = new MatchingAlgo();
                            String nameBestMatch = ma.optimalMatch(play);
                            Players opp = this.match.getPlayerObject(nameBestMatch);
                            if(play.getName() == nameBestMatch || opp.isInGame() == false){
                                continue;
                            }
                            System.out.println("Current Player White: "+play.getName()+"\n"); 
                            System.out.println("Current Player Black: "+nameBestMatch+"\n"); 
                            int opponentElo = ACPlayer.ELONAMES.get(nameBestMatch);
                            
                            
                            // x1 = 1 for win, 2 for loss, 3 for draw
                            int x1 = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                            System.out.println("\n");
                            if(x1 == 1){
                                System.out.println("Win");
                                System.out.println("Previous white elo: "+play.getElo());
                                int kScore = ma.getKScore(play.getElo());
                                System.out.println("Previous black elo: "+opponentElo);
                                int newElo = ma.newPlayerRating(play.getElo(), kScore, ma.percPlayerAWin(play.getElo(), opponentElo), 1);
                                
                                System.out.println("The new elo of main player (white): "+newElo);
                                System.out.println("The new elo of second player (black): "+(opponentElo - Math.abs(newElo - play.getElo())));
                                play.updateElo(opponentElo - Math.abs(newElo - play.getElo()), nameBestMatch);
                                play.updateElo(newElo, play.getName());
                       
                                opp.setNotInGame();
                                
                                System.out.println(MatchReal.ALLPLAYERS);
                                
                            } 
                            
                            else if (x1 == 2){
                                System.out.println("Loss");
                                
                                
                               
                                System.out.println("Previous white elo: "+play.getElo());
                                System.out.println("Previous black elo: "+opponentElo);
                                int kScore = ma.getKScore(opponentElo);
                                int newElo = ma.newPlayerRating(opponentElo, kScore, ma.percPlayerAWin(opponentElo, play.getElo()), 1);
                                int difference = Math.abs(opponentElo - newElo);
                                
                                System.out.println("The new elo of the main character (white): "+(play.getElo() - difference));
                                System.out.println("The new elo of secondary player (black): "+newElo);
                                System.out.println("This is the elo difference for black winning: "+ difference);
                                play.updateElo(newElo, nameBestMatch);
                                play.updateElo((play.getElo() - Math.abs(newElo - opponentElo)), play.getName()); 
                                p.remove();
                                
                            }
                            
                            else if (x1 == 3){
                                System.out.println("Draw");
                               

                                //DRAW FUNCTION
                                int playerWDrawVal = ma.drawValue(play.getElo(), ma.currentPercentage);
                                // int playerBDrawVal = ma.drawValue(opponentElo, ma.percPlayerAWin(opponentElo, play.getElo()));
                                int difference = Math.abs(play.getElo() - playerWDrawVal);
                                System.out.print(difference);
                                System.out.println("Previous white elo: "+play.getElo());
                                System.out.println("Previous white elo: "+opponentElo);
                                if(play.getElo() >= opponentElo){
                                    System.out.println("Player white elo after draw: "+playerWDrawVal);
                                    // System.out.println("Player black elo after draw: "+playerBDrawVal);
                                    System.out.println("Player white UPDATED elo after draw: "+(play.getElo() - difference));
                                    System.out.println("Player black UPDATED elo after draw: "+ (opponentElo + difference));
                                    play.updateElo((play.getElo() - difference), play.getName());
                                    play.updateElo(opponentElo + difference, nameBestMatch);
                                    
                                }
                                else{
                                    System.out.println("In the play less than opponent statement");
                                    System.out.println("Player white elo after draw: "+playerWDrawVal);
                                    // System.out.println("Player black elo after draw: "+playerBDrawVal);
                                    System.out.println("Player white UPDATED elo after draw: "+(play.getElo() + difference) );
                                    System.out.println("Player black UPDATED elo after draw: "+ (opponentElo - difference));
                                    play.updateElo((play.getElo() + difference), play.getName());
                                    play.updateElo((opponentElo - difference), nameBestMatch);

                                }

                            }
                            System.out.println(MatchReal.ALLPLAYERS);
                        }
                        System.out.println("This is the winner!: "+MatchReal.ALLPLAYERS);



                    }
                    
                    
                    
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
