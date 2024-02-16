package ex01v3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@Data
public class User {
    private int userId;
    private String username;
    private String email;
}
