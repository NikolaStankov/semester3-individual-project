package fontys.sem3.individual_track.repository.impl;

import fontys.sem3.individual_track.model.Team;
import fontys.sem3.individual_track.repository.TeamsRepository;
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
        this.teamList.add(new Team(1, "LAL","Los Angeles",
                "Western","Pacific",
                "Los Angeles Lakers", "Lakers"));
        this.teamList.add(new Team(2, "LAC","Los Angeles",
                "Western", "Atlantic",
                "Los Angeles Clippers", "Clippers"));
        this.teamList.add(new Team(3, "MIH", "Miami",
                "Eastern", "Central",
                "Miami Heat", "Heat"));
        this.teamList.add(new Team(4, "CHB", "Chicago",
                "Eastern", "Southwest",
                "Chicago Bulls", "Bulls"));
    }

    @Override
    public List<Team> selectAllTeams() {
        return this.teamList;
    }

    @Override
    public Team selectTeam(long teamId) {
        for (Team team : this.teamList) {
            if (team.getId() == teamId)
                return team;
        }

        return null;
    }

    @Override
    public boolean insertTeam(Team team) {
        if (this.selectTeam(team.getId()) != null) {
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
