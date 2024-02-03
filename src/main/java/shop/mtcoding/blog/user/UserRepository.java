package shop.mtcoding.blog.user;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


@AllArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    public User findByUsername(String username) {
        Query query = em.createNativeQuery("select * from user_tb where username = ? ", User.class);
        query.setParameter(1, username);

        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }


    public User findByUsernameAndPassword(UserRequest.JoinDTO requestDTO) {
        Query query = em.createNativeQuery("select * from user_tb where username = ? and password = ?", User.class);
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());

        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }


    @Transactional
    public void save(UserRequest.JoinDTO requestDTO) {
        Query query = em.createNativeQuery("insert into user_tb(username, password, email, created_at) values (?,?,?,now())");
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getEmail());

        query.executeUpdate();

    }
}
