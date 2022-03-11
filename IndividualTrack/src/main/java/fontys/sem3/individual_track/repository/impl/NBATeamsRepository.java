package fontys.sem3.individual_track.repository.impl;

import fontys.sem3.individual_track.model.Team;
import fontys.sem3.individual_track.model.Ticket;
import fontys.sem3.individual_track.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("nbaTeams")
@Primary
public class NBATeamsRepository implements TeamsRepository {

    private final List<Team> teamList;

    public NBATeamsRepository() {
        this.teamList = new ArrayList<>();
        this.teamList.add(new Team(1, "LA Lakers", "Western"));
        this.teamList.add(new Team(2, "LA Clippers", "Western"));
        this.teamList.add(new Team(3, "Miami Heat", "Eastern"));
        this.teamList.add(new Team(4, "Chicago Bulls", "Eastern"));
    }

    @Override
    public List<Team> selectAllTeams() {
        return this.teamList;
    }

    @Override
    public Team selectTeam(long teamId) {
        for (Team team : this.teamList) {
            if (team.getTeamId() == teamId)
                return team;
        }

        return null;
    }

    @Override
    public boolean insertTeam(Team team) {
        if (this.selectTeam(team.getTeamId()) != null) {
            return false;
        }

        this.teamList.add(team);
        return true;
    }

    @Override
    public boolean deleteTeam(long teamId) {
        return false;
    }
}
