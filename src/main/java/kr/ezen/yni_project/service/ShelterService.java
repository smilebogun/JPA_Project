package kr.ezen.yni_project.service;


import kr.ezen.yni_project.domain.Shelter;
import kr.ezen.yni_project.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShelterService {
    @Autowired
    private ShelterRepository shelterRepo;

    public void insertShelter(Shelter shelter) {
        shelterRepo.save(shelter);
    }


    public Page<Shelter> getlistShelter(Pageable pageable) {
        return shelterRepo.findAll(pageable);
    }


    public Shelter findShelterName(String shelName) {
        Shelter findShelName = shelterRepo.findByShelName(shelName).orElseGet(() -> new Shelter());

        return findShelName;
    }

    public Object getList(int no) {
        Shelter shelter = shelterRepo.findById(no).get();
        return shelter;
    }

    @Transactional
    public void updateShelter(Shelter shelter) {
        Shelter findShelter = shelterRepo.findById(shelter.getNo()).get();
        findShelter.setShelName(shelter.getShelName());
        findShelter.setShelArea(shelter.getShelArea());
        findShelter.setShelPostcode(shelter.getShelPostcode());
        findShelter.setShelAddress(shelter.getShelAddress());
        findShelter.setShelDetailAddress(shelter.getShelDetailAddress());
        findShelter.setShelTel(shelter.getShelTel());
    }

    @Transactional
    public void deleteShelter(int no) {
        shelterRepo.deleteById(no);
    }

    public List<Shelter> getListcnt() {
        List<Shelter> listcnt = shelterRepo.findAll();

        return listcnt;
    }

    //지역 값과 키워드값 두개로 검색시
    public Page<Shelter> shelterSearch(String searchArea, String keyword, Pageable pageable) {

        if(keyword.equals("null")){
            if(searchArea.equals("all")){
                Page<Shelter> searchList = shelterRepo.findAll(pageable);
                return searchList;
            }else { //지역으로 검색시
                Page<Shelter> searchList = shelterRepo.findByShelArea(searchArea, pageable);
                return searchList;
            }
        }else{
            if(searchArea.equals("all")){ //키워드로 검색시
                Page<Shelter> searchList = shelterRepo.findByShelNameContaining(keyword, pageable);
                return searchList;
            }else {//지역 값과 키워드값 두개로 검색시
                Page<Shelter> searchList = shelterRepo.findByShelAreaAndShelNameContaining(searchArea, keyword, pageable);
                return searchList;
            }

        }
    }
}