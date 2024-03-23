package shop.mtcoding.blog.board;


import jakarta.persistence.*;
import lombok.Data;

@Table(name = "board_tb")
@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String author;
    private String title;
    private String content;
    private String 머지테스트브랜치;
    private String 머지테스트브랜치2;
    private String 머지테스트브랜치3;
    private String 머지테스트브랜치4;
    private String 머지테스트브랜치5;
    private String 머지테스트브랜치6;
}
