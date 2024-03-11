package kr.ezen.yni_project.Specification;

import kr.ezen.yni_project.domain.Post;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class PostSpecification {

    //선택한 카테고리 별 리스트 검색
    public static Specification<Post> equalPostCategory(String Category) {
        return new Specification<Post>() {
            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // 1) equal
                return criteriaBuilder.equal(root.get("postCategory"), Category);
            }

        };
    }
    // 제목으로 리스트 찾기
    public static Specification<Post> likeTitle(String keyword) {
        return new Specification<Post>() {
            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // 2) like
                return criteriaBuilder.like(root.get("postTitle"), "%" + keyword + "%");
            }
        };
    }
    // 작성자로 리스트 찾기
    public static Specification<Post> likeWriter(String keyword) {
        return new Specification<Post>() {
            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // 2) like

                return criteriaBuilder.like(root.get("postWriter"), "%" + keyword + "%");
            }
        };
    }



}
