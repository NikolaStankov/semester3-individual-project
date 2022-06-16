package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsersRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    void findByUsername_shouldReturnUserByGivenUsername() {
        User expectedUser = User.builder()
                .username("test_username")
                .password("test_password")
                .build();

        User savedUser = this.usersRepository.save(expectedUser);
        savedUser.setUserRoles(null);
        Optional<User> returnedUser = usersRepository.findByUsername(savedUser.getUsername());
        returnedUser.get().setUserRoles(null);

        assertEquals(expectedUser, returnedUser.get());
    }
}