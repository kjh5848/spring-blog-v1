package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final EntityManager em;

    public List<Board> findAll(int page) {
        final int COUNT = 3;
        int value = page*COUNT;
        Query query = em.createNativeQuery("select * from board_tb order by id desc limit ?,?", Board.class);
        query.setParameter(1, value);
        query.setParameter(2, COUNT);

        List<Board> boardList = query.getResultList();
        return boardList;
    }

    public int count() {
        Query query = em.createNativeQuery("SELECT count(*) FROM BOARD_TB");

        int totalCount = ((BigInteger) query.getSingleResult()).intValue();
//       int totalCount =  (Integer) query.getSingleResult();
        return totalCount;
    }

    public BoardResponse.DatailDTO findById(int id) {
        Query query = em.createNativeQuery(
                "SELECT bt.id, bt.title, bt.content, bt.created_at, bt.user_id uid, ut.username\n" +
                "FROM board_tb bt inner join user_tb ut\n" +
                "on bt.user_id = ut.id \n" +
                "where bt.id = ?");
        query.setParameter(1, id);

        JpaResultMapper rm = new JpaResultMapper();
        BoardResponse.DatailDTO responseDTO = rm.uniqueResult(query, BoardResponse.DatailDTO.class);
        return responseDTO;
    }
}
