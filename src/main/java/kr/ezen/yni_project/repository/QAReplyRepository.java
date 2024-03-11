package kr.ezen.yni_project.repository;

import kr.ezen.yni_project.domain.QAReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QAReplyRepository extends JpaRepository<QAReply,Integer> {

}
