package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.PlayersService;
import fontys.sem3.individual_track.business.converter.PlayerDTOConverter;
import fontys.sem3.individual_track.business.validator.TeamIdValidator;
import fontys.sem3.individual_track.model.CreatePlayerRequestDTO;
import fontys.sem3.individual_track.model.CreatePlayerResponseDTO;
import fontys.sem3.individual_track.model.PlayerDTO;
import fontys.sem3.individual_track.repository.PlayersRepository;
import fontys.sem3.individual_track.repository.entity.Player;
import fontys.sem3.individual_track.repository.entity.Team;
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
    private final TeamIdValidator teamIdValidator;

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
    public CreatePlayerResponseDTO createPlayer(CreatePlayerRequestDTO playerRequest) {
        this.teamIdValidator.validateTeamId(playerRequest.getTeamId());

        Player playerToSave = Player.builder()
                .firstName(playerRequest.getFirstName())
                .lastName(playerRequest.getLastName())
                .position(playerRequest.getPosition())
                .team(Team.builder().id(playerRequest.getTeamId()).build())
                .build();

        Player savedPlayer = this.playersRepository.save(playerToSave);

        return CreatePlayerResponseDTO.builder()
                .playerId(savedPlayer.getId())
                .build();
    }

    @Override
    public boolean removePlayer(long playerId) {
        return false;
    }
}
