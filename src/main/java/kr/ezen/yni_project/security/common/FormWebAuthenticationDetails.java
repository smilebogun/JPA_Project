package kr.ezen.yni_project.security.common;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

// 이 클래스는 사용자가 전달한 추가적인 파라미터(쿼리, 값)를 저장하는 클래스
public class
FormWebAuthenticationDetails extends WebAuthenticationDetails {
    private String secretKey;
    
    // 알트 인서트 > 컨스트럭터, 생성자생성 > FormWebAuthenticationDetails 생성
    // 매개변수 request는 전달받아서 넘겨줄때 사용
    public FormWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        secretKey = request.getParameter("secret_key");
    }

    // secretKey를 가져오는 게터메소드 생성
    public String getSecretKey(){
        return secretKey;
    }
}
