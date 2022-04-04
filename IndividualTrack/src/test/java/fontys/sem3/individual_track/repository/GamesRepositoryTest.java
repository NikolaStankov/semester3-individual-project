package fontys.sem3.individual_track.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import fontys.sem3.individual_track.model.Game;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GamesRepositoryTest {

    @Autowired
    @Qualifier("gamesMock")
    private GamesRepository underTest;

    @Test
    void itShouldSelectAllGames() throws JsonProcessingException {
        //given
        List<Game> games = null;

        //when
        games = this.underTest.selectAllGames();

        //then
        assertThat(games).isNotNull();
        assertThat(games.stream()).isEqualTo(4);
    }

    @Test
    void selectGame() {
    }
}