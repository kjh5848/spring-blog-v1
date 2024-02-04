package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor

public class BoardRepository {
    private final EntityManager em;


    public List<Board> findAll(int page) {
        final int COUNT = 3;
        int value = page * COUNT;
        Query query = em.createNativeQuery("select * from board_tb order by id desc limit ?,?", Board.class);
        query.setParameter(1, value);
        query.setParameter(2, COUNT);

        List<Board> boardList = query.getResultList();
        return boardList;
    }

    public int findById(int id) {
        Query query = em.createNativeQuery("select count(*)from board_tb", Board.class);
        Long user = (Long) query.getSingleResult();
        return  user.intValue();
    }
}
