package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.LoginRequestDTO;
import fontys.sem3.individual_track.model.LoginResponseDTO;

public interface LoginService {
    LoginResponseDTO login(LoginRequestDTO loginRequest);
}
