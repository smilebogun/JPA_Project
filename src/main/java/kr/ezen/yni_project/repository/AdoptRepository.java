package kr.ezen.yni_project.repository;

import kr.ezen.yni_project.domain.Adopt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdoptRepository extends JpaRepository<Adopt, Integer> {  // 제네릭은 int로 안되고 Integer로 변경

    // 쿼리메소드
    // findUserByUsername 에서 엔티티인 User는 생략
    // find + 엔티티 + By + 변수명 == > findUserByUsername

    // SELECT * FROM users WHERE username = ?1;   // #{username}
    // Optional을 사용하면 JPQL문을 만들고 ==> 일반 위와 같은 SQL문이 생성되어 DB에 전송이 됌

    // ID 중복체크 // 회원가입 OK // 로그인 OK // 회원 등록폼에서 ID 중복체크 ajax
    List<Adopt> findByUserId(String userid);

    // 입양예약 중복제거 1페이지 ==> distinct
//   1번 방식 @Query("select distinct petNo from Adoption where adoptionStatus = 0")
//   2번 방식 List<String> findDistinctByPetName(String PetName);
    @Query("select distinct petNo from Adopt where adoptStatus = '1'")
    List<Integer> petNoList();

//    @Query("select adoptNo from Adopt where petNo = ? limit 1")
//    Adopt adoptPetList(Integer petNo);

    // limit
    Adopt findFirst1ByPetNo(Integer petNo);

    List<Adopt> findByPetNo(int petNo);

    Page<Adopt> findByAdoptStatus(String adoptStatus, Pageable pageable);

    // 멤버 전화번호 중복체크 // 회원 등록폼에서 tel 중복체크 ajax // 아이디 찾기 OK
//    Optional<Adopt> findByTel(String tel);
//
//    Optional<Adopt> findByIdAndEmail(String id, String email);

    // 입양등록 유효성 검사
    List<Adopt> findByPetNoAndUserId(int petNo, String id);

//    List<Adopt> findByAdoptStatus(String adoptStatus);

    Adopt findTop1ByPetNo(int petNo);
    Page<Adopt> findByListStatusAndAdoptStatus(String number, String number2,Pageable pageable);
    Page<Adopt> findByPetNo(int petNo, Pageable pageable);
}
