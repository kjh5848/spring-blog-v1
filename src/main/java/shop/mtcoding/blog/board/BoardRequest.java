package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {
    @Data
    public static class saveDTO {
        private String title;
        private String content;

    }

    @Data
    public static class DateilDTO {
        private String title;
        private String content;
        private int userId;


    }


}
