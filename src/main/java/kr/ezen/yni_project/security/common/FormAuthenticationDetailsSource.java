package kr.ezen.yni_project.security.common;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

// 이 클래스는 FormWebAuthenticationDetails를 생성하는 클래스
@Component // 컴포넌트는 Spring 컨네이너에다가 저장? securityConfig에서 주입받아 사용하게끔 하는역할?
public class FormAuthenticationDetailsSource implements 
        AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {
    
    // 알트인서트 > 임플리먼트 메소드 > 빌드디테일스 생성
    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        // context는 폼웹어센티케이션디테일스의 전달된 request
        return new FormWebAuthenticationDetails(context);
    }
}
