package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.Admin;
import kr.ezen.yni_project.dto.ResponseDTO;
import kr.ezen.yni_project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AdminLoginController {

    @Autowired
    private AdminService adminService;

    // 로그인 폼이동
    @GetMapping("/admin/adminLogin")
    public String login(@RequestParam(value="error", required = false) String error,
                        @RequestParam (value="exception", required = false) String exception, Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "/system/adminLogin";
    }

    // 로그인 인증 처리
//    @PostMapping("/admin/adminLogin")
//    public @ResponseBody ResponseDTO<?> login(@RequestBody Admin admin, HttpSession session) {
//        Admin findUser = adminService.getAdmin(admin.getAdminId());
//
//        // findUser 유무 확인
//        if (findUser.getAdminId() == null) {
//            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "아이디가 존재하지 않습니다!!");
//        } else {
//            // 비번 검증
//            if (admin.getAdminPw().equals(findUser.getAdminPw())) {
//                session.setAttribute("principal", findUser);
//                return new ResponseDTO<>(HttpStatus.OK.value(), findUser.getAdminId() + "님 로그인 되었습니다!!");
//            } else { // 비번 불일치
//                return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "비밀번호 오류");
//            }
//        }
//    }

//    // 로그아웃
//    @GetMapping("/admin/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/adminHome";
//    }
        // 로그아웃
    @GetMapping("/admin/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 마지막 1개 authentication 를 가져오기

        if (authentication != null){
            // get 방식의 로그아웃 처리 SecurityContextLogoutHandler 핸들러 사용
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        redirectAttributes.addFlashAttribute("msg", "로그아웃 완료!!");
        return "redirect:/admin/adminLogin";
    }

    // 인가예외
    @GetMapping("/denied")
    public String accessDenied(@RequestParam (value="exception", required = false)
                               String exception, Model model){

        // authentication = 인증한 정보를 모두 가지고 있음
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Admin admin = (Admin)authentication.getPrincipal(); // 컨트롤 알트 V 해놓고 오브젝트에서 멤버로 변경

        model.addAttribute("adminId", admin.getAdminId());
        model.addAttribute("exception", exception);
        return "/system/denied";

    }
}
