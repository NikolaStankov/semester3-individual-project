package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.model.PlayerDTO;

import java.util.List;

public interface PlayersRepository {
    List<PlayerDTO> selectAllPlayers();

    PlayerDTO selectPlayer(long playerId);

    boolean insertPlayer(PlayerDTO player);

    boolean deletePlayer(long plaayerId);
}
