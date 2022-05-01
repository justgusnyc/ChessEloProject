package chess;

import java.util.HashMap;
import java.util.Map;

public abstract class ACPlayer {
    private int elo;
    private String name;
    static final Map<String, Integer> ELONAMES = new HashMap<>();

    ACPlayer(String name, int elo){
        if(name == null){
            throw new IllegalArgumentException("Player must have a name");
        }

        this.elo = elo;
        this.name = name;

        ELONAMES.put(this.name, this.elo);
    }

    abstract void getPlayers();
    
}
