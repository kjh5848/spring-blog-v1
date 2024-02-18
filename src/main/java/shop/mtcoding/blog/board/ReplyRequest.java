package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ReplyRequest {
    @AllArgsConstructor
    @Data
    public static class replySaveDTO {
        private int boardId;
        private String comment;
    }

    @AllArgsConstructor
    @Data
    public static class replyDeleteDTO {
        private int id;
    }

}
