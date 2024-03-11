package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.Post;
import kr.ezen.yni_project.domain.User;
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
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private ModelMapper modelMapper;
    //게시판 이동
    @GetMapping("/post")
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
        Page<Post> postList = postService.findAll(pageable);
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

        return "/post/post";
    }
    //게시글 입력
    @GetMapping("/post/register")
    public String register(Model m,HttpSession session){
        User principal = (User)session.getAttribute("principal");
        List<Post> postList = postService.findPost();
        int temp = 1;
        int uno = principal.getNo();
        //임시 저장된 값 불러오기
//        List<Post> tempList = postService.findPostByTemp(temp);
        List<Post> tempList = postService.findPostByTemp(temp,uno);
        System.out.println("tempList = " + tempList);
        String name = principal.getName();
        m.addAttribute("name",name);
        m.addAttribute("tempList",tempList);
        return "/post/postRegister";
    }

    //게시글 저장
    @RequestMapping(value = "/post/registerPost",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<?> registerPost(@RequestParam(value = "data",required = false) MultipartFile[] uploadFiles,
                                       @Valid @RequestPart("post") PostDTO postDto ,BindingResult bindingResult,
                                       HttpSession session) throws IOException {
        User principal = (User) session.getAttribute("principal");

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
        post.setPostTemp(0);
        post.setUser(principal);
        post.setPostWriter(principal.getName());
        postService.insertTemp(post);
        return new ResponseDTO<>(HttpStatus.OK.value(), "게시글 저장 완료하였습니다.");
    }
    // 임시저장
    @RequestMapping(value = "/post/temp",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<?> tempPost(@Valid @RequestPart("post") PostDTO postDto, BindingResult bindingResult,
                                   @RequestParam(value = "data",required = false) MultipartFile[] uploadFiles,
                                   HttpSession session) throws IOException {
        User principal = (User) session.getAttribute("principal");
        Post post = modelMapper.map(postDto, Post.class);
        if(uploadFiles == null){
            post.setUser(principal);
            post.setPostWriter(principal.getName());
            post.setPostImage1("0");
            post.setPostTemp(1);
            postService.insertTemp(post);
            return new ResponseDTO<>(HttpStatus.OK.value(), "게시글 임시저장 완료하였습니다.");
        }

        //파일의 이름
        String origin = uploadFiles[0].getOriginalFilename();
        //저장경로
        String savePath = "C:\\STUDY_SpringBoot\\YnI_Project_test\\src\\main\\resources\\static\\fileRepo";
        File saveFile = new File(savePath, origin);
        //파일이 이미 존재하는 경우
        if (saveFile.exists()) {
            origin = System.currentTimeMillis() + "_" + origin;
            saveFile = new File(savePath, origin);
        }
        uploadFiles[0].transferTo(saveFile);
        post.setPostImage1(origin);
        post.setPostTemp(1);
        post.setUser(principal);
        post.setPostWriter(principal.getName());
        postService.insertTemp(post);
        return new ResponseDTO<>(HttpStatus.OK.value(), "게시글 임시저장 완료하였습니다.");
    }
    // 게시글 상세보기
    @GetMapping("/post/get/{bno}")
    public String getPost(@PathVariable int bno, Model m){
        System.out.println("bno = " + bno);
        Post post = postService.findById(bno);
        post.setPostViewCnt(post.getPostViewCnt()+1);
        postService.updateCnt(post);
        System.out.println("post.getPostViewCnt() = " + post.getPostViewCnt());
        m.addAttribute("post",post);



        return "/post/getPost";
    }
    // 게시글 업데이트 폼이동
    @GetMapping("/post/{bno}")
    public String update(@PathVariable int bno, Model m){
//        System.out.println("bno = " + bno);
        Post post = postService.findById(bno);
        m.addAttribute("post",post);

        return "/post/postUpdate";
    }
    // 게시글 업데이트
    @RequestMapping(value = "/post/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<?> update(@RequestParam(value = "data",required = false) MultipartFile[] uploadFiles,
                                 @Valid @RequestPart("post") PostDTO postDto, BindingResult bindingResult,
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
    //게시글 삭제
    @RequestMapping(value = "/post/delPost")
    @ResponseBody
    public ResponseDTO<?> delPost(@RequestBody Post post, BindingResult bindingResult){
        int bno = post.getId();
        System.out.println("post = " + post);
        System.out.println("bno = " + bno);
        postService.delPost(bno);
        return new ResponseDTO<>(HttpStatus.OK.value(),"게시글 삭제 완료하였습니다." );
    }

    // 카테고리 별 이동(지금 사용 안함)
//    @GetMapping("/post/category/{category}")
//    public String categoryPost(@PathVariable("category") String category,Model m,
//                               @PageableDefault(size = 5, direction = Sort.Direction.DESC)Pageable pageable){
//        int currentPage = pageable.getPageNumber()+1;
//        int blockSize = 3;
//        int curBlock = (currentPage - 1)/blockSize;
//        int blockStart = (blockSize * curBlock) + 1;
//        int blockEnd = blockStart + (blockSize-1);
//        int prevPage = blockStart - 1;
//        int nextPage = blockEnd + 1;
//        Page<Post> postList = postService.findByCategory(category,pageable);
//        int totalPage = postList.getTotalPages();
//        List<Post> userList = null;
//        List<Post> adminList = null;
//        if(blockEnd>totalPage){
//            blockEnd = totalPage;
//        }
//        System.out.println("postList = " + postList);
//        m.addAttribute("prevPage",prevPage);
//        m.addAttribute("nextPage",nextPage);
//        m.addAttribute("blockStart",blockStart);
//        m.addAttribute("blockEnd",blockEnd);
//        m.addAttribute("postList",postList);
//        m.addAttribute("cat",category);
//
////            m.addAttribute("category",category);
//        return "/post/post";
//    }

    // 임시저장 페이지(값) 불러오기
    @GetMapping("/post/temp/{bno}")
    public String temp(@PathVariable int bno, Model m){
//        System.out.println("bno = " + bno);
        Post post = postService.findById(bno);
        m.addAttribute("post",post);

        return "/post/postUpdate";
    }
    // 게시글 검색
    @GetMapping("/post/searchList")
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
        if(postCategory.trim().equals("전체")){
            postCategory =null;
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

        List<Post> postList1 = postService.searchList1(postCategory,keyword,searchCategory); //공지 가져오기
        List<Post> userList = new ArrayList<>();
        List<Post> adminList = new ArrayList<>();

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

        if(blockEnd>totalPage){
            blockEnd = totalPage;
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

        return "/post/post";
    }

}
