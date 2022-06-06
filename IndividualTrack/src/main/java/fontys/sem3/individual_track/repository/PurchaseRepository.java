package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.repository.entity.Purchase;
import fontys.sem3.individual_track.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    boolean existsById(long purchaseId);

    List<Purchase> findAllByUser(User user);
}
