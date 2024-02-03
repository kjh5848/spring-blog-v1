package shop.mtcoding.blog.user;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;

    @Timestamp
    private LocalDateTime createdAt;


}
