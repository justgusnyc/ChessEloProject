package chess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MatchReal implements IMatchReal{
    static final List<Players> ALLPLAYERS = new ArrayList<>();
    List<String> leftInTournament = new ArrayList<>();
    List<String> notInTournament = new ArrayList<>();


	public List<Integer> getPlayerStats(Players p){
		return p.getCurrentPlayerStats(p); 
	}
	
    public void addPlayerToTournament(Players p){
        ALLPLAYERS.add(p);
    }

    public Players getPlayerObject(String name){
        for(Iterator<Players> p = ALLPLAYERS.iterator(); p.hasNext(); ){
            Players player = p.next();
            if(player.getName() == name){
                return player;
            } 
        }
        return null;
    }
    

    public void deletePlayerFromAllPlayers(String name){
        for(Iterator<Players> p = ALLPLAYERS.iterator(); p.hasNext(); ){
            Players player = p.next();
            if(player.getName() == name){
                p.remove();
            } 
        }
    }

	public void getPlayerStatus(){
        
        for(Players i : ALLPLAYERS){
            if(i.isInGame()){
                leftInTournament.add(i.getName());
            }
            else{
                notInTournament.add(i.getName());
            }

        }

        for(String k : notInTournament){
            System.out.println("Knocked out of the tournament:"+"\t"+k);
        }
        for(String j : leftInTournament){
            System.out.println("Still in the tournament:"+"\t"+j);
        }

        
    }



    
}
