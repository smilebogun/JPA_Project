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
public class QA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QA_no")
    private int id;

    private String qaTitle;
    private String qaContent;
    @CreationTimestamp
    private Timestamp qaIndate;

    private String answer;

    @OneToMany(mappedBy = "qa",cascade = CascadeType.REMOVE)
    @OrderBy("id desc")
    private List<QAReply> qaReplyList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="user_no")
    private User user;

}
