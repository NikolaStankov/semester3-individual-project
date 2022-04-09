package fontys.sem3.individual_track.business.validator;

import fontys.sem3.individual_track.business.exception.InvalidGameIdException;

public interface GameIdValidator {
    void validateGameId(Long gameId) throws InvalidGameIdException;
}
