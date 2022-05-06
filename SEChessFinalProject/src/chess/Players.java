package chess;

// import java.security.KeyStore.Entry;
// import java.util.HashMap;
import java.util.Map;


public class Players extends ACPlayer{
    private int elo;
    private String name;
    private int[] stats;
    private PlayerStatus playerStatus;
    //Map<String, Integer> ELONAMES = new HashMap<>();

    Players(String name, int elo){
        super(name, elo);
        this.name = name;
        this.elo = elo;
        this.stats = new int[3];
        this.playerStatus = new PlayerStatus();
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
    
    public int[] getStats(){
    	return this.stats;
    }
    
    public PlayerStatus getStatus(){
    	return this.playerStatus;
    }

    
    
    
    public void addMatchStat(int input){
    	if(input == 1){
    		this.stats[0]++;
    	}else if(input == 2){
    		this.stats[1]++;
    	}else if(input == 3){
    		this.stats[2]++;
    	}else{
    		System.out.println("Bruh");
    	}
    	
    }


    public void updateElo(int elo, String name){
        if(name == this.name){
            this.elo = elo;
        }
        ELONAMES.replace(name, elo);
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
