package shop.mtcoding.blog.love;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name="love_tb", uniqueConstraints = {
        @UniqueConstraint(
                name="love_uk",
                columnNames={"board_id","user_id"}
        )})
@Data
@Entity
public class Love {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer boardId;
    private Integer userId;
    private LocalDateTime createdAt;
}

