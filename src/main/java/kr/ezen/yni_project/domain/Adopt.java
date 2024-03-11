package kr.ezen.yni_project.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "adopt")
public class Adopt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "adopt_no")
    private int no;

    // Pet
    @Column(nullable = false)
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
    private String petAdopt; // 기본 : 입양대기
    @Column(nullable = false)
    private String petSysdate; // 등록날짜
    @Column(nullable = false)
    private String petUpdate; // 업데이트 날짜

    // User
    @Column(nullable = false)
    private int userNo; // 회원의 일련번호
    @Column(nullable = false, length = 50)
    // null을 허용하지 않겠다 = not null과 같음, 길이 50, 유니크조건 true
    private String userId;    // 아이디
    @Column(nullable = false, length = 200)
    private String userPw;    // 비번
    @Column(nullable = false, length = 50)
    private String userName;    // 아이디
    @Column(nullable = false, length = 10)
    private String userAge;    // 아이디
    @Column(nullable = false, length = 20)
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
    @Column(nullable = false)  // 현재시간이 기본값으로 등록되도록 설정, default = now()랑 같음
    private String userCreateDate;
    @Column(nullable = false)
    private String userUpdateDate; // 업데이트 날짜

    @Column(nullable = false)
    private String adoptStatus;
    @Column(nullable = false)
    private String listStatus;
    @Column(nullable = false)
    private int adoptCnt;

    @CreationTimestamp  // 현재시간이 기본값으로 등록되도록 설정, default = now()랑 같음
    private Timestamp adoptCreateDate;
    @UpdateTimestamp
    private Timestamp adoptUpdateDate; // 업데이트 날짜
}

