package ex02v3;

import ex02v3.model.Reply;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class ViewData2 {
    private int boardId;
    private String title;
    private String content;

    // Board 데이터만 넣는 생성자
//    public ViewData2(List<DBData2> dbData2List) {
//        this.boardId = dbData2List.get(0).getBoardId();
//        this.title = dbData2List.get(0).getTitle();
//        this.content = dbData2List.get(0).getContent();
//
//        for (DBData2 data : dbData2List) {
//            Reply reply1 = new Reply(data.getReplyId(),getContent());
//            addReply(reply1);
//        }
//    }

    // 댓글들은 addReply로 추가하기
    private List<Reply> replies = new ArrayList<>();

    public void addReply(Reply reply) {
        replies.add(reply);
    }
}
