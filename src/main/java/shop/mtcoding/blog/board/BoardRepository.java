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



    public Board findById(int id) {
        Query query = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        query.setParameter(1, id);

        Board board = (Board) query.getSingleResult();
        return board;
    }

    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        return query.getResultList();
    }

    public BoardResponse.DetailDTO findByIdWithUser(int idx) {
        Query query = em.createNativeQuery("select b.id, b.title, b.content, b.user_id, u.username from board_tb b inner join user_tb u on b.user_id = u.id where b.id = ?");
        query.setParameter(1, idx);

        Object[] row = (Object[]) query.getSingleResult();

        Integer id = (Integer) row[0];
        String title = (String) row[1];
        String content = (String) row[2];
        int userId = (Integer) row[3];
        String username = (String) row[4];

        System.out.println("id : " + id);
        System.out.println("title : " + title);
        System.out.println("content : " + content);
        System.out.println("userId : " + userId);
        System.out.println("username : " + username);

        BoardResponse.DetailDTO responseDTO = new BoardResponse.DetailDTO();
        responseDTO.setId(id);
        responseDTO.setTitle(title);
        responseDTO.setContent(content);
        responseDTO.setUserId(userId);
        responseDTO.setUsername(username);

        return responseDTO;
    }

    @Transactional
    public void save(BoardRequest.SaveDTO requestDTO, int userId) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, user_id, created_at) values (?,?,?,now())");
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getContent());
        query.setParameter(3, userId);
        query.executeUpdate();
    }

    @Transactional
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Transactional
    public void update(BoardRequest.updateDTO requestDTO, int id) {
        Query query = em.createNativeQuery("update board_tb set title = ?, content = ?  where id = ?");
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getContent());
        query.setParameter(3, id);

        query.executeUpdate();
    }

    @Transactional
    public void replySave(BoardRequest.replyDTO requestDTO, int id, int idx) {
        Query query = em.createNativeQuery("insert into reply_tb(board_id, user_id, comment, created_at)values (?,?,?, now())");
        query.setParameter(1, id);
        query.setParameter(2, idx);
        query.setParameter(3, requestDTO.getComent());

        query.executeUpdate();

    }


    public BoardRequest.replyDTO findByIdWithUserIdAndBoardId(int idx) {
        Query query = em.createNativeQuery("select b.id, b.user_id, u.username, r.comment from board_tb b inner join user_tb u on b.user_id = u.id inner join reply_tb r on b.user_id = r.id where u.username = ?");
        query.setParameter(1, idx);

        return null;
    }
}
