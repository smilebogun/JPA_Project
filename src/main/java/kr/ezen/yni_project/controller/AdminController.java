package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.Admin;
import kr.ezen.yni_project.dto.AdminDTO;
import kr.ezen.yni_project.dto.ResponseDTO;
import kr.ezen.yni_project.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ModelMapper modelMapper;

    // 관리자 리스트
    @GetMapping("/admin/adminList")
    public String adminList(Model model, @PageableDefault(size=5, direction = Sort.Direction.DESC) Pageable pageable){
        int currentPage = pageable.getPageNumber() + 1;
        int blockSize = 3;
        int curBlock = (currentPage - 1) / blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize - 1);

        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;

        Page<Admin> adminList = adminService.getAdminList(pageable);
        System.out.println("adminList" + adminList);

        int totalPages = adminList.getTotalPages(); // 전체 페이지수
        if (blockEnd > totalPages) blockEnd = totalPages;
        if (nextPage > totalPages) nextPage = totalPages;


        model.addAttribute("adminList", adminList);
        model.addAttribute("blockStart", blockStart);
        model.addAttribute("blockEnd", blockEnd);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);

        return "/admin/adminList";
    }

    // 관리자 상세 보기
    @GetMapping("/admin/{no}")
    public String adminDetail(@PathVariable int no, Model model) {
        Admin admin = adminService.adminDetail(no);
        model.addAttribute("admin", admin);
//        adminService.detailAdmin(no);
//        return "/admin/adminDetail";
        return "/admin/adminUpdate";
    }

    // 관리자 삭제
    @DeleteMapping("/admin/{no}")
    public @ResponseBody ResponseDTO<?> adminDelete(@PathVariable int no) {
        adminService.adminDelete(no);
        return new ResponseDTO<>(HttpStatus.OK.value(), no + " 관리자님이 삭제 되었습니다!!");
    }

//    // 관리자 수정 폼 이동
//    @GetMapping("/admin/adminUpdate/{no}")
//    public String adminUpdate(@PathVariable int no, Model model) {
//        model.addAttribute("admin", adminService.adminDetail(no));
//        return "/admin/adminUpdate";
//    }

    // 관리자 수정
    @PutMapping("/admin")
    public @ResponseBody ResponseDTO<?> adminUpdate(@RequestBody Admin admin) {
        adminService.adminUpdate(admin);
        return new ResponseDTO<>(HttpStatus.OK.value(), admin.getNo() + "번 관리자 수정완료!!");
    }

//    @RequestMapping("/memberList.do")
//    public String memberList(Model model) {
//        ArrayList<MemberDTO> memberList = service.memberList();
//        model.addAttribute("list", memberList);
//
//        return "member/memberList";
//    }

    // 회원 가입폼 이동
    @GetMapping("/admin/adminRegister")
    public String adminRegister() {
//        System.out.println(10/0);
        return "/admin/adminRegister";
    }
// 회원가입 OK
    @PostMapping("/admin/adminRegister")
//    @Valid : UserDTO에 설정 된 사용자 입력값들에 대한 유효성 체크를 하는 어노테이션
//    BindingResult는 유효성 체크 결과를 저장하는 객체
//    public @ResponseBody String insertUser(@RequestBody  User user){
    public @ResponseBody ResponseDTO<?> adminInsert(@Valid @RequestBody AdminDTO adminDTO, BindingResult bindingResult) {

        System.out.println("AdminDTO@@@@@@ ="+adminDTO);
        // bindingResult.hasErrors() :유효성 체크에 위배되는 데이터(error)가 하나라도 있으면 true 값을 return

        // ----------------------------------------
//        if (bindingResult.hasErrors()) { // hasErrors 참이 됨
//            Map<String, String> errorMap = new HashMap<>();
//
//            for(FieldError error : bindingResult.getFieldErrors()){
//                // 어떤 필드에 어떤 문제가 있는지 해당메시지를 맵에 저장
//                errorMap.put(error.getField(), error.getDefaultMessage());
//            }
//            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
//        }

        // UserDTO --> User객체로 변환
        Admin admin = modelMapper.map(adminDTO, Admin.class);

//        System.out.println("admin테스트@@@@@@"+admin);
        //  아이디 중복체크
        Admin findAdmin = adminService.getAdmins(admin.getAdminId());
        System.out.println("findAdmin@@@@@@@@@@@@@@@@@@@"+findAdmin);

        if (findAdmin.getAdminId() == null) {
            admin.setAdminPw(passwordEncoder.encode(admin.getAdminPw()));
            adminService.adminInsert(admin);
//            return user.getUsername() + "님 회원가입 완료!!";
            return new ResponseDTO<>(HttpStatus.OK.value(), admin.getAdminId() + "님 회원가입 완료!!");
        }else{
//            return user.getUsername() + "님은 이미 회원이십니다!!";
            return new ResponseDTO<>(HttpStatus.CONFLICT.value(), admin.getAdminId() + "님은 이미 회원이십니다!!");
        }
    }


}
