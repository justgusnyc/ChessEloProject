package ChessEloProject.chess;

import java.util.HashMap;
import java.util.Map;


public class Players {
    private int elo;
    private String name;
    Map<String, Integer> eloNames = new HashMap<>();

    Players(int elo, String name){
        if(name == null){
            throw new IllegalArgumentException("Player must have a name");
        }

        this.elo = elo;
        this.name = name;

        eloNames.put(this.name, this.elo);
    }

    public void getNames(){
        for(Map.Entry<String, Integer> entry : eloNames.entrySet()){
            String n = entry.getKey();
            Integer el = entry.getValue();
            System.out.println("Name: "+n+ "\tValue: "+el);
        }
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
