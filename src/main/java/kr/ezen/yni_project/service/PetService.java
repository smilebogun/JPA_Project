package kr.ezen.yni_project.service;


import kr.ezen.yni_project.Specification.PetSpecification;
import kr.ezen.yni_project.domain.Pet;
import kr.ezen.yni_project.domain.Shelter;
import kr.ezen.yni_project.repository.PetRepository;
import kr.ezen.yni_project.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepo;

    @Autowired
    private ShelterRepository shelterRepo;

    //지역별 보호소 리스트 가져옴
    public List<Shelter> getShelList(String shelArea) {
        List<Shelter> list = shelterRepo.findByShelArea(shelArea).get();
        return list;
    }


    public void insertPet(Pet pet) {
        petRepo.save(pet);
    }



    public Pet getPetListOne(int no) {
        Pet pet = petRepo.findById(no).get();
        return pet;
    }

    @Transactional
    public void updatePet(Pet pet) {
        Pet updatePet = petRepo.findById(pet.getNo()).get();
        updatePet.setPetGender(pet.getPetGender());
        updatePet.setPetAge(pet.getPetAge());
        updatePet.setPetImage(pet.getPetImage());
        updatePet.setPetName(pet.getPetName());
        updatePet.setPetInoculation(pet.getPetInoculation());
        updatePet.setPetNeutering(pet.getPetNeutering());
        updatePet.setPetPlace(pet.getPetPlace());
        updatePet.setPetSpecies(pet.getPetSpecies());
        updatePet.setPetRemarks(pet.getPetRemarks());
        updatePet.setPetUpdate(pet.getPetUpdate());
        updatePet.setShelArea(pet.getShelArea());
        updatePet.setShelName(pet.getShelName());
    }

    public void deletePetList(int no) {
        petRepo.deleteById(no);
    }

    public Page<Pet> getPetList(Pageable pageable) {
        Page<Pet> petList = petRepo.findAll(pageable);
        return petList;
    }

    public Page<Pet> searchPetList(Pageable pageable, String searchSpecies, String searchArea, String keyword) {

        if(keyword.equals("")){
            if (searchSpecies.equals("all")) {
                if (searchArea.equals("all")) {
                    return petRepo.findAll(pageable);
                } else {
                    Specification<Pet> spec = Specification.where(PetSpecification.equalPetShelArea(searchArea));
                    return petRepo.findAll(spec,pageable);
                }
            } else {
                if (searchArea.equals("all")) {
                    Specification<Pet> spec = Specification.where(PetSpecification.equalPetPetSpecies(searchSpecies));
                    return petRepo.findAll(spec, pageable);
                } else {
                    Specification<Pet> spec = Specification.where(PetSpecification.equalPetPetSpecies(searchSpecies));
                    spec = spec.and(PetSpecification.equalPetShelArea(searchArea));
                    return petRepo.findAll(spec, pageable);
                }

            }
        }else { // 주의할점@@@@@@@@@ //지역값과 키워드값으로 검색시부터 and()안에서 코드 붙여줘야한다 밖에다 붙이는 실수 잦음 주의할것
            if (searchSpecies.equals("all")) {
                if (searchArea.equals("all")) {
                    // 키워드값으로 검색시
                    Specification<Pet> spec = Specification.where(PetSpecification.likePetPetRemarks(keyword)).or(PetSpecification.likePetPetPlace(keyword))
                            .or(PetSpecification.likePetPetName(keyword)).or(PetSpecification.likePetShelName(keyword)).or(PetSpecification.likePetPetGender(keyword));
                    return petRepo.findAll(spec, pageable);
                } else {
                    // 지역값과 키워드값으로 검색시
                    Specification<Pet> spec = Specification.where(PetSpecification.equalPetShelArea(searchArea));
                    spec = spec.and(Specification.where(PetSpecification.likePetPetRemarks(keyword)).or(PetSpecification.likePetPetPlace(keyword))
                            .or(PetSpecification.likePetPetName(keyword)).or(PetSpecification.likePetShelName(keyword)).or(PetSpecification.likePetPetGender(keyword)));
                    return petRepo.findAll(spec, pageable);
                }
            } else {
                //견/묘종 값과 키워드 값으로 검색시
                if (searchArea.equals("all")) {
                    Specification<Pet> spec = Specification.where(PetSpecification.equalPetPetSpecies(searchSpecies));
                    spec = spec.and(Specification.where(PetSpecification.likePetPetRemarks(keyword)).or(PetSpecification.likePetPetPlace(keyword))
                            .or(PetSpecification.likePetPetName(keyword)).or(PetSpecification.likePetShelName(keyword)).or(PetSpecification.likePetPetGender(keyword)));
                    return petRepo.findAll(spec, pageable);
                } else {
                    //모든 값으로 검색시
                    Specification<Pet> spec = Specification.where(PetSpecification.equalPetPetSpecies(searchSpecies)).and(PetSpecification.equalPetShelArea(searchArea));
                    spec = spec.and(Specification.where(PetSpecification.likePetPetRemarks(keyword)).or(PetSpecification.likePetPetPlace(keyword))
                            .or(PetSpecification.likePetPetName(keyword)).or(PetSpecification.likePetShelName(keyword)).or(PetSpecification.likePetPetGender(keyword)));

                    return petRepo.findAll(spec, pageable);

                }

            }

        }
    }

    // 펫 전체 구하기 graph에 사용 됨
    public List<Pet> getListcnt() {
        List<Pet> petListcnt = petRepo.findAll();
        return petListcnt;
    }
}
