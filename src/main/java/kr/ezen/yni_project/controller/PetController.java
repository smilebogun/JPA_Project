package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.Pet;
import kr.ezen.yni_project.domain.Shelter;
import kr.ezen.yni_project.service.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;


@Controller
public class PetController {
    @Autowired
    private PetService petService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/admin/petRegister")
    public String movePetRegister(){
        return "/admin/petRegister";
    }

    //동물 등록내 지역별 보호소리스트 가져옴
    @PostMapping("/pet/registerShelter")
    @ResponseBody
    public List<Shelter> movePetRegister(@RequestBody String shelArea){
        System.out.println("shelArea@@@@ ="+shelArea);
        List<Shelter> list = petService.getShelList(shelArea);
        return list;
    }
    //관리자 펫 등록
    @PostMapping("/admin/petInsert")

    public String petInsert(MultipartHttpServletRequest mhr,
                            HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException{

        /*String repo = "fileRepo";

        String savePath = request.getServletContext().getRealPath("")+ File.separator+repo;*/
        String savePath = "C:\\STUDY_SpringBoot\\YnI_Project_test\\src\\main\\resources\\static\\fileRepo";
        System.out.println("savePath :"+savePath);


        Map map = new HashMap();

        // mhr는 일반 텍스트, 바이너리 파일 정보를 모두 얻어올 수 있는 객체
        // input의 name속성의 값 = 파라미터값을 가져옴 (id, name)
        Enumeration<String> enu = mhr.getParameterNames();

        // 일반 텍스트의 파라미터명과 입력값을 모두 출력하기
        while(enu.hasMoreElements()) {
            String paramName = enu.nextElement();
            // 해당 파라미터명의 값 => input의 value값(사용자가 입력한 값)
            String paramValue = mhr.getParameter(paramName);

            //name: kim, id: test
            System.out.println(paramName + ": "+paramValue);
            map.put(paramName, paramValue);
        }

        Iterator<String> iter = mhr.getFileNames();
        System.out.println("iter = " + iter);

        List<String> fileList = new ArrayList<String>();
        String petImage = new String();

        while(iter.hasNext()) {
            String fileParamName = iter.next();
            System.out.println("fileParamName = "+fileParamName);

            // MultipartFile : 파일정보를 갖고 있는 객체
            MultipartFile mFile= mhr.getFile(fileParamName);
            // 첨부된 이미지 파일명
            String originName = mFile.getOriginalFilename();
            System.out.println("originName =" + originName);

            // ~ \day05_fileUpDown\\resources/fileRepo\\file1
            File file = new File(savePath+"\\"+fileParamName);

            System.out.println("사이즈 : " + mFile.getSize());

            if(mFile.getSize() !=0) { // 업로드 된 경우
//		            System.out.println("11 : "+ file.exists());
                if(!file.exists()) { // 파일이 존재하지 않으면
//		               file.createNewFile(); // 임시파일 생성
//		               System.out.println(file.getParentFile());
                    if(file.getParentFile().mkdir()) { // fileRepo폴더를 생성
                        file.createNewFile(); // 임시파일 생성
                    }//if
                }//if

                File uploadFile = new File(savePath+"\\"+originName);

                // 중복시 파일명 대체
                if(uploadFile.exists()) {
                    originName = System.currentTimeMillis()+"_"+originName;
                    uploadFile = new File(savePath+"\\"+originName);
                }

                // 실제 파일 업로드
                mFile.transferTo(uploadFile);
                // 파일명을 list에 추가
                //fileList.add(originName);
                petImage = originName;

            }//if
        }
        // 파일명을 저장한 리스트를 map 추가
        map.put("fileList", fileList);
        map.put("petImage", petImage);


        Pet pet = modelMapper.map(map, Pet.class);
        pet.setPetAdopt("입양대기");
        System.out.println("pet :"+pet);
        petService.insertPet(pet);
        redirectAttributes.addFlashAttribute("msg", "보호동물 등록완료!!");

        return "redirect:/admin/petList";
    }

    //관리자 펫리스트 보기 페이지
    @GetMapping("/admin/petList")
    public String movePetRegister(Model model, @PageableDefault(size=5, sort="no",
            direction = Sort.Direction.DESC) Pageable pageable){
        int currentPage = pageable.getPageNumber() + 1;
        int blockSize = 3;
        int curBlock = (currentPage -1) / blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize - 1);

        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;
        Page<Pet> petlist = petService.getPetList(pageable);

        int totalPages = petlist.getTotalPages(); // 전체 페이지수
        if(blockEnd > totalPages) blockEnd = totalPages;
        if(nextPage > totalPages) nextPage = totalPages;

        model.addAttribute("blockStart", blockStart);
        model.addAttribute("blockEnd",blockEnd);
        model.addAttribute("prevPage",prevPage);
        model.addAttribute("nextPage",nextPage);

        model.addAttribute("list",petlist);

        return "/admin/petList";
    }

