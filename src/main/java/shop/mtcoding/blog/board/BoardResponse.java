package shop.mtcoding.blog.board;

import lombok.Data;

import java.security.Timestamp;

public class BoardResponse {

    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private Timestamp createdAt;
        private int userId;
        private String username;
    }
}
