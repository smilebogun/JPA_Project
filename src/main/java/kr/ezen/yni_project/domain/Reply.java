package kr.ezen.yni_project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reply_no")
    private int id;

    private String replyContent;
    @CreationTimestamp
    private Timestamp replyUpdate;

    @ManyToOne
    @JoinColumn(name="user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name="admin_no")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name="post_no")
    private Post post;

}

