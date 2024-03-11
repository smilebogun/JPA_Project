package kr.ezen.yni_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QADTO {
    private int id;

    @NotNull(message = "제목을 입력해주세요")
    @NotBlank(message = "제목을 입력해주세요")
    @Size(max=30,message = "제목은 최대 30글자까지 입력할 수 있습니다.")
    private String qaTitle;

    @NotNull(message = "내용을 입력해주세요")
    @NotBlank(message = "내용을 입력해주세요")
    @Size(max=1000,message = "게시글 내용은 최대 1000자까지 입력할 수 있습니다.")
    private String qaContent;
}
