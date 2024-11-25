package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.PlayersService;
import fontys.sem3.individual_track.business.TeamsService;
import fontys.sem3.individual_track.model.LiveSimulationDTO;
import fontys.sem3.individual_track.model.LiveSimulationResponseDTO;
import fontys.sem3.individual_track.model.PlayerDTO;
import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.repository.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class LiveSimulationController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final TeamsService teamsService;
    private final PlayersService playersService;

    private final Random rand = new SecureRandom();

    @MessageMapping("/simulation")
    public void scoreBoard(@Payload LiveSimulationDTO liveSimulationDTO) throws InterruptedException {
        Thread.sleep(1000);
        int team1Score = 0;
        int team2Score = 0;
        TeamDTO team1DTO = this.teamsService.getTeamByFullName(liveSimulationDTO.getTeam1());
        TeamDTO team2DTO = this.teamsService.getTeamByFullName(liveSimulationDTO.getTeam2());

        LiveSimulationResponseDTO responseDTO = new LiveSimulationResponseDTO();

        Team team1 = Team.builder()
                .id(team1DTO.getId())
                .abbreviation(team1DTO.getAbbreviation())
                .city(team1DTO.getCity())
                .conference(team1DTO.getConference())
                .division(team1DTO.getDivision())
                .fullName(team1DTO.getFullName())
                .name(team1DTO.getName())
                .build();
        Team team2 = Team.builder()
                .id(team2DTO.getId())
                .abbreviation(team2DTO.getAbbreviation())
                .city(team2DTO.getCity())
                .conference(team2DTO.getConference())
                .division(team2DTO.getDivision())
                .fullName(team2DTO.getFullName())
                .name(team2DTO.getName())
                .build();


        List<PlayerDTO> team1Players = this.playersService.getPlayersByTeam(team1);
        List<PlayerDTO> team2Players = this.playersService.getPlayersByTeam(team2);

        while (team1Score < liveSimulationDTO.getScore() &&
                team2Score < liveSimulationDTO.getScore()) {
            Thread.sleep(3000);

            int teamValue = generateTeamValue();
            PlayerDTO playerToScore = new PlayerDTO();
            int scoredPoints = generateScoredPoints();

            if (teamValue == 1) {
                responseDTO.setTeam1(true);
                responseDTO.setTeam2(false);

                playerToScore = getPlayerToScore(team1Players);
                team1Score += scoredPoints;
            } else if (teamValue == 2) {
                responseDTO.setTeam2(true);
                responseDTO.setTeam1(false);

                playerToScore = getPlayerToScore(team2Players);
                team2Score += scoredPoints;
            }

            responseDTO.setPlayer(playerToScore.getFirstName() + " " + playerToScore.getLastName());
            responseDTO.setPoints(scoredPoints);

            this.simpMessagingTemplate.convertAndSend("/topic/simulation", responseDTO);
        }
    }

    private int generateTeamValue() {
        int teamValue = 0;

        while (teamValue == 0) {
            teamValue = rand.nextInt(3);
        }

        return teamValue;
    }

    private PlayerDTO getPlayerToScore(List<PlayerDTO> teamPlayers) {
        int playerValue = rand.nextInt(teamPlayers.size());

        return teamPlayers.get(playerValue);
    }

    private int generateScoredPoints() {
        return rand.nextInt(3) + 1;
    }
}
