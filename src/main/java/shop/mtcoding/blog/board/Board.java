package shop.mtcoding.blog.board;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

// 오브젝트인 데이터 때문에 따로 만든다.
@Data//get,set, tostring이 있다.
@Entity
@Table(name = "board_tb")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;

    //orm을 사용한다. 객체를 변환할 필요없이 int로 치환해서 자동을 변환해준다.
    @ManyToOne
    private User user;


    @CreationTimestamp
    private LocalDateTime createdAt;
}