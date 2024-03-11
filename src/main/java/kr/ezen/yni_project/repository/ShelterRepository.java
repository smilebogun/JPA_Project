package kr.ezen.yni_project.repository;


import kr.ezen.yni_project.domain.Shelter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Integer> {
    Optional<Shelter> findByShelName(String shelName);

    Optional<List<Shelter>> findByShelArea(String shelArea);

    //지역으로만 검색
    Page<Shelter> findByShelArea(String shelArea, Pageable pageable);

    //지역+키워드로 검색시
    Page<Shelter> findByShelAreaAndShelNameContaining(String searchArea, String keyword, Pageable pageable);

    //키워드로만 검색시
    Page<Shelter> findByShelNameContaining(String keyword, Pageable pageable);
    // 쿼리메소드
    // find + 엔티티 + By + 변수명 ==> findUserByUsername

    //SELECT * FROM users WHERE username = ?1;

}