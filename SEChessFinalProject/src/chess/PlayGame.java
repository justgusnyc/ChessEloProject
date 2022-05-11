package chess;

import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom; 

public class PlayGame implements IPlayGame {
	MatchReal match;
	
    PlayGame(){
    	this.match = new MatchReal();
    }

    /**

     * beginTournament
     * .

     * @return void - begins the tournaments and has everyone face each other leaving 
     * only one winner!
     * 

     */

    public void beginTournament(){
        try (Scanner s = new Scanner(System.in)) {
            boolean b = true;

            while(b){      
            	
            	
                    
                while(MatchReal.ALLPLAYERS.size() != 1){
    
                    for(Iterator<Players> p = MatchReal.ALLPLAYERS.iterator(); p.hasNext(); ){

                        System.out.println("\n");
                        
                        MatchingAlgo ma = new MatchingAlgo();

                        Players play = p.next();
                        if(play.isInGame() == false){
                            p.remove();
                            continue;
                        }
                        

                        String nameBestMatch = ma.optimalMatch(play);
                        Players opp = this.match.getPlayerObject(nameBestMatch);
                        
                        if(play.getName() == nameBestMatch || opp.isInGame() == false){
                            continue;
                        }
                        
                        System.out.println("\n"); 
                        System.out.println("----------------------------------------------------------------"); 
                        System.out.println("Current Player White: " + play.getName()+"\n"); 
                        System.out.println("Current Player Black: " + nameBestMatch+"\n"); 
                        int opponentElo = ACPlayer.ELONAMES.get(nameBestMatch);
                        
                        
                        // x1 = 1 for win, 2 for loss, 3 for draw
                        int x1 = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                        System.out.println("\n");
                        if(x1 == 1){

                            System.out.println("White Wins");
                            System.out.println("Initial White Elo: " + play.getElo());
                            System.out.println("Previous Black Elo: " + opponentElo);

                            int kScore = ma.getKScore(play.getElo());
                            int newElo = ma.newPlayerRating(play.getElo(), kScore, ma.percPlayerAWin(play.getElo(), opponentElo), 1);
                            
                            System.out.println("New Elo of White: " + newElo);
                            System.out.println("New Elo of Black: " + (opponentElo - Math.abs(newElo - play.getElo())));

                            play.updateElo(opponentElo - Math.abs(newElo - play.getElo()), nameBestMatch);
                            play.updateElo(newElo, play.getName());
                    
                            opp.setNotInGame();

                            play.addMatchStat(1);
                            opp.addMatchStat(2);

                            System.out.println("White player "+"("+play.getName()+")"+" Amount of wins: "+play.getWins());
                            System.out.println("White player "+"("+play.getName()+")"+" Amount of draws: "+play.getDraws());
                            
                        } 
                        
                        else if (x1 == 2){

                            System.out.println("White Loses");
                            System.out.println("Player White initial Elo: "+play.getElo());
                            System.out.println("Player Black initial elo: "+opponentElo);

                            int kScore = ma.getKScore(opponentElo);
                            int newElo = ma.newPlayerRating(opponentElo, kScore, ma.percPlayerAWin(opponentElo, play.getElo()), 1);
                            int difference = Math.abs(opponentElo - newElo);
                            
                            System.out.println("New Elo of White: " + (play.getElo() - difference));
                            System.out.println("New Elo of Black: " + newElo);
                            
                            play.updateElo(newElo, nameBestMatch);
                            play.updateElo((play.getElo() - difference), play.getName()); 

                            play.addMatchStat(2);
                            opp.addMatchStat(1);
                            
                            System.out.println("White player "+"("+play.getName()+")"+" Amount of wins: "+play.getWins());
                            System.out.println("White player "+"("+play.getName()+")"+" Amount of draws: "+play.getDraws());
                            
                            p.remove();
                            
                        }
                        
                        else if (x1 == 3){
                            System.out.println("Draw");
                            
                            int playerWDrawVal = ma.drawValue(play.getElo(), ma.currentPercentage);
                            int difference = Math.abs(play.getElo() - playerWDrawVal);
        
                            System.out.println("Initial White Elo: " + play.getElo());
                            System.out.println("Initial Black Elo: " + opponentElo);
                            if(play.getElo() >= opponentElo){
                            
                                System.out.println("New Elo of White: " + (play.getElo() - difference));
                                System.out.println("New Elo of Black: "+ (opponentElo + difference));

                                play.updateElo((play.getElo() - difference), play.getName());
                                play.updateElo(opponentElo + difference, nameBestMatch);
                                
                            }
                            else{

                                System.out.println("New Elo of White: "+(play.getElo() + difference) );
                                System.out.println("New Elo of Black: "+ (opponentElo - difference));

                                play.updateElo((play.getElo() + difference), play.getName());
                                play.updateElo((opponentElo - difference), nameBestMatch);

                            }
                            play.addMatchStat(3);
                            opp.addMatchStat(3);
                            
                            System.out.println("White player "+"("+play.getName()+")"+" Amount of wins: "+play.getWins());
                            System.out.println("White player "+"("+play.getName()+")"+" Amount of draws: "+play.getDraws());
                            

                        }

                    }
                    
                    
                    
                }
                System.out.println("\n");
                System.out.println("----------------------------------------------------------------");
                System.out.println("This is the winner!: " + MatchReal.ALLPLAYERS.get(0));
                System.out.println("----------------------------------------------------------------");

                b = false;
                
                
                
            }

               
    

            }
        }
    }
    

