package kr.ezen.yni_project.Specification;

import kr.ezen.yni_project.domain.Pet;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class PetSpecification {

    //검색 조건이 지역 일때
    public static Specification<Pet> equalPetShelArea(String ShelArea) {
        return new Specification<Pet>() {
            @Override
            public Predicate toPredicate(Root<Pet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // 1) equal
                return criteriaBuilder.equal(root.get("shelArea"), ShelArea);
            }
        };
    }

    //검색 조건이 고양이/강아지 일때
    public static Specification<Pet> equalPetPetSpecies(String petSpecies) {
        return new Specification<Pet>() {
            @Override
            public Predicate toPredicate(Root<Pet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.equal(root.get("petSpecies"), petSpecies);
            }
        };
    }

    //검색시 특이사항 에서 keyword로 리스트를 찾을대
    public static Specification<Pet> likePetPetRemarks(String petRemarks) {
        return new Specification<Pet>() {
            @Override
            public Predicate toPredicate(Root<Pet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.like(root.get("petRemarks"), "%"+petRemarks+"%");
            }
        };
    }

    //검색시 발견된 장소에서 keyword로 리스트를 찾을떄
    public static Specification<Pet> likePetPetPlace(String petPlace) {
        return new Specification<Pet>() {
            @Override
            public Predicate toPredicate(Root<Pet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.like(root.get("petPlace"), "%"+petPlace+"%");
            }
        };
    }

    //검색시 견/묘종 에서 keyword로 리스트를 찾을떄
    public static Specification<Pet> likePetPetName(String petName) {
        return new Specification<Pet>() {
            @Override
            public Predicate toPredicate(Root<Pet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.like(root.get("petName"), "%"+petName+"%");
            }
        };
    }

    //검색시 보호소 이름 에서 keyword로 리스트를 찾을떄
    public static Specification<Pet> likePetShelName(String shelName) {
        return new Specification<Pet>() {
            @Override
            public Predicate toPredicate(Root<Pet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.like(root.get("shelName"), "%"+shelName+"%");
            }
        };
    }

    //검색시 성별 에서 keyword로 리스트를 찾을떄
    public static Specification<Pet> likePetPetGender(String petGender) {
        return new Specification<Pet>() {
            @Override
            public Predicate toPredicate(Root<Pet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.like(root.get("petGender"), "%"+petGender+"%");
            }
        };
    }


}
