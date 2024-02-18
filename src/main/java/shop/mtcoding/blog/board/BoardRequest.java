package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BoardRequest {
    @AllArgsConstructor
    @Data
    public static class saveDTO {
        private String title;
        private String content;

    }

    @AllArgsConstructor
    @Data
    public static class updateDTO {
        private String title;
        private String content;

    }

    @AllArgsConstructor
    @Data
    public static class searchDTO {
        private String title;

    }


}
