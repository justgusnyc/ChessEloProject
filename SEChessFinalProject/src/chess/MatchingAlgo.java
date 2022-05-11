package chess;
import java.util.concurrent.ThreadLocalRandom;

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




public class MatchingAlgo implements IMatchingAlgo{
    public float currentPercentage;


    MatchingAlgo() {
        
    }


    public float percPlayerAWin(int ratingPlayerA, int ratingPlayerB){
        float difference = Math.abs(ratingPlayerB - ratingPlayerA);
        float power = difference/400;
        this.currentPercentage = (float) (1 / (1+(Math.pow(10,power))));
        return (this.currentPercentage);
    }

    public int newPlayerRating(int currentElo, int kValue, float expectedWinPercentage, float actualWinPercentage){
        int newRating = currentElo + (int)(kValue * (actualWinPercentage - expectedWinPercentage));
        return newRating;
    }

    public int drawValue(int currentElo, float expectedWinPercentage){
        int drawRating = currentElo + (int)(5 * (.05 - expectedWinPercentage));
        return drawRating;
    }
    
    

    public int getKScore(int currentElo){
        if(currentElo >= 2400){
            return 10;
        }
        return 20;
    }

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
