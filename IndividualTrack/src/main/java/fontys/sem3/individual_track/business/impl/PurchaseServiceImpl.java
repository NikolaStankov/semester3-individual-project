package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.PurchaseService;
import fontys.sem3.individual_track.business.converter.PurchaseDTOConverter;
import fontys.sem3.individual_track.business.validator.GameIdValidator;
import fontys.sem3.individual_track.business.validator.TicketIdValidator;
import fontys.sem3.individual_track.business.validator.UserIdValidator;
import fontys.sem3.individual_track.model.*;
import fontys.sem3.individual_track.repository.PurchaseRepository;
import fontys.sem3.individual_track.repository.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserIdValidator userIdValidator;
    private final GameIdValidator gameIdValidator;
    private final TicketIdValidator ticketIdValidator;

    @Override
    public List<PurchaseDTO> getAllPurchases() {
        List<Purchase> purchaseList = this.purchaseRepository.findAll();
        List<PurchaseDTO> purchaseDTOList = new ArrayList<>();

        for (Purchase purchase : purchaseList) {
            purchaseDTOList.add(PurchaseDTOConverter.convertToDTO(purchase));
        }

        return purchaseDTOList;
    }

    @Override
    public Optional<PurchaseDTO> getPurchase(long purchaseId) {
        return this.purchaseRepository.findById(purchaseId)
                .map(PurchaseDTOConverter::convertToDTO);
    }

    @Override
    public CreatePurchaseResponseDTO createPurchase(CreatePurchaseRequestDTO purchaseRequest) {
        ticketIdValidator.validateTicketId(purchaseRequest.getTicketId());
        userIdValidator.validateUserId(purchaseRequest.getUserId());
        gameIdValidator.validateGameId(purchaseRequest.getGameId());

        Purchase purchaseToSave = Purchase.builder()
                .ticket(Ticket.builder().id(purchaseRequest.getTicketId()).build())
                .game(Game.builder().id(purchaseRequest.getGameId()).build())
                .quantity(purchaseRequest.getQuantity())
                .user(User.builder().id(purchaseRequest.getUserId()).build())
                .build();

        Purchase savedPurchase = this.purchaseRepository.save(purchaseToSave);

        return CreatePurchaseResponseDTO.builder()
                .purchaseId(savedPurchase.getId())
                .build();
    }

    @Override
    public List<PurchaseDTO> getPurchasesByUser(UserDTO userDTO) {
        User userToQueryOn = User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .build();

        List<Purchase> purchasesByUser = this.purchaseRepository.findAllByUser(userToQueryOn);
        List<PurchaseDTO> purchaseDTOsByUser = new ArrayList<>();

        for (Purchase purchase : purchasesByUser) {
            purchaseDTOsByUser.add(PurchaseDTOConverter.convertToDTO(purchase));
        }

        return purchaseDTOsByUser;
    }
}
