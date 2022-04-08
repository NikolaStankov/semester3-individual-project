package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.TeamsService;
import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NBATeamsService implements TeamsService {

    private final TeamsRepository teamsRepository;

    @Autowired
    public NBATeamsService(@Qualifier("nbaTeams") TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }

    @Override
    public List<TeamDTO> getAllTeams() {
        return this.teamsRepository.selectAllTeams();
    }

    @Override
    public TeamDTO getTeam(long teamId) {
        return this.teamsRepository.selectTeam(teamId);
    }

    @Override
    public boolean addTeam(TeamDTO team) {
        return this.teamsRepository.insertTeam(team);
    }

    @Override
    public boolean removeTeam(long teamId) {
        return false;
    }
}
