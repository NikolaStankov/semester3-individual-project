package fontys.sem3.individual_track.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePurchaseResponseDTO {
    private Long purchaseId;
}