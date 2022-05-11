package chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Players extends ACPlayer implements IACPlayer{
    private boolean inGame = true;
    private int elo;
    private String name;
    private List<Integer> stats = new ArrayList<>();
    private int wins;
 
    private int draws;
    
    static final Map<String, List<Integer>> STATS1 = new HashMap<>();

    

    Players(String name, int elo){
        super(name, elo);
        this.name = name;
        this.elo = elo;
        for(int i = 0; i < 3; i++){
            this.stats.add(0);
        }
        STATS1.put(this.name, this.stats);

        this.wins = 0;
      
        this.draws = 0;
    }

    @Override
    public String toString(){
        return this.name + " (Elo): " + this.elo;
    }

    /**

     * getPlayers

     * 
     * 
     * .
     * 

     * @return Returns the name and elo pair of every player.
     * 

     */

    public void getPlayers(){  
        for(Map.Entry<String, Integer> entry : ELONAMES.entrySet()){
            String n = entry.getKey();
            Integer el = entry.getValue();
            System.out.println("Name: "+n+ "\t\tValue: "+el);
        }
    }


    /**

     * getElo

     * .
     * 

     * @return Returns current players elo.
     * 

     */
    public int getElo(){
        return this.elo;
    }


    /**

     * getName

     * .
     * 

     * @return Returns current players name.
     * 

     */
    public String getName(){
        return this.name;
    }
    
    /**

     * viewStats
     * .
     * 

     * @return Shows the stats of a player.
     * 

     */
    public void viewStats(){
        for(Entry<String, List<Integer>> entry : STATS1.entrySet()){
            String n = entry.getKey();
            List<Integer> el = entry.getValue();
            System.out.println("Name: "+n+ "\tStats(Wins,Losses,Draws): "+el);
        }
    }

    /**

     * getCurrentPlayerStats
     * .
     * @param name - The string name of the player you want to get stats of

     * @return Returns the set of stats from a specific player.
     * 

     */
    public List<Integer> getCurrentPlayerStats(String name){
        return STATS1.get(name);
    }

    /**

     * getWins
     * .

     * @return Returns the amount of wins the player has.
     * 

     */
    public int getWins(){
        return this.wins;
    }
   

    /**

     * getDraws
     * .

     * @return Returns the number of draws the player has.
     * 

     */
    public int getDraws(){
        return this.draws;
    }
    

    /**

     * getCurrentPlayerStats
     * .
     * @param input - The value (1-2) that designates whether 
     * you would like to add to the win count or the draw count.

     * @return void.
     * 

     */
    public void addMatchStat(int input){
        List<Integer> s = Players.STATS1.get(this.name);
        s.toArray();
    	if(input == 1){ 
    		this.wins += 1;
    	}else if(input == 2){ 
    		this.draws += 1;
    	}
        else{
    		System.out.println("Invalid input");
    	}
    	
    }

    /**

     * remove
     * .

     * @return Removes player from ALLPLAYERS.
     * 

     */
    public void remove(){
        for(Players p:MatchReal.ALLPLAYERS){
            if(this.name == p.getName()){
                MatchReal.ALLPLAYERS.remove(p);   
            }
        }
    }


    /**

     * updateElo
     * .
     * @param name - The elo you would like to assign to the player.
     * @param name - The string name of the player you want to update the elo of

     * @return void - updates the elos
     * 

     */
    public void updateElo(int elo, String name){
        if(name == this.name){
            this.elo = elo;
        }
        ELONAMES.replace(name, elo);
    }

    /**

     * setNotInGame
     * .

     * @return Sets the player to not be in the tournament.
     * 

     */
	public void setNotInGame(){
		this.inGame = false;
	}


    /**

     * isInGame
     * .

     * @return Returns whether the player is in game or not.
     * 

     */
	public boolean isInGame(){
		return this.inGame;
	}




    /**

     * Bracket enum
     * .
     * @param value - Elo of player you'd like to place

     * @return Returns the bracket that player is in.
     * 

     */
    public enum Bracket {
        
        BEGINNER(800),
        CLUB_PLAYER(1400),
        CLASS_PLAYER(2000),
        EXPERT(2200),
        MASTER(2400),
        TITLED(2800);
    
        private final int value;
    
        private Bracket(final int value) {
            this.value = value;
        }
    
        public final int getValue() {
            return value;
        }
    
        /**
           we are taking the middle as the root of what we are looking for and 
           checking if there is a different value to our left and right
         * @param v
         *        the value we're looking for
         * @return Bracket
         */

         
        
        public static Bracket lookup(final int v) {
            final Bracket[] a = Bracket.values();
            int min = 0;
            int max = a.length  - 1;
            int i;
            do {
                i = (min + max) / 2;
                final int av = a[i].value;
                if (v < av) {
                    max = i;
                } else if (v > av) {
                    if (i + 1 < a.length && v < a[i + 1].value) {
                        break;
                    }
                    min = i + 1;
                }
            } while (v != a[i].value && min < max);
            if (min == max) {
                return a[max];
            }
            return a[i];
        }

    }


    
}
