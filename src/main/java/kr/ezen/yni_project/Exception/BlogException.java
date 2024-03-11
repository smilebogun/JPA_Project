package kr.ezen.yni_project.Exception;

public class BlogException extends RuntimeException{

    // 생성자 : 예외 메세지를 매개변수로 받는 생성자
    public BlogException(String message){
        super(message);
    }
}
