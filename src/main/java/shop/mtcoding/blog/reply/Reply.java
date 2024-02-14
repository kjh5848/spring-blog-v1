package shop.mtcoding.blog.reply;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reply_tb")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int boardId;
    private int userId;

    @Column(length = 200, nullable = false)
    private String comment;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
