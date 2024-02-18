package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BoardResponse {
    @AllArgsConstructor
    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private String username;
    }
}
