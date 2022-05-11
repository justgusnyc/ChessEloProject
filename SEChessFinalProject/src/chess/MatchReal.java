package chess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class MatchReal implements IMatchReal{
    static final List<Players> ALLPLAYERS = new ArrayList<>();
    List<String> leftInTournament = new ArrayList<>();
    List<String> notInTournament = new ArrayList<>();


    /**

     * getPlayerStats
     * .
     * @param p - A player you would like to get the stats of.

     * @return Returns the stats of the player.
     * 

     */
	public List<Integer> getPlayerStats(Players p){
		return p.getCurrentPlayerStats(p.getName()); 
	}
	

    /**

     * addPlayerToTournament
     * .
     * @param p - Player you would like to add to the tournament

     * @return void - adds player.
     * 

     */
    public void addPlayerToTournament(Players p){
        ALLPLAYERS.add(p);
    }


    /**

     * getCurrentPlayerStats
     * .
     * @param name - The string name of the player you want to get player
     * object of

     * @return Returns the player object correlated to that name.
     * 

     */
    public Players getPlayerObject(String name){
        for(Iterator<Players> p = ALLPLAYERS.iterator(); p.hasNext(); ){
            Players player = p.next();
            if(player.getName() == name){
                return player;
            } 
        }
        return null;
    }
    

    /**

     * deletePlayerFromAllPlayers
     * .
     * @param name - The string name of the player you want to delete from
     * ALLPLAYERS

     * @return Returns the set of stats from a specific player.
     * 

     */
    public void deletePlayerFromAllPlayers(String name){
        for(Iterator<Players> p = ALLPLAYERS.iterator(); p.hasNext(); ){
            Players player = p.next();
            if(player.getName() == name){
                p.remove();
            } 
        }
    }

    /**

     * getPlayerStatus
     * .

     * @return void - prints who is still in the tournament now
     * and who is not.
     * 

     */
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

    /**

     * generatePlayers
     * .
     * @param n - The number of players you would like to generate.

     * @return void - generate those players and adds them to tournament.
     * 

     */
    public void generatePlayers(int n){

        for(int i = 0; i < n; i++){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
            
            int randomPlayerElo = ThreadLocalRandom.current().nextInt(800, 2800);
            String randomPlayer = "FakeGuy"+System.currentTimeMillis();
            Players p = new Players(randomPlayer, randomPlayerElo);
            this.addPlayerToTournament(p);
        }
    }

    



    
}
