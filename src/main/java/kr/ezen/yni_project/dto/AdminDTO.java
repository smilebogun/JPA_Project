package kr.ezen.yni_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {

    // dependencies 추가 후 사용 유효성검사!
//    implementation 'org.springframework.boot:spring-boot-starter-validation'
//    https://mvnrepository.com/artifact/org.modelmapper/modelmapper
//    implementation group: 'org.modelmapper', name: 'modelmapper', version: '2.4.4'

    @NotNull(message="adminId 파라미터가 전달되지 않았습니다.") // null 확인
    @NotBlank(message="adminId 필수 입력 항목 입니다.") // 빈문자열
    @Size(min = 1, max=20, message = "adminId 1 ~ 20자 미만으로 입력하세요")
    private String adminId;

    @NotNull(message="adminPw 파라미터가 전달되지 않았습니다.") // null 확인
    @NotBlank(message="adminPw 필수 입력 항목 입니다.") // 빈문자열
    private String adminPw;

    @NotNull(message="adminName 파라미터가 전달되지 않았습니다.") // null 확인
    @NotBlank(message="adminName 필수 입력 항목 입니다.") // 빈문자열
    private String adminName;

    @NotNull(message="adminTel 파라미터가 전달되지 않았습니다.") // null 확인
    @NotBlank(message="adminTel 필수 입력 항목 입니다.") // 빈문자열
    private String adminTel;

    @NotNull(message="adminRole 파라미터가 전달되지 않았습니다.") // null 확인
    @NotBlank(message="adminRole 필수 입력 항목 입니다.") // 빈문자열
    private String adminRole;

    @NotNull(message="email 파라미터가 전달되지 않았습니다.") // null 확인
    @NotBlank(message="email은 필수 입력 항목 입니다.") // 빈문자열
    @Email(message = "이메일 형식이 아닙니다.")
    private String adminEmail;
}
