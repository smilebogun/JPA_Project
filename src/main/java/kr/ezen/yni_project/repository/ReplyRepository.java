package kr.ezen.yni_project.repository;

import kr.ezen.yni_project.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Integer> {
}
