package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.Admin;
import kr.ezen.yni_project.domain.QA;
import kr.ezen.yni_project.domain.QAReply;
import kr.ezen.yni_project.domain.User;
import kr.ezen.yni_project.dto.ResponseDTO;
import kr.ezen.yni_project.service.QAReplyService;
import kr.ezen.yni_project.service.QAService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class QAReplyController {
    @Autowired
    private QAReplyService qaReplyService;
    @Autowired
    private QAService qaService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/QAR/{id}")
    @ResponseBody
    public ResponseDTO<?> insertQARly(@RequestBody String QAReplyContent,
                                      @PathVariable("id") int id,
                                      HttpSession session, ServletRequest request, Authentication authentication){
//        System.out.println("QAReplyContent = " + QAReplyContent);
        System.out.println("QAReplyContent = " + QAReplyContent);
        QA findQA = qaService.findById(id);
        QAReply qaReply = new QAReply();
        System.out.println("id = " + id);
        if(session.getAttribute("principal") != null){
            User user = (User) session.getAttribute("principal");
            System.out.println("user = " + user);
            findQA.setAnswer("답변 대기 중");
            qaService.answerQA(findQA);
            qaReply.setUser(user);
        } else if(authentication.getPrincipal() != null){
//            Admin admin = (Admin) session.getAttribute("admin");
            Admin admin = (Admin) authentication.getPrincipal();
            System.out.println("admin = " + admin);
            qaReply.setAdmin(admin);

            qaReply.setQAReplyContent(QAReplyContent);
            qaReplyService.insertRly(id,qaReply);
            findQA.setAnswer("답변완료");
            qaService.answerQA(findQA);
            return new ResponseDTO<>(HttpStatus.OK.value(),"답변 등록을 완료하였습니다." );
        }
        System.out.println("reply = " + QAReplyContent);

        qaReply.setQAReplyContent(QAReplyContent);
        qaReplyService.insertRly(id, qaReply);
        return new ResponseDTO<>(HttpStatus.OK.value(),"댓글 등록을 완료하였습니다." );
    }
    @DeleteMapping("/QAR/{rid}")
    @ResponseBody
    public ResponseDTO<?> delRly(@PathVariable int rid){
        qaReplyService.delRly(rid);

        return new ResponseDTO<>(HttpStatus.OK.value(),"댓글 삭제를 완료하였습니다." );
    }
    @PutMapping("/QAR/{rid}")
    @ResponseBody
    public ResponseDTO<?> updateRly(@PathVariable int rid, @RequestBody String QAReplyContent, HttpSession session){
//            User user = session.getAttribute("pricipel");
        System.out.println("reply = " + QAReplyContent);
        qaReplyService.updateRly(rid, QAReplyContent);
        return new ResponseDTO<>(HttpStatus.OK.value(),"댓글 수정을 완료하였습니다." );
    }
}
