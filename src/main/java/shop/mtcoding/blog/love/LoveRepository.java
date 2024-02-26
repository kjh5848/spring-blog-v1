package shop.mtcoding.blog.love;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.board.BoardRequest;

@RequiredArgsConstructor
@Repository
public class LoveRepository {
    private final EntityManager em;

    public LoveResponse.DetailDTO findLove(int boardId, int sessionUserId) {
        String q = """
                select
                  id,
                  case
                    when user_id is null then false
                    else true
                  end as islove,
                  (select count(*) from love_tb where board_id = ?) as lovecount
                from
                  love_tb
                where
                  board_id = ? and user_id = ?;
                """;
       Query query = em.createNativeQuery(q);
        query.setParameter(1, boardId);
        query.setParameter(2, boardId);
        query.setParameter(3, sessionUserId);
        Object[] row = (Object[]) query.getSingleResult();
        Integer id = (Integer) row[0];
        Boolean isLove = (Boolean) row[1];
        Long loveCount = (Long) row[2];

        System.out.println("id = " + id);
        System.out.println("isLove = " + isLove);
        System.out.println("loveCount = " + loveCount);

        LoveResponse.DetailDTO responseDTO = new LoveResponse.DetailDTO(
                id, isLove, loveCount
        );
        return responseDTO;
    }

    @Transactional
    public void save(BoardRequest.SaveDTO requestDTO, int userId) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, user_id, created_at) values(?,?,?, now())");
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

}
