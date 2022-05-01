package chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;



public class MatchingAlgo{



    MatchingAlgo() {
        
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


    public float percPlayerAWin(int ratingPlayerA, int ratingPlayerB){
        float difference = Math.abs(ratingPlayerB - ratingPlayerA);
        float power = difference/400;
        float percPlayerAWins = (float) (1 / (1+(Math.pow(10,-power))));
        return percPlayerAWins;
    }

    public int newPlayerRating(int currentElo, int kValue, float expectedWinPercentage, float actualWinPercentage){
        int newRating = currentElo + kValue * (int)(actualWinPercentage - expectedWinPercentage);
        return newRating;
    }
    
    

    public int getKScore(int currentElo){
        if(currentElo >= 2400){
            return 20;
        }
        return 30;
    }

    // not sure if I should return type string below and just return the name of the first best match
    // or if i should return type Players and return an entire Player for the best match.
    // Either way I would ideally like to have a list of "best matches" sorted for the person we are currently 
    // searching for, so that if the first match does not work out it would just go to the next match and so on
    // the issue with this is that then I may need to change the datastructure of scores below to a hashmap or 
    // figure out a way to store a list inside of a list or something like that

    public String optimalMatch(Players playerA){
        List<Float> scores = new ArrayList<>(); 
        for(Map.Entry<String, Integer> entry : ACPlayer.ELONAMES.entrySet()){

            if(entry.getKey() == playerA.getName()){
                continue;
            }
            else{
                System.out.println("PlayerA Elo: "+playerA.getElo());
                System.out.println("Entry Value: "+entry.getValue());
                float currentPercentage = this.percPlayerAWin(playerA.getElo(), entry.getValue());
                System.out.println("Current Percentage: "+currentPercentage);
                scores.add(currentPercentage);
                if(currentPercentage == 0.5){
                    return entry.getKey();
                }
                else if(currentPercentage <= 0.63 && currentPercentage >= 0.38){
                    return entry.getKey();
                }
                
            }
        }
        Collections.sort(scores);
        return "Error: No Match Found";
        
        

    }



    
}
