package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.Adopt;
import kr.ezen.yni_project.domain.Pet;
import kr.ezen.yni_project.domain.Post;
import kr.ezen.yni_project.domain.User;
import kr.ezen.yni_project.dto.PostDTO;
import kr.ezen.yni_project.dto.ResponseDTO;
import kr.ezen.yni_project.dto.UserDTO;
import kr.ezen.yni_project.service.AdoptService;
import kr.ezen.yni_project.service.PetService;
import kr.ezen.yni_project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class AdoptController {

    @Autowired
    private AdoptService adoptService;

    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired	// 암호화를위해서 root 추가한 pwEncoder에서 주입받음
//    private PasswordEncoder pwEncoder;

    // ## 1. DTO를 사용할거면 @Valid
    // ## 2. data:JSON.stringify(adopt), ==> @RequestBody 로 받는다
    // ## 3. url:"/petAdopt.do/"+no, ==> @PathVariable 로 받는다
    // ## 4. data.append("post",new Blob([post],{ type: 'application/json' }));
    //       data.append("data",files);  ==>> 2개로 나눠서 받아야할 경우는
    //          @Valid @ModelAttribute(value = "data") MultipartFile uploadFiles,
    //          @Valid @RequestPart("post") PostDTO postDto, 로 받는다
    
    // ##### 유효성 체크
    // 입양신청 OK
    @PostMapping("/petAdopt.do")  // JSON형태로 넘어온걸 받아야되서 @RequestBody 사용함
    //@PostMapping("/petAdopt.do/{petNo}")  // JSON형태로 넘어온걸 받아야되서 @RequestBody 사용함
    @ResponseBody
    // @Valid : UserDTO에 설정된 사용자 입력값들에 대한 유효성 체크를 하는 어노테이션
    // BindingResult : 유효성 체크 결과를 저장하는 객체
    // 유효성을 userDTO가 받고 결과를 BindingResult로 내고 @Valid 체크한다
    public ResponseDTO<?> insertAdopt(@RequestBody Adopt adopt, BindingResult bindingResult,
                                     HttpSession session) throws Exception {
        // @Valid @ModelAttribute(value = "data") MultipartFile uploadFiles,
        // @PathVariable("petNo") int petNo,
        System.out.println("Adopt@@ " + adopt);
        //이미지 이름
//        if(uploadFiles != null){
//            String origin = uploadFiles.getOriginalFilename();
//            //저장 경로
//            String savePath = "C:\\STUDY_SpringBoot\\YnI_Project_test\\src\\main\\resources\\static\\fileRepo";
//            File saveFile = new File(savePath, origin);
//            //파일이 존재할 경우 새로운 이름으로 저장
//            if (saveFile.exists()) {
//                origin = System.currentTimeMillis() + "_" + origin;
//                saveFile = new File(savePath, origin);
//            }
//            Adopt adopt = modelMapper.map(adoptDTO, Adopt.class);
//            uploadFiles.transferTo(saveFile);
//            adopt.setPetImage(origin);
//        }

        Pet adoptPet = petService.getPetListOne(adopt.getPetNo());     // .js에서 petNo로 Pet 엔티티 전부 가져오기
        User principal = (User) session.getAttribute("principal");  // 세션으로 유저속성 전부 속성가져오기

        // UserDTO --> User 객체의 Entity로 변환
        //Adopt adopt = modelMapper.map(adoptDTO, Adopt.class);   // adoptDTO를 adopt 엔티티로 가져오기

        // ID 중복체크
//        List<Adopt> adoptUser = adoptService.getAdoptUser(principal.getId());
//        int size = adoptUser.size();
//
//        System.out.println("adoptUser@@@@@@@@@@@@@@@@@@@@@@:" + adoptUser);

        //        List<Adopt> adoptPetNo = adoptService.getAdoptPetNo(adopt.getPetNo());
        //         int size2 = adoptPetNo.size();

        List<Adopt> adoptPetNoANDUserId = adoptService.getAdoptPetNoANDUserId(adopt.getPetNo(), principal.getId());
        int size2 = adoptPetNoANDUserId.size();


        //System.out.println("size@@@@@@@@@@@@@@@@@@@@@@@@@@@" + size);
       // System.out.println("size2@@@@@@@@@@@@@@@@@@@@@@@@@@@" + size2);


        // DB에 없으면 또는 db에 있는데 petNo 값이 있을경우
        if (size2 == 0) {
            adopt.setPetNo(adoptPet.getNo());
            adopt.setShelName(adoptPet.getShelName());
            adopt.setShelArea(adoptPet.getShelArea());
            adopt.setPetSpecies(adoptPet.getPetSpecies());
            adopt.setPetName(adoptPet.getPetName());
            adopt.setPetAge(adoptPet.getPetAge());
            adopt.setPetGender(adoptPet.getPetGender());
            adopt.setPetNeutering(adoptPet.getPetNeutering());
            adopt.setPetInoculation(adoptPet.getPetInoculation());
            adopt.setPetPlace(adoptPet.getPetPlace());
            adopt.setPetRemarks(adoptPet.getPetRemarks());
            adopt.setPetImage(adoptPet.getPetImage());
            adopt.setPetAdopt(adoptPet.getPetAdopt());
            adopt.setPetSysdate(String.valueOf(adoptPet.getPetSysdate()));
            adopt.setPetUpdate(String.valueOf(adoptPet.getPetUpdate()));

            adopt.setUserNo(principal.getNo());
            adopt.setUserId(principal.getId());
            adopt.setUserPw(principal.getPw());
            adopt.setUserName(principal.getName());
            adopt.setUserAge(principal.getAge());
            adopt.setUserTel(principal.getTel());
            adopt.setUserEmail(principal.getEmail());
            adopt.setZipcode(principal.getZipcode());
            adopt.setRoadAddr(principal.getRoadAddr());
            adopt.setDetailAddr(principal.getDetailAddr());
            adopt.setPlusAddr(principal.getPlusAddr());
            adopt.setRole(principal.getRole());
            adopt.setOAuth(principal.getOAuth());
            adopt.setUserCreateDate(String.valueOf(principal.getCreateDate()));
            adopt.setUserUpdateDate(String.valueOf(principal.getUpdateDate()));

            adopt.setAdoptStatus("1");
            adopt.setListStatus("1");
            adopt.setAdoptCnt(0);

            adoptService.insertAdopt(adopt);
            //            return user.getUsername()+"님 회원가입 완료!!";
            return new ResponseDTO<>(HttpStatus.OK.value(), adopt.getUserId() + "님 입양예약 완료!!" + "\n\n" + "입양확정을 위해 운영진이 연락드릴 예정이오니" + "\n" + "기다려주시면 감사하겠습니다.");
            // .OK 는 상태 status가 200 -> 성공 // JSON 형태로 만들어서 콘솔에 띄워줌
        } else { // DB에 있으면

            //            return user.getUsername()+"님은 이미 회원이십니다.";
            return new ResponseDTO<>(HttpStatus.CONFLICT.value(), "이미 입양신청 하셨습니다.");
            // .BAD_REQUEST 는 status가 400 -> 실패 // JSON 형태로 만들어서 콘솔에 띄워줌
        }

    }


//    // 멤버정보(인포)폼에서 수정OK
//    @RequestMapping("/memberUpdate.do")
//    public String memberUpdate(User user, RedirectAttributes redirectAttributes) {
//        userService.memberUpdate(user);
//        redirectAttributes.addFlashAttribute("msg", "멤버정보 수정완료!!");
//        return "redirect:/";
//    }
//
    /* 추가 */
    // 관리자 홈에서 유저 리스트 폼 이동 // 리스트1이동
    @GetMapping("/admin/adoptList")
    public String adoptList(Model model, @PageableDefault(size=5, sort="no",
            direction = Sort.Direction.DESC) Pageable pageable) {

    //        int currentPage = pageable.getPageNumber() + 1;
    //        int blockSize = 3;
    //        int curBlock = (currentPage -1) / blockSize;
    //        int blockStart = (blockSize * curBlock) + 1;
    //        int blockEnd = blockStart + (blockSize - 1);
    //        int prevPage = blockStart - 1;
    //        int nextPage = blockEnd + 1;


        List<Integer> petNoList = adoptService.getAdoptPetNoList();



        model.addAttribute("petNoList", petNoList);

    //        List<Adopt> adoptList = adoptService.getAdoptList();
    //        model.addAttribute("adoptList", adoptList);

//        List<Adopt> adoptPetList = new ArrayList<>();
//
//        for(Integer a : petNoList){
//            List<Adopt> adoptList2 = adoptService.getAdoptList2(a);
//
//            Adopt adopt = adoptService.getAdoptPetList(a);
//            adopt.setAdoptCnt(adoptList2.size());
//            adoptPetList.add(adopt);
//
//        }

        // 위에꺼 주석처리 // 페이징처리
        for(Integer a : petNoList){
            List<Adopt> adoptList2 = adoptService.getAdoptList2(a);
//            Adopt adopt = adoptService.getAdoptPetList(a);
            String listStatus = "2";
            int cnt = adoptList2.size();
            adoptService.petNoList(a,cnt,listStatus);

        }

//        System.out.println("test@@@@@@@@@@@@ : "+adoptPetList);

    //        int totalPages = adoptPetList.getTotalPages(); // 전체 페이지수
    //        if(blockEnd > totalPages) blockEnd = totalPages;
    //        if(nextPage > totalPages) nextPage = totalPages;


//        model.addAttribute("adoptPetList", adoptPetList);
    //        model.addAttribute("blockStart", blockStart);
    //        model.addAttribute("blockEnd",blockEnd);
    //        model.addAttribute("prevPage",prevPage);
    //        model.addAttribute("nextPage",nextPage);

        // 페이지처리위한 위에 주석처리
        int currentPage = pageable.getPageNumber() + 1;
        int blockSize = 3;
        int curBlock = (currentPage -1) / blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize - 1);

        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;
        Page<Adopt> adoptPetList = adoptService.getAdoptList3("2", pageable , "1");

        int totalPages = adoptPetList.getTotalPages(); // 전체 페이지수
        if(blockEnd > totalPages) blockEnd = totalPages;
        if(nextPage > totalPages) nextPage = totalPages;
        
        System.out.println("test@@@@@@@@@@@@ : "+adoptPetList);
        
        model.addAttribute("adoptPetList", adoptPetList);
        model.addAttribute("blockStart", blockStart);
        model.addAttribute("blockEnd",blockEnd);
        model.addAttribute("prevPage",prevPage);
        model.addAttribute("nextPage",nextPage);

        return "/admin/adoptList";
    }

    /* 추가 */
    // 리스트2 폼이동
    @GetMapping("/admin/adoptList2")
    public String adoptList2(Model model, int petNo, @PageableDefault(size=5, sort="no",
            direction = Sort.Direction.DESC) Pageable pageable) {

        int currentPage = pageable.getPageNumber() + 1;
        int blockSize = 3;
        int curBlock = (currentPage -1) / blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize - 1);

        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;
        
        Page<Adopt> adoptList2 = adoptService.getAdoptList2Page(petNo, pageable);

        int totalPages = adoptList2.getTotalPages(); // 전체 페이지수
        if(blockEnd > totalPages) blockEnd = totalPages;
        if(nextPage > totalPages) nextPage = totalPages;

        System.out.println("^^^^^^^^^^^^^^^petNo = " + petNo);

        //Page<Adopt> adoptList2 사용되어 아래 주석처리
        //List<Adopt> adoptList2 = adoptService.getAdoptList2(petNo);

        System.out.println("test23@@@@@@@@@@" + adoptList2);

        Adopt image = adoptService.getAdoptPetList(petNo);
        model.addAttribute("image", image.getPetImage());

        model.addAttribute("adoptList2", adoptList2);
        model.addAttribute("petNo", petNo);
        // 페이지처리
        model.addAttribute("blockStart", blockStart);
        model.addAttribute("blockEnd",blockEnd);
        model.addAttribute("prevPage",prevPage);
        model.addAttribute("nextPage",nextPage);

