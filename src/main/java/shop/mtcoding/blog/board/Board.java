package shop.mtcoding.blog.board;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

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
    private int userId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
