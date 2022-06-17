package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.exception.InvalidAccessTokenException;
import fontys.sem3.individual_track.model.AccessTokenDTO;
import fontys.sem3.individual_track.repository.entity.RoleEnum;
import fontys.sem3.individual_track.repository.entity.User;
import fontys.sem3.individual_track.repository.entity.UserRole;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.io.Decoders;
import org.hibernate.mapping.Array;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Key;
import java.util.HexFormat;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccessTokenEncoderDecoderImplTest {

    @Mock
    private Key key;

    @Mock()
    private AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder =
            new AccessTokenEncoderDecoderImpl("eyJhbGciOiJSU0EtT0FFUCIsImVuYyI6IkExMjhHQ00ifQK52jFwAQJH");

    @Test
    void encode_shouldReturnAStringWithEncodedToken() {
        String expectedEncodedToken = "eyJhbGciOiJSU0EtT0FFUCIsImVuYyI6IkExMjhHQ00ifQ.K52jFwAQJH-\n" +
                " DxMhtaq7sg5tMuot_mT5dm1DR_01wj6ZUQQhJFO02vPI44W5nDjC5C_v4p\n" +
                " W1UiJa3cwb5y2Rd9kSvb0ZxAqGX9c4Z4zouRU57729ML3V05UArUhck9Zv\n" +
                " ssfkDW1VclingL8LfagRUs2z95UkwhiZyaKpmrgqpKX8azQFGNLBvEjXnx\n" +
                " -xoDFZIYwHOno290HOpig3aUsDxhsioweiXbeLXxLeRsivaLwUWRUZfHRC\n" +
                " _HGAo8KSF4gQZmeJtRgai5mz6qgbVkg7jPQyZFtM5_ul0UKHE2y0AtWm8I\n" +
                " zDE_rbAV14OCRZJ6n38X5urVFFE5sdphdGsNlA.gjI_RIFWZXJwaO9R.oa\n" +
                " E5a-z0N1MW9FBkhKeKeFa5e7hxVXOuANZsNmBYYT8G_xlXkMD0nz4fIaGt\n" +
                " uWd3t9Xp-kufvvfD-xOnAs2SBX_Y1kYGPto4mibBjIrXQEjDsKyKwndxzr\n" +
                " utN9csmFwqWhx1sLHMpJkgsnfLTi9yWBPKH5Krx23IhoDGoSfqOquuhxn0\n" +
                " y0WkuqH1R3z-fluUs6sxx9qx6NFVS1NRQ-LVn9sWT5yx8m9AQ_ng8MBWz2\n" +
                " BfBTV0tjliV74ogNDikNXTAkD9rsWFV0IX4IpA.sOLijuVySaKI-FYUaBy\n" +
                " wpg";

        User fakeUser = User.builder()
                .id(22L)
                .username("nikola01")
                .password("asda223as1")
                .build();
        fakeUser.setUserRoles(Set.of(UserRole.builder().role(RoleEnum.USER).id(11L).user(fakeUser).build()));

        AccessTokenDTO accessTokenDTO = AccessTokenDTO.builder()
                .subject(fakeUser.getUsername())
                .roles(fakeUser.getUserRoles().stream().map(ur -> ur.getRole().toString()).toList())
                .userId(fakeUser.getId())
                .build();

        byte[] decodedKey = HexFormat.of().parseHex("e04fd020ea3a6910a2d808002b30309d");

        when(accessTokenEncoderDecoder.encode(accessTokenDTO)).thenReturn(expectedEncodedToken);

        String actualEncodedToken = accessTokenEncoderDecoder.encode(accessTokenDTO);

        assertEquals(expectedEncodedToken, actualEncodedToken);
        verify(accessTokenEncoderDecoder).encode(accessTokenDTO);
    }

    @Test
    void decode_shouldReturnAccessTokenDTOFromTheDecodedString() {
        String accessTokenEncoded = "eyJhbGciOiJSU0EtT0FFUCIsImVuYyI6IkExMjhHQ00ifQ.K52jFwAQJH-\n" +
                " DxMhtaq7sg5tMuot_mT5dm1DR_01wj6ZUQQhJFO02vPI44W5nDjC5C_v4p\n" +
                " W1UiJa3cwb5y2Rd9kSvb0ZxAqGX9c4Z4zouRU57729ML3V05UArUhck9Zv\n" +
                " ssfkDW1VclingL8LfagRUs2z95UkwhiZyaKpmrgqpKX8azQFGNLBvEjXnx\n" +
                " -xoDFZIYwHOno290HOpig3aUsDxhsioweiXbeLXxLeRsivaLwUWRUZfHRC\n" +
                " _HGAo8KSF4gQZmeJtRgai5mz6qgbVkg7jPQyZFtM5_ul0UKHE2y0AtWm8I\n" +
                " zDE_rbAV14OCRZJ6n38X5urVFFE5sdphdGsNlA.gjI_RIFWZXJwaO9R.oa\n" +
                " E5a-z0N1MW9FBkhKeKeFa5e7hxVXOuANZsNmBYYT8G_xlXkMD0nz4fIaGt\n" +
                " uWd3t9Xp-kufvvfD-xOnAs2SBX_Y1kYGPto4mibBjIrXQEjDsKyKwndxzr\n" +
                " utN9csmFwqWhx1sLHMpJkgsnfLTi9yWBPKH5Krx23IhoDGoSfqOquuhxn0\n" +
                " y0WkuqH1R3z-fluUs6sxx9qx6NFVS1NRQ-LVn9sWT5yx8m9AQ_ng8MBWz2\n" +
                " BfBTV0tjliV74ogNDikNXTAkD9rsWFV0IX4IpA.sOLijuVySaKI-FYUaBy\n" +
                " wpg";

        User fakeUser = User.builder()
                .id(22L)
                .username("nikola01")
                .password("asda223as1")
                .build();
        fakeUser.setUserRoles(Set.of(UserRole.builder().role(RoleEnum.USER).id(11L).user(fakeUser).build()));

        AccessTokenDTO expectedAccessTokenDTO = AccessTokenDTO.builder()
                .subject(fakeUser.getUsername())
                .roles(fakeUser.getUserRoles().stream().map(ur -> ur.getRole().toString()).toList())
                .userId(fakeUser.getId())
                .build();

        when(accessTokenEncoderDecoder.decode(accessTokenEncoded)).thenReturn(expectedAccessTokenDTO);

        AccessTokenDTO actualAccessTokenDTO = accessTokenEncoderDecoder.decode(accessTokenEncoded);

        assertEquals(expectedAccessTokenDTO,actualAccessTokenDTO);
        verify(accessTokenEncoderDecoder).decode(accessTokenEncoded);
    }

    @Test
    void decode_shouldTrowInvalidAccessTokenExceptionWhenFailedToDecode(){
        String accessTokenEncoded = "eyJhbGciOiJSU0EtT0FFUCIsImVuYyI6IkExMjhHQ00ifQ.K52jFwAQJH-\n" +
                " DxMhtaq7sg5tMuot_mT5dm1DR_01wj6ZUQQhJFO02vPI44W5nDjC5C_v4p\n" +
                " W1UiJa3cwb5y2Rd9kSvb0ZxAqGX9c4Z4zouRU57729ML3V05UArUhck9Zv\n" +
                " ssfkDW1VclingL8LfagRUs2z95UkwhiZyaKpmrgqpKX8azQFGNLBvEjXnx\n" +
                " -xoDFZIYwHOno290HOpig3aUsDxhsioweiXbeLXxLeRsivaLwUWRUZfHRC\n" +
                " _HGAo8KSF4gQZmeJtRgai5mz6qgbVkg7jPQyZFtM5_ul0UKHE2y0AtWm8I\n" +
                " zDE_rbAV14OCRZJ6n38X5urVFFE5sdphdGsNlA.gjI_RIFWZXJwaO9R.oa\n" +
                " E5a-z0N1MW9FBkhKeKeFa5e7hxVXOuANZsNmBYYT8G_xlXkMD0nz4fIaGt\n" +
                " uWd3t9Xp-kufvvfD-xOnAs2SBX_Y1kYGPto4mibBjIrXQEjDsKyKwndxzr\n" +
                " utN9csmFwqWhx1sLHMpJkgsnfLTi9yWBPKH5Krx23IhoDGoSfqOquuhxn0\n" +
                " y0WkuqH1R3z-fluUs6sxx9qx6NFVS1NRQ-LVn9sWT5yx8m9AQ_ng8MBWz2\n" +
                " BfBTV0tjliV74ogNDikNXTAkD9rsWFV0IX4IpA.sOLijuVySaKI-FYUaBy\n" +
                " wpg";

        User fakeUser = User.builder()
                .id(22L)
                .username("nikola01")
                .password("asda223as1")
                .build();
        fakeUser.setUserRoles(Set.of(UserRole.builder().role(RoleEnum.USER).id(11L).user(fakeUser).build()));

        when(accessTokenEncoderDecoder.decode(accessTokenEncoded)).thenThrow(InvalidAccessTokenException.class);

        InvalidAccessTokenException exception = assertThrows(InvalidAccessTokenException.class,
                () -> accessTokenEncoderDecoder.decode(accessTokenEncoded));

        assertNotNull(exception);
        verify(accessTokenEncoderDecoder).decode(accessTokenEncoded);
    }
}