package fontys.sem3.individual_track.business.validator.impl;

import fontys.sem3.individual_track.business.exception.InvalidTicketIdException;
import fontys.sem3.individual_track.business.validator.TicketIdValidator;
import fontys.sem3.individual_track.repository.TicketsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketIdValidatorImpl implements TicketIdValidator {
    private final TicketsRepository ticketsRepository;

    @Override
    public void validateTicketId(Long ticketId) throws InvalidTicketIdException {
        if (ticketId == null || !this.ticketsRepository.existsById(ticketId)) {
            throw new InvalidTicketIdException();
        }
    }
}
