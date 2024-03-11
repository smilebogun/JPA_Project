package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.User;
import kr.ezen.yni_project.dto.ResponseDTO;
import kr.ezen.yni_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired	// 암호화를위해서 root 추가한 pwEncoder에서 주입받음
    private PasswordEncoder pwEncoder;

    // 로그인 폼 이동
    @GetMapping("/memberLogin.do")
    public String login(){
        return "/system/login";
    }

    // 로그인 OK
    @PostMapping("/memberLogin.do")
    @ResponseBody
    public ResponseDTO<?> login(@RequestBody User user, HttpSession session){

        User findUser = userService.getUser(user.getId());

        // findUser 유무확인
        if(findUser.getId() == null){         // 아이디 존재 유무확인
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "아이디 또는 비밀번호 불일치");
        } else {
            String inputPw = user.getPw();      // 입력한 비밀번호
            String dbPw = findUser.getPw();     // DB에서 가져온 비밀번호
            // 비번검증
            if(pwEncoder.matches(inputPw, dbPw) || inputPw.equals(dbPw)){  // 비번일치
                session.setAttribute("principal", findUser);
                return new ResponseDTO<>(HttpStatus.OK.value(), findUser.getId() + "님 로그인 성공");
            } else {    // 비번불일치
                return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "아이디 또는 비밀번호 불일치");
            }
        }
    }

    // 멤버 아이디 & 비번 찾기 폼으로 이동 - Get방식
    @GetMapping("/idPwFind.do")
    public String idPwFind(String find, Model model) {
        model.addAttribute("find", find); // find는 아이디찾기인지 비밀번호찾기인지 구분해주는 것을 바인딩해서 뷰에서 뷰로 넘겨줌
        return "/system/idPwFind";
    }

    // 아이디 찾기 OK
    @PostMapping("/findId.do")
    @ResponseBody
    public User findId(User user) {
        System.out.println("#################### = " + user);
        System.out.println("@@@@@@@@@@@@@@@@@@@  = " + user.getTel());
        return userService.memberTelCheck(user.getTel());
    }

    // 비밀번호 찾기 OK // 임시비밀번호 발급 이후 임시비밀번호로 로그인
    @PostMapping("/findPw.do")
    @ResponseBody
    public User findPw(User user) {
        System.out.println("#################### = " + user);
        System.out.println("@@@@@@@@@@@@@@@@@@@  = " + user.getId());
        System.out.println("@@@@@@@@@@@@@@@@@@@  = " + user.getEmail());
        return userService.findPw(user.getId(), user.getEmail());
    }

    // 로그아웃
    @GetMapping("/memberLogout.do")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
