package kr.ezen.yni_project.repository;

import kr.ezen.yni_project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {  // 제네릭은 int로 안되고 Integer로 변경

    // 쿼리메소드
    // findUserByUsername 에서 엔티티인 User는 생략
    // find + 엔티티 + By + 변수명 == > findUserByUsername

    // SELECT * FROM users WHERE username = ?1;   // #{username}
    // Optional을 사용하면 JPQL문을 만들고 ==> 일반 위와 같은 SQL문이 생성되어 DB에 전송이 됌

    // ID 중복체크 // 회원가입 OK // 로그인 OK // 회원 등록폼에서 ID 중복체크 ajax
    Optional<User> findById(String id);

    // 멤버 전화번호 중복체크 // 회원 등록폼에서 tel 중복체크 ajax // 아이디 찾기 OK
    Optional<User> findByTel(String tel);

    Optional<User> findByIdAndEmail(String id, String email);

}
