package chess;

import java.util.List;



public interface IPlayers {

public String toString();

public void getPlayers();

public int getElo();

public String getName();

public void viewStats();

public List<Integer> getCurrentPlayerStats(Players p);

public void addMatchStat(int input);

public void remove();

public void updateElo(int elo, String name);

public void setNotInGame();

public boolean isInGame();

}
