package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.Team;

import java.util.List;

public interface TeamsService {
    List<Team> getAllTeams();
    Team getTeam(long teamId);
    boolean addTeam(Team team);
    boolean removeTeam(long teamId);
}
