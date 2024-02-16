package ex02;

import ex02.model.Reply;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App2 {
    public static void main(String[] args) {
        // 1. DB에서 가져온 데이터 - Flat(평평) 하게 가져올 수 밖에 없다
        DBData2 dbData1 = new DBData2(1, "제목1", "내용1", 1, "댓글1");
        DBData2 dbData2 = new DBData2(1, "제목1", "내용1", 2, "댓글2");
        DBData2 dbData3 = new DBData2(1, "제목1", "내용1", 3, "댓글3");

        List<DBData2> dbList = Arrays.asList(dbData1, dbData2, dbData3);

        // 2. ddbList(컬렉션을) ViewData(오브젝트)에 옮기시오 - ORM
        Integer boardId = dbList.get(0).getBoardId();
        String title = dbList.get(0).getTitle();
        String content = dbList.get(0).getContent();

        ViewData2 viewData2 = new ViewData2(
                boardId, title, content);

        for (DBData2 db : dbList) {
            Integer rId = db.getReplyId();
            String comment = db.getComment();
            Reply reply = new Reply(rId, comment);

            if (rId != null) {
                viewData2.addReply(reply);
            }
        }
        System.out.println("viewData2 = " + viewData2);

    }
}
