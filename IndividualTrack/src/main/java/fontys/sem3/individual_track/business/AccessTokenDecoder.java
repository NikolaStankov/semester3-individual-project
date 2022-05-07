package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.AccessTokenDTO;

public interface AccessTokenDecoder {
    AccessTokenDTO decode(String accessTokenEncoded);
}

