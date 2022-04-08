package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.TeamDTO;

import java.util.List;

public interface TeamsService {
    List<TeamDTO> getAllTeams();

    TeamDTO getTeam(long teamId);

    boolean addTeam(TeamDTO team);

    boolean removeTeam(long teamId);
}
