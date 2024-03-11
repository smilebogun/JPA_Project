package kr.ezen.yni_project.service;

import kr.ezen.yni_project.domain.Adopt;
import kr.ezen.yni_project.domain.OauthType;
import kr.ezen.yni_project.domain.RoleType;
import kr.ezen.yni_project.domain.User;
import kr.ezen.yni_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired // root-context에서 생성된 Bean class 를 주입받기 // servlet에서 생성된건 받지 못함
    JavaMailSender mailSender;

    @Autowired	// 암호화를위해서 root 추가한 pwEncoder에서 주입받음
    private PasswordEncoder pwEncoder;

    // ID 중복체크 #1 // 회원가입 OK // 로그인 OK
    public User getUser(String id) {
        // 기존에 .findByUsername()이 없음 기본 .findById 밖에 없음 ==> UserRepository 계층에서 생성해줘야 함
        // 예외를 던진게 아니라 .orElseThrow가 아닌 User객체를 리턴할거라 .orElseGet를 사용
//        User findUser = userRepository.findByUsername(username).orElseGet(new Supplier<User>() {
//            @Override
//            public User get() {
//                return new User();
//            }
//        });
        // 가독성때문에  위코드 대신 람다형식으로 변환해서 사용
//        User findUser = userRepository.findByUsername(username).orElseGet( () -> {return new User();} );
        User findUser = userRepository.findById(id).orElseGet(() -> new User()); // 또 생략

        return findUser;
    }

    // 회원가입 OK
    public void insertUser(User user) {
        // RoleType 설정 -> enum에 설정된 값으로만 읽을 수 있음(무결성 에러방지)
        user.setRole(RoleType.ROLE_USER);

        if(user.getOAuth() == null) {
            user.setOAuth(OauthType.YNI);
        }

        String inputPw = user.getPw();		// 회원가입시 입력된 비번
        String chiperPw = pwEncoder.encode(inputPw);	// 암호화된 비번으로 다시 dto에 셋팅 후 전달
        user.setPw(chiperPw);
        userRepository.save(user);
    }

    // 멤버 ID 중복체크 #2 // 회원 등록폼에서 ID 중복체크 ajax
    public User memberIdCheck(String uid) {
        System.out.println("uid = " + uid);
        return userRepository.findById(uid).orElseGet(() -> new User());
    }

    // 멤버 전화번호 중복체크 // 회원 등록폼에서 tel 중복체크 ajax // 아이디 찾기 OK
    public User memberTelCheck(String utel) {
        System.out.println("utel = " + utel);       // .get() 은 null값은 가져오지 못함
        return userRepository.findByTel(utel).orElseGet(() -> new User());
    }

    // 비밀번호 찾기 OK // 임시비밀번호 발급 이후 임시비밀번호로 로그인
    @Transactional
    public User findPw(String id, String email) {
        // 임시비밀번호 생성, java.util 안에 UUID를 이용
//		String uuid = UUID.randomUUID().toString(); // c5f3b7e9-4f18-4d90-9664-bc717bfc8834
//		System.out.println(uuid);
        String tempPw = UUID.randomUUID().toString().substring(0, 8); // 앞에서 8자리 자름

        User finduser = userRepository.findByIdAndEmail(id, email).orElseGet(() -> new User());

        System.out.println("##############finduser = " + finduser);
        if (finduser.getNo() != 0) {
            String chiperPw = pwEncoder.encode(tempPw);	// 암호화된 비번으로 다시 dto에 셋팅 후 전달
            finduser.setPw(chiperPw);
            String uEmail = email;

            // MimeMessage 객체 생성 : 데이터(Mime 타입) 전송 (예 : text/html, image/jpg)
            MimeMessage mail = mailSender.createMimeMessage();
            // 보내지는 메일내용 셋팅
            String mailContents = "<h3>임시 비밀번호 발급</h3><br/>"
                    + "<h2>" + tempPw + "</h2>"
                    + "<p>임시 비밀번호가 발급되었습니다. 로그인 이후에 비밀번호를 재설정 해주세요.</p>";

            try {
                // 보내지는 메일제목 셋팅
                mail.setSubject("ez-아카데미 [임시 비밀번호]", "utf-8");
                // 보내지는 메일 내용셋팅
                mail.setText(mailContents, "utf-8", "html");
                // 보내지는 메일 수신자셋팅 - 인터넷 주소체계로 바꿔서 uEmail로 보냄
                mail.addRecipient(RecipientType.TO, new InternetAddress(uEmail));
                mailSender.send(mail);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        //return userRepository.save(finduser);
        return finduser;
    }
    
    // 내 정보수정, 내 비밀번호수정 조회하기
    public User memberInfo(int no) {
        return userRepository.findById(no).orElseGet(() -> new User());
    }

    // 멤버정보(인포)폼에서 수정OK
    @Transactional
    public void memberUpdate(User user) {
        User findUser = userRepository.findById(user.getNo()).orElseGet(() -> new User());
        findUser.setName(user.getName());
        findUser.setAge(user.getAge());
        findUser.setTel(user.getTel());
        findUser.setZipcode(user.getZipcode());
        findUser.setRoadAddr(user.getRoadAddr());
        findUser.setDetailAddr(user.getDetailAddr());
        findUser.setPlusAddr(user.getPlusAddr());
        // 업데이트 날짜가 어떻게 찍히는지 테스트해봐야 함
        findUser.setUpdateDate(user.getUpdateDate());
    }

    // 마이프로필 폼 -> 비밀번호 변경 OK
    @Transactional
    public int modifyPw(User user) {
        User findUser = userRepository.findById(user.getId()).orElseGet(() -> new User());
        String inputPw = user.getPw();		// 회원가입시 입력된 비번
        String chiperPw = pwEncoder.encode(inputPw);	// 암호화된 비번으로 다시 dto에 셋팅 후 전달
        findUser.setPw(chiperPw);
        return findUser.getNo();
    }

    // 멤버리스트에서 삭제OK (회원탈퇴)
    @Transactional
    public void memberDelete(int no) {
        userRepository.deleteById(no);
    }

    /* 추가 */
    // 관리자 홈에서 유저 리스트 폼 이동
    public List<User> getUserList() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    // 페이징 처리
    public Page<User> getUserList2(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
