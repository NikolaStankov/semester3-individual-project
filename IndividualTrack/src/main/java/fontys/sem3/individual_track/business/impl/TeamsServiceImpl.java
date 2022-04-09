package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.TeamsService;
import fontys.sem3.individual_track.business.converter.TeamDTOConverter;
import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.repository.TeamsRepository;
import fontys.sem3.individual_track.repository.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class TeamsServiceImpl implements TeamsService {

    private final TeamsRepository teamsRepository;

    @Override
    public List<TeamDTO> getAllTeams() {
        List<Team> teamList = this.teamsRepository.findAll();
        List<TeamDTO> teamDTOList = new ArrayList<>();

        for (Team team : teamList) {
            teamDTOList.add(TeamDTOConverter.convertToDTO(team));
        }

        return teamDTOList;
    }

    @Override
    public Optional<TeamDTO> getTeam(long teamId) {
        return this.teamsRepository.findById(teamId).
                map(TeamDTOConverter::convertToDTO);
    }
}
