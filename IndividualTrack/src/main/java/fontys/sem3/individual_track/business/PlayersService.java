package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.Player;

import java.util.List;

public interface PlayersService {
    List<Player> getAllPlayers();

    Player getPlayer(long playerId);

    boolean addPlayer(Player player);

    boolean removePlayer(long playerId);
}
