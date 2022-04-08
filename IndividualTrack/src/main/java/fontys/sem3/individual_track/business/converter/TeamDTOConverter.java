package fontys.sem3.individual_track.business.converter;

import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.repository.entity.Team;

public class TeamDTOConverter {
    private TeamDTOConverter() {
    }

    public static TeamDTO convertToDTO(Team team) {
        return TeamDTO.builder()
                .id(team.getId())
                .abbreviation(team.getAbbreviation())
                .city(team.getCity())
                .conference(team.getConference())
                .division(team.getDivision())
                .fullName(team.getFullName())
                .name(team.getName())
                .build();
    }
}
