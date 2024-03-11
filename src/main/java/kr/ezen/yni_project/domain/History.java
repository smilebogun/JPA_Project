package kr.ezen.yni_project.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "History")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int historyNo;

    // Pet
    @Column(name = "pet_no")
    private int petNo; // 회원 일련 번호

    @Column(nullable = false, length = 50)
    private String shelName; //보호소 이름

    @Column(nullable = false)
    private String shelArea; //지역

    @Column(length = 100)
    private String petSpecies; // 동물 종 분류

    @Column(nullable = false)
    private String petName; //견/묘종

    @Column(nullable = false, length = 100)
    private String petAge; // 펫나이

    @Column(nullable = false)
    private String petGender; //펫 성별

    @Column(nullable = false)
    private String petNeutering; // 중성화 여부

    @Column(nullable = false)
    private String petInoculation; //접종 여부

    @Column(nullable = false)
    private String petPlace; //발견된 장소

    @Column(nullable = false)
    private String petRemarks; //특이사항

    @Column(nullable = false)
    private String petImage; //이미지

    @Column(nullable = false)
    private String petAdopt;

    @CreationTimestamp
    private Timestamp petSysdate; // 등록날짜

    @CreationTimestamp
    private Timestamp petUpdate; // 업데이트 날짜

    // User
    @Column(name = "user_no")
    private int userNo; // 회원의 일련번호

    @Column(nullable = false, length = 50, unique = true)
    // null을 허용하지 않겠다 = not null과 같음, 길이 50, 유니크조건 true
    private String userId;    // 아이디
    @Column(nullable = false, length = 200)
    private String userPw;    // 비번
    @Column(nullable = false, length = 50)
    private String userName;    // 아이디
    @Column(nullable = false, length = 10)
    private String userAge;    // 아이디
    @Column(nullable = false, length = 20, unique = true)
    private String userTel;    // 아이디
    @Column(nullable = false, length = 50)
    private String userEmail;

    @Column(nullable = false, length = 5)
    private String zipcode;
    @Column(nullable = false, length = 100)
    private String roadAddr;
    @Column(nullable = false, length = 100)
    private String detailAddr;
    @Column(length = 100)
    private String plusAddr;

    //--> Enum 생성방법 : enum을 쓰는이유는 코딩할때부터 다른값이 못들어옴(무결성 에러가 줄어듬)
    @Enumerated(EnumType.STRING)
    // EnumType.STRING : DB에 입력할때 문자열로 String USER, ADMIN으로 입력하겠다.
    // EnumType.ORDINAL : enum의 순서값으로 입력하겠다.
    private RoleType role;

    @Enumerated(EnumType.STRING)
    private OauthType oAuth;

    @CreationTimestamp  // 현재시간이 기본값으로 등록되도록 설정, default = now()랑 같음
    private Timestamp userCreateDate;

    @CreationTimestamp  // 현재시간이 기본값으로 등록되도록 설정, default = now()랑 같음
    private Timestamp CreateDate;
}
