package fontys.sem3.individual_track.business.validator.impl;

import fontys.sem3.individual_track.business.exception.InvalidTeamIdException;
import fontys.sem3.individual_track.repository.GamesRepository;
import fontys.sem3.individual_track.repository.TeamsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamIdValidatorImplTest {

    @Mock
    private TeamsRepository teamsRepository;

    @InjectMocks
    private TeamIdValidatorImpl gameIdValidator;

    @Test
    void validateTeamId_shouldThrowInvalidTeamIdException_whenIdIsNull() {
        InvalidTeamIdException exception = assertThrows(InvalidTeamIdException.class,
                () -> gameIdValidator.validateTeamId(null));

        assertNotNull(exception);
        assertTrue(exception instanceof InvalidTeamIdException);
    }

    @Test
    void validateTeamId_shouldThrowInvalidTeamIdException_whenIdIsNotFound() {
        Long wrongId = 1111L;

        when(teamsRepository.existsById(wrongId)).thenReturn(false);

        InvalidTeamIdException exception = assertThrows(InvalidTeamIdException.class,
                () -> gameIdValidator.validateTeamId(wrongId));

        assertNotNull(exception);
        assertTrue(exception instanceof InvalidTeamIdException);

        verify(teamsRepository).existsById(wrongId);
    }
}