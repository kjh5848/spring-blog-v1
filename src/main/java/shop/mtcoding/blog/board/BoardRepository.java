package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public Board selectByContent(int id, String content) {
        Query query = em.createNativeQuery("select * from board_tb where content =? AND id = ?", Board.class);
        query.setParameter(1, content);
        query.setParameter(2, id);
        Board board = (Board) query.getSingleResult();
        return board;
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Transactional
    public void update(int id, String content) {
        Query query = em.createNativeQuery("update board_tb set  content = ?  where id =? ");
        query.setParameter(1, content);
        query.setParameter(2, id);
        query.executeUpdate();
    }

    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from board_tb", Board.class);
        return query.getResultList();
    }

    public Board selectOne(int id) {
        Query query = em.createNativeQuery("select * from board_tb where id =?", Board.class);
        query.setParameter(1, id);

        try {
            Board board = (Board) query.getSingleResult();
            return board;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void insert(String title, String content, String author) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, author) values(?, ?, ?)");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, author);

        query.executeUpdate();
    }
}