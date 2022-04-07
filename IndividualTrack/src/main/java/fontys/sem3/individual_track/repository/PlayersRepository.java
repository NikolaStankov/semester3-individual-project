package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.model.Player;

import java.util.List;

public interface PlayersRepository {
    List<Player> selectAllPlayers();

    Player selectPlayer(long playerId);

    boolean insertPlayer(Player player);

    boolean deletePlayer(long playerId);
}
