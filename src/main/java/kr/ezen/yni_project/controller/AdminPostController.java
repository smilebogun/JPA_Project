package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.Admin;
import kr.ezen.yni_project.domain.Post;
import kr.ezen.yni_project.dto.PostDTO;
import kr.ezen.yni_project.dto.ResponseDTO;
import kr.ezen.yni_project.service.PostService;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminPostController {
    @Autowired
    private PostService postService;
    @Autowired
    private ModelMapper modelMapper;
    //게시판 이동
    @GetMapping("/admin/post")
    public String post(Model m,
                       @SortDefault.SortDefaults({
                               @SortDefault(sort= "id", direction = Sort.Direction.DESC)})
                       @PageableDefault(size = 6, direction = Sort.Direction.DESC)Pageable pageable){
        int currentPage = pageable.getPageNumber()+1;
        int blockSize = 3;
        int curBlock = (currentPage - 1)/blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize-1);
        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;

        List<Post> userList = new ArrayList<>();
        List<Post> adminList = new ArrayList<>();

        Page<Post> postList = postService.findAll(pageable); //유저 포스트 가져오기
        List<Post> postList1 = postService.finAll(); //공지 가져오기
        for(Post p : postList){
            if(p.getUser() != null) {
                userList.add(p);
            }
        }
        for(Post p1: postList1){
            if(p1.getAdmin() != null){
                adminList.add(p1);
            }
        }
        int totalPage = postList.getTotalPages();
        if(blockEnd>totalPage){
            blockEnd = totalPage;
        }
        System.out.println("postList = " + postList);
        m.addAttribute("prevPage",prevPage);
        m.addAttribute("nextPage",nextPage);
        m.addAttribute("blockStart",blockStart);
        m.addAttribute("blockEnd",blockEnd);
        m.addAttribute("postList",postList);
        m.addAttribute("userList",userList);
        m.addAttribute("adminList",adminList);
        return "/admin/adminPost";
    }
    //게시글 입력
    @GetMapping("/admin/post/register")
    public String register(){
        return "/admin/adminPostRegister";
    }

    //게시글 저장
    @RequestMapping(value = "/admin/post/registerPost",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<?> registerPost(@RequestParam(value = "data",required = false) MultipartFile[] uploadFiles,
                                       @Valid @RequestPart("post") PostDTO postDto , BindingResult bindingResult,
                                       HttpSession session, Authentication authentication) throws IOException {
    System.out.println("postDto = " + postDto);
//    Admin admin = (Admin) session.getAttribute("admin");
        Admin admin = (Admin) authentication.getPrincipal();
    Post post = modelMapper.map(postDto, Post.class);

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
    post.setPostImage1(origin);

    }

    //일반 저장시 0, 임시저장시 1
    System.out.println("post = " + post);
    post.setPostTemp(0);
    post.setAdmin(admin);
    post.setPostWriter(admin.getAdminName());
    postService.insertTemp(post);
    return new ResponseDTO<>(HttpStatus.OK.value(), "게시글 저장 완료하였습니다.");
}
// 게시글 상세보기
    @GetMapping("/admin/post/get/{bno}")
    public String getPost(@PathVariable int bno, Model m){
        System.out.println("bno = " + bno);
        Post post = postService.findById(bno);
        post.setPostViewCnt(post.getPostViewCnt()+1);
        postService.updateCnt(post);
        System.out.println("post.getPostViewCnt() = " + post.getPostViewCnt());
        m.addAttribute("post",post);
        return "/admin/getAdminPost";
    }
    // 게시글 업데이트 폼이동
    @GetMapping("/admin/post/{bno}")
    public String update(@PathVariable int bno, Model m){
//        System.out.println("bno = " + bno);
        Post post = postService.findById(bno);
        m.addAttribute("post",post);

        return "/admin/postUpdate";
    }
    // 게시글 업데이트
    @RequestMapping(value = "/admin/post/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<?> update(@RequestParam(value = "data",required = false) MultipartFile[] uploadFiles,
                                       @RequestPart("post") PostDTO postDto,
                                       HttpSession session) throws IOException {
        Post post = modelMapper.map(postDto, Post.class);
        System.out.println("post = " + post);
        Post findPost = postService.findById(post.getId());
        //이미지 이름
        post.setPostImage1(findPost.getPostImage1());
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
            post.setPostImage1(origin);

        }
        //일반 저장시 0, 임시저장시 1
        post.setPostTemp(0);
        postService.updatePost(post);
        return new ResponseDTO<>(HttpStatus.OK.value(), "게시글 수정 완료하였습니다.");
    }

    // 게시글 검색
    @GetMapping("/admin/post/searchList")
    public String searchList(@RequestParam(value = "category",required = false) String postCategory,
                             @RequestParam(value = "keyword",required = false) String keyword,
                             @RequestParam(value = "searchCategory",required = false) String searchCategory,
                             @SortDefault.SortDefaults({
                                     @SortDefault(sort= "id", direction = Sort.Direction.DESC)})
                             @PageableDefault(size = 6, direction = Sort.Direction.DESC)Pageable pageable,Model m){

        if(keyword.trim().equals("")){
            keyword = "null";
        }
        if(postCategory.trim().equals("")){
            postCategory = "null";
        }
        System.out.println("postCategory = " + postCategory);

        int currentPage = pageable.getPageNumber()+1;
        int blockSize = 3;
        int curBlock = (currentPage - 1)/blockSize;
        int blockStart = (blockSize * curBlock) + 1;
        int blockEnd = blockStart + (blockSize-1);
        int prevPage = blockStart - 1;
        int nextPage = blockEnd + 1;
        Page<Post> postList = postService.searchList(postCategory,keyword,searchCategory,pageable);
        System.out.println("postList = " + postList);
        int totalPage = postList.getTotalPages();
        if(blockEnd>totalPage){
            blockEnd = totalPage;
        }
        List<Post> userList = new ArrayList<>();
        List<Post> adminList = new ArrayList<>();
        List<Post> postList1 = postService.searchList1(postCategory,keyword,searchCategory); //공지 가져오기

        for(Post p : postList){
            if(p.getUser() != null) {
                userList.add(p);
            }
        }
        for(Post p1: postList1){
            if(p1.getAdmin() != null){
                adminList.add(p1);
            }
        }

        m.addAttribute("prevPage",prevPage);
        m.addAttribute("nextPage",nextPage);
        m.addAttribute("blockStart",blockStart);
        m.addAttribute("blockEnd",blockEnd);
        m.addAttribute("postList",postList);
        m.addAttribute("cat",postCategory);
        m.addAttribute("keyword",keyword);
        m.addAttribute("searchCategory",searchCategory);
        m.addAttribute("userList",userList);
        m.addAttribute("adminList",adminList);

        return "/admin/adminPost";
    }

    @RequestMapping(value = "/admin/post/delPost")
    @ResponseBody
    public ResponseDTO<?> delPost(@RequestBody Post post, BindingResult bindingResult){
        int bno = post.getId();
        System.out.println("post = " + post);
        System.out.println("bno = " + bno);
        postService.delPost(bno);
        return new ResponseDTO<>(HttpStatus.OK.value(),"게시글 삭제 완료하였습니다." );
    }

}
