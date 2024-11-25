package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.repository.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamesRepository extends JpaRepository<Game, Long> {
    boolean existsById(long gameId);
    List<Game> findAllByHomeTeamId(long homeTeamId);
    List<Game> findAllByVisitorTeamId(long visitorTeamId);
}
