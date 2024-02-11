package shop.mtcoding.blog.board;

import jakarta.el.ELManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRequest;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public Board findById(int id) {
        Query query = em.createNativeQuery("select * from board_tb where  id = ?", Board.class);
        query.setParameter(1, id);
        Board board = (Board) query.getSingleResult();
        return board;
    }

    @Transactional
    public void save(BoardRequest.saveDTO requestDTO, int id) {
        Query query = em.createNativeQuery("insert into board_tb(title,content,user_id,created_at) values (?,?,?,now())");
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getContent());
        query.setParameter(3, id);
        query.executeUpdate();
    }

    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from board_tb order by id desc ", Board.class);
        return query.getResultList();
    }

    public void findByIdWithUser(int id) {
        em.createNativeQuery("");
    }
}
