package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.PlayersService;
import fontys.sem3.individual_track.business.converter.PlayerDTOConverter;
import fontys.sem3.individual_track.model.PlayerDTO;
import fontys.sem3.individual_track.repository.PlayersRepository;
import fontys.sem3.individual_track.repository.entity.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class PlayersServiceImpl implements PlayersService {

    private final PlayersRepository playersRepository;

    @Override
    public List<PlayerDTO> getAllPlayers() {
        List<Player> playerList = this.playersRepository.findAll();
        List<PlayerDTO> playerDTOList = new ArrayList<>();

        for (Player player : playerList) {
            playerDTOList.add(PlayerDTOConverter.convertToDTO(player));
        }

        return playerDTOList;
    }

    @Override
    public Optional<PlayerDTO> getPlayer(long playerId) {
        return this.playersRepository.findById(playerId)
                .map(PlayerDTOConverter::convertToDTO);
    }

    @Override
    public boolean addPlayer(Player player) {
        return false;
    }

    @Override
    public boolean removePlayer(long playerId) {
        return false;
    }
}
