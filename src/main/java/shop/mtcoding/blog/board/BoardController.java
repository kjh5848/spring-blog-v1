package shop.mtcoding.blog.board;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;
    private final HttpSession session;

    @GetMapping({"/", "/board"})
    public String index(HttpServletRequest request, @RequestParam(defaultValue = "0") int page) {


        List<Board> boardList = boardRepository.findAll(page);
        request.setAttribute("boardList", boardList);

        int currentPage = page;
        int nextPage = currentPage + 1;
        int previousPage = currentPage - 1;

        request.setAttribute("nextPage", nextPage);
        request.setAttribute("previousPage", previousPage);

        boolean first = currentPage == 0 ? true : false;
        request.setAttribute("first", first);


//        boolean last = false;
//
//        int totalCount = 5;
//        int totalPage = 5;
//        int remainCount = totalCount % totalPage;
//        System.out.println("remainCount = " + remainCount);
//
//        int totalPageCount = totalCount / totalPage;
//        System.out.println("totalPageCount = " + totalPageCount);
//
//        if (remainCount > 0) {
//            totalPageCount = totalPageCount + 1;
//        } else {
//            last = currentPage + 1 == totalPageCount ? true : false;
//        }
//        request.setAttribute("last", last);

        return "index";


    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}")
    public String detail() {
        return "board/detail";
    }
}
