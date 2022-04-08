package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.PlayerDTO;

import java.util.List;

public interface PlayersService {
    List<PlayerDTO> getAllPlayers();

    PlayerDTO getPlayer(long playerId);

    boolean addPlayer(PlayerDTO player);

    boolean removePlayer(long playerId);
}
