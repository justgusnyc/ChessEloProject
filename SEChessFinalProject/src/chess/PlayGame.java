package chess;

import java.util.Scanner;

public class PlayGame {

    PlayGame(){

    }

    public void beginGame(Players p){
        try (Scanner s = new Scanner(System.in)) {
            boolean b = true;

            while(b){
                System.out.println("Please select one of the options below: ");
                System.out.println("Enter 1 to match with a new opponent: ");
                System.out.println("Enter 2 to check your current stats: ");
                System.out.println("Enter 3 to see all players currently in the tournament: ");
                System.out.println("Enter 4 to quit game: ");
                int x = s.nextInt();
                if(x == 1){
                    MatchingAlgo ma = new MatchingAlgo();
                    String nameBestMatch = ma.optimalMatch(p);
                    System.out.println("Current Best Match: "+nameBestMatch); 
                    // should we make it randomized as to who wins or loses?
                    // or should we allow the user to decide? Currently the user will decide
                    System.out.println("Press 1 if you won the match: "); 
                    System.out.println("Press 2 if you lost the match: "); 
                    System.out.println("Press 3 if it was a draw: ");
                    int x1 = s.nextInt();
                    if(x1 == 1){
                        int kScore = ma.getKScore(p.getElo());
                        int opponentElo = ACPlayer.ELONAMES.get(nameBestMatch);
                        int newElo = ma.newPlayerRating(p.getElo(), kScore, ma.currentPercentage, 1);
                        System.out.println("The new elo: "+newElo);
                        p.updateElo(opponentElo - Math.abs(newElo - p.getElo()), nameBestMatch);
                        p.updateElo(newElo, p.getName());
                    } 

                    

                    
                }
                else if(x == 2){
                    
                }
                else if(x == 3){
                    
                }
                else{
                    System.out.println("Quitting the game now... ");
                    b = false;
                }

            }
        }
    }
    
}
