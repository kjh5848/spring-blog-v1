package shop.mtcoding.blog.board;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.user.User;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;
    private final HttpSession session;

    @PostMapping("/reply/{id}/delete")
    public String replyDelete(@PathVariable int id, HttpServletRequest request) {
        System.out.println("idx = " + id);

        List<Reply> replyAll = boardRepository.replyAll();
        request.setAttribute("replyAll", replyAll);

        Reply reply = boardRepository.findByReplyId(id);
        request.setAttribute("reply", reply);

        boardRepository.replyDelete(id);

        return "redirect:/board/{id}";
    }

    @PostMapping("/reply/save")
    public String replySave(ReplyRequest.replySaveDTO requestDTO) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        boardRepository.replySave(requestDTO, sessionUser.getId(), sessionUser.getUsername());


        return "redirect:/board/{id}";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam String title, HttpServletRequest request) {
        List<Board> boardList = boardRepository.search(title);
        request.setAttribute("boardList", boardList);

        return "index";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id, HttpServletRequest request) {
        // 인증체크
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        //조회 및 모델위임
        Board board = boardRepository.findById(id);
        if (board.getUserId() != sessionUser.getId()) {
            request.setAttribute("status", 403);
            request.setAttribute("msg", "권한이 없습니다.");
            return "error/40x";
        }

        boardRepository.deleteId(id);

        return "redirect:/";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, HttpServletRequest request, BoardRequest.updateDTO requestDTO) {

        //1. 인증체크
        User sesstionUser = (User) session.getAttribute("sessionUser");
        if (sesstionUser == null) {
            return "redirect:/loginForm";
        }
        //2. 권한 체크
        Board board = boardRepository.findById(id);
        if (board.getUserId() != sesstionUser.getId()) {
            request.setAttribute("status", 403);
            request.setAttribute("msg", "권한이 없습니다.");
            return "error/40x";
        }

        //3. 모델위임
        boardRepository.update(requestDTO, board.getId());


        return "redirect:/board/{id}";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

//       변경 전 게시물 조회
        Board board = boardRepository.findById(id);


        request.setAttribute("board", board);


        return "board/updateForm";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.saveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

//        유효성 검사

//        모델 위임
        boardRepository.save(requestDTO, sessionUser.getId());

        return "redirect:/";
    }

    @GetMapping({"/", "/board"})
    public String index(HttpServletRequest request) {

        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);

        return "index";
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
        // 1. 모델진입 = 상세보기 데이터 가져오기
        // 바디 데이터가 없으면 유효성 검사가 필요없지
        BoardResponse.DetailDTO responseDTO = boardRepository.findByIdWithUser(id);
        User sessionUser = (User) session.getAttribute("sessionUser");
        boolean pageOwner = false;
        int boardUserId = responseDTO.getUserId();
        if (sessionUser != null) {
            if (boardUserId == sessionUser.getId()) {
                pageOwner = true;
            }
        }

//        Reply reply = boardRepository.findByReplyId(id);


        List<ReplyResponse.replyDetailDTO> replyDetailDTOList = boardRepository.findByBoardIdAndReply(id);
        System.out.println("id = " + id);
        request.setAttribute("pageOwner", pageOwner);
        request.setAttribute("board", responseDTO);
        request.setAttribute("replyList", replyDetailDTOList);


        return "board/detail";
    }
}































