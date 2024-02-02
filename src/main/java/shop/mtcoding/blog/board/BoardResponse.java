package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

public class BoardResponse {

    //bt.id, bt.content, bt.title, bt.created_at, bt.user_id uid, ut.usernamea
    @AllArgsConstructor
    @Data
    public static class DatailDTO {
        private Integer id;
        private String title;
        private String content;
        private Timestamp createdAt;
        private Integer userId;
        private String username;

    }
}
