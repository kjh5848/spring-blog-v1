package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

public class BoardResponse {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private String username;
        private Timestamp createdAt;

        private Integer rId;
        private Integer rUserId;
        private String rUsername;
        private String rComment;


    }

    @Data
    public static class DetailReplyDTO {
        private int id;
        private int userId;
        private int boardId;
        private String comment;
    }

}
