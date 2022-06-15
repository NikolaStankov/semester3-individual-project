package fontys.sem3.individual_track.business.converter;

import fontys.sem3.individual_track.model.UserDTO;
import fontys.sem3.individual_track.repository.entity.User;

import java.util.stream.Collectors;

public class UserDTOConverter {
    private UserDTOConverter() {

    }

    public static UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getUserRoles().stream().map(r -> r.getRole().toString()).toList())
                .build();
    }
}
