package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.repository.entity.Team;

import java.util.List;
import java.util.Optional;

public interface TeamsService {
    List<TeamDTO> getAllTeams();

    Optional<TeamDTO> getTeam(long teamId);
}
