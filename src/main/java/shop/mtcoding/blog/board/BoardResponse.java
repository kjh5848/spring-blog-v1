package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

public class BoardResponse {

    @AllArgsConstructor
    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Timestamp createdAt;
        private Integer userId;
        private String username;

    }

    @AllArgsConstructor
    @Data
    public static class saveFormDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;

    }



}
