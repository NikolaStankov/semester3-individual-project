package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.*;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {
    List<PurchaseDTO> getAllPurchases();

    Optional<PurchaseDTO> getPurchase(long purchaseId);

    CreatePurchaseResponseDTO createPurchase(CreatePurchaseRequestDTO purchaseRequest);

    boolean removePurchase(long purchaseId);

    List<PurchaseDTO> getPurchasesByUser(UserDTO userDTO);
}
