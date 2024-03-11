package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.User;
import kr.ezen.yni_project.service.KakaoLoginService;
import kr.ezen.yni_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class KakaoLoginController {

    @Autowired
    private KakaoLoginService kakaoLoginService;

    @Autowired
    private UserService userService;

    // 카카오 로그인
    @GetMapping("/oauth/kakao")
    public String kakaoCallback(String code, HttpSession session){
    //public @ResponseBody User kakaoCallback(String code){
    //public @ResponseBody String  kakaoCallback(String code){

        // return "카카오 서버에서 보낸 CODE : " + code; // 코드 확인을 위한 테스트페이지

        // 1. 서버로부터 받은 인가코드를 이용하여 Access Token을 받기
        String accessToken = kakaoLoginService.getAccessToken(code);
        // return accessToken; // 토큰을 요청 테스트 ==> 토큰을 이용한 사용자 정보가 필요함 2번으로 진행

        // accessToken 리턴 값 JSON 형태로 확인
//        {
//            "access_token": "GYBgeZ2tCtS7OSajxfDm9EZGe4jHDQltx4EKPXVbAAABjWJ8dFP-oZq-Jypvmw",
//                "token_type": "bearer",
//                "refresh_token": "Jfwv-dvel1St-lGOR-RE-0-YfyUsEUxxHhUKPXVbAAABjWJ8dFH-oZq-Jypvmw",
//                "expires_in": 21599,
//                "scope": "profile_nickname",
//                "refresh_token_expires_in": 5183999
//        }

        // 2. accessToken을 이용한 사용자 정보 가져오기
        //return kakaoLoginService.getUserInfo(accessToken);
        // 토큰을 이용한 사용자 정보가 필요함
//        String userInfo = kakaoLoginService.getUserInfo(accessToken);
//        return userInfo;
        // return kakaoLoginService.getUserInfo(accessToken);
        // 사용자 정보 값 확인되었으면 3번으로 넘어가기

        // userInfo 리턴 값 JSON 형태로 확인
//        {
//            "id": 3323883482,
//                "connected_at": "2024-02-01T02:25:40Z",
//                "properties": {
//            "nickname": "김보건"
//        },
//            "kakao_account": {
//            "profile_nickname_needs_agreement": false,
//                        "profile": {
//                    "nickname": "김보건"
//                },
//                "has_email": true,
//                        "email_needs_agreement": false,
//                        "is_email_valid": true,
//                        "is_email_verified": true,
//                        "email": "qldotjq@naver.com"
//            }
//        }

        // 3. 가져온 정보를 가지고 카카오 회원정보를 이용하여 회원가입하기
        User kakaoUser = kakaoLoginService.getUserInfo(accessToken);
        User findUser = userService.getUser(kakaoUser.getId());

        // 이미 가입된 회원인지 확인한다.
        if(findUser.getId() == null){
            // 기존 회원이 아니면 가입진행
            userService.insertUser(kakaoUser);
        }
        // 기존회원이 아니면 회원가입 진행 하고 로그인 // 기존회원이면 그냥 로그인

        // 4. 카카오 정보를 이용하여
        session.setAttribute("principal", findUser);

        return "redirect:/";
    }
}
