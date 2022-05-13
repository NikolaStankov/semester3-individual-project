package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.CreatePlayerRequestDTO;
import fontys.sem3.individual_track.model.CreatePlayerResponseDTO;
import fontys.sem3.individual_track.model.PlayerDTO;
import fontys.sem3.individual_track.model.PurchaseDTO;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {
    List<PurchaseDTO> getAllPurchases();

    Optional<PurchaseDTO> getPurchase(long playerId);

    CreatePlayerResponseDTO createPurchase (CreatePlayerRequestDTO playerRequest);

    boolean removePurchase (long purchaseId);
}
