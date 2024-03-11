package kr.ezen.yni_project.Specification;

import kr.ezen.yni_project.domain.SC;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class SCSpecification {

    //선택한 카테고리 별 리스트 검색
    public static Specification<SC> equalPostCategory(String Category) {
        return new Specification<SC>() {
            @Override
            public Predicate toPredicate(Root<SC> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // 1) equal
                return criteriaBuilder.equal(root.get("postCategory"), Category);
            }

        };
    }
    // 제목으로 리스트 찾기
    public static Specification<SC> likeTitle(String keyword) {
        return new Specification<SC>() {
            @Override
            public Predicate toPredicate(Root<SC> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // 2) like
                return criteriaBuilder.like(root.get("scTitle"), "%" + keyword + "%");
            }
        };
    }
    // 작성자로 리스트 찾기
    public static Specification<SC> likeContent(String keyword) {
        return new Specification<SC>() {
            @Override
            public Predicate toPredicate(Root<SC> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // 2) like

                return criteriaBuilder.like(root.get("scContent"), "%" + keyword + "%");
            }
        };
    }



}
