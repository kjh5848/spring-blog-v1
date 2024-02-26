package shop.mtcoding.blog.love;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(LoveRepository.class)
class LoveRepositoryTest {
    @Autowired
    private LoveRepository loveRepository;

    @Test
    public void findLove_test() {
        int BoardId = 9;
        int sessionUserId = 1;

        loveRepository.findLove(BoardId, sessionUserId);

    }


}