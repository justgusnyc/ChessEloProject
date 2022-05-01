package chess;

import java.util.Map;

import chess.Players.Bracket;

public class MatchingAlgo extends ACPlayer{

    MatchingAlgo(String name, int elo, Bracket b) {
        super(name, elo);
        
    }

    @Override
    void getPlayers() {
        for(Map.Entry<String, Integer> entry : ELONAMES.entrySet()){
            String n = entry.getKey();
            Integer el = entry.getValue();
            System.out.println("Name: "+n+ "\tValue: "+el);
        }
        
    }




    // win = 1, lose = 0, draw = .5, if someone is slightly below you, maybe it would be .63 or something
    // P(A wins) = 1 / (1+10^((RatingB - RatingA)/400))

    // New Rating = rating + 32(score - expected score)
    // New Rating example:
    // Player A = 1656
    // Player B = 1763
    // (New Rating Player A) -> 1677 = 1656 + 32(1 - 0.35)
    // the actual score was 1 because they won, and the expected score was .35, since
    // the other player was slightly higher rated

    // In reality, we would take the change in rating that the player was going to experience
    // in its raw form, and then using "k" values, we would skew the result in a certain way depending on
    // this is a different way of doing it where it would not be a fixed value of 32, but a range of values
    // for "K" instead
    // Ex: USCF official K value for a 2100 rated player would be 21 instead, because at such
    // a high rating, because intense volatility is not fair as it is much harder to gain points
    // at a higher level than it is at a lower level iuiuh


    public float percPlayerAWin(int ratingPlayerA, int ratingPlayerB, int kScoreValue){
        float percPlayerAWins = 1 / (1+10^((ratingPlayerB - ratingPlayerA)/400));
        return percPlayerAWins;
    }
    
    public int newRatingPlayerA(int currentElo, float expectedWinPercentage, float actualWinResult, int kScoreValue){
        int newRating = currentElo + kScoreValue * (int)(actualWinResult - expectedWinPercentage);
        return newRating;
    }

    
}
