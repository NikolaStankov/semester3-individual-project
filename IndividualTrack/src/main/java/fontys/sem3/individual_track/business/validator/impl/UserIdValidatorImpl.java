package fontys.sem3.individual_track.business.validator.impl;

import fontys.sem3.individual_track.business.exception.InvalidUserIdException;
import fontys.sem3.individual_track.business.validator.UserIdValidator;
import fontys.sem3.individual_track.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserIdValidatorImpl implements UserIdValidator {
    private final UsersRepository usersRepository;

    @Override
    public void validateUserId(Long userId) throws InvalidUserIdException {
        if (userId == null || !this.usersRepository.existsById(userId)) {
            throw new InvalidUserIdException();
        }
    }
}
