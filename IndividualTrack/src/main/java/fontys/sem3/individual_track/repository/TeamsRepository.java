package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.repository.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamsRepository extends JpaRepository<Team, Long> {
}
