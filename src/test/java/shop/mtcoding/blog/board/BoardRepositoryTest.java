package shop.mtcoding.blog.board;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class) // 내가 만든 클래스는 import 해줘야 함.
@DataJpaTest // DB 관련 객체들이 IoC에 뜬다.
public class BoardRepositoryTest {

    @Autowired // Test에서 DI 하는 코드
    private BoardRepository boardRepository;

    @Test
    public void findAll() {

        List<Board> board = boardRepository.findAll();

        Assertions.assertThat(board.get(0).getTitle()).isEqualTo("제목1");
        Assertions.assertThat(board.get(0).getContent()).isEqualTo("내용1");
        Assertions.assertThat(board.get(0).getAuthor()).isEqualTo("홍길동");
        Assertions.assertThat(board.size()).isEqualTo(8);
    }

    @Test
    public void selectOne_test() {
        //given
        int id = 1;

        // when
        Board board = boardRepository.selectOne(id);

        //then (상태 검사)
        //System.out.println("board = " + board);
        Assertions.assertThat(board.getTitle()).isEqualTo("제목2");
        Assertions.assertThat(board.getContent()).isEqualTo("내용1");
        Assertions.assertThat(board.getAuthor()).isEqualTo("홍길동");

    }

    @Test
    public void insert_test() { // Test메서드는 파라미터,리턴이 없다
        // given
        String title = "제목10";
        String content = "내용10";
        String author = "이순신";

        // when
        boardRepository.insert(title, content, author);

        // then -> 눈으로 확인 (쿼리)
    } // Rollback (자동)


}