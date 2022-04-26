package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.UsersService;
import fontys.sem3.individual_track.model.CreateUserRequestDTO;
import fontys.sem3.individual_track.repository.UsersRepository;
import fontys.sem3.individual_track.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User readUserByUsername(String username) {
        return this.usersRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void createUser(CreateUserRequestDTO createUserRequestDTO) {
        User user = new User();
        Optional<User> userByUsername = this.usersRepository.findByUsername(createUserRequestDTO.getUsername());

        if (userByUsername.isPresent()) {
            throw new RuntimeException("User already registered. Please use different username.");
        }

        user.setUsername(createUserRequestDTO.getUsername());
        user.setPassword(this.passwordEncoder.encode(createUserRequestDTO.getPassword()));
        user.setRole(createUserRequestDTO.getRole());
        this.usersRepository.save(user);
    }
}
