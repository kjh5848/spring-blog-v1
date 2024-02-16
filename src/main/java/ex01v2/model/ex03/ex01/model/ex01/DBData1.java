package ex01v2.model.ex03.ex01.model.ex01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DBData1 {
    private int boardId;
    private String title;
    private String content;
    private int userId;
    private String username;
    private String email;
}
