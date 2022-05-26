package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.CreateTeamRequestDTO;
import fontys.sem3.individual_track.model.CreateTeamResponseDTO;
import fontys.sem3.individual_track.model.TeamDTO;

import java.util.List;
import java.util.Optional;

public interface TeamsService {
    List<TeamDTO> getAllTeams();

    Optional<TeamDTO> getTeam(long teamId);

    CreateTeamResponseDTO createTeam(CreateTeamRequestDTO teamRequest);

    void removeTeam(long teamId);

    TeamDTO getTeamByFullName(String teamFullName);
}
