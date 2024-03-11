package kr.ezen.yni_project.service;

import com.google.gson.Gson;
import kr.ezen.yni_project.domain.OauthType;
import kr.ezen.yni_project.domain.RoleType;
import kr.ezen.yni_project.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Service
public class KakaoLoginService {

    // Spring.framework.annotataion 으로 호출
    @Value("${kakao.default.password}")
    private String kakaoPassword;

    // 카카오에서 받은 코드로 토큰을 요청하기 위한 클래스
    public String getAccessToken(String code){

        // import는 Spring Framework에 있는걸로 불러와야함

    // ================ Http Request Message 설정 Start ================
        // HTTP MSG 로 토큰을 요청하고 받는데 Header와 Body로 구성됌
    // ---------------- Header 설정 ----------------
        HttpHeaders header = new HttpHeaders();
        header.add("Content-type",
                "application/x-www-form-urlencoded;charset=utf-8"); // 카카오요청 헤더 타입

    // ---------------- Body 설정 ----------------
        // LinkedMultiValueMap 맵으로 사용하여 key, value 한쌍으로 여러개 파라미터를 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");       // 카카오요청 바디 타입 5가지 중 필수 4가지
        body.add("client_id", "14f0feede173ead67552a0434a92fe35");
        body.add("redirect_uri", "http://localhost:8899/oauth/kakao");
        body.add("code", code);

        // HttpEntity는 위에서 설정한 Header + Body 를 MultiValueMap로 묶어 Http Request Message로 보냄
        HttpEntity<MultiValueMap<String, String>> requestEntity =
                new HttpEntity<>(body, header);
    // ================ Http Request Message 설정 End ================

        // RestTemplate은 보이지 않는 웹브라우저라고 생각하면 됌
        // 이 객체를 이용해서 http 요청을 수행함
        RestTemplate restTemplate = new RestTemplate();

        // HTTP 요청 및 응답 받기, exchange(...)는 요청메소드
        // ResponseEntity는 응답받을 메세지 "Http Response Message"를 의미
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",    // AccessToken 요청 주소
                HttpMethod.POST,            // 카카오에서 요청한 방식
                requestEntity,                // 카카오에서 요청한 헤더와 바디가 셋팅된 http Request MSG 요청
                String.class                // 카카오에서 요청한 응답 받을 타입
        );

        // 응답받을 Http Response Message 중에서 body정보만 리턴하겠다.
        // return responseEntity.getBody();
        
        // 리턴의 여러 값들중에 AcceessToken 값만 가져와야 함 ==> gson 라이브러리 사용
        String jsonData = responseEntity.getBody();
        
        Gson gson = new Gson();
        Map data = gson.fromJson(jsonData, Map.class);
        // jsonData를 자바의 맵객체로 변환
        // JSON 데이터를 MAP의 키와 값으로 저장됌

        // jsonData에서 AccessToken의 Value(값)만 가져옴
        return (String)data.get("access_token");
    }

    // 사용자 정보 가져오기
    public User getUserInfo(String accessToken){

        // import는 Spring Framework에 있는걸로 불러와야함

        // ================ Http Request Message 설정 Start ================
        // HTTP MSG 로 토큰을 요청하고 받는데 Header와 Body로 구성됌
        // ---------------- Header만 설정 ----------------
        HttpHeaders header = new HttpHeaders();
        // Authorization: Bearer ${ACCESS_TOKEN}
        header.add("Authorization",
                "Bearer " + accessToken); // Bearer 뒤에 공백이 꼭 있어줘야 함
        header.add("Content-type",
                "application/x-www-form-urlencoded;charset=utf-8"); // 카카오요청 헤더 타입
        // ---------------- Body는 필요없음----------------

        // HttpEntity는 위에서 설정한 Header + Body 를 MultiValueMap로 묶어 Http Request Message로 보냄
        HttpEntity<MultiValueMap<String, String>> requestEntity =
                new HttpEntity<>(header);
        // ================ Http Request Message 설정 End ================

        // RestTemplate은 보이지 않는 웹브라우저라고 생각하면 됌
        // 이 객체를 이용해서 http 요청을 수행함
        RestTemplate restTemplate = new RestTemplate();

        // HTTP 요청 및 응답 받기, exchange(...)는 요청메소드
        // ResponseEntity는 응답받을 메세지 "Http Response Message"를 의미
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",    // AccessToken 요청 주소
                HttpMethod.POST,            // 카카오에서 요청한 방식
                requestEntity,                // 카카오에서 요청한 헤더와 바디가 셋팅된 http Request MSG 요청
                String.class                // 카카오에서 요청한 응답 받을 타입
        );

        // 응답받을 Http Response Message 중에서 body정보만 리턴하겠다.
        //return responseEntity.getBody(); // 토큰을 요청 테스트 ==> 토큰을 이용한 사용자 정보가 필요함

//        // 리턴의 여러 값들중에 AcceessToken 값만 가져와야 함 ==> gson 라이브러리 사용
//        String jsonData = responseEntity.getBody();
//
//        Gson gson = new Gson();
//        Map data = gson.fromJson(jsonData, Map.class);
//        // jsonData를 자바의 맵객체로 변환
//        // JSON 데이터를 MAP의 키와 값으로 저장됌
//
//        // jsonData에서 AccessToken의 Value(값)만 가져옴
//        return (String)data.get("access_token");

        // 토큰을 이용한 사용자 정보가 필요함
//        String userInfo = responseEntity.getBody();
//        return userInfo;

        // 사용자 정보에서 kakao_account > email 값 가져오기
        String userInfo = responseEntity.getBody();
        Gson gson = new Gson();
        Map data = gson.fromJson(userInfo, Map.class);
        // jsonData를 자바의 맵객체로 변환
        // JSON 데이터를 MAP의 키와 값으로 저장됌

        String email = (String)((Map)(data.get("kakao_account"))).get("email");

        User user = new User();
        user.setId(email);
        // 임시비밀번호 생성, java.util 안에 UUID를 이용
//		String uuid = UUID.randomUUID().toString(); // c5f3b7e9-4f18-4d90-9664-bc717bfc8834
//		System.out.println(uuid);
        String uuid = UUID.randomUUID().toString().substring(0, 8); // 앞에서 8자리 자름
        user.setPw(kakaoPassword);
        user.setName("KakaoName");
        user.setAge("KakaoAge");
        user.setTel("KakaoTel");
        user.setEmail(email);
        user.setZipcode("00000");
        user.setRoadAddr("KakaoRoadAddr");
        user.setDetailAddr("KakaoDetailAddr");
        user.setPlusAddr("KakaoPlusAddr");
        user.setRole(RoleType.ROLE_USER);
        user.setOAuth(OauthType.KAKAO);

        return user;
    }
}
