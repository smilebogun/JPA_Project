package kr.ezen.yni_project.config;

import kr.ezen.yni_project.security.common.FormAuthenticationDetailsSource;
import kr.ezen.yni_project.security.handler.CustomAccessDeniedHandler;
import kr.ezen.yni_project.security.provider.MemberAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity  // 커스터마이징을 위한 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired      // UserDetailService == memberDetailService임
    // 우리가 만들어준 memberDetailService를 Spring에 내장된 검증메소드 UserDetailService 얘한테 맡김
    private UserDetailsService memberDetailsService;    // 헷갈려서 user를 mem로 바꿈

    @Autowired
    private FormAuthenticationDetailsSource formAuthDetailsSource;

    @Autowired  // CustomAuthenticationFailureHandler 사용
    private AuthenticationFailureHandler customAuthenticationFailureHandler;

    // 알트+인서트 + 오버라이드메소드 + web:security 선택
    // static css, js, imgs 밑 경로 권한 허용 (리소스 접근 허용)
    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css/**", "/js/**", "/imgs/**", "/fileRepo");
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        // 한번에 Static > Resources는 공통 로케이션으로 설정하겠다는 의미 ==> 위에줄 업그레이드 버전
    }
///// 1단계 ///////////////////////////////////////////////////////////////////////////////////////////////
    // example 메모리에 계정추가해서 사용해보기
    // 알트+인서트 + 오버라이드메소드 + configure:Authe... 추가
//    @Override   // yml파일보다 후선으로 적용되고 유저 아이디/비번/권한이 설정 가능함
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        String cipher = pwEncoder().encode("1212");     // 1212 암호화가 안되어 사용안됌
//        auth.inMemoryAuthentication().withUser("user").password(cipher).roles("USER");
//        auth.inMemoryAuthentication().withUser("manager").password(cipher).roles("USER", "MANAGER");
//        auth.inMemoryAuthentication().withUser("admin").password(cipher).roles("USER", "MANAGER", "ADMIN");
//    }
///// 1단계 ///////////////////////////////////////////////////////////////////////////////////////////////

///// 2단계 ///////////////////////////////////////////////////////////////////////////////////////////////
    //AuthenticationManagerBuilder는 AuthenticationManager 클래스를 생성하는 역할
//    @Override   // yml파일보다 후선으로 적용되고 유저 아이디/비번/권한이 설정 가능함
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 스프링 시큐리티는 우리가 만든 MemberDetailService 객체를 가지고 인증처리 진행.
//        auth.userDetailsService(memberDetailsService);
//    }     // memberProvider를 만들어주는순간 userDetailsService는 필요없음 - 주석처리
//            // 스프링에서 해주는 자동메소드사용을 하지 않고 따로 만들어줌

//        @Override   // yml파일보다 후선으로 적용되고 유저 아이디/비번/권한이 설정 가능함
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 스프링 시큐리티는 우리가 만든 MemberDetailService 객체를 가지고 인증처리 진행.
//        auth.userDetailsService(userDetailsService);  // 스프링 자동객체 user 디테일 사용
//    }
///// 2단계 ///////////////////////////////////////////////////////////////////////////////////////////////

///// 3단계 ///////////////////////////////////////////////////////////////////////////////////////////////
    // 우리가 만든 MemberAuthenticationProvider를 참조해서 시큐리티가 인증처리를 해준다.
    @Override   // yml파일보다 후선으로 적용되고 유저 아이디/비번/권한이 설정 가능함
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(memberProvider());
        // authenticationProvider 를 추가해주는 API(메소드)
//        public AuthenticationManagerBuilder authenticationProvider(AuthenticationProvider authenticationProvider) {
//            this.authenticationProviders.add(authenticationProvider);
//            return this;
//        }
    }
    // 우리가 만든 MemberAuthenticationProvider를 참조해서 시큐리티가 인증처리를 해준다.
    @Bean
    public AuthenticationProvider memberProvider() {
        return new MemberAuthenticationProvider();
    }
///// 3단계 ///////////////////////////////////////////////////////////////////////////////////////////////

///// pwEncoder //////////////////////////////////////////////////////////////////////////////////////////
//    @Bean으로 쓸거면 private을 public으로 ==> 스프링 컨테이너에서 사용할거면 @Bean을 생성
//     암호화 사용
    @Bean
    public PasswordEncoder pwEncoder(){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return passwordEncoder;
    }
///// pwEncoder //////////////////////////////////////////////////////////////////////////////////////////

///// 최종생성 ///////////////////////////////////////////////////////////////////////////////////////////////
//     알트+인서트 + 오버라이드메소드 + http:security 선택
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http           // 시큐리티를 진행하겟다
            .authorizeRequests()
            // 특정 리소스에 대한 권한 설정 + 추가로 아래 권한에서 유저페이지 헤더쪽 다 열어줘야함
            //0.antMatchers("/**").permitAll()
            .antMatchers("/adminHome", "/admin/adminLogin", "/admin/adminRegister").permitAll()   // 루트에 대한 권한은 전부 허용 // 단 static css 권한은 없음(web:security 추가)
            .antMatchers("/", "/memberLogin.do" , "/memberRegister.do", "/admin/logout", "/auth",
                    "/memberUpdate.do*/**", "user/**", "/memberIdCheck.do",
                    "/memberTelCheck.do", "/memberEmailCheck.do", "/memberInsert.do",
                    "/pwCheck.do", "/pwChange.do", "/memberDelete.do", "/memberInfo.do",
                    "/memberLogout.do", "/idPwFind.do", "/findId.do**", "/findPw.do**").permitAll()   // 루트에 대한 권한은 전부 허용 // 단 static css 권한은 없음(web:security 추가)
            .antMatchers("/graph.do/**", "/user/**", "/post/**", "/sc/**", "/SC/**",
                    "/reply/**", "/QA/**","/QAR/**").permitAll()
            .antMatchers("/oauth/kakao", "/fileRepo/**", "/petAdopt.do").permitAll()
//            .hasAnyRole("USER", "MANAGER", "ADMIN")  // 공지사항은 매너지의 권한만 허용
//            .antMatchers("/note/**").hasAnyRole("USER", "MANAGER", "ADMIN")     // 개인노트는 유저의 권한만 허용
//            .antMatchers("/notice/**").hasAnyRole("MANAGER", "ADMIN")  // 공지사항은 매너지의 권한만 허용
//            .antMatchers("/forAll").hasRole("ADMIN")    // 환경설정은 최고관리자의 권한만 허용
            .antMatchers("/adminHome","/admin/pet**", "/admin/logout", "/admin/post*/**", "/admin/sc*/**", "/admin/adoptList*/**","/admin/backupAdoptList*/**", "/admin/registerAnno").hasAnyRole("MANAGER", "ADMIN")  // 공지사항은 매너지의 권한만 허용
            .antMatchers("/admin/**").hasRole("ADMIN")    // 환경설정은 최고관리자의 권한만 허용
                // 아래 주석 에러남
                //.antMatchers("/note").hasRole("USER")     // 개인노트는 유저의 권한만 허용
                //.antMatchers("/note", "/notice").hasRole("MANAGER")  // 공지사항은 매너지의 권한만 허용
                //.antMatchers("/note", "/notice", "/forAll").hasRole("ADMIN")    // 환경설정은 최고관리자의 권한만 허용
            .anyRequest().authenticated()
                // *** 인증 API
        .and()
            .csrf().disable()   // .csrf()를 적용시키면 회원가입이 안됌
//                .formLogin().disable()    // 시큐리티에서 제공하는 로그인 기본화면
            .formLogin()    // 시큐리티에서 제공하는 로그인 기본화면
            .loginPage("/admin/adminLogin")    // 사용자 정의 로그인 페이지 경로를 표시(컨트롤러에서 경로) // 예시)/login/login 등
            .loginProcessingUrl("/admin/login")  // 실제의 로그인 프로세스 URL (LoginOK.do 의 경로)
            .usernameParameter("adminId")
            .passwordParameter("adminPw")
            .defaultSuccessUrl("/adminHome") // 로그인 성공 후 이동 url
            .authenticationDetailsSource(formAuthDetailsSource)
            // 권한? => FormWebAuthenticationDetails 이게 생성됌
            .failureHandler(customAuthenticationFailureHandler) // 인증예외처리
            .permitAll()    // 사용자정의 로그인페이지는 누구나 접근가능
                // *** 인가 예외가 발생했을 경우
        .and()
            // ExceptionTranslationFilter가 작동되도록 초기화
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler())
        ;
    }
///// 최종생성 ///////////////////////////////////////////////////////////////////////////////////////////////

    // 페이지 설정하는 메소드
    // .accessDeniedHandler(accessDeniedHandler()) 해당메소드로 리턴 됌// .accessDeniedHandler(accessDeniedHandler()) 해당메소드로 리턴 됌
    private AccessDeniedHandler accessDeniedHandler() {
        CustomAccessDeniedHandler customAccessDeniedHandler = new CustomAccessDeniedHandler();
        customAccessDeniedHandler.setErrorPage("/denied");  // CustomAccessDeniedHandler 세터메소드 호출

        return customAccessDeniedHandler;
    }
}
