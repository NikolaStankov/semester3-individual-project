package fontys.sem3.individual_track.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidGameIdException extends ResponseStatusException {
    public InvalidGameIdException() {
        super(HttpStatus.BAD_REQUEST, "GAME_INVALID");
    }
}
