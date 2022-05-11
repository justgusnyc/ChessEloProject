package chess;

import java.util.List;



public interface IMatchReal {

public List<Integer> getPlayerStats(Players p);

public void addPlayerToTournament(Players p);

public Players getPlayerObject(String name);

public void deletePlayerFromAllPlayers(String name);

public void getPlayerStatus();

}
