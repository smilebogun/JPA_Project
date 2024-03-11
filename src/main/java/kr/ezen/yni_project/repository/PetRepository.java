package kr.ezen.yni_project.repository;


import kr.ezen.yni_project.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>, JpaSpecificationExecutor<Pet> {

    // 쿼리메소드
    // find + 엔티티 + By + 변수명 ==> findUserByUsername

    //SELECT * FROM users WHERE username = ?1;

}

