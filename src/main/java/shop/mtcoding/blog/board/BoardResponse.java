package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardResponse {

    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private String username;
        // boolean 소문자는 getter만들어 주지 않고 isboardOwner로 만든다.
        // 기본값은 falseek.
        private Boolean boardOwner;

        public void isOwner(User sessionUser) {
            if (sessionUser == null) boardOwner = false;
            else boardOwner = sessionUser.getId() == userId;
        }
    }

    @Data
    public static class ReplyDTO {
        private Integer id;

        //댓글의 주인여부(주인만 삭제가능)
        private Integer userId;
        private String comment;
        private String username;
        private boolean replyOwner;

        public ReplyDTO(Object[] object, User sessionUser) {
            this.id = (Integer) object[0];
            this.userId = (Integer) object[1];;
            this.comment = (String) object[2];;
            this.username = (String) object[3];

            if (sessionUser == null) replyOwner = false;
            else replyOwner = sessionUser.getId() == userId;
        }
    }
}
