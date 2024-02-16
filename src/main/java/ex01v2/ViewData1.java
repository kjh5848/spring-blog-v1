package ex01v2;

import ex01v2.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class ViewData1 {
    private int boardId;
    private String title;
    private String content;

    private User user;

    public ViewData1(DBData1 dbData1) {

        this.boardId = dbData1.getBoardId();
        this.title = dbData1.getTitle();
        this.content = dbData1.getContent();
        this.user = new User(dbData1.getUserId(), dbData1.getUsername(), dbData1.getEmail());
    }
}
