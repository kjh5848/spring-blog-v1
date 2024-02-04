package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(BoardRepository.class)
//엔티티 매니저랑 필요한 @import한 테이터만 만들어 준다.DB관련 어노테이션
@DataJpaTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findById_test() {
        BoardResponse.DetailDTO board = boardRepository.findById(1);
        System.out.println("board = " + board);
    }
}