package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.AccessTokenEncoder;
import fontys.sem3.individual_track.business.LoginService;
import fontys.sem3.individual_track.business.exception.InvalidCredentialsException;
import fontys.sem3.individual_track.model.AccessTokenDTO;
import fontys.sem3.individual_track.model.LoginRequestDTO;
import fontys.sem3.individual_track.model.LoginResponseDTO;
import fontys.sem3.individual_track.repository.UsersRepository;
import fontys.sem3.individual_track.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        Optional<User> userByUsername = usersRepository.findByUsername(loginRequest.getUsername());

        if (userByUsername.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        User user = userByUsername.get();

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponseDTO.builder().accessToken(accessToken).build(); 
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(User user) {
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();

        return accessTokenEncoder.encode(
                AccessTokenDTO.builder()
                        .subject(user.getUsername())
                        .roles(roles)
                        .userId(user.getId())
                        .build());
    }
}
