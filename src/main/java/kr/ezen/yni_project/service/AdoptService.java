package kr.ezen.yni_project.service;

import kr.ezen.yni_project.domain.*;
import kr.ezen.yni_project.repository.AdoptRepository;
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
import java.util.Optional;
import java.util.UUID;

@Service
public class AdoptService {

    @Autowired
    private AdoptRepository adoptRepository;

//    @Autowired // root-context에서 생성된 Bean class 를 주입받기 // servlet에서 생성된건 받지 못함
//    JavaMailSender mailSender;

//    @Autowired	// 암호화를위해서 root 추가한 pwEncoder에서 주입받음
//    private PasswordEncoder pwEncoder;

    // ID 중복체크 #1 // 회원가입 OK // 로그인 OK
    public List<Adopt> getAdoptUser(String userId) {
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
        List<Adopt> adoptUser = adoptRepository.findByUserId(userId); // 또 생략

        return adoptUser;
    }

    // 입양예정 OK
    public void insertAdopt(Adopt adopt) {
        adoptRepository.save(adopt);
    }

    //    // 멤버정보(인포)폼에서 수정OK
//    @Transactional
//    public void memberUpdate(User user) {
//        User findUser = userRepository.findById(user.getNo()).orElseGet(() -> new User());
//        findUser.setName(user.getName());
//        findUser.setAge(user.getAge());
//        findUser.setTel(user.getTel());
//        findUser.setZipcode(user.getZipcode());
//        findUser.setRoadAddr(user.getRoadAddr());
//        findUser.setDetailAddr(user.getDetailAddr());
//        findUser.setPlusAddr(user.getPlusAddr());
//    }
//
    /* 추가 */
    // 관리자 홈에서 유저 리스트 폼 이동 (리스트1로 이동)
    public List<Integer> getAdoptPetNoList() {
//      List<String> adoptList = adoptRepository.findDistinctByPetName(PetName);
        List<Integer> petNoList = adoptRepository.petNoList();
        return petNoList;
    }

    /* 추가 */
    // 관리자 홈에서 유저 리스트 폼 이동
//    public List<Adopt> getAdoptList() {
//        List<Adopt> adoptList = adoptRepository.findAll();
//        return adoptList;
//    }

    // 리스트1에서 limit 1 // 시켜서 뷰로 이동
    public Adopt getAdoptPetList(Integer petNo) {
//        Adopt adoptPetList = adoptRepository.adoptPetList(petNo);
        Adopt adoptPetList = adoptRepository.findFirst1ByPetNo(petNo);
        return adoptPetList;
    }

    // 리스트 2 이동
    public List<Adopt> getAdoptList2(int petNo) {
        List<Adopt> adoptList2 = adoptRepository.findByPetNo(petNo);
        return adoptList2;
    }

    // 입양확정 OK #1
    @Transactional
    public void updateAdopt(int no) {
        Adopt adopt = adoptRepository.findById(no).orElseGet(() -> new Adopt());
        adopt.setAdoptStatus("3");
    }
    @Transactional
    public void updateAdopt2(int no) {
        Adopt adopt = adoptRepository.findById(no).orElseGet(() -> new Adopt());
        adopt.setAdoptStatus("2");
    }
    // 입양확정 OK #2
//    public List<Adopt> getAdoptPetNo(int petNo) {
//        List<Adopt> list = adoptRepository.findByPetNo(petNo);
//        return list;
//    }

    // 입양확정내역 리스트 이동 #1
    public Page<Adopt> getAdoptListBackupList(String adoptStatus, Pageable pageable) {
        Page<Adopt> list = adoptRepository.findByAdoptStatus(adoptStatus, pageable);
        return list;
    }
    // 입양확정내역 리스트 이동 #2
    public Adopt getAdoptListBackupList2(int no) {
        Adopt list = adoptRepository.findById(no).get();
        return list;
    }

    /* 추가 */
    // 나의 입양신청리스트
    public List<Adopt> getAdoptList() {
        List<Adopt> adoptList = adoptRepository.findAll();
        return adoptList;
    }

    // 나의 입양신청리스트에서 삭제OK
    @Transactional
    public void adoptDelete(int no) {
        adoptRepository.deleteById(no);
    }

    // 입양등록 유효성 검사
    public List<Adopt> getAdoptPetNoANDUserId(int petNo, String id) {
        List<Adopt> list = adoptRepository.findByPetNoAndUserId(petNo,id);
        return list;
    }

//    public List<Adopt> adoptGraph(String adoptStatus) {
//        List<Adopt> adoptGraph = adoptRepository.findByAdoptStatus(adoptStatus);
//        System.out.println("adoptGraph = " + adoptGraph);
//        return adoptGraph;
//    }

    //펫 번호로 검색시 나오는 리스트중 가장 위에 1개를 가져와서 listStatus와 cnt를 넣어준다
    @Transactional
    public void petNoList(int petNo, int cnt, String listStatus){
        Adopt adopt = adoptRepository.findTop1ByPetNo(petNo);
        adopt.setListStatus(listStatus);
        adopt.setAdoptCnt(cnt);
    }

    //입양신청내역 리스트 페이징처리를 위해 새로 추가함
    public Page<Adopt> getAdoptList3(String number, Pageable pageable, String number2) {
        Page<Adopt> list = adoptRepository.findByListStatusAndAdoptStatus(number,number2, pageable);
        return list;
    }

    //입양신청한 user들 리스트 페이징처리를 위해 추가
    public Page<Adopt> getAdoptList2Page(int petNo, Pageable pageable) {
        Page<Adopt> adoptList2 = adoptRepository.findByPetNo(petNo, pageable);
        return adoptList2;
    }
}