    //2024-02-27 관리자 펫 검색 기능 추가
    @GetMapping("/admin/searchList")
    public String petSearchList(@RequestParam(value = "searchSpecies",required = false) String searchSpecies,
                                @RequestParam(value = "searchArea",required = false) String searchArea,
                                @RequestParam(value = "keyword",required = false) String keyword,
                                Model model, @PageableDefault(size=5, sort="no",
            direction = Sort.Direction.DESC) Pageable pageable){

        int currentPage = pageable.getPageNumber() + 1;
        int blockSize = 3;
        int curBlock = (currentPage -1) / blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize - 1);

        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;

        Page<Pet> petList = petService.searchPetList(pageable,searchSpecies,searchArea,keyword);

        int totalPages = petList.getTotalPages(); // 전체 페이지수
        if(blockEnd > totalPages) blockEnd = totalPages;
        if(nextPage > totalPages) nextPage = totalPages;

        model.addAttribute("searchSpecies", searchSpecies);
        model.addAttribute("searchArea", searchArea);
        model.addAttribute("keyword", keyword);

        model.addAttribute("blockStart", blockStart);
        model.addAttribute("blockEnd",blockEnd);
        model.addAttribute("prevPage",prevPage);
        model.addAttribute("nextPage",nextPage);

        model.addAttribute("list", petList);

