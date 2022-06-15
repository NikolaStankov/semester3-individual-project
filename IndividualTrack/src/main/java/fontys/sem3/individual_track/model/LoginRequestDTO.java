package fontys.sem3.individual_track.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Generated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
