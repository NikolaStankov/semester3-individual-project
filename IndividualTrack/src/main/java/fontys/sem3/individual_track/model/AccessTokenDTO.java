package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Data
@Generated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenDTO {
    private String subject;
    private List<String> roles;
    private Long userId;

    @JsonIgnore
    public boolean hasRole(String roleName) {
        if (roles == null) {
            return false;
        }
        return roles.contains(roleName);
    }
}