package fontys.sem3.individual_track.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateUserRequestDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String role;
}
