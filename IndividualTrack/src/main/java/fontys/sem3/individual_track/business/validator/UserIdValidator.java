package fontys.sem3.individual_track.business.validator;

import fontys.sem3.individual_track.business.exception.InvalidUserIdException;

public interface UserIdValidator {
    void validateUserId(Long userId) throws InvalidUserIdException;
}
