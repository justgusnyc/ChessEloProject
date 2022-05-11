package chess;

import java.util.Scanner;

public class TestProgram{
    
    public static void main(String[] args) {
        PlayGame play = new PlayGame();
        MatchReal match = new MatchReal();
        Players G = new Players("Gus", 1000);
        Players J = new Players("Jerry", 1850);
        Players C = new Players("Cesar", 1800);
        Players V = new Players("Vish", 800);
        Players N = new Players("Noah", 1000);
        Players A = new Players("Arthur", 950);
        Players D = new Players("David", 2000);
        Players JO = new Players("Jorge", 2800);
   
        match.addPlayerToTournament(G);
        match.addPlayerToTournament(C);
        match.addPlayerToTournament(V);
        match.addPlayerToTournament(N);
        match.addPlayerToTournament(A);
        match.addPlayerToTournament(D);
        match.addPlayerToTournament(J);
        match.addPlayerToTournament(JO);
        



       
            try (Scanner s = new Scanner(System.in)) {
                
                System.out.println("Press 1 to begin tournament: ");
                System.out.println("Press 2 to view all players and their Elo's: ");
                System.out.println("Press 3 to exit: ");
                System.out.print("Input: ");
                int n = s.nextInt();
                if(n == 1){
                    System.out.println("How many players would you like to generate? ");
                    System.out.print("Number of bots: ");
                    int x = s.nextInt();
                    match.generatePlayers(x);
                    play.beginTournament();
                }
                if(n == 2){
                    System.out.println("All Players and their Elo's: ");
                    G.getPlayers();

                }
                else{
                    System.out.println("Quitting...");
                }
            
            }
            
            // System.out.println(Bracket.lookup(C.getElo()));
    
    

        }
        
        
      

        


        
    }

