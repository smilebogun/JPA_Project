package kr.ezen.yni_project.dto;

import kr.ezen.yni_project.domain.Admin;
import kr.ezen.yni_project.domain.Reply;
import kr.ezen.yni_project.domain.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Data
public class PostDTO {
    private int id;
    @NotNull(message = "제목을 입력해주세요")
    @NotBlank(message = "제목을 입력해주세요")
    @Size(max=30,message = "제목은 최대 30글자까지 입력할 수 있습니다.")
    private String postTitle;

    @NotNull(message = "내용을 입력해주세요")
    @NotBlank(message = "내용을 입력해주세요")
    @Size(max=1000,message = "게시글 내용은 최대 1000자까지 입력할 수 있습니다.")
    private String postContent;
    private String postWriter;
    private User user;
    private Admin admin;
    private List<Reply> replyList;
    private String postCategory;
    private int postViewCnt;
    private String postImage1;
    private int postTemp;
    private Timestamp postIndate;
}
