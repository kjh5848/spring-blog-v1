package shop.mtcoding.blog._core.config.security;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRepository;
// post, /login, /x-www-form, 키값이 username, password
@RequiredArgsConstructor
@Service// 뉴하는 어노테이션(컴포넌트 스캔) UserDetailsService에 덮어씌어 무력화 시킨다.
public class MyLoginService implements UserDetailsService {

    private final UserRepository userRepository;
    private final HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 세션에 알아서 저장한다.
        System.out.println("username = " + username);
        User user = userRepository.findByUsername(username);

        if (user == null) {
            System.out.println("유저는 널");
            return null;
        } else {
            session.setAttribute("sessionUser", user); //머스태치에서만 가져오기
            System.out.println("유저를 찾았어요");
            return new MyLoginUser(user); // SecurityContextHolder

        }
    }
}
