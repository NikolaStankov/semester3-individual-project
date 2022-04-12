package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.repository.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesRepository extends JpaRepository<Game, Long> {
    boolean existsById(long gameId);
}
