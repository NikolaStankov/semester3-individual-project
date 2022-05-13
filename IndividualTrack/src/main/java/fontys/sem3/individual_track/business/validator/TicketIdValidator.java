package fontys.sem3.individual_track.business.validator;

import fontys.sem3.individual_track.business.exception.InvalidTicketIdException;

public interface TicketIdValidator {
    void validateTicketId(Long ticketId) throws InvalidTicketIdException;
}
