package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.TeamsService;
import fontys.sem3.individual_track.business.converter.TeamDTOConverter;
import fontys.sem3.individual_track.model.CreateTeamRequestDTO;
import fontys.sem3.individual_track.model.CreateTeamResponseDTO;
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

    @Override
    public CreateTeamResponseDTO createTeam(CreateTeamRequestDTO teamRequest) {

        Team teamToSave = Team.builder()
                .abbreviation(teamRequest.getAbbreviation())
                .city(teamRequest.getCity())
                .conference(teamRequest.getConference())
                .division(teamRequest.getDivision())
                .fullName(teamRequest.getFullName())
                .name(teamRequest.getName())
                .build();

        Team savedTeam = this.teamsRepository.save(teamToSave);

        return CreateTeamResponseDTO.builder()
                .teamId(savedTeam.getId())
                .build();
    }

    @Override
    public TeamDTO getTeamByFullName(String teamFullName) {
        Team team = this.teamsRepository.getTeamByFullName(teamFullName);

        return TeamDTO.builder()
                .id(team.getId())
                .abbreviation(team.getAbbreviation())
                .city(team.getCity())
                .conference(team.getConference())
                .division(team.getDivision())
                .fullName(team.getFullName())
                .name(team.getName())
                .build();
    }
}
