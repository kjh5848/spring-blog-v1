package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@Import(BoardRepository.class)
@DataJpaTest
class BoardControllerTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void reply() {


    }
}