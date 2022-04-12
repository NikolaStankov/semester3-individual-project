package fontys.sem3.individual_track.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidTeamIdException extends ResponseStatusException {
    public InvalidTeamIdException() {
        super(HttpStatus.BAD_REQUEST, "TEAM_INVALID");
    }
}
