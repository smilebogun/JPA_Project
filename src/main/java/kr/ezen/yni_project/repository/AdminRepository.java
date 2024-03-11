package kr.ezen.yni_project.repository;

import kr.ezen.yni_project.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {  // 제네릭은 int로 안되고 Integer로 변경

    // 쿼리메소드
    // findUserByUsername 에서 엔티티인 User는 생략
    // find + 엔티티 + By + 변수명 == > findUserByUsername

    // SELECT * FROM users WHERE username = ?1;   // #{username}
    // Optional을 사용하면 JPQL문을 만들고 ==> 일반 위와 같은 SQL문이 생성되어 DB에 전송이 됌

    // ID 중복체크
    Optional<Admin> findByAdminId(String adminId);

}