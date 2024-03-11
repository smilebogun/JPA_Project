package kr.ezen.yni_project.Exception;

import kr.ezen.yni_project.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// @ControllerAdvice : 어플리케이션(프로잭트) 내에 모든 컨트롤러에서 발생하는 예외를 처리
// @ControllerAdvice 를 사용하여 컨트롤러는 좀 더 자신의 역할에 집중할 수 있고,
//          중복된 예외처리 코드를 제거 할 수 있다. 즉, 관심사의 분리를 이뤄낼 수 있다.
@ControllerAdvice
@RestController
// RestController를 사용했기때문에 @ResponseBody를 안붙임
// @RestController == @Controller + @ResponseBody
public class BlogExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    // 아래와 동일
//    public String globalExceptionHandler(Exception e){
//        return "<h1>" + e.getMessage() + "</h1>";
//    }

    // 400번대 에러는 클라이언트 잘못
    // 500번대는 서버에러
    // ResponseDTO를 이용하면 일관된 응답처리를 할 수 있다.
    // JSON 방식으로 리턴 - 블로그익셉션핸들러도 ResponseDTO 방식으로 리턴
    public ResponseDTO<?> globalExceptionHandler(Exception e){
        return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
