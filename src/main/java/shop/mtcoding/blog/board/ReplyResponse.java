package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ReplyResponse {
    @AllArgsConstructor
    @Data
    public static class replyDetailDTO {
        private int id;
        private String username;
        private String comment;
    }
}
