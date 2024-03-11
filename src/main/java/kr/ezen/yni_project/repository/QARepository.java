package kr.ezen.yni_project.repository;

import kr.ezen.yni_project.domain.QA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QARepository extends JpaRepository<QA,Integer>{
    Page<QA> findByUserNo(int userNo, Pageable pageable);
}
