package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.Shelter;
import kr.ezen.yni_project.dto.ResponseDTO;
import kr.ezen.yni_project.dto.ShelterDTO;
import kr.ezen.yni_project.service.ShelterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShelterController {
    @Autowired
    ShelterService shelterService;

    @Autowired
    ModelMapper modelMapper;


    @GetMapping("/admin/shelterRegister")
    public String moveShelterRegister(){
        return "/admin/shelterRegister";
    }

    @PostMapping("/shelter")
    @ResponseBody
    public ResponseDTO<?> shelterRegister(@Valid @RequestBody ShelterDTO shelterDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()){
                //어떤 필드에 어떤 문제가 있는지 해당메세지를 맵에 저장
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
        }

        Shelter shelter = modelMapper.map(shelterDTO, Shelter.class);


        Shelter findShelName = shelterService.findShelterName(shelter.getShelName());
        if(findShelName.getShelName() == null){
            shelterService.insertShelter(shelter);

//            return user.getUsername() + "님 회원가입 완료";
            return new ResponseDTO<>(HttpStatus.OK.value(), shelter.getShelName()+"보호소 등록 완료");
        }else{
//            return user.getUsername() + "님은 이미 회원이십니다";
            return new ResponseDTO<>(HttpStatus.CONFLICT.value(),shelter.getShelName()+"라는 이름의 보호소가 존재합니다");

        }

    }

    @GetMapping("/admin/shelterList")
    public String shelterGetList(Model model, @PageableDefault(size=5, sort="no",
            direction = Sort.Direction.DESC) Pageable pageable){
        int currentPage = pageable.getPageNumber() + 1;
        int blockSize = 3;
        int curBlock = (currentPage -1) / blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize - 1);

        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;

        Page<Shelter> shelterList= shelterService.getlistShelter(pageable);
        int totalPages = shelterList.getTotalPages(); // 전체 페이지수
        if(blockEnd > totalPages) blockEnd = totalPages;
        if(nextPage > totalPages) nextPage = totalPages;

        List<Shelter> listcnt = shelterService.getListcnt();
        int size = listcnt.size();
        System.out.println("size = " + size);
        model.addAttribute("listSize", size);

        model.addAttribute("list",shelterList);
        model.addAttribute("blockStart", blockStart);
        model.addAttribute("blockEnd",blockEnd);
        model.addAttribute("prevPage",prevPage);
        model.addAttribute("nextPage",nextPage);

        return "/admin/shelterList";
    }

    @GetMapping("/admin/shelterSearch")
    public String adminShelterSearch(@RequestParam(value = "searchArea", required = false) String searchArea,
                                     @RequestParam(value = "keyword", required = false) String keyword,
                                     Model model, @PageableDefault(size=5, sort="no",
            direction = Sort.Direction.DESC) Pageable pageable) {

        if(keyword.trim().equals("")){
            keyword = "null";
        }

        int currentPage = pageable.getPageNumber() + 1;
        int blockSize = 3;
        int curBlock = (currentPage -1) / blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize - 1);

        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;

        Page<Shelter> searchList =  shelterService.shelterSearch(searchArea,keyword,pageable);
        List<Shelter> listcnt = shelterService.getListcnt();
        int size = listcnt.size();
        System.out.println("size = " + size);

        int totalPages = searchList.getTotalPages(); // 전체 페이지수
        if(blockEnd > totalPages) blockEnd = totalPages;
        if(nextPage > totalPages) nextPage = totalPages;


        model.addAttribute("searchArea", searchArea);
        model.addAttribute("Listsize", size);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listSize", size);
        model.addAttribute("list", searchList);
        model.addAttribute("blockStart", blockStart);
        model.addAttribute("blockEnd",blockEnd);
        model.addAttribute("prevPage",prevPage);
        model.addAttribute("nextPage",nextPage);

        return "/admin/shelterSearchList";
    }


    @GetMapping("/admin/shelterModify/{no}")
    public String moveModifyShelter(@PathVariable int no, Model model){
        model.addAttribute("shelter", shelterService.getList(no));
        return "/admin/shelterModify";
    }

    @PutMapping("/shelter")
    @ResponseBody
    public ResponseDTO<?> modifyShelter(@RequestBody Shelter shelter){
        System.out.println("shelter@@@@@@ = "+shelter);
        shelterService.updateShelter(shelter);

        return new ResponseDTO<>(HttpStatus.OK.value(), shelter.getNo()+"번 보호소 수정완료");
    }

    @DeleteMapping("/shelter/{no}")
    @ResponseBody
    public ResponseDTO<?> deleteShelter(@PathVariable int no){
        shelterService.deleteShelter(no);
        return new ResponseDTO<>(HttpStatus.OK.value(), no+"번 보호소 삭제 완료");
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@User@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @GetMapping("/user/shelterList")
    public String u_shelterGetList(Model model, @PageableDefault(size=5, sort="no",
            direction = Sort.Direction.DESC) Pageable pageable){
        int currentPage = pageable.getPageNumber() + 1;
        int blockSize = 3;
        int curBlock = (currentPage -1) / blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize - 1);

        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;

        Page<Shelter> shelterList= shelterService.getlistShelter(pageable);
        List<Shelter> listcnt = shelterService.getListcnt();
        int size = listcnt.size();
        System.out.println("size = " + size);

        int totalPages = shelterList.getTotalPages(); // 전체 페이지수
        if(blockEnd > totalPages) blockEnd = totalPages;
        if(nextPage > totalPages) nextPage = totalPages;


        model.addAttribute("listSize", size);
        model.addAttribute("list",shelterList);
        model.addAttribute("blockStart", blockStart);
        model.addAttribute("blockEnd",blockEnd);
        model.addAttribute("prevPage",prevPage);
        model.addAttribute("nextPage",nextPage);

        return "/user/u_shelterList";
    }

    // 보호소 상세보기
    @GetMapping("/user/shelterInfo/{no}")
    public String shelterInfo(@PathVariable int no, Model model){

        Shelter shelter = (Shelter) shelterService.getList(no);

        model.addAttribute("shelter", shelter);

        return "/user/shelterInfo";
    }

    // 검색
    @GetMapping("/user/shelterSearch")
    public String shelterSearch(@RequestParam(value = "searchArea", required = false) String searchArea,
                                @RequestParam(value = "keyword", required = false) String keyword,
                                Model model, @PageableDefault(size=5, sort="no",
            direction = Sort.Direction.DESC) Pageable pageable) {

        if(keyword.trim().equals("")){
            keyword = "null";
        }

        int currentPage = pageable.getPageNumber() + 1;
        int blockSize = 3;
        int curBlock = (currentPage -1) / blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize - 1);

        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;

        Page<Shelter> searchList =  shelterService.shelterSearch(searchArea,keyword,pageable);
        List<Shelter> listcnt = shelterService.getListcnt();
        int size = listcnt.size();
        System.out.println("size = " + size);

        int totalPages = searchList.getTotalPages(); // 전체 페이지수
        if(blockEnd > totalPages) blockEnd = totalPages;
        if(nextPage > totalPages) nextPage = totalPages;


        model.addAttribute("searchArea", searchArea);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listSize", size);
        model.addAttribute("list", searchList);
        model.addAttribute("blockStart", blockStart);
        model.addAttribute("blockEnd",blockEnd);
        model.addAttribute("prevPage",prevPage);
        model.addAttribute("nextPage",nextPage);
        return "/user/u_shelterSearchList";
    }

}