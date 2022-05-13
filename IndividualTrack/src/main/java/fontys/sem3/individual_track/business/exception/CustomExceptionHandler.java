package fontys.sem3.individual_track.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidCredentials() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Invalid username or password");
        return errorResponse;
    }

    @ExceptionHandler(MismatchingPasswordsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMismatchingPasswords() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Passwords don't match");
        return errorResponse;
    }

    @ExceptionHandler(ExistingUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleExistingUsername() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("This username already exists");
        return errorResponse;
    }

    @ExceptionHandler(InvalidUserIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidUserId() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Invalid user id.");
        return errorResponse;
    }

    @ExceptionHandler(InvalidTicketIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidTicketId() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Invalid ticket id.");
        return errorResponse;
    }

    @ExceptionHandler(InvalidGameIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidGameId() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Invalid game id.");
        return errorResponse;
    }
}
