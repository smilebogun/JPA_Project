package kr.ezen.yni_project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 인터셉터 구현, IOC관리
@Configuration  // 환경설정파일이라고 인식
// component 스캔을 통해서 자동으로 Bean 생성 -> Bean은 스프링컨테이너가 관리해주는 객체
public class YnIConfig implements WebMvcConfigurer {
    // alt + insert 로 인터셉터 진행 --> ppt파일에 스샷

    // 보통 시큐리티를 사용하면 인터셉터를 사용하지 않음
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/", "/graph.do/**", "/user/**", "/post/**", "/sc/**", "/memberInfo.do**", "/memberLogout.do");
        // 인터셉터가 "/"루트형으로 들어왔을때 진행
    }

    @Bean       // 빌드그래들의 디펜던시.model매퍼추가 이후 사용하기위한 환경설정
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
