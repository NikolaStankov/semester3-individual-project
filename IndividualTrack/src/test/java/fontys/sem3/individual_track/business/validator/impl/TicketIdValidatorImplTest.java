package fontys.sem3.individual_track.business.validator.impl;

import fontys.sem3.individual_track.business.exception.InvalidTicketIdException;
import fontys.sem3.individual_track.repository.TicketsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketIdValidatorImplTest {

    @Mock
    private TicketsRepository ticketsRepository;

    @InjectMocks
    private TicketIdValidatorImpl ticketIdValidator;

    @Test
    void validateTicketId_shouldThrowInvalidTicketIdException_whenIdIsNull() {
        InvalidTicketIdException exception = assertThrows(InvalidTicketIdException.class,
                () -> ticketIdValidator.validateTicketId(null));

        assertNotNull(exception);
        assertTrue(exception instanceof InvalidTicketIdException);
    }

    @Test
    void validateTicketId_shouldThrowInvalidTicketIdException_whenIdIsNotFound(){
        Long wrongId = 1234L;

        when(ticketsRepository.existsById(wrongId)).thenReturn(false);

        InvalidTicketIdException exception = assertThrows(InvalidTicketIdException.class,
                () -> ticketIdValidator.validateTicketId(wrongId));

        assertNotNull(exception);
        assertTrue(exception instanceof InvalidTicketIdException);
    }
}