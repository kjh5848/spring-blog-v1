package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "board_tb")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String title;
    private String content;
    private int userId;

    @Timestamp
    private LocalDateTime createdAt;
}