        return "/admin/petSearchList";
    }

    //관리자 펫리스트 수정페이지 이동
    @GetMapping("/admin/petModify")
    public String movePetModify(int no, Model model){
        Pet pet = petService.getPetListOne(no);
        model.addAttribute("pet", pet);
        return "/admin/petModify";
    }
    //관리자 펫리스트 수정
    @PostMapping("/admin/petModify")
    public String petModify(MultipartHttpServletRequest mhr,
                            HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException{

        String savePath = "C:\\STUDY_SpringBoot\\YnI_Project_test\\src\\main\\resources\\static\\fileRepo";
        System.out.println("savePath :"+savePath);

        Map map = new HashMap();
        Enumeration<String> enu = mhr.getParameterNames();

        while(enu.hasMoreElements()) {
            String paramName = enu.nextElement();
            // 해당 파라미터명의 값 => input의 value값(사용자가 입력한 값)
            String paramValue = mhr.getParameter(paramName);

            //name: kim, id: test
            System.out.println(paramName + ": "+paramValue);
            map.put(paramName, paramValue);
        }

        Iterator<String> iter = mhr.getFileNames();
        System.out.println("iter = " + iter);

        List<String> fileList = new ArrayList<String>();
        String petImage = new String();

        while(iter.hasNext()) {
            String fileParamName = iter.next();
            System.out.println("fileParamName = "+fileParamName);

            // MultipartFile : 파일정보를 갖고 있는 객체
            MultipartFile mFile= mhr.getFile(fileParamName);
            // 첨부된 이미지 파일명
            String originName = mFile.getOriginalFilename();
            System.out.println("originName =" + originName);

            // ~ \day05_fileUpDown\\resources/fileRepo\\file1
            File file = new File(savePath+"\\"+fileParamName);

            System.out.println("사이즈 : " + mFile.getSize());

            if(mFile.getSize() !=0) { // 업로드 된 경우
//		            System.out.println("11 : "+ file.exists());
                if(!file.exists()) { // 파일이 존재하지 않으면
//		               file.createNewFile(); // 임시파일 생성
//		               System.out.println(file.getParentFile());
                    if(file.getParentFile().mkdir()) { // fileRepo폴더를 생성
                        file.createNewFile(); // 임시파일 생성
                    }//if
                }//if

                File uploadFile = new File(savePath+"\\"+originName);

                // 중복시 파일명 대체
                if(uploadFile.exists()) {
                    originName = System.currentTimeMillis()+"_"+originName;
                    uploadFile = new File(savePath+"\\"+originName);
                }

                // 실제 파일 업로드
                mFile.transferTo(uploadFile);
                // 파일명을 list에 추가
                //fileList.add(originName);
                petImage = originName;


            }//if
        }
        if(petImage.trim().equals("")) {
            petImage = (String)map.get("petImageOld");
        }

        // 파일명을 저장한 리스트를 map 추가
        map.put("fileList", fileList);
        map.put("petImage", petImage);

        Pet pet = modelMapper.map(map, Pet.class);
        System.out.println("pet@@@@@@@@@@@@@@@ :"+pet);
        petService.updatePet(pet);
        redirectAttributes.addFlashAttribute("msg", "보호동물 수정완료!!");

        return "redirect:/admin/petList";
    }

    //펫리스트에서 삭제
    @GetMapping("/admin/petDelete")
    public String petDelete(int no, RedirectAttributes redirectAttributes){
        System.out.println("no = " + no);

        petService.deletePetList(no);
        redirectAttributes.addFlashAttribute("msg", "보호동물 삭제완료!!");
        return "redirect:/admin/petList";
    }

    /* @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@User PAGE@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/

    @GetMapping("/user/petList")
    public String u_getPetList(Model model, @PageableDefault(size=8, sort="no",
            direction = Sort.Direction.DESC) Pageable pageable){

        int currentPage = pageable.getPageNumber() + 1;
        int blockSize = 3;
        int curBlock = (currentPage -1) / blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize - 1);

        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;

        Page<Pet> petList = petService.getPetList(pageable);

        int totalPages = petList.getTotalPages(); // 전체 페이지수
        if(blockEnd > totalPages) blockEnd = totalPages;
        if(nextPage > totalPages) nextPage = totalPages;

        model.addAttribute("blockStart", blockStart);
        model.addAttribute("blockEnd",blockEnd);
        model.addAttribute("prevPage",prevPage);
        model.addAttribute("nextPage",nextPage);

        model.addAttribute("list", petList);

        return "/user/u_petList";
    }

    @GetMapping("/user/searchList")
    public String searchPetList(@RequestParam(value = "searchSpecies",required = false) String searchSpecies,
                                @RequestParam(value = "searchArea",required = false) String searchArea,
                                @RequestParam(value = "keyword",required = false) String keyword,
                                Model model, @PageableDefault(size=8, sort="no",
            direction = Sort.Direction.DESC) Pageable pageable){

        int currentPage = pageable.getPageNumber() + 1;
        int blockSize = 3;
        int curBlock = (currentPage -1) / blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize - 1);

        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;

        Page<Pet> petList = petService.searchPetList(pageable,searchSpecies,searchArea,keyword);

        int totalPages = petList.getTotalPages(); // 전체 페이지수
        if(blockEnd > totalPages) blockEnd = totalPages;
        if(nextPage > totalPages) nextPage = totalPages;

        model.addAttribute("searchSpecies", searchSpecies);
        model.addAttribute("searchArea", searchArea);
        model.addAttribute("keyword", keyword);

        model.addAttribute("blockStart", blockStart);
        model.addAttribute("blockEnd",blockEnd);
        model.addAttribute("prevPage",prevPage);
        model.addAttribute("nextPage",nextPage);

        model.addAttribute("list", petList);

        return "/user/u_searchPetList";
    }

    @GetMapping("/user/petDetail")
    public String petDetail(int no, Model model){
        Pet petListOne = petService.getPetListOne(no);
        model.addAttribute("list", petListOne);

        return "/user/petDetail";
    }
}