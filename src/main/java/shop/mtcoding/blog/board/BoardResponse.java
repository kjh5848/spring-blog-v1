package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardResponse {

    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private int userId;
        private String username;
    }

    @Data
    public static class DetailReplyDTO {
        private int id;
        private int userId;
        private int boardId;
        private String comment;
    }

}
