package chess;

public interface IMatchingAlgo {


    public float percPlayerAWin(int ratingPlayerA, int ratingPlayerB);
    
    public int newPlayerRating(int currentElo, int kValue, float expectedWinPercentage, float actualWinPercentage);
    
    public int drawValue(int currentElo, float expectedWinPercentage);
    
    public int getKScore(int currentElo);
    
    public String optimalMatch(Players playerA);
    
    }
