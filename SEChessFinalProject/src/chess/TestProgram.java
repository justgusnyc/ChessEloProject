package chess;

import chess.Players.Bracket;
import java.util.ArrayList;

public class TestProgram{
    
    public static void main(String[] args) {
        MatchingAlgo MA = new MatchingAlgo();
        ArrayList<Players> players = new ArrayList<Players>();
        
        Players G = new Players("Gus", 1000);
        Players C = new Players("Cesar", 1800);
        Players V = new Players("Vish", 800);
        Players N = new Players("Noah", 1000);
        Players A = new Players("Arthur", 950);
        Players R = new Players("Renzo", 920);
        
        players.add(G);
        players.add(C);
        players.add(V);
        players.add(N);
        players.add(A);
        players.add(R);
        
        PlayGame play = new PlayGame(players);
        
        G.getPlayers();
        System.out.println("Buffr");
        C.getPlayers();

        

        Bracket beg = Bracket.BEGINNER;

        System.out.println(beg);
        System.out.println(beg.getValue());
        System.out.println(Bracket.lookup(G.getElo()));
        System.out.println(Bracket.lookup(C.getElo()));
//        System.out.println("Optimal First Match for Gus player: "+ MA.optimalMatch(G));
        play.beginGame();
        V.getPlayers();

        


        
    }
}
