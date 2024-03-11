package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.Admin;
import kr.ezen.yni_project.domain.Post;
import kr.ezen.yni_project.domain.QA;
import kr.ezen.yni_project.domain.SC;
import kr.ezen.yni_project.dto.ResponseDTO;
import kr.ezen.yni_project.dto.SCDTO;
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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminSCController {
    @Autowired
    private SCService scService;
    @Autowired
    private QAService qaService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/admin/sc")
    public String serviceCenter(@SortDefault.SortDefaults({
            @SortDefault(sort= "id", direction = Sort.Direction.DESC)})
                                @PageableDefault(size = 5, direction = Sort.Direction.DESC)Pageable pageable, Model m){
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
        return "/admin/AdminServiceCenter";
    }
    @GetMapping("/admin/scRegister")
    public String scRegister(){
        return "/admin/scRegister";
    }
    @RequestMapping(value = "/admin/registerAnno",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<?> registerAnno(@RequestParam(value = "data",required = false) MultipartFile[] uploadFiles,
                                       @Valid @RequestPart(value = "sc") SCDTO scDto , BindingResult bindingResult,
                                       HttpSession session, Authentication authentication) throws IOException {
        Admin admin = (Admin) authentication.getPrincipal();
        SC sc = modelMapper.map(scDto,SC.class);
        //이미지 이름
        if(uploadFiles!=null){
            String origin = uploadFiles[0].getOriginalFilename();
            //저장 경로
            String savePath = "C:\\STUDY_SpringBoot\\YnI_Project_test\\src\\main\\resources\\static\\fileRepo";
            File saveFile = new File(savePath, origin);
            //파일이 존재할 경우 새로운 이름으로 저장
            if (saveFile.exists()) {
                origin = System.currentTimeMillis() + "_" + origin;
                saveFile = new File(savePath, origin);
            }
//            Post post = modelMapper.map(postDto, Post.class);

            uploadFiles[0].transferTo(saveFile);
            sc.setScImage(origin);
            sc.setAdmin(admin);
            scService.insertAnno(sc);

        }
        else {
            sc.setAdmin(admin);
            scService.insertAnno(sc);
        }
//        Post post = modelMapper.map(postDto, Post.class);
        //일반 저장시 0, 임시저장시 1
        return new ResponseDTO<>(HttpStatus.OK.value(), "공지사항 등록을 완료하였습니다.");
    }
    @GetMapping("/admin/scGet/{id}")
    public String getAnno(@PathVariable("id") int id,Model m){
        SC sc = scService.getAnno(id);
        m.addAttribute("sc",sc);
        return "/admin/getAnno";
    }
    @GetMapping("/admin/sc/category/{category}")
    public String categoryList(@PathVariable("category") String category,HttpSession session, Authentication authentication,Model model){

        if(category.equals("공지사항")){
            return "redirect:/admin/sc";
        }
        if(category.equals("F&A")){
            model.addAttribute("cat","F&A");
            return "/admin/adminFA";
        }
        if(category.equals("Q&A")){
            if(authentication.getPrincipal() == null){
                return "redirect:/admin/adminLogin";
            }
            return "redirect:/admin/sc/QaA";
        }
        return "redirect:/sc";
    }
    @GetMapping("/admin/sc/QaA")
    public  String QA(@SortDefault.SortDefaults({
            @SortDefault(sort= "id", direction = Sort.Direction.DESC)})
                      @PageableDefault(size = 5, direction = Sort.Direction.DESC)Pageable pageable, Model m){
        int currentPage = pageable.getPageNumber()+1;
        int blockSize = 3;
        int curBlock = (currentPage - 1)/blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize-1);
        Page<QA> qaList = qaService.findAll(pageable);
        int totalPage = qaList.getTotalPages();
        if(blockEnd>totalPage){
            blockEnd = totalPage;
        }
        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;

        m.addAttribute("prevPage",prevPage);
        m.addAttribute("nextPage",nextPage);
        m.addAttribute("blockStart",blockStart);
        m.addAttribute("blockEnd",blockEnd);
        m.addAttribute("qaList",qaList);
        m.addAttribute("cat","Q&A");

        return "/admin/adminQA";
    }
    @GetMapping("/admin/QA/get/{id}")
    public String getQA(@PathVariable("id")int id,Model model){
        QA qa = qaService.findById(id);

        model.addAttribute("qa",qa);
        return "/admin/getAdminQA";
    }
    @GetMapping("/admin/deleteQA/{id}")
    public String delQA(@PathVariable("id") int id, RedirectAttributes redirectAttributes){
        qaService.deleteQA(id);
        redirectAttributes.addFlashAttribute("msg", "Q&A 삭제완료!!");
        return "redirect:/admin/sc/QaA";
    }
    @GetMapping("/admin/sc/{id}")
    public String updateSC(@PathVariable("id")int id,Model m){
        SC sc = scService.findById(id);
        m.addAttribute("sc",sc);
        return "/admin/scUpdate";
    }
    @PostMapping("/admin/sc/update")
    @ResponseBody
    public ResponseDTO<?> scUpdate(@RequestParam(value = "data",required = false) MultipartFile[] uploadFiles,
                                   @Valid @RequestPart(value = "SC") SCDTO scDto , BindingResult bindingResult,
                                   HttpSession session) throws IOException {
        System.out.println("scDto = " + scDto);
        SC sc = modelMapper.map(scDto, SC.class);
        SC findSC = scService.findById(sc.getId());

        //이미지 이름
        sc.setScImage(findSC.getScImage());
        //이미지 이름
        if(uploadFiles!=null){
            String origin = uploadFiles[0].getOriginalFilename();
            //저장 경로
            String savePath = "C:\\STUDY_SpringBoot\\YnI_Project_test\\src\\main\\resources\\static\\fileRepo";
            File saveFile = new File(savePath, origin);
            //파일이 존재할 경우 새로운 이름으로 저장
            if (saveFile.exists()) {
                origin = System.currentTimeMillis() + "_" + origin;
                saveFile = new File(savePath, origin);
            }
            uploadFiles[0].transferTo(saveFile);
            sc.setScImage(origin);

        }
        //일반 저장시 0, 임시저장시 1
        scService.updateSC(sc);
        return new ResponseDTO<>(HttpStatus.OK.value(), "게시글 수정 완료하였습니다.");
    }
    @PostMapping("/admin/sc/del/{id}")
    @ResponseBody
    public ResponseDTO<?> delSC(@PathVariable("id") int id){
        System.out.println("id = " + id);
        scService.delSC(id);
        return new ResponseDTO<>(HttpStatus.OK.value(), "공지사항 삭제를 완료하였습니다.");
    }
}
