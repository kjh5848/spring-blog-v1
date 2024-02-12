package shop.mtcoding.blog.board;

import lombok.Data;

public class ReplyRequest {
    @Data
    public static class replySaveDTO {
        private String comment;
    }

    @Data
    public static class replyDeleteDTO {
        private int id;
    }

}
