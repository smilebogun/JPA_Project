package kr.ezen.yni_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelterDTO {

    @NotNull(message="shelName 파라미터가 전달되지 않았습니다.") //null 확인
    @NotBlank(message = "보호소 명은 필수 입력 항목 입니다") // 빈문자열
    private String shelName;

    @NotNull(message="shelArea 파라미터가 전달되지 않았습니다.") //null 확인
    @NotBlank(message = "지역은 필수 입력 항목 입니다") // 빈문자열
    private String shelArea;

    @NotNull(message="shelAddress 파라미터가 전달되지 않았습니다.") //null 확인
    @NotBlank(message = "shelAddress은 필수 입력 항목 입니다") // 빈문자열
    private String shelAddress;
    @NotNull(message="shelDetailAddress 파라미터가 전달되지 않았습니다.") //null 확인
    @NotBlank(message = "shelDetailAddress은 필수 입력 항목 입니다") // 빈문자열
    private String shelDetailAddress;
    @NotNull(message="shelPostcode 파라미터가 전달되지 않았습니다.") //null 확인
    @NotBlank(message = "shelPostcode은 필수 입력 항목 입니다") // 빈문자열
    private String shelPostcode;
    @NotNull(message="shelTel 파라미터가 전달되지 않았습니다.") //null 확인
    @NotBlank(message = "shelTel은 필수 입력 항목 입니다") // 빈문자열
    private String shelTel;
}
