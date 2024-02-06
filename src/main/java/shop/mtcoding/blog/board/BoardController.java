package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;
    private final HttpSession session;


    @GetMapping({"/", "/board"})
    public String index(HttpServletRequest request) {

        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);

        return "index";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO, HttpServletRequest request) {
        // 1. 인증체크
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        // 2. 바디 데이터 확인 및 유효성 검사
        System.out.println("requestDTO = " + requestDTO);

        if (requestDTO.getTitle().length() > 10) {
            request.setAttribute("status", 400);
            request.setAttribute("msg", "타이틀의 길이가 10자를 초과하였습니다.");
            return "error/40x";
        } else if (requestDTO.getTitle().length() == 0){
            request.setAttribute("status", 400);
            request.setAttribute("msg", "타이틀을 입력하시오.");
            return "error/40x";
        }

        // 3. 모델 위임
        boardRepository.save(requestDTO,sessionUser.getId());

        return "redirect:/";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        return "board/saveForm";
    }





    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request) {
        System.out.println("id : " + id);

        // 바디 데이터가 없으면 유효성 검사가 필요없지 ㅎ
        BoardResponse.DetailDTO responseDTO = boardRepository.findById(id);

        request.setAttribute("board", responseDTO);
        return "board/detail";
    }
}
