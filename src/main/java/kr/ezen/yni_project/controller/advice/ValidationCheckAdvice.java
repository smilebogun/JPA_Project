package kr.ezen.yni_project.controller.advice;

import kr.ezen.yni_project.dto.ResponseDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

// ##############   AOP   ##############
// 1. advice : 부가기능
// 2. joinpoint : 부가기능의 메소드들
// 3. point-cut : joinpoint(부가기능의 메소드들)의 패턴을 적용 ==> 원하는 패턴을 정의할 수 있음
// 4. target : 핵심기능
// 5. weaving : target과 advice를 합치는 과정
// 6. proxy : target과 advice가 합쳐져서 생성된 객체

@Component
@Aspect // 관점 : Aspect = Advice + Pointcut 이라고 보면 됌
//              (부가기능을 어떤 관점으로 메소드에 적용할거냐)
public class ValidationCheckAdvice {
    // controller의 시작하는 패키지에서 끝에서 *Controller로 
    // 끝나는것(즉 컨트롤러 클래스들)중에서 모든 *(..) 메소드들 포함
    @Around("execution(* kr.ezen.yni_project..controller.*Controller.*(..))")
    // ProceedingJoinPoint는 JoinPoint를 상속받고 있음 ( 부가기능을 추가하기 위한 추가 메소드 )
    public Object validationCheck(ProceedingJoinPoint jp) throws Throwable{
        // 지금 실행중인 메소드에서 매개변수를 얻어오기
        Object[] args = jp.getArgs();
        for(Object arg : args){ // arg의 instanceof 자식 BindingResult 과 비교??
            if(arg instanceof BindingResult){
                BindingResult bindingResult = (BindingResult) arg;
                // ------------- 공통코드 PostController 주석처리 --------------
                // 공통코드들의 유효성검사가 모든 컨트롤러에 들어갈 수 없기때문에 
                // 유지보수를 편하게 하기 위하여 핵심기능과 부가기능을 분리를 해놓고, 하나로 가져다 쓰기위해 만듬
                if(bindingResult.hasErrors()){
                    Map<String, String> errorMap = new HashMap<>();
                    for(FieldError error : bindingResult.getFieldErrors()){
                        // 어떤 필드에 어떤 문제가 있는지 해당메세지를 맵에 저장
                        errorMap.put(error.getField(), error.getDefaultMessage()); // 맵에 키 밸류형태로 추가
                    }
                    // 에러맵을 리턴
                    return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
                }
                // ------------- 공통코드 PostController 주석처리 --------------
            }
        }
        return jp.proceed();    // 실행..?
    }
}
