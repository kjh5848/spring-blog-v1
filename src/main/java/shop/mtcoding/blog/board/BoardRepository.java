package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.Constant;
import shop.mtcoding.blog.user.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;
    private final HttpSession session;

    public int count() {
        Query query = em.createNativeQuery("select count(*) from board_tb");

        Number user = (Number) query.getSingleResult();
        return user.intValue();
    }

    public List<Board> findAll(int page) {
        int value = page* Constant.PAGE_COUNT;
        Query query = em.createNativeQuery("select * from board_tb order by id desc limit ?,? ", Board.class);
        query.setParameter(1, value);
        query.setParameter(2, Constant.PAGE_COUNT);

        List<Board> boardList = query.getResultList();
        return boardList;
    }

    public BoardResponse.DetailDTO findById(int id) {
        Query query = em.createNativeQuery("select b.id, b.title, b.content, b.created_at, b.user_id, u.username from board_tb b inner join user_tb u on b.user_id = u.id where b.id = ?");
        query.setParameter(1, id);

        JpaResultMapper rm = new JpaResultMapper();
        BoardResponse.DetailDTO responseDTO = rm.uniqueResult(query, BoardResponse.DetailDTO.class);
        return responseDTO;
    }

    @Transactional
    public void save(BoardResponse.saveFormDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        Query query = em.createNativeQuery("insert into board_tb(title, content, user_id) values (?,?,?)");
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getContent());
        query.setParameter(3, sessionUser.getId());

        query.executeUpdate();

    }

    public void searchByTitle(String title) {
        Query query = em.createNativeQuery("select * from board_tb where title like ? order by id desc");
        query.setParameter(1, "%"+title+"%");

        query.getSingleResult();
    }
}
