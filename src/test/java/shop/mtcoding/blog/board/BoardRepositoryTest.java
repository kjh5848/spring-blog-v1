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
    public void findByIdWithUserIdAndBoardId(BoardRequest.replyDTO requestDTO) {
//        Query query = em.createNativeQuery("select b.id, b.user_id, u.username, r.comment from board_tb b inner join user_tb u on b.user_id = u.id inner join reply_tb r on b.user_id = r.id where u.username = ?");
    }
}