package kr.ezen.yni_project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_no")
    private int id;

    private String postTitle;
    private String postContent;
    private String postWriter;
    @ManyToOne
    @JoinColumn(name="user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name="admin_no")
    private Admin admin;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    // cascade = CascadeType.REMOVE 댓글 있어서 댓글까지 삭제
    @OrderBy("id desc")
    private List<Reply> replyList = new ArrayList<>();

    private String postCategory;
    private int postViewCnt;
    @Lob
    private String postImage1;
    private String postImage2;
    private String postImage3;
    private int postTemp;

    @CreationTimestamp
    private Timestamp postIndate;

}
