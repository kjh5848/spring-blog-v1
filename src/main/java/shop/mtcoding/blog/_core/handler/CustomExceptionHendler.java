package shop.mtcoding.blog._core.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.mtcoding.blog._core.util.Script;

@RestControllerAdvice
//@ControllerAdvice//응답 에러 컨트롤러(view==파일 리턴)
public class CustomExceptionHendler {

    @ExceptionHandler(Exception.class)
    public  String error1(Exception e) {
        return Script.back(e.getMessage());
    }
}
