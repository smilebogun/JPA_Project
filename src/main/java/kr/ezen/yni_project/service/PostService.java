package kr.ezen.yni_project.service;

import kr.ezen.yni_project.Specification.PostSpecification;
import kr.ezen.yni_project.domain.Post;
import kr.ezen.yni_project.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepo;


    public Page<Post> findAll(Pageable pageable) {
        Page<Post> postList = postRepo.findAll(pageable);
        return postList;
    }

    public void insertPost(Post post) {
        postRepo.save(post);
    }

    public Post findById(int bno) {
        Post post =postRepo.findById(bno).get();
        return post;
    }
    @Transactional
    public void updatePost(Post post) {
        System.out.println("post = " + post);
        Post findPost = postRepo.findById(post.getId()).get();
        findPost.setPostContent(post.getPostContent());
        findPost.setPostCategory(post.getPostCategory());
        findPost.setPostTitle(post.getPostTitle());
        findPost.setPostImage1(post.getPostImage1());
        findPost.setPostTemp(0);
//        postRepo.save(findPost);
    }
    @Transactional
    public void updateCnt(Post post){
        Post findPost = postRepo.findById(post.getId()).get();
        findPost.setPostViewCnt(post.getPostViewCnt());
    }

    public void delPost(long bno) {
        postRepo.deleteById((int) bno);
    }

    public List<Post> findPost() {
        List<Post> postList = postRepo.findAll();
        return postList;
    }

    public void insertTemp(Post post) { postRepo.save(post);
    }


    public Page<Post> findByCategory(String category, Pageable pageable) {
        Page<Post> postList =  postRepo.findByPostCategory(category,pageable);
        return postList;
    }

    //    public List<Post> findPostByTemp(int temp,int uno) {
    public List<Post> findPostByTemp(int temp, int uno) {
//        List<Post> = postRepo.findByTempAndUser();
        List<Post> tempList = postRepo.findByPostTempAndUserNo(temp, uno);
        return tempList;
    }

    public Page<Post> searchList(String postCategory, String keyword, String searchCategory,Pageable pageable) {
        //카테고리값이 없을때
        if(postCategory.equals("null")){
            //검색 카테고리가 all 일때
            switch (searchCategory) {
                case "all":
                    if (!keyword.equals("null")) {
                        Specification<Post> spec = Specification.where(PostSpecification.likeTitle(keyword)).or(PostSpecification.likeWriter(keyword));
                        return postRepo.findAll(spec, pageable);
                    }

                    break;
                case "title":
                    if (!keyword.equals("null")) {
                        Specification<Post> spec = Specification.where(PostSpecification.likeTitle(keyword));
                        return postRepo.findAll(spec, pageable);
                    }
                    break;
                case "writer":
                    if (!keyword.equals("null")) {
                        Specification<Post> spec = Specification.where(PostSpecification.likeWriter(keyword));
                        return postRepo.findAll(spec, pageable);
                    }
                    break;
            }
            return postRepo.findAll(pageable);
        } else{
            Specification<Post> spec = Specification.where(PostSpecification.equalPostCategory(postCategory));
            switch (searchCategory) {
                case "all":
                    if (!keyword.equals("null")) {
                        spec = spec.and((PostSpecification.likeTitle(keyword)).or(PostSpecification.likeWriter(keyword)));
                        return postRepo.findAll(spec, pageable);
                    }
                    break;
                case "title":
                    if (!keyword.equals("null")) {
                        spec = spec.and(PostSpecification.likeTitle(keyword));
                        return postRepo.findAll(spec, pageable);
                    }
                    break;
                case "writer":
                    if (!keyword.equals("null")) {
                        spec = spec.and(PostSpecification.likeWriter(keyword));
                        return postRepo.findAll(spec, pageable);
                    }
                    break;
            }
            return postRepo.findAll(spec,pageable);
        }
//        Specification<Post> spec = Specification.where(PostSpecification.equalPostCategory(postCategory))
//
//                .or(PostSpecification.likeWriter(keyword));
//        return postRepo.findAll(spec,pageable);

    }
    public List<Post> finAll() {
        return postRepo.findAll();
    }

    public List<Post> searchList1(String postCategory, String keyword, String searchCategory) {
        if(postCategory.equals("null")){
            //검색 카테고리가 all 일때
            switch (searchCategory) {
                case "all":
                    if (!keyword.equals("null")) {
                        Specification<Post> spec = Specification.where(PostSpecification.likeTitle(keyword)).or(PostSpecification.likeWriter(keyword));
                        return postRepo.findAll(spec);
                    }

                    break;
                case "title":
                    if (!keyword.equals("null")) {
                        Specification<Post> spec = Specification.where(PostSpecification.likeTitle(keyword));
                        return postRepo.findAll(spec);
                    }
                    break;
                case "writer":
                    if (!keyword.equals("null")) {
                        Specification<Post> spec = Specification.where(PostSpecification.likeWriter(keyword));
                        return postRepo.findAll(spec);
                    }
                    break;
            }
            return postRepo.findAll();
        } else{
            Specification<Post> spec = Specification.where(PostSpecification.equalPostCategory(postCategory));
            switch (searchCategory) {
                case "all":
                    if (!keyword.equals("null")) {
                        spec = spec.and((PostSpecification.likeTitle(keyword)).or(PostSpecification.likeWriter(keyword)));
                        return postRepo.findAll(spec);
                    }
                    break;
                case "title":
                    if (!keyword.equals("null")) {
                        spec = spec.and(PostSpecification.likeTitle(keyword));
                        return postRepo.findAll(spec);
                    }
                    break;
                case "writer":
                    if (!keyword.equals("null")) {
                        spec = spec.and(PostSpecification.likeWriter(keyword));
                        return postRepo.findAll(spec);
                    }
                    break;
            }
            return postRepo.findAll(spec);
        }
    }

//    public Page<Post> findAll(Pageable pageable) {
//        return postRepo.findAll(pageable);
//    }

}
