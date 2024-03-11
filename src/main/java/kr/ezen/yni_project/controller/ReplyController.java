package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.Admin;
import kr.ezen.yni_project.domain.Reply;
import kr.ezen.yni_project.domain.User;
import kr.ezen.yni_project.dto.ReplyDTO;
import kr.ezen.yni_project.dto.ResponseDTO;
import kr.ezen.yni_project.service.ReplyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/reply/{bno}")
    @ResponseBody
    public ResponseDTO<?> insertRly(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult,
                                    @PathVariable int bno,
                                    HttpSession session){
        System.out.println("replyDTO = " + replyDTO);
        User user = (User) session.getAttribute("principal");
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        System.out.println("reply = " + reply);
        reply.setUser(user);
        replyService.insertRly(bno, reply);
        return new ResponseDTO<>(HttpStatus.OK.value(),"댓글 등록을 완료하였습니다." );
    }
    @DeleteMapping("/reply/{rno}")
    @ResponseBody
    public ResponseDTO<?> delRly( @PathVariable int rno){
        replyService.delRly(rno);

        return new ResponseDTO<>(HttpStatus.OK.value(),"댓글 삭제를 완료하였습니다." );
    }
    @PostMapping("/reply/update/{rno}")
    @ResponseBody
    public ResponseDTO<?> updateRly(@PathVariable int rno, @Valid @RequestBody ReplyDTO replyDTO, HttpSession session){
//            User user = session.getAttribute("pricipel");
        Reply reply = modelMapper.map(replyDTO,Reply.class);
        replyService.updateRly(rno, reply);
        return new ResponseDTO<>(HttpStatus.OK.value(),"댓글 수정을 완료하였습니다." );
    }
    @PostMapping("/admin/reply/{bno}")
    @ResponseBody
    public ResponseDTO<?> insertAdminRly(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult,
                                         @PathVariable int bno,
                                         HttpSession session, Authentication authentication){
//        Admin admin = (Admin) session.getAttribute("admin");
        Admin admin = (Admin) authentication.getPrincipal();
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        System.out.println("reply = " + reply);
        reply.setAdmin(admin);
        replyService.insertRly(bno, reply);
        return new ResponseDTO<>(HttpStatus.OK.value(),"댓글 등록을 완료하였습니다." );
    }
}
