package chess;

import chess.Players.Bracket;

public class TestProgram{
    
    public static void main(String[] args) {
        Players p = new Players(1000, "Gus");
    
        p.getNames();

        Bracket beg = Bracket.BEGINNER;

        System.out.println(beg);
        System.out.println(beg.getValue());
    }
}
