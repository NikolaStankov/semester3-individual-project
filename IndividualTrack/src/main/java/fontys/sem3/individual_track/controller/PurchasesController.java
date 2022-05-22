package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.PurchaseService;
import fontys.sem3.individual_track.model.CreatePurchaseRequestDTO;
import fontys.sem3.individual_track.model.CreatePurchaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchasesController {
    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<CreatePurchaseResponseDTO> createPurchase(@RequestBody @Valid CreatePurchaseRequestDTO purchaseRequest) {
        CreatePurchaseResponseDTO purchaseResponse = this.purchaseService.createPurchase(purchaseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseResponse);
    }

}
