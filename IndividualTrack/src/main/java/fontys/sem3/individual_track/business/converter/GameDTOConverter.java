package fontys.sem3.individual_track.business.converter;

import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.repository.entity.Game;

public class GameDTOConverter {
    private GameDTOConverter() {
    }

    public static GameDTO convertToDTO(Game game) {
        return GameDTO.builder()
                .id(game.getId())
                .date(game.getDate())
                .season(game.getSeason())
                .homeTeam(TeamDTOConverter.convertToDTO(game.getHomeTeam()))
                .visitorTeam(TeamDTOConverter.convertToDTO(game.getVisitorTeam()))
                .build();
    }
}
