package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.converter.UserDTOConverter;
import fontys.sem3.individual_track.business.exception.ExistingUsernameException;
import fontys.sem3.individual_track.business.exception.MismatchingPasswordsException;
import fontys.sem3.individual_track.model.CreateUserRequestDTO;
import fontys.sem3.individual_track.model.UserDTO;
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

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {
    @Mock
    UsersRepository usersRepositoryMock;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UsersServiceImpl usersService;

    private User createFakeUser() {
        User fakeUser = User.builder()
                .id(3L)
                .username("test_username")
                .password("test_password")
                .userRoles(Set.of(UserRole.builder().build()))
                .build();

        fakeUser.setUserRoles(Set.of(new UserRole(null, RoleEnum.USER, fakeUser)));

        return fakeUser;
    }

    @Test
    void readUserByUsername() {
        User expectedUser = this.createFakeUser();

        when(usersRepositoryMock.findByUsername(expectedUser.getUsername())).thenReturn(Optional.of(expectedUser));

        User actualUser = usersService.readUserByUsername(expectedUser.getUsername());

        assertEquals(expectedUser, actualUser);
        verify(usersRepositoryMock).findByUsername(expectedUser.getUsername());
    }

    @Test
    void createUser_shouldCreateUserIfRequestIsValid() {
        User userToSave = this.createFakeUser();
        userToSave.setId(null);
        userToSave.setPassword(this.passwordEncoder.encode("test_password"));

        User expectedUser = User.builder()
                .id(14L)
                .username(userToSave.getUsername())
                .password("test_password")
                .userRoles(userToSave.getUserRoles())
                .build();

        when(usersRepositoryMock.save(userToSave)).thenReturn(expectedUser);
        when(usersRepositoryMock.findById(14L)).thenReturn(Optional.of(expectedUser));


        CreateUserRequestDTO createUserRequestDTO = CreateUserRequestDTO.builder()
                .username(userToSave.getUsername())
                .password("test_password")
                .repeatedPassword("test_password")
                .role(RoleEnum.USER)
                .build();

        usersService.createUser(createUserRequestDTO);

        UserDTO expectedUserDTO = UserDTOConverter.convertToDTO(expectedUser);
        Optional<UserDTO> actualUserDTO = usersService.getUser(14L);

        assertEquals(expectedUserDTO, actualUserDTO.get());
        verify(usersRepositoryMock).findById(14L);
    }

    @Test
    void createUser_shouldThrowExistingUsernameExceptionIfUsernameExists() {
        User userToSave = this.createFakeUser();
        userToSave.setId(null);
        userToSave.setPassword(this.passwordEncoder.encode("test_password"));

        CreateUserRequestDTO createUserRequestDTO = CreateUserRequestDTO.builder()
                .username(userToSave.getUsername())
                .password("test_password")
                .repeatedPassword("test_password")
                .role(RoleEnum.USER)
                .build();

        when(usersRepositoryMock.findByUsername(userToSave.getUsername())).thenThrow(ExistingUsernameException.class);

        ExistingUsernameException exception = assertThrows(ExistingUsernameException.class,
                () -> usersService.createUser(createUserRequestDTO));

        assertNotNull(exception);
        verify(usersRepositoryMock).findByUsername(userToSave.getUsername());
    }

    @Test
    void createUser_shouldThrowMismatchingPasswordsExceptionIfRequestPasswordsAreNotMatching(){
        CreateUserRequestDTO createUserRequestDTO = CreateUserRequestDTO.builder()
                .username("test")
                .password("test_password1")
                .repeatedPassword("test_password2")
                .role(RoleEnum.USER)
                .build();

        MismatchingPasswordsException exception = assertThrows(MismatchingPasswordsException.class,
                () -> usersService.createUser(createUserRequestDTO));

        assertNotNull(exception);
    }

    @Test
    void getUser_shouldReturnUserByGivenId() {
        User expectedUser = this.createFakeUser();

        when(usersRepositoryMock.findById(expectedUser.getId())).thenReturn(Optional.of(expectedUser));

        UserDTO expectedUserDTO = UserDTOConverter.convertToDTO(expectedUser);
        Optional<UserDTO> actualUserDTO = usersService.getUser(expectedUser.getId());

        assertEquals(expectedUserDTO, actualUserDTO.get());
        verify(usersRepositoryMock).findById(expectedUser.getId());
    }
}