package kr.ezen.yni_project.security.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

// 인증 실패 후 추가작업 처리하는 클래스
@Component  // Config에 주입받기 위하여 컴포넌트를 적어준다
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = "Invalid username or password!!";

        // 1. 아이디, 2. 비번, 3. 시크릿 키 검증된 이후 메세지 발생
        if(exception instanceof UsernameNotFoundException){
            errorMsg = "Invalid username or password!!";
        } else if(exception instanceof BadCredentialsException){   // 멤버어센티케이션프로바이더 검증2가지를 받아서 예외처리
            errorMsg = "Invalid username or password!!";
            // errorMsg = "비번 불일치";
        } else if(exception instanceof InsufficientAuthenticationException){
            errorMsg = "Secret Key Error!!";
        }

        errorMsg = URLEncoder.encode(errorMsg, "UTF-8");    // 한글처리
        setDefaultFailureUrl("/admin/adminLogin?error=true&exception="+errorMsg);

        super.onAuthenticationFailure(request, response, exception);
    }
}
