package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.PlayersService;
import fontys.sem3.individual_track.model.Player;
import fontys.sem3.individual_track.repository.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NBAPlayersService implements PlayersService {

    private final PlayersRepository playersRepository;

    @Autowired
    public NBAPlayersService(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    @Override
    public List<Player> getAllPlayers() {
        return this.playersRepository.selectAllPlayers();
    }

    @Override
    public Player getPlayer(long playerId) {
        return this.playersRepository.selectPlayer(playerId);
    }

    @Override
    public boolean addPlayer(Player player) {
        return this.playersRepository.insertPlayer(player);
    }

    @Override
    public boolean removePlayer(long playerId) {
        return this.playersRepository.deletePlayer(playerId);
    }
}
