package kr.ezen.yni_project.security.provider;

import kr.ezen.yni_project.security.common.FormWebAuthenticationDetails;
import kr.ezen.yni_project.security.service.MemberDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MemberAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private UserDetailsService memberDetailsService; // 헷갈려서 user를 mem로 바꿈

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 사용자가 입력한 username, password를 가져옴
        String adminId = authentication.getName();
        String pw = (String)authentication.getCredentials();
        // 리턴타입이 오브젝트라 String 타입으로 변경

        // DB에서 가져온 사용자 정보 - 자동메소드 UserDetails에서 형변환 진행 => (MemberDetails)
        MemberDetails memberDetails = (MemberDetails)memberDetailsService.loadUserByUsername(adminId);

        // ---------- 추가 검증하기 ----------
        FormWebAuthenticationDetails formWebAuthenticationDetails
                = (FormWebAuthenticationDetails)authentication.getDetails(); // Details 가져와서 secretKey 가져옴
        String secretKey = formWebAuthenticationDetails.getSecretKey();
        System.out.println("secretKey = " + secretKey);

        if(secretKey == null || !"secretData".equals(secretKey)){
            System.out.println("SecretKey Error!!");
            // login th:value="secretData"를 다른데이터인 th:value="secretData11"로 바꾸면 에러메세지 발생
            throw new InsufficientAuthenticationException("InsufficientAuthenticationException!!");
        }
        // ---------- 추가 검증하기 ----------
        // 비밀번호 검증 실패한 경우, 입력한 pw와 DB에서 가져온 비밀번호가 일치하지 않으면 예외발생
        if(!passwordEncoder.matches(pw,
                memberDetails.getAdmin().getAdminPw())) {
            throw new BadCredentialsException("Invalid Password!!");
        }
        // 인증 전 토큰을 만들때 2개의 파라미터 필요
        // 인증 성공 후 토큰을 만들때 3개의 파라미터가 필요
        // 인증에 성공한 경우 3개의 파라미터 필요,
        // 인증에 성공한 1.사용자객체, 2.패스워드 정보, 3.권한정보를
        // 파라미터로 해서 인증토큰을 만들어 준다
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(memberDetails.getAdmin(),
                null, memberDetails.getAuthorities());

        // AuthenticationManager에게 전달해준다.
        return authenticationToken;
    }

    // 필터로부터 전달되는 authentication 타입과
    // 우리가 사용하고자 하는 UsernamePasswordAuthenticationToken 토큰의 타입이 일치하는지 확인
    // 확인 이후 리턴값이 True 이면 위 authenticate 호출
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
        // 테이블 만든것과 토큰의 타입이 일치하는지 확인
    }
}
