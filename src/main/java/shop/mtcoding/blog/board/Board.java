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

    private String UI로머지테스트하기1;
    private String UI로머지테스트하기2;
    private String UI로머지테스트하기3;
    private String UI로머지테스트하기4;
    private String UI로머지테스트하기5;

}
