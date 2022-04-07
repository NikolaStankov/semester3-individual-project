package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.model.Team;

import java.util.List;

public interface TeamsRepository {
    List<Team> selectAllTeams();

    Team selectTeam(long teamId);

    boolean insertTeam(Team team);

    boolean deleteTeam(long teamId);
}
