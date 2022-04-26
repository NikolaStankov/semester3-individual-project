package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.UsersService;
import fontys.sem3.individual_track.model.CreateUserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
        this.usersService.createUser(createUserRequestDTO);
        return ResponseEntity.ok().build();
    }
}