//        List<Integer> petNoList = adoptService.getAdoptPetNoList();
//        model.addAttribute("petNoList", petNoList);
//
//        List<Adopt> adoptList = adoptService.getAdoptList();
//        model.addAttribute("adoptList", adoptList);
//
//        List<Adopt> adoptPetList = new ArrayList<>();
//        for(Integer a : petNoList){
//            Adopt adopt = adoptService.getAdoptPetList(a);
//            adoptPetList.add(adopt);
//        }
//        System.out.println("test@@@@@@@@@@@@ : "+adoptPetList);
//        model.addAttribute("adoptPetList", adoptPetList);

        return "/admin/adoptList2";
    }

    // 입양확정 OK
    @GetMapping("/admin/adoptOk")
    public String adoptOk(int no, int petNo, RedirectAttributes redirectAttributes) {

        List<Adopt> adoptList2 = adoptService.getAdoptList2(petNo);

        List<Integer> adoptNo = new ArrayList<>();


        for(Adopt list : adoptList2){
            adoptNo.add(list.getNo());
        }

        for(int num : adoptNo){

            if(num == no){
                adoptService.updateAdopt(num);
                petService.deletePetList(petNo);
            } else{
                adoptService.updateAdopt2(num);
            }
        }

        redirectAttributes.addFlashAttribute("msg", "입양확정이 되었습니다!!");
        return "redirect:/admin/adoptList";
    }

    // 입양확정내역 리스트
    @GetMapping("/admin/backupAdoptList")
    public String backupAdoptList(Model model, @PageableDefault(size=5, sort="no",
            direction = Sort.Direction.DESC) Pageable pageable){
        String adoptStatus = "3";

        int currentPage = pageable.getPageNumber() + 1;
        int blockSize = 3;
        int curBlock = (currentPage -1) / blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize - 1);
        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;

        Page<Adopt> adoptListBackupList = adoptService.getAdoptListBackupList(adoptStatus, pageable);
        System.out.println("test@@@@@@@@:"+adoptListBackupList);

        int totalPages = adoptListBackupList.getTotalPages(); // 전체 페이지수
        if(blockEnd > totalPages) blockEnd = totalPages;
        if(nextPage > totalPages) nextPage = totalPages;


        model.addAttribute("list", adoptListBackupList);
        model.addAttribute("blockStart", blockStart);
        model.addAttribute("blockEnd",blockEnd);
        model.addAttribute("prevPage",prevPage);
        model.addAttribute("nextPage",nextPage);


        return "/admin/adoptBackupList";
    }

    // 입양확정내역 펫 상세내역
    @GetMapping("/admin/backupAdoptListPet")
    public String backupAdoptList2(Model model, int no){

        Adopt adoptListBackupListPet = adoptService.getAdoptListBackupList2(no);
        model.addAttribute("list", adoptListBackupListPet);

        return "/admin/adoptBackupListPet";
    }


}
