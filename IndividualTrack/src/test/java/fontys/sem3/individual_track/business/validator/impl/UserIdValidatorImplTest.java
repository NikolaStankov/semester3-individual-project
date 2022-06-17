package fontys.sem3.individual_track.business.validator.impl;

import fontys.sem3.individual_track.business.exception.InvalidUserIdException;
import fontys.sem3.individual_track.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserIdValidatorImplTest {
    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UserIdValidatorImpl userIdValidator;

    @Test
    void validateUserId_shouldThrowInvalidUserIdException_whenIdIsNull() {
        InvalidUserIdException exception = assertThrows(InvalidUserIdException.class,
                () -> userIdValidator.validateUserId(null));

        assertNotNull(exception);
        assertTrue(exception instanceof InvalidUserIdException);
    }

    @Test
    void validateUserId_shouldThrowInvalidUserIdException_whenIdIsNotFound(){
        Long wrongId = 4321L;

        when(usersRepository.existsById(wrongId)).thenReturn(false);

        InvalidUserIdException exception = assertThrows(InvalidUserIdException.class,
                () -> userIdValidator.validateUserId(wrongId));

        assertNotNull(exception);
        assertTrue(exception instanceof InvalidUserIdException);
    }
}