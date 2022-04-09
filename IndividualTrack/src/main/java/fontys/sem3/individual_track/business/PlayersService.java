package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.PlayerDTO;
import fontys.sem3.individual_track.repository.entity.Player;

import java.util.List;
import java.util.Optional;

public interface PlayersService {
    List<PlayerDTO> getAllPlayers();

    Optional<PlayerDTO> getPlayer(long playerId);

    boolean addPlayer(Player player);

    boolean removePlayer(long playerId);
}
