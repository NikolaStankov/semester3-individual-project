package fontys.sem3.individual_track.business.validator;

import fontys.sem3.individual_track.business.exception.InvalidTeamIdException;

public interface TeamIdValidator {
    void validateTeamId(Long teamId) throws InvalidTeamIdException;
}
