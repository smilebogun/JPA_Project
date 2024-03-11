package kr.ezen.yni_project.repository;

import kr.ezen.yni_project.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>, JpaSpecificationExecutor<Post> {

    Page<Post> findByPostCategory(String category, Pageable pageable);


    List<Post> findByPostTempAndUserNo(int temp, int uno);

    Page<Post> findAllByOrderByIdDesc(Specification<Post> spec, Pageable pageable);

}