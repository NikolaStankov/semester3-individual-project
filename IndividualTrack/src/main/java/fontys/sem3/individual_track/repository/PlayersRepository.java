package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.repository.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayersRepository extends JpaRepository<Player, Long> {
}
