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
@Table(name="admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_no")
    private int no; // 회원 일련번호

    @Column(nullable = false, length = 50, unique = true)
//    private String adminId; //아이디
    private String adminId;
    @Column(length = 100)
    private String adminPw;

    @Column(nullable = false, length = 50)
    private String adminName;

    @Column(nullable = false, length = 100)
    private String adminTel;

    @Column(nullable = false, length = 100)
    private String adminEmail;

    @Column(nullable = false, length = 50)
    private String adminRole;

    @CreationTimestamp // 현재시간이 기본값으로 등록 되도록 설정
    private Timestamp rDate;
}
