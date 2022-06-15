package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.UsersService;
import fontys.sem3.individual_track.model.CreateUserRequestDTO;
import fontys.sem3.individual_track.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") long id){
        Optional<UserDTO> optionalUserDTO = this.usersService.getUser(id);

        if (optionalUserDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(optionalUserDTO.get());
        }
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
        this.usersService.createUser(createUserRequestDTO);
        return ResponseEntity.ok().build();
    }
}
