package chess;

import chess.Players.Bracket;

public class TestProgram{
    
    public static void main(String[] args) {
        Players G = new Players("Gus", 1000);
        Players C = new Players("Cesar", 1800);
    
        G.getPlayers();
        System.out.println("Buffr");
        C.getPlayers();

        

        Bracket beg = Bracket.BEGINNER;

        System.out.println(beg);
        System.out.println(beg.getValue());
        
    }
}
