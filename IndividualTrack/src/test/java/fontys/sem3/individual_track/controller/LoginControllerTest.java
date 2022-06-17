package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.AccessTokenDecoder;
import fontys.sem3.individual_track.business.LoginService;
import fontys.sem3.individual_track.model.LoginRequestDTO;
import fontys.sem3.individual_track.model.LoginResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LoginController.class)
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @MockBean
    private AccessTokenDecoder accessTokenDecoder;

    @Test
    void login_shouldLoginUserWhenRequestIsValid() throws Exception {
        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
                .username("test")
                .password("test")
                .build();

        LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
                .accessToken("accessToken")
                .build();

        when(loginService.login(loginRequestDTO)).thenReturn(loginResponseDTO);

        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "username": "test",
                                    "password": "test"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            { "accessToken":  "accessToken" }
                        """));

        verify(loginService).login(loginRequestDTO);
    }
}