package shop.mtcoding.blog.board;

import lombok.Data;

public class ReplyResponse {
    @Data
    public static class replyDetailDTO {
        private String username;
        private String comment;
    }
}
