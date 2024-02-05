package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor

public class BoardRepository {
    private final EntityManager em;


    public List<Board> findAll(int page) {
        int value = page*3;
        Query query = em.createNativeQuery("select * from board_tb order by id desc limit ?,? ", Board.class);
        query.setParameter(1, value);
        query.setParameter(2, 3);

        List<Board> boardList = query.getResultList();
        return boardList;
    }

    public BoardResponse.DetailDTO findById(int id) {
        Query query = em.createNativeQuery("select b.id, b.title, b.content, b.created_at, b.user_id,u.username u.id from board_tb b inner join user_tb u on b.user_id = u.id where b.id = ?,");
        query.setParameter(1, id);

        JpaResultMapper rm = new JpaResultMapper();
        BoardResponse.DetailDTO responseDTO = rm.uniqueResult(query, BoardResponse.DetailDTO.class);
        return responseDTO;
    }
}
