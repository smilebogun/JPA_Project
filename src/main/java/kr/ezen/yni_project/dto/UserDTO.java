package kr.ezen.yni_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;

    @NotNull(message = "password 파라미터가 전달되지 않았습니다.")    // Null 확인
    @NotBlank(message = "password 은 필수 입력 항목입니다.")      // 빈 문자열인 경우에 확인
    @Size(min = 4, max = 16, message = "password 은 4 ~ 16자 미만으로 입력하세요.")
    private String pw;

    @NotNull(message = "username 파라미터가 전달되지 않았습니다.")    // Null 확인
    @NotBlank(message = "username 은 필수 입력 항목입니다.")      // 빈 문자열인 경우에 확인
    @Size(min = 2, max = 16, message = "username 은 4 ~ 16자 미만으로 입력하세요.")
    private String name;

    @NotNull(message = "age 파라미터가 전달되지 않았습니다.")    // Null 확인
    @NotBlank(message = "age 은 필수 입력 항목입니다.")      // 빈 문자열인 경우에 확인
    @Size(min = 1, max = 3, message = "age 은 2 ~ 16자 미만으로 입력하세요.")
    private String age;
    private String tel;

    @NotNull(message = "email 파라미터가 전달되지 않았습니다.")    // Null 확인
    @NotBlank(message = "email 은 필수 입력 항목입니다.")      // 빈 문자열인 경우에 확인
    @Email(message = "email 형식이 아닙니다.")
    private String email;

    private String zipcode;
    private String roadAddr;
    private String detailAddr;
    private String plusAddr;

}
