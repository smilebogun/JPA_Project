package kr.ezen.yni_project.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "shelter")
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelter_no")
    private int no; // 회원 일련 번호

    @Column(nullable = false, length = 50, unique = true)
    private String shelName;

    @Column(length = 100)
    private String shelArea;
    @Column(nullable = false)
    private String shelAddress;
    @Column(nullable = false, length = 100)
    private String shelDetailAddress;
    @Column(nullable = false)
    private String shelPostcode;
    @Column(nullable = false)
    private String shelTel;


    //    @Enumerated(EnumType.STRING)
    @CreationTimestamp // 현재시간이 기본값으로 등록 되도록 설정
    private Timestamp shelSysdate;
}

