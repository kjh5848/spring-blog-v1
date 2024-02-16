package ex01v2;

import ex01v2.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewData1 {
    private int boardId;
    private String title;
    private String content;

    private User user;

    public ViewData1(int boardId, String title, String content) {
    }
}
