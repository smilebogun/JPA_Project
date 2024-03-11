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
public class QAReply {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="QAR_no")
        private int id;

        private String QAReplyContent;
        @CreationTimestamp
        private Timestamp QAReplyUpdate;

        @ManyToOne
        @JoinColumn(name="user_no")
        private User user;
        @ManyToOne
        @JoinColumn(name = "admin_no")
        private Admin admin;


        @ManyToOne
        @JoinColumn(name="QA_no")
        private QA qa;

    }
