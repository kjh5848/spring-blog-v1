package shop.mtcoding.blog.user;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.time.LocalDateTime;

//유저 데이터베이스에 있는 값을 받는다.
@Data//get,set, tostring이 있다.
@Entity
@Table(name = "user_tb")
public class User {
    //프라이머리 키
    @Id
    //auto_Increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;
    private String email;

    @CreationTimestamp
    private LocalDateTime createAt;

}
