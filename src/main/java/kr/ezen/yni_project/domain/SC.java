package kr.ezen.yni_project.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class SC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SC_no")
    private int id;

    @ManyToOne
    @JoinColumn(name = "admin_no")
    private Admin admin;
    private String scImage;

    private String scTitle;

    private String scContent;

    private String scCategory;
    @CreationTimestamp
    private Timestamp SCIndate;
}
