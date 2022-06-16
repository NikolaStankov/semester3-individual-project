package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.AccessTokenDecoder;
import fontys.sem3.individual_track.business.UsersService;
import fontys.sem3.individual_track.business.converter.UserDTOConverter;
import fontys.sem3.individual_track.model.UserDTO;
import fontys.sem3.individual_track.repository.entity.RoleEnum;
import fontys.sem3.individual_track.repository.entity.User;
import fontys.sem3.individual_track.repository.entity.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UsersController.class)
class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private AccessTokenDecoder accessTokenDecoder;

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
    void getUserById_shouldReturn() throws Exception {
        UserDTO expectedUserDTO = UserDTOConverter.convertToDTO(this.createFakeUser());

        when(usersService.getUser(expectedUserDTO.getId())).thenReturn(Optional.of(expectedUserDTO));

        mockMvc.perform(get("/users/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"id":3,"username":  "test_username","role": ["USER"]}
                        """));

        verify(usersService).getUser(3L);
    }

    @Test
    void createUser() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "username": "test_username",
                                    "password": "test_password",
                                    "repeatedPassword": "test_password",
                                    "role": "USER"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk());
    }
}