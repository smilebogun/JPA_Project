package kr.ezen.yni_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data@AllArgsConstructor@NoArgsConstructor
public class ReplyDTO {
    private String id;
    @NotNull(message = "댓글을 입력해주세요")
    @NotBlank(message = "댓글을 입력해주세요")
    @Size(max=30,message = "댓글은 최대 200자까지 입력할 수 있습니다.")
    private String replyContent;
}
