package shop.mtcoding.blog.user;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final HttpSession session;

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO, HttpServletRequest request) {

        User user = userRepository.findByUsername(requestDTO);
        if (user == null) {
            userRepository.save(requestDTO);
        } else {
            request.setAttribute("status", 401);
            request.setAttribute("msg", "동일한 유저네임이 존재합니다.");
            return "error/40x";
        }
        return "redirect:/loginForm";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO, HttpServletRequest request) {

        User user = userRepository.findByUsernameAndPassword(requestDTO);
        System.out.println("user = " + user);
        if (user == null) {
            request.setAttribute("status", 401);
            request.setAttribute("msg", "동일한 회원정보가 없습니다.");
            return "error/40x";
        } else {
            session.setAttribute("sessionUser", user);
            return "redirect:/";
        }

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

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";

        }

        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
