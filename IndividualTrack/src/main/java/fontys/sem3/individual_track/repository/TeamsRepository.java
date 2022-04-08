package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.model.TeamDTO;

import java.util.List;

public interface TeamsRepository {
    List<TeamDTO> selectAllTeams();

    TeamDTO selectTeam(long teamId);

    boolean insertTeam(TeamDTO team);

    boolean deleteTeam(long teamId);
}
