package chess;
import java.util.concurrent.ThreadLocalRandom;



/**

     * MATH FOR MATCHING ALGORITHM
     * .win = 1, lose = 0, draw = .5, if someone is slightly below you, maybe it would be .63 or something
        P(A wins) = 1 / (1+10^((RatingB - RatingA)/400))

        New Rating = rating + kValue(score - expected score)
        New Rating example:
        Player A = 1656
        Player B = 1763
        (New Rating Player A) -> 1677 = 1656 + kValue(1 - 0.35)
        the actual score was 1 because they won, and the expected score was .35, since
        the other player was slightly higher rated

     *
     * 

     */






public class MatchingAlgo implements IMatchingAlgo{
    public float currentPercentage;


    MatchingAlgo() {
        
    }


    /**

     * percPlayerAWin

     * @param ratingPlayerA - Rating of player A (white).
     * 
     * 
     * @param ratingPlayerB - Rating of player B (black)
     * .
     * 

     * @return The percentage of likeliness that player A wins.
     * 

     */
    public float percPlayerAWin(int ratingPlayerA, int ratingPlayerB){
        float difference = Math.abs(ratingPlayerB - ratingPlayerA);
        float power = difference/400;
        this.currentPercentage = (float) (1 / (1+(Math.pow(10,power))));
        return (this.currentPercentage);
    }


    /**
        newPlayerRating
     * 

     * @param currentElo - Current elo of player.
     * @param kValue - The factor by which we multiple our percentage in a way.
     * @param expectedWinPercentage - Percentage likeliness to win estimated.
     * @param actualWinPercentage - Whether you actually won (0 - lost, 1 - won, .5 - draw)
     * .
     * 

     * @return New rating of player.
     * 

     */

    public int newPlayerRating(int currentElo, int kValue, float expectedWinPercentage, float actualWinPercentage){
        int newRating = currentElo + (int)(kValue * (actualWinPercentage - expectedWinPercentage));
        return newRating;
    }

    /**

     * drawValue

     * @param currentElo - Current elo of player.
     * 
     * 
     * @param expectedWinPercentage - Expected percentage to win against opponent.
     * .
     * 

     * @return What your rating will be for a draw.
     * 

     */

    public int drawValue(int currentElo, float expectedWinPercentage){
        int drawRating = currentElo + (int)(5 * (.05 - expectedWinPercentage));
        return drawRating;
    }
    
    
    /**

     * getKScore

     * @param currentElo - Current elo of player.
     * 
     * 
     * .
     * 

     * @return What the factor for rating change will be.
     * 

     */

    public int getKScore(int currentElo){
        if(currentElo >= 2400){
            return 10;
        }
        return 20;
    }


    /**

     * optimalMatch

     * @param playerA - Player you would like to match.
     * 
     * 
     * .
     * 

     * @return The name of your best matched opponent.
     * 

     */

    public String optimalMatch(Players playerA){

        for(Players p : MatchReal.ALLPLAYERS){
            
            if(p.isInGame() && p != playerA){
                float currentPercentage = this.percPlayerAWin(playerA.getElo(), p.getElo());
                System.out.println("Matching Process... ");
                System.out.println("Player White (Current) Elo: " + playerA.getElo());
                System.out.println("Possible Match Elo: " + p.getElo());
                System.out.println("Current Percentage Chance of Player White to Win: " + currentPercentage);
                
                if(currentPercentage == 0.5){
                    return p.getName();
                }
                else if(currentPercentage <= 0.63 && currentPercentage >= 0.38){
                    return p.getName();
                }
            }
            
        }
        System.out.println("\n");
        int randomPlayer = ThreadLocalRandom.current().nextInt(0, MatchReal.ALLPLAYERS.size());
        return MatchReal.ALLPLAYERS.get(randomPlayer).getName();
        
        

    }



    
}
