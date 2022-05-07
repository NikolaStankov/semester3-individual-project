package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.AccessTokenDTO;

public interface AccessTokenEncoder {
    String encode(AccessTokenDTO accessTokenDTO);
}