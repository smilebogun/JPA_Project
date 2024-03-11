package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.Adopt;
import kr.ezen.yni_project.domain.Pet;
import kr.ezen.yni_project.domain.User;
import kr.ezen.yni_project.dto.ResponseDTO;
import kr.ezen.yni_project.dto.UserDTO;
import kr.ezen.yni_project.service.AdoptService;
import kr.ezen.yni_project.service.UserService;
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

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdoptService adoptService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired // root-context에서 생성된 Bean class 를 주입받기 // servlet에서 생성된건 받지 못함
    JavaMailSender mailSender;

    @Autowired	// 암호화를위해서 root 추가한 pwEncoder에서 주입받음
    private PasswordEncoder pwEncoder;

    // 회원가입 폼
    @GetMapping("/memberRegister.do")
    public String registerUser(){

        return "/user/registerUser";
    }
    // ##### 유효성 체크
    // 회원가입 OK
    @PostMapping("/memberInsert.do")  // JSON형태로 넘어온걸 받아야되서 @RequestBody 사용함
    @ResponseBody
    // @Valid : UserDTO에 설정된 사용자 입력값들에 대한 유효성 체크를 하는 어노테이션
    // BindingResult : 유효성 체크 결과를 저장하는 객체
    // 유효성을 userDTO가 받고 결과를 BindingResult로 내고 @Valid 체크한다
    public ResponseDTO<?> insertUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){
        System.out.println("userDTO " + userDTO);

        // ==> ValidationCheckAdvice(AOP)가 적용되면 주석처리
        // .hasErrors는 유효성 체크에 위배되는 데이터(error)가 하나라도 있으면 true값을 리턴
//        if(bindingResult.hasErrors()){
//            Map<String, String> errorMap = new HashMap<>();
//            for(FieldError error : bindingResult.getFieldErrors()){
//                // 어떤 필드에 어떤 문제가 있는지 해당메세지를 맵에 저장
//                errorMap.put(error.getField(), error.getDefaultMessage()); // 맵에 키 밸류형태로 추가
//            }
//            // 에러맵을 리턴
//            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
//        }

        // UserDTO --> User 객체의 Entity로 변환
        User user = modelMapper.map(userDTO, User.class);

        // ID 중복체크
        User finduser = userService.getUser(user.getId());

        if(finduser.getId() == null){
            userService.insertUser(user);
//            return user.getUsername()+"님 회원가입 완료!!";
            return new ResponseDTO<>(HttpStatus.OK.value(), user.getId()+"님 회원가입 완료!!");
            // .OK 는 상태 status가 200 -> 성공 // JSON 형태로 만들어서 콘솔에 띄워줌
        } else {
//            return user.getUsername()+"님은 이미 회원이십니다.";
            return new ResponseDTO<>(HttpStatus.CONFLICT.value(), user.getId()+"님은 이미 회원이십니다.");
            // .BAD_REQUEST 는 status가 400 -> 실패 // JSON 형태로 만들어서 콘솔에 띄워줌
        }
        //return "home";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 회원 등록폼에서 ID 중복체크 ajax
    @RequestMapping("/memberIdCheck.do")
    @ResponseBody
    public String memberIdCheck(@RequestParam("uid") String uid) {
        User user = userService.memberIdCheck(uid);

        //아이디가 중복되거나 || 빈값이 넘어왔을때
        if(user.getId() != null || "".equals(uid.trim())) {
            return "no";
        }
        // 아이디 중복이 아닌경우 (dto.uid가 null 인 경우)
        return "yes";		// 바로 View -> 자바스크립트로 yes 데이터가 넘어감
    }

    // 회원 등록폼에서 전화번호 중복체크 ajax
    @RequestMapping("/memberTelCheck.do")
    @ResponseBody
    public String memberTelCheck(@RequestParam("utel") String utel) {
        User user = userService.memberTelCheck(utel);

        //아이디가 중복되거나 || 빈값이 넘어왔을때
        if(user.getTel() != null || "".equals(utel.trim())) {
            return "no";
        }
        // 아이디 중복이 아닌경우 (dto.uid가 null 인 경우)
        return "yes";		// 바로 View -> 자바스크립트로 yes 데이터가 넘어감
    }

    // 회원 등록폼에서 이메일 인증번호 발송
    @RequestMapping("/memberEmailCheck.do")
    @ResponseBody
    public String memberEmailCheck(@RequestParam("uEmail") String uEmail) {
        System.out.println(uEmail);
        // 인증코드 생성, java.util 안에 UUID를 이용
//		String uuid = UUID.randomUUID().toString(); // c5f3b7e9-4f18-4d90-9664-bc717bfc8834
//		System.out.println(uuid);
        String uuid = UUID.randomUUID().toString().substring(0,6); // 앞에서 6자리 자름
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@uuid = " + uuid);

        // MimeMessage 객체 생성 : 데이터(Mime 타입) 전송 (예 : text/html, image/jpg)
        MimeMessage mail = mailSender.createMimeMessage();
        // 보내지는 메일내용 셋팅
        String mailContents = "<h3>이메일 주소 확인</h3><br/>"
                +"<span>사용자가 본인임을 확인하려고 합니다.<br/>다음 확인 코드를 입력하세요.</span>"
                +"<h2>"+uuid+"</h2>";
        try {
            // 보내지는 메일제목 셋팅
            mail.setSubject("ez-아카데미 [이메일 인증코드]", "utf-8");
            // 보내지는 메일 내용셋팅
            mail.setText(mailContents, "utf-8", "html");
            // 보내지는 메일 수신자셋팅 - 인터넷 주소체계로 바꿔서 uEmail로 보냄
            mail.addRecipient(RecipientType.TO, new InternetAddress(uEmail));
            mailSender.send(mail);
            return uuid;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "fail";		// 바로 View -> 자바스크립트로 fail 데이터가 넘어감
    }

    // 내정보보기 폼으로 가기 - 마이프로필 폼(탭스사용)
    @RequestMapping("/memberInfo.do") // Model model은 바인딩
    public String memberInfo(int no, String find, Model model) {
        System.out.println("############### no = " + no);
        System.out.println("@@@@@@@@@@@@@@@ find = " + find);
        User user = userService.memberInfo(no);
        model.addAttribute("dto", user);
        model.addAttribute("find", find); // find는 회원정보 수정인지 비밀번호 변경인지 구분해주는 것을 바인딩해서 뷰에서 뷰로 넘겨줌
        return "/user/memberInfo";
    }

    // 멤버정보(인포)폼에서 수정OK
    @RequestMapping("/memberUpdate.do")
    public String memberUpdate(User user, RedirectAttributes redirectAttributes) {
        userService.memberUpdate(user);
        redirectAttributes.addFlashAttribute("msg", "멤버정보 수정완료!!");
        return "redirect:/";
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 마이프로필폼 -> 현재 비밀번호 옵션들 확인
    @PostMapping("pwCheck.do")
    @ResponseBody			// 서비스 단에서 session.setAttribute("loginDTO", loginDTO); 에 DTO가 저장되어 있음 getAttribute로 가져옴
    public String pwCheck(String pw, HttpSession session) {     // 사용자가 입력한 비번 pw
        // DB에서 확인할 필요없이 세션에 바인딩된 값을 가져와서 확인함 --> DB까지 갈필요가 없어 시간 절약
        // 세션에서 바인딩 된 값을 가져올때는 (Object)타입이라 (MemberDTO)로 형변환을 해준다.
        User user = (User)session.getAttribute("principal");

        String dbPw = user.getPw();	// dbPw : DB에서 가져온 비번, pw : 사용자가 직접 입력한 비번(ajax)
        String chkResult = "";

        if(pwEncoder.matches(pw, dbPw) || pw.equals(dbPw)) {	// 비번일치 // 기존비번 로그인 || pw.equals(dbPw)
            chkResult = "ok";
        } else {
            chkResult = "no";
        }
        return chkResult;
    }

    // 마이프로필 폼 -> 비밀번호 변경 OK
    @PostMapping("/pwChange.do")
    @ResponseBody // 자바객체를 클라이언트에 전송할 데이터를 http 메세지 바디에 사용하여 보냄 // 네트워크 전송을 할 수 있도록 Json으로 변환(jackson API)
    // @RequestBody는 Http 메세지의 바디에 있는 Json 데이터를 읽어오는데 자바객체로 변환까지 함께 함 (jackson lib)
    public int pwChange(@RequestBody User user) {
        int n = userService.modifyPw(user);
        return n;
    }

    // 멤버리스트에서 삭제OK (회원탈퇴)
    @RequestMapping("/memberDelete.do")
    public String memberDelete(int no, HttpSession session, RedirectAttributes redirectAttributes) {
        userService.memberDelete(no);  // cnt는 값을가지고 추가로 어떠한 작업을 하고 싶을때 추가(유효성검사 등)
        session.invalidate();
        redirectAttributes.addFlashAttribute("msg", "회원탈퇴 완료!!");
        return "redirect:/memberLogin.do";
    }

    /* 추가 */
    // 관리자 홈에서 유저 리스트 폼 이동
//    @GetMapping("/admin/userList")
//    public String userList(Model model){
//        List<User> userList = userService.getUserList();
//        model.addAttribute("userList", userList);
//
//        return "/admin/userList";
//    }
    @GetMapping("/admin/userList")
    public String userList2(Model model, @PageableDefault(size=5, direction = Sort.Direction.DESC) Pageable pageable){
        int currentPage = pageable.getPageNumber() + 1;
        int blockSize = 3;
        int curBlock = (currentPage - 1) / blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize - 1);

        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;

        Page<User> userList = userService.getUserList2(pageable);
        System.out.println("userList" + userList);

        int totalPages = userList.getTotalPages(); // 전체 페이지수
        if (blockEnd > totalPages) blockEnd = totalPages;
        if (nextPage > totalPages) nextPage = totalPages;


        model.addAttribute("userList", userList);
        model.addAttribute("blockStart", blockStart);
        model.addAttribute("blockEnd", blockEnd);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);

        return "/admin/userList";
    }

    // 나의 입양신청내역
    /* 추가 */
    // 관리자 홈에서 유저 리스트 폼 이동
    @GetMapping("/user/u_adoptList")
    public String adoptList(Model model){
        List<Adopt> adoptList = adoptService.getAdoptList();
        model.addAttribute("adoptList", adoptList);
        return "/user/u_adoptList";
    }

    // 나의 입양신청 상세내역
    @GetMapping("/user/petDetail2")
    public String petDetail2(int no, Model model){
        System.out.println("@@@@@@@@@@@@@no = " + no);
        Adopt petListOne = adoptService.getAdoptListBackupList2(no);
        System.out.println("################petListOne = " + petListOne);
        model.addAttribute("list", petListOne);

        return "/user/petDetail2";
    }

    // 나의 입양신청내역 삭제OK
    @RequestMapping("/user/adoptDelete")
    public String adoptDelete(int no, RedirectAttributes redirectAttributes) {
        adoptService.adoptDelete(no);  // cnt는 값을가지고 추가로 어떠한 작업을 하고 싶을때 추가(유효성검사 등)
        redirectAttributes.addFlashAttribute("msg", "입양신청취소 완료!!");
        return "redirect:/user/u_adoptList";
    }

}
