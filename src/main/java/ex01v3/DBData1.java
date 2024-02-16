package ex01v3;

import ex01v3.model.User;
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

    public ViewData1 toViewData() {

        return new ViewData1(boardId,title,content,new User(userId,username,email));
    }

}
