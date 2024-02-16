package ex02v3;

import ex01v3.ViewData1;
import ex01v3.model.User;
import ex02v3.model.Reply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
public class DBData2 {
    private int boardId;
    private String title;
    private String content;

    private int replyId;
    private String comment;

    public ViewData2 toViewData() {

        return new ViewData2();
    }

}
