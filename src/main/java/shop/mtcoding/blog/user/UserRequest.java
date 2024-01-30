package shop.mtcoding.blog.user;

import lombok.Data;

/**
 * 요청 DTO = Data Transfer Object
 * 통신으로부터 데이터를 전달받아 넘기는 데이터 오브젝트
 */

public class UserRequest {

    @Data//get,set을 다 들고 있음.
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;
    }

    @Data
    public static class loginDTO {
        private String username;
        private String password;
    }
}
