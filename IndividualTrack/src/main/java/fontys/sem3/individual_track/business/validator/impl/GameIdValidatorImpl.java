package fontys.sem3.individual_track.business.validator.impl;

import fontys.sem3.individual_track.business.exception.InvalidGameIdException;
import fontys.sem3.individual_track.business.validator.GameIdValidator;
import fontys.sem3.individual_track.repository.GamesRepository;
import fontys.sem3.individual_track.repository.entity.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GameIdValidatorImpl implements GameIdValidator {
    private final GamesRepository gamesRepository;


    @Override
    public void validateGameId(Long gameId) throws InvalidGameIdException {
        if (gameId == null || !gamesRepository.existsById(gameId)) {
            throw new InvalidGameIdException();
        }
    }
}
