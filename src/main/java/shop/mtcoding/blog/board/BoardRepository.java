package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    public BoardResponse.DetailDTO findByIdWithUser(int idx) {
        Query query = em.createNativeQuery("select b.id, b.title, b.content, b.user_id, u.username from board_tb b inner join user_tb u on b.user_id = u.id where b.id = ?");
        query.setParameter(1, idx);

        JpaResultMapper rm = new JpaResultMapper();
        return rm.uniqueResult(query, BoardResponse.DetailDTO.class);
//        Object[] row = (Object[]) query.getSingleResult();
//
//        Integer id = (Integer) row[0];
//        String title = (String) row[1];
//        String content = (String) row[2];
//        int userId = (Integer) row[3];
//        String username = (String) row[4];
//
//        BoardResponse.DetailDTO responseDTO = new BoardResponse.DetailDTO();
//        responseDTO.setId(id);
//        responseDTO.setTitle(title);
//        responseDTO.setContent(content);
//        responseDTO.setUserId(userId);
//        responseDTO.setUsername(username);

    }

    @Transactional
    public void update(BoardRequest.updateDTO requestDTO, int id) {
        Query query = em.createNativeQuery("update board_tb set title = ?, content = ? where id =? ");
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getContent());
        query.setParameter(3, id);
        query.executeUpdate();
    }

    @Transactional
    public void deleteId(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id= ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    public List<Board> search(String title) {
        Query query = em.createNativeQuery("select * from board_tb where title like ? order by id desc", Board.class);
        query.setParameter(1, "%" + title + "%");
        return query.getResultList();

    }

    @Transactional
    public void replySave(ReplyRequest.replySaveDTO requestDTO, int userId, String username) {
        Query query = em.createNativeQuery("insert into reply_tb(user_id, board_id, comment,username, created_at) values (?,?,?,?,now())");
        query.setParameter(1, userId);
        query.setParameter(2, requestDTO.getBoardId());
        query.setParameter(3, requestDTO.getComment());
        query.setParameter(4, username);
        query.executeUpdate();
    }

    public List<ReplyResponse.replyDetailDTO> findByBoardIdAndReply(int idx) {
        Query query = em.createNativeQuery("select r.id, r.board_id, u.username, r.comment from reply_tb r inner join board_tb b on r.board_id = b.id inner join user_tb u on r.user_id = u.id where r.board_id = ? order by r.id desc");
        query.setParameter(1, idx);

        JpaResultMapper rm = new JpaResultMapper();
        return rm.list(query, ReplyResponse.replyDetailDTO.class);

//        List<Object[]> rows = query.getResultList();
//
//        Object[] username = rows.get(1);
//        Object[] comment = rows.get(2);
//
//        ReplyResponse.replyDetailDTO responseDTO = new ReplyResponse.replyDetailDTO();
//
//        responseDTO.setUsername(Arrays.toString(username));
//        responseDTO.setComment(Arrays.toString(comment));


    }

    @Transactional
    public void replyDelete(int id) {
        Query query = em.createNativeQuery("delete from reply_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }


    public Reply findByReplyId(int idx) {
        Query query = em.createNativeQuery("select * from reply_tb where" +
                " id = ?", Reply.class);
        query.setParameter(1, idx);
        Reply reply = (Reply) query.getSingleResult();
        return reply;
    }

    public List<Reply> replyAll() {
        Query query = em.createNativeQuery("select * from reply_tb", Reply.class);
        return query.getResultList();
    }
}
