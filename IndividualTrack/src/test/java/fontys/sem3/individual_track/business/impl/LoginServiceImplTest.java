package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.AccessTokenEncoder;
import fontys.sem3.individual_track.business.exception.InvalidCredentialsException;
import fontys.sem3.individual_track.model.AccessTokenDTO;
import fontys.sem3.individual_track.model.LoginRequestDTO;
import fontys.sem3.individual_track.model.LoginResponseDTO;
import fontys.sem3.individual_track.repository.UsersRepository;
import fontys.sem3.individual_track.repository.entity.RoleEnum;
import fontys.sem3.individual_track.repository.entity.User;
import fontys.sem3.individual_track.repository.entity.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @Mock
    private UsersRepository usersRepositoryMock;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @InjectMocks
    private LoginServiceImpl loginService;

    @Test
    void login_shouldReturnAccessTokenWhenSuccessful() {
        User expectedUser = User.builder()
                .id(22L)
                .username("nikola01")
                .password("asda223as1")
                .build();

        expectedUser.setUserRoles(Set.of(UserRole.builder().role(RoleEnum.USER).id(11L).user(expectedUser).build()));

        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
                .username("nikola01")
                .password("password")
                .build();

        when(usersRepositoryMock.findByUsername(loginRequestDTO.getUsername())).thenReturn(Optional.of(expectedUser));
        when(passwordEncoder.matches(loginRequestDTO.getPassword(), expectedUser.getPassword())).thenReturn(true);

        LoginResponseDTO expectedResponse = LoginResponseDTO.builder()
                .accessToken(accessTokenEncoder.encode(
                        AccessTokenDTO.builder()
                                .subject(expectedUser.getUsername())
                                .roles(expectedUser.getUserRoles().stream().map(ur -> ur.getRole().toString()).toList())
                                .userId(expectedUser.getId())
                                .build()))
                .build();
        LoginResponseDTO actualResponse = loginService.login(loginRequestDTO);

        assertEquals(expectedResponse, actualResponse);
        verify(usersRepositoryMock).findByUsername(loginRequestDTO.getUsername());
        verify(passwordEncoder).matches(loginRequestDTO.getPassword(), expectedUser.getPassword());
    }

    @Test
    void login_shouldThrowInvalidCredentialsExceptions_whenMismatchingPasswords(){
        User expectedUser = User.builder()
                .id(22L)
                .username("nikola01")
                .password("asda223as1")
                .build();

        expectedUser.setUserRoles(Set.of(UserRole.builder().role(RoleEnum.USER).id(11L).user(expectedUser).build()));

        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
                .username("nikola01")
                .password("password")
                .build();

        when(usersRepositoryMock.findByUsername(loginRequestDTO.getUsername())).thenReturn(Optional.of(expectedUser));
        when(passwordEncoder.matches(loginRequestDTO.getPassword(), expectedUser.getPassword())).thenReturn(false);

        InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, () -> loginService.login(loginRequestDTO));

        assertNotNull(exception);
        verify(usersRepositoryMock).findByUsername(loginRequestDTO.getUsername());
        verify(passwordEncoder).matches(loginRequestDTO.getPassword(), expectedUser.getPassword());
    }

    @Test
    void login_shouldThrowInvalidCredentialsExceptions_whenUsernameIsNotFoundPasswords(){
        User expectedUser = User.builder()
                .id(22L)
                .username("nikola01")
                .password("asda223as1")
                .build();

        expectedUser.setUserRoles(Set.of(UserRole.builder().role(RoleEnum.USER).id(11L).user(expectedUser).build()));

        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
                .username("nikola01")
                .password("password")
                .build();

        when(usersRepositoryMock.findByUsername(loginRequestDTO.getUsername())).thenReturn(Optional.empty());

        InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, () -> loginService.login(loginRequestDTO));

        assertNotNull(exception);
        verify(usersRepositoryMock).findByUsername(loginRequestDTO.getUsername());
    }
}