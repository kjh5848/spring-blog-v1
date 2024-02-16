package shop.mtcoding.blog.reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.board.BoardResponse;
import shop.mtcoding.blog.user.User;

// 댓글쓰기, 댓글삭제, 댓글 목록보기
@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final HttpSession session;
    private final ReplyRepository replyRepository;

    @PostMapping("/reply/save")
    public String write(ReplyRequest.WriteDTO requestDTO){
        System.out.println(requestDTO);

        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        // 유효성 검사 (님들이 하세요)

        // 핵심 코드
        replyRepository.save(requestDTO, sessionUser.getId());

        return "redirect:/board/"+requestDTO.getBoardId();
    }

    @PostMapping("/reply/{id}/delete")
    public String Delete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        Reply reply = replyRepository.findById(id);
        System.out.println("reply = " + reply);
        // 댓글 없거나, 댓글 주인이 아닌거나, 댓글 주인이거나
        if (reply == null) {
            return "error/404";
        }
        if (reply.getUserId() != sessionUser.getId()) {
            return "error/403";
        }
        replyRepository.deleteById(id);

        return "redirect:/board/"+reply.getBoardId();
    }
}


