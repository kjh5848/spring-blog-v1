package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardRepository boardRepository;

    //http://localhost:8080?page=0
    @GetMapping({ "/", "/board" })
    public String index(HttpServletRequest request,@RequestParam(defaultValue = "0") int page) {

        List<Board> boardList = boardRepository.findAll(page);
        request.setAttribute("boardList", boardList);

        int currentPage = page;
        int nextPage = currentPage+1;
        int prevPage = currentPage-1;

        request.setAttribute("nextPage",nextPage);
        request.setAttribute("prevPage",prevPage);


        boolean first = currentPage == 0 ? true : false;
        request.setAttribute("first",first);


//        Board board = boardRepository.count();
//        request.setAttribute("board", board);

        int totalCount = boardRepository.count();;
//       int totalCount = 4;
        int paging = 3;
        int lastPage = totalCount%paging; // 3 % 3 = 0
        boolean last = currentPage == lastPage ?  true: false;
        request.setAttribute("last",last);

        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/1")
    public String detail() {
        return "board/detail";
    }
}
