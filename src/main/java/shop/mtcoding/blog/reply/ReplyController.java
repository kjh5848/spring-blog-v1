package shop.mtcoding.blog.reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.user.User;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyRepository replyRepository;
    private final HttpSession session;

    @PostMapping("/reply/save")
    public String write(ReplyRequest.WriteDTO requestSTO) {
        System.out.println("requestSTO = " + requestSTO);

        User sesstionUser = (User) session.getAttribute("sessionUser");
        if (sesstionUser == null) {
            return "redirect:/loginForm";
        }
        // 유효성 검사 안해

        replyRepository.save(requestSTO, sesstionUser.getId());
        return "board/{id}";
    }
}
