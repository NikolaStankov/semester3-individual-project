package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.UsersService;
import fontys.sem3.individual_track.business.exception.ExistingUsernameException;
import fontys.sem3.individual_track.business.exception.MismatchingPasswordsException;
import fontys.sem3.individual_track.model.CreateUserRequestDTO;
import fontys.sem3.individual_track.repository.UsersRepository;
import fontys.sem3.individual_track.repository.entity.User;
import fontys.sem3.individual_track.repository.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Primary
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User readUserByUsername(String username) {
        return this.usersRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void createUser(CreateUserRequestDTO createUserRequestDTO) {
        User user = new User();
        Optional<User> userByUsername = this.usersRepository.findByUsername(createUserRequestDTO.getUsername());

        if (userByUsername.isPresent()) {
            throw new ExistingUsernameException();
        }

        if (!(createUserRequestDTO.getPassword().equals(createUserRequestDTO.getRepeatedPassword()))) {
            throw new MismatchingPasswordsException();
        }

        user.setUsername(createUserRequestDTO.getUsername());
        user.setPassword(this.passwordEncoder.encode(createUserRequestDTO.getPassword()));
        user.setUserRoles(Set.of(
                UserRole.builder()
                        .user(user)
                        .role(createUserRequestDTO.getRole())
                        .build()));

        this.usersRepository.save(user);
    }
}
