package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.PurchaseService;
import fontys.sem3.individual_track.business.UsersService;
import fontys.sem3.individual_track.business.exception.InvalidUserIdException;
import fontys.sem3.individual_track.model.PurchaseDTO;
import fontys.sem3.individual_track.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users/{userId}/purchases")
@RequiredArgsConstructor
public class PurchasesByUserController {
    private final PurchaseService purchaseService;
    private final UsersService usersService;

    @GetMapping
    public List<PurchaseDTO> getPurchasesByUser(@PathVariable(value = "userId") long userId) {
        Optional<UserDTO> optionalUser = this.usersService.getUser(userId);

        if (optionalUser.isEmpty()) {
            throw new InvalidUserIdException();
        }

        UserDTO userDTO = optionalUser.get();

        return this.purchaseService.getPurchasesByUser(userDTO);
    }
}
