package shop.mtcoding.blog.user;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final HttpSession session;

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO) {
        System.out.println(requestDTO);
        // 1, 유효성 검사
        if (requestDTO.getUsername().length() <= 3) {
            return "error/400";
        }

        // 2. 모델 위
        User user = userRepository.findByUsernameAndPassword(requestDTO);

        if (user == null) {
            return "error/401";
        } else {
            session.setAttribute("sesstionUser", user);
            return "redirect:/";
        }

    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO) {
        System.out.println(requestDTO);

        // 1, 유효성 검사
        if (requestDTO.getUsername().length() <= 3) {
            return "error/400";
        }

        // 2. 모델 위임
        User user = userRepository.findByUsername(requestDTO.getUsername());
        if (user == null) {
            userRepository.save(requestDTO);
        } else {
            return "error/400";
        }

        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {


        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
