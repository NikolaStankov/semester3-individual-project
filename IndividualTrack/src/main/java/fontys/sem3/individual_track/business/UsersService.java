package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.CreateUserRequestDTO;
import fontys.sem3.individual_track.model.UserDTO;
import fontys.sem3.individual_track.repository.entity.User;

import java.util.Optional;

public interface UsersService {
    User readUserByUsername(String username);

    void createUser(CreateUserRequestDTO createUserRequestDTO);

    Optional<UserDTO> getUser(long id);
}
