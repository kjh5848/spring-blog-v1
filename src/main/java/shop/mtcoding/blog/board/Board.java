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
    private String 메인필드1;
    private String 메인필드2;
    private String 메인필드3;
    private String 메인필드4;

}
