package chess;

import java.util.ArrayList;
// import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Players extends ACPlayer{
    private boolean inGame;
    private int elo;
    private String name;
    private List<Integer> stats = new ArrayList<>();
    
    static final Map<String, List<Integer>> STATS1 = new HashMap<>();

    Players(String name, int elo){
        super(name, elo);
        this.name = name;
        this.elo = elo;
        this.stats = stats;
        for(int i = 0; i < 3; i++){
            this.stats.add(0);
        }
        STATS1.put(this.name, this.stats);
    }

    public void getPlayers(){  //Maybe this funtion needs to be somewhere else?
        for(Map.Entry<String, Integer> entry : ELONAMES.entrySet()){
            String n = entry.getKey();
            Integer el = entry.getValue();
            System.out.println("Name: "+n+ "\tValue: "+el);
        }
    }

    public int getElo(){
        return this.elo;
    }

    public String getName(){
        return this.name;
    }
    
    public void viewStats(){
        for(Entry<String, List<Integer>> entry : STATS1.entrySet()){
            String n = entry.getKey();
            List<Integer> el = entry.getValue();
            System.out.println("Name: "+n+ "\tStats(Wins,Losses,Draws): "+el);
        }
    }

    public List<Integer> getCurrentPlayerStats(Players p){
        return STATS1.get(p.getName());
    }
    
    public void addMatchStat(int input){
        List<Integer> s = this.STATS1.get(this.name);
    	if(input == 1){ // for win?
    		int v = s.get(0);
            s.add(0, v+1);
    	}else if(input == 2){ // for loss?
    		int v = s.get(1);
            s.add(1, v+1);
    	}else if(input == 3){ // for draw?
    		int v = s.get(2);
            s.add(2, v+1);
    	}else{
    		System.out.println("Bruh"); // lmao
    	}
    	
    }



    public void updateElo(int elo, String name){
        if(name == this.name){
            this.elo = elo;
        }
        ELONAMES.replace(name, elo);
    }

	public void setInGame(){
		this.inGame = true;
	}

	public void setNotInGame(){
		this.inGame = false;
	}

	public boolean isInGame(){
		return this.inGame;
	}









    
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
         * @param v
         *        the value we're looking for
         * @return Bracket
         */

         
         // we are taking the middle as the root of what we are looking for and checking if there is a different value to our left and right
        
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
