package fontys.sem3.individual_track.business.validator.impl;

import fontys.sem3.individual_track.business.exception.InvalidTeamIdException;
import fontys.sem3.individual_track.business.validator.TeamIdValidator;
import fontys.sem3.individual_track.repository.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamIdValidatorImpl implements TeamIdValidator {
    private final TeamsRepository teamsRepository;

    @Override
    public void validateTeamId(Long teamId) throws InvalidTeamIdException {
        if (teamId == null || !this.teamsRepository.existsById(teamId)) {
            throw new InvalidTeamIdException();
        }
    }
}
