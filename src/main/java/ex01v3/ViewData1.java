package ex01v3;

import ex01v3.model.User;
import ex02v3.model.Reply;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ViewData1 {
    private int boardId;
    private String title;
    private String content;

    private User user;


}
