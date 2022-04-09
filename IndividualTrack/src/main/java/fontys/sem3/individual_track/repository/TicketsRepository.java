package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.repository.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsRepository extends JpaRepository<Ticket, Long> {
}
