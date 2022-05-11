package chess;


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
   
        match.addPlayerToTournament(G);
        match.addPlayerToTournament(C);
        match.addPlayerToTournament(V);
        match.addPlayerToTournament(N);
        match.addPlayerToTournament(A);
        match.addPlayerToTournament(D);
        match.addPlayerToTournament(J);


        System.out.println("All Players and their Elo's before the tournament: ");
        G.getPlayers();
        
        // System.out.println(Bracket.lookup(C.getElo()));
        
        play.beginTournament();
        
        System.out.println("\n");
        System.out.println("Players and their Elo's after the tournament: ");
        V.getPlayers();

        
      

        


        
    }
}
