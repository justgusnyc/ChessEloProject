package chess;

import chess.Players.Bracket;

public class TestProgram{
    
    public static void main(String[] args) {
        MatchingAlgo MA = new MatchingAlgo();
        PlayGame play = new PlayGame();
        MatchReal match = new MatchReal();
        Players G = new Players("Gus", 1000);
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
    
        G.getPlayers();
        System.out.println("Buffr");
        C.getPlayers();

        

        Bracket beg = Bracket.BEGINNER;

        System.out.println(beg);
        System.out.println(beg.getValue());
        System.out.println(Bracket.lookup(G.getElo()));
        System.out.println(Bracket.lookup(C.getElo()));
        System.out.println("Optimal First Match for Gus player: "+ MA.optimalMatch(G));
        play.beginTournament();
        V.getPlayers();

        


        
    }
}
