package fontys.sem3.individual_track.business.validator.impl;

import fontys.sem3.individual_track.business.exception.InvalidGameIdException;
import fontys.sem3.individual_track.business.validator.GameIdValidator;
import fontys.sem3.individual_track.repository.GamesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameIdValidatorImplTest {

    @Mock
    private GamesRepository gamesRepository;

    @InjectMocks
    private GameIdValidatorImpl gameIdValidator;

    @Test
    void validateGameId_shouldThrowInvalidGameIdException_whenIdIsNull() {
        InvalidGameIdException exception = assertThrows(InvalidGameIdException.class,
                () -> gameIdValidator.validateGameId(null));

        assertNotNull(exception);
        assertTrue(exception instanceof InvalidGameIdException);
    }

    @Test
    void validateGameId_shouldThrowInvalidGameIdException_whenIdIsNotFound(){
        Long wrongId = 2222L;

        when(gamesRepository.existsById(wrongId)).thenReturn(false);

        InvalidGameIdException exception = assertThrows(InvalidGameIdException.class,
                () -> gameIdValidator.validateGameId(wrongId));

        assertNotNull(exception);
        assertTrue(exception instanceof InvalidGameIdException);

        verify(gamesRepository).existsById(wrongId);
    }
}