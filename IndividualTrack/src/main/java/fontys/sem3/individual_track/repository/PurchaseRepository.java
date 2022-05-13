package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.repository.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    boolean existsById(long purchaseId);
}
