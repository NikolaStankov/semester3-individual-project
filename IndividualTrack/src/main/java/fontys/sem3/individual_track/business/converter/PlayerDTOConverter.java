package fontys.sem3.individual_track.business.converter;

import fontys.sem3.individual_track.model.PlayerDTO;
import fontys.sem3.individual_track.repository.entity.Player;

public class PlayerDTOConverter {
    private PlayerDTOConverter() {
    }

    public static PlayerDTO convertToDTO(Player player) {
        return PlayerDTO.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .position(player.getPosition())
                .team(TeamDTOConverter.convertToDTO(player.getTeam()))
                .build();
    }
}
