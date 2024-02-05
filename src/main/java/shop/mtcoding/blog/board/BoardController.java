package shop.mtcoding.blog.board;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog._core.PagingUtil;
import shop.mtcoding.blog.user.User;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardRepository boardRepository;


    @GetMapping("/board")
    public String search(@RequestParam = "") {


        return null;
    }

    @PostMapping("/board/save")
    public String post(BoardResponse.saveFormDTO repusetDTO) {
        boardRepository.save(repusetDTO);
        return "redirect:/";
    }

    @GetMapping({"/", "/board"})
    public String index(@RequestParam(defaultValue = "0") int page, HttpServletRequest request) {

        List<Board> boardList = boardRepository.findAll(page);
        request.setAttribute("boardList", boardList);

        int currentPage = page;
        int nextPage = currentPage + 1;
        int prevPage = currentPage - 1;

        request.setAttribute("nextPage", nextPage);
        request.setAttribute("prevPage", prevPage);

        boolean first = PagingUtil.isFirst(currentPage);
        boolean last = PagingUtil.isLast(currentPage, boardRepository.count());

        request.setAttribute("first", first);
        request.setAttribute("last", last);

        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request) {

        BoardResponse.DetailDTO responseDTO = boardRepository.findById(id);
        request.setAttribute("board", responseDTO);


        boolean owner = false;

        int boardUserId = responseDTO.getUserId();

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser != null) {
            if (boardUserId == sessionUser.getId()) {
                owner = true;
            }
        }
        request.setAttribute("owner", owner);

        return "board/detail";
    }
}
