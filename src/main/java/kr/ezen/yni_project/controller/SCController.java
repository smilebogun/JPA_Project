package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.Post;
import kr.ezen.yni_project.domain.QA;
import kr.ezen.yni_project.domain.SC;
import kr.ezen.yni_project.domain.User;
import kr.ezen.yni_project.dto.ResponseDTO;
import kr.ezen.yni_project.service.QAService;
import kr.ezen.yni_project.service.SCService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SCController {
    @Autowired
    private SCService scService;
    @Autowired
    private QAService qaService;
    @Autowired
    private ModelMapper modelMapper;
    //공시사항 이동
    @GetMapping("/sc")
    public String sc(Model m,
                     @SortDefault.SortDefaults({
                             @SortDefault(sort= "id", direction = Sort.Direction.DESC)})
                     @PageableDefault(size = 5, direction = Sort.Direction.DESC)Pageable pageable){
        int currentPage = pageable.getPageNumber()+1;
        int blockSize = 3;
        int curBlock = (currentPage - 1)/blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize-1);
        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;
        Page<SC> scList = scService.findAll(pageable);
        int totalPage = scList.getTotalPages();
        List<Post> userList = null;
        List<Post> adminList = null;
        if(blockEnd>totalPage){
            blockEnd = totalPage;
        }
        m.addAttribute("prevPage",prevPage);
        m.addAttribute("nextPage",nextPage);
        m.addAttribute("blockStart",blockStart);
        m.addAttribute("blockEnd",blockEnd);
        m.addAttribute("scList",scList);
        m.addAttribute("cat","공지사항");
//        for(Post p : postList){
//            if(p.getUser() != null){
//                userList.add(p);
//            }else if (p.getAdmin() != null){
//                adminList.add(p);
//            }
//            m.addAttribute("userList",userList);
//            m.addAttribute("adminList",adminList);
//        }
        return "/SC/ServiceCenter";
    }
    @GetMapping("/SC/get/{id}")
    public String getAnno(@PathVariable("id") int id,Model m){
        SC sc = scService.getAnno(id);
        m.addAttribute("sc",sc);
        return "/SC/getAnno";
    }
    @GetMapping("/SC/searchList")
    public String searchList(@RequestParam(value = "keyword",required = false) String keyword,
                             @RequestParam(value = "searchCategory",required = false) String searchCategory,
                             @SortDefault.SortDefaults({
                                     @SortDefault(sort= "id", direction = Sort.Direction.DESC)})
                             @PageableDefault(size = 5, direction = Sort.Direction.DESC)Pageable pageable,Model m){

        if(keyword.trim().equals("")){
            keyword = "null";
        }
        int currentPage = pageable.getPageNumber()+1;
        int blockSize = 3;
        int curBlock = (currentPage - 1)/blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize-1);
        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;
        Page<SC> scList = scService.searchList(keyword,searchCategory,pageable);
        System.out.println("postList = " + scList);
        int totalPage = scList.getTotalPages();
        if(blockEnd>totalPage){
            blockEnd = totalPage;
        }


        m.addAttribute("keyword",keyword);
        m.addAttribute("searchCategory",searchCategory);
        m.addAttribute("prevPage",prevPage);
        m.addAttribute("nextPage",nextPage);
        m.addAttribute("blockStart",blockStart);
        m.addAttribute("blockEnd",blockEnd);
        m.addAttribute("scList",scList);

        return "/SC/ServiceCenter";
    }
    @GetMapping("/SC/category/{category}")
    public String categoryList(@PathVariable("category") String category,HttpSession session){

        if(category.equals("공지사항")){
            return "redirect:/sc";
        }
        if(category.equals("F&A")){
            return "redirect:/SC/FaA";
        }
        if(category.equals("Q&A")){
            if(session.getAttribute("principal") == null){
                return "redirect:/memberLogin.do";
            }
            return "redirect:/SC/QaA";
        }
        return "redirect:/sc";
    }
    @GetMapping("/SC/QaA")
    public  String QA(@SortDefault.SortDefaults({
            @SortDefault(sort= "id", direction = Sort.Direction.DESC)})
                      @PageableDefault(size = 5, direction = Sort.Direction.DESC)Pageable pageable,Model m,HttpSession session){
        User user = (User) session.getAttribute("principal");
        System.out.println(user);
        int id = user.getNo();
        int currentPage = pageable.getPageNumber()+1;
        int blockSize = 3;
        int curBlock = (currentPage - 1)/blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize-1);
        Page<QA> qaList = qaService.findByUserNo(pageable,id);
        int totalPage = qaList.getTotalPages();
        if(blockEnd>totalPage){
            blockEnd = totalPage;
        }
        System.out.println("qaList = " + qaList);
        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;

        m.addAttribute("prevPage",prevPage);
        m.addAttribute("nextPage",nextPage);
        m.addAttribute("blockStart",blockStart);
        m.addAttribute("blockEnd",blockEnd);
        m.addAttribute("qaList",qaList);
        m.addAttribute("cat","Q&A");

        return "/SC/QA";
    }
    @PostMapping(value = "/SC/qaRegister",produces = "application/json")
    @ResponseBody
    public ResponseDTO<?> qaRegister(@RequestBody QA qa,HttpSession session){
        User user = (User) session.getAttribute("principal");
        qa.setUser(user);
        qa.setAnswer("답변 대기 중");
        qaService.insertQA(qa);
        return new ResponseDTO<>(HttpStatus.OK.value(), "문의 등록을 완료하였습니다.");
    }
    @GetMapping("/SC/QARegister")
    public String QA(){
        return "/SC/QARegister";
    }
    @GetMapping("/QA/get/{id}")
    public String getQA(@PathVariable("id")int id,Model model){
        QA qa = qaService.findById(id);

        model.addAttribute("qa",qa);
        return "/SC/getQA";
    }
    @GetMapping("/QA/{id}")
    public String QAUpdate(@PathVariable("id") int id,Model model){
        QA findQA = qaService.findById(id);

        model.addAttribute("qa",findQA);
        return "/SC/QAUpdate";
    }
    @PostMapping("/QA/qaUpdate")
    @ResponseBody
    public ResponseDTO<?> qaUpdate(@RequestBody QA qa){
        System.out.println("qa = " + qa);
        qaService.updateQA(qa);

        return new ResponseDTO<>(HttpStatus.OK.value(), "문의사항 수정을 완료하였습니다.");
    }
//    @GetMapping("/QA/deleteQA/{id}")
//    @ResponseBody
//    public String qaDelete(@PathVariable("id") int id, RedirectAttributes redirectAttributes){
//        System.out.println("qa = " + id);
//        qaService.deleteQA(id);
//        redirectAttributes.addFlashAttribute("msg", "문의사항 삭제완료!!");
//        return "redirect:/SC/QaA";
//    }
    @GetMapping("/QA/deleteQA/{id}")
    @ResponseBody
    public ResponseDTO<?> qaDelete(@PathVariable("id") int id, RedirectAttributes redirectAttributes){
        System.out.println("qa = " + id);
        qaService.deleteQA(id);
//        redirectAttributes.addFlashAttribute("msg", "문의사항 삭제완료!!");
        return new ResponseDTO<>(HttpStatus.OK.value(), "문의사항 삭제를 완료하였습니다.");
    }
    @GetMapping("/SC/FaA")
    public String FA(Model m){
        m.addAttribute("cat","F&A");
        return "/SC/FA";
    }





}
