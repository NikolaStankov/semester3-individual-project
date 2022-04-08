package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.repository.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamesRepository extends JpaRepository<Game, Long> {
}
