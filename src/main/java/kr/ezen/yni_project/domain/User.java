package kr.ezen.yni_project.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data // getter, setter, tostring, equals,...
@Table(name = "users")  // 이름을 User로 했지만 DB-Table에는 users로 만들어짐
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private int no; // 회원의 일련번호

    @Column(nullable = false, length = 50, unique = true)
    // null을 허용하지 않겠다 = not null과 같음, 길이 50, 유니크조건 true
    private String id;    // 아이디
    @Column(nullable = false, length = 200)
    private String pw;    // 비번
    @Column(nullable = false, length = 50)
    private String name;    // 아이디
    @Column(nullable = false, length = 10)
    private String age;    // 아이디
    @Column(nullable = false, length = 20, unique = true)
    private String tel;    // 아이디
    @Column(nullable = false, length = 50)
    private String email;

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
    private Timestamp createDate;
    @UpdateTimestamp
    private Timestamp updateDate; // 업데이트 날짜
}